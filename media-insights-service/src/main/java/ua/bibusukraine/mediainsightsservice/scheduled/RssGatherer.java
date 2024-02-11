package ua.bibusukraine.mediainsightsservice.scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.bibusukraine.mediainsightsservice.model.RssFeed;
import ua.bibusukraine.mediainsightsservice.service.ArticleService;
import ua.bibusukraine.mediainsightsservice.service.RssFeedService;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class RssGatherer {
  private static final Integer BATCH_SIZE = 5;
  private final RssFeedService rssSourceService;
  private final ArticleService articleService;

  @Scheduled(fixedDelayString = "${rss-feed.gathering.interval}")
  public void saveNewRssFeedInfoIntoElasticSearch() {
    Page<RssFeed> page = rssSourceService.findAllPaginated(0, BATCH_SIZE);
    while (page.hasNext()) {
      if (page.hasContent()) {
        page
            .getContent()
            .parallelStream()
            .flatMap(rssFeed -> {
              try {
                return rssSourceService.extractArticlesFromRssUrl(rssFeed.getLink()).stream();
              } catch (Exception e) {
                return Stream.empty();
              }
            }).forEach(articleService::saveArticle);
      }
      page = rssSourceService.findAllPaginated(page.nextPageable().getPageNumber(), BATCH_SIZE);
    }
  }
}
