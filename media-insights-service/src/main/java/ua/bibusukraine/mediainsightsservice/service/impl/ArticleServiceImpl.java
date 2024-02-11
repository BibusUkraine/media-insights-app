package ua.bibusukraine.mediainsightsservice.service.impl;

import co.elastic.clients.json.JsonData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.model.RssFeed;
import ua.bibusukraine.mediainsightsservice.repository.ArticleRepository;
import ua.bibusukraine.mediainsightsservice.service.ArticleService;
import ua.bibusukraine.mediainsightsservice.service.NytApiService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService, ApplicationListener<ApplicationReadyEvent> {

    private final Integer ARTICLE_PAGE_SIZE = 20;

    private final ArticleRepository articleRepository;
    private final ElasticsearchOperations operations;
    private final RssFeedServiceImpl rssFeedService;
    private final NytApiService nytApiService;

    @Value("${initialize-articles-on-startup}")
    private boolean initializeArticlesOnStartup;

    @Override
    public List<Article> findAllByQueryBetweenDates(String query, LocalDate startDate, LocalDate endDate, Integer pageNumber) {
        if (query == null) {
            return articleRepository.findAll(PageRequest.of(pageNumber, ARTICLE_PAGE_SIZE)).getContent();
        }
        Query nativeQuery = NativeQuery.builder()
                .withQuery(q -> q.multiMatch(m -> m
                        .fields(List.of("title", "description"))
                        .query(query)
                        .fuzziness("2")
                ))
                .withFilter(f -> f.range(r -> r
                        .field("publish_date")
                        .gte(startDate != null ? JsonData.of(startDate) : null)
                        .lte(endDate != null ? JsonData.of(endDate) : null)
                ))
                .withPageable(PageRequest.of(pageNumber, ARTICLE_PAGE_SIZE))
                .build();

        return operations.search(nativeQuery, Article.class).stream()
                .map(SearchHit::getContent)
                .toList();
    }

    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (initializeArticlesOnStartup) {
            saveAllArticlesFromRss();
            saveAllArticlesFromNyt();
        }
    }

    private List<Article> reedRssFeed(RssFeed rssFeed) {
        try {
            return rssFeedService.extractArticlesFromRssUrl(rssFeed.getLink());
        } catch (Exception e) {
            log.error("Error extracting articles from RSS link: {}", rssFeed.getLink(), e);
            return Collections.emptyList();
        }
    }

    private void saveAllArticlesFromRss() {
        List<RssFeed> rssFeeds = rssFeedService.findAllRssFeeds();
        rssFeeds.parallelStream()
                .forEach(rssFeed -> {
                    List<Article> articles = reedRssFeed(rssFeed);
                    articles.forEach(article -> {
                        article.setLogoLink(rssFeed.getLogoLink());
                        if (article.getImageLink() == null) {
                            article.setImageLink(rssFeed.getLogoLink());
                        }
                    });
                    articleRepository.saveAll(articles);
                });
    }

    private void saveAllArticlesFromNyt() {
        YearMonth now = YearMonth.now();
        for (int year = now.getYear(); year >= 2015; year--) {
            for (int month = (year == now.getYear() ? now.getMonthValue() : 12); month >= 1; month--) {
                try {
                    List<Article> articlesForMonth = nytApiService.getArticlesByYearAndMonth(year, month);
                    articleRepository.saveAll(articlesForMonth);
                    log.info("Saved {} articles for year {} and month {}", articlesForMonth.size(), year, month);
                } catch (Exception e) {
                    log.error("Error while saving articles for year {} and month {}", year, month, e);
                }

            }
        }
    }


}
