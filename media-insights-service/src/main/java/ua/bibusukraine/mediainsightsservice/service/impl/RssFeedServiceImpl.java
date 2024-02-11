package ua.bibusukraine.mediainsightsservice.service.impl;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.feed.synd.SyndFeedImpl;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.bibusukraine.mediainsightsservice.mapper.ArticleMapper;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.model.RssFeed;
import ua.bibusukraine.mediainsightsservice.repository.RssFeedRepository;
import ua.bibusukraine.mediainsightsservice.service.RssFeedService;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RssFeedServiceImpl implements RssFeedService {

    private final ArticleMapper articleMapper;
    private final RssFeedRepository rssFeedRepository;

    @Override
    @SneakyThrows
    public List<Article> extractArticlesFromRssUrl(String url) {
        SyndFeed feed = readRssFeed(url);
        return filterAndMapEntries(feed);
    }

    private SyndFeed readRssFeed(String url) throws IOException, FeedException {
        URL feedSource = new URL(url);
        try (XmlReader xmlReader = new XmlReader(feedSource)) {
            SyndFeedInput input = new SyndFeedInput();
            return input.build(xmlReader);
        }
    }

    private List<Article> filterAndMapEntries(SyndFeed feed) {
        return feed.getEntries().stream()
                .filter(this::isValidEntry)
                .map(articleMapper::mapEntryToArticle)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private boolean isValidEntry(SyndEntry entry) {
        return entry.getTitle() != null && entry.getDescription() != null && entry.getPublishedDate() != null;
    }

    @Override
    public List<RssFeed> findAllRssFeeds() {
        return rssFeedRepository.findAll();
    }

    @Override
    public SyndFeed buildRssFeedFromArticles(List<Article> articles) {
        SyndFeed feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("Media Insights RSS Feed");
        feed.setLink("http://mediainsightsservice.northeurope.cloudapp.azure.com:8080/api/articles/feed");
        feed.setDescription("Customized by query RSS feed");
        feed.setPublishedDate(new Date());
        List<SyndEntry> syndEntries = articles.stream()
                .map(articleMapper::mapArticleToSyndEntry)
                .collect(Collectors.toList());
        feed.setEntries(syndEntries);
        return feed;
    }

  @Override
  public Page<RssFeed> findAllPaginated(int pageNumber, int pageSize) {
    return rssFeedRepository.findAll(PageRequest.of(pageNumber, pageSize));
  }

}
