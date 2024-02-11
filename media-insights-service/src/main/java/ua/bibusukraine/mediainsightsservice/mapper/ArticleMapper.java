package ua.bibusukraine.mediainsightsservice.mapper;

import com.rometools.rome.feed.synd.SyndEntry;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.payload.resonse.NytResponse;

public interface ArticleMapper {

    Article mapEntryToArticle(SyndEntry syndEntry);

    SyndEntry mapArticleToSyndEntry(Article article);

    Article mapNytArticleToArticle(NytResponse.Response.NYTAtricles nytArticle);

}
