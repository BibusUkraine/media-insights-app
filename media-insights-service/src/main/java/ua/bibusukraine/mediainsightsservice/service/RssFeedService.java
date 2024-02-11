package ua.bibusukraine.mediainsightsservice.service;

import org.springframework.data.domain.Page;
import com.rometools.rome.feed.synd.SyndFeed;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.model.RssFeed;

import java.util.List;

public interface RssFeedService {
    List<Article> extractArticlesFromRssUrl(String url);

    List<RssFeed> findAllRssFeeds();

    SyndFeed buildRssFeedFromArticles(List<Article> articles);

    Page<RssFeed> findAllPaginated(int pageNumber, int pageSize);
}
