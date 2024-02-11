package ua.bibusukraine.mediainsightsservice.mapper.impl;

import com.rometools.rome.feed.synd.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ua.bibusukraine.mediainsightsservice.mapper.ArticleMapper;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.payload.resonse.NytResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ArticleMapperImpl implements ArticleMapper {

    @Override
    public Article mapEntryToArticle(SyndEntry entry) {
        Article article = new Article();
        article.setUrl(entry.getLink());
        article.setTitle(entry.getTitle());
        article.setImageLink(parseImageLink(entry));
        if (entry.getDescription().getValue() != null) {
            article.setDescription(Jsoup.parse(entry.getDescription().getValue()).text());
        }
        article.setPublishDate(entry.getPublishedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return article;
    }

    private String parseImageLink(SyndEntry entry) {
        if (entry.getEnclosures() != null && !entry.getEnclosures().isEmpty()) {
            SyndEnclosure enclosure = entry.getEnclosures().get(0);
            if (enclosure.getType().equals("image/jpeg") || enclosure.getType().equals("image/png")) {
                return enclosure.getUrl();
            }
        }
        Optional<SyndContent> content = Optional.ofNullable(entry.getContents().stream().findFirst().orElse(null));
        if (content.isPresent()) {
            Document doc = Jsoup.parse(content.get().getValue());
            Elements imgElements = doc.select("img");
            if (!imgElements.isEmpty()) {
                return imgElements.first().attr("src");
            }
        }
        if (entry.getDescription() != null) {
            Document doc = Jsoup.parse(entry.getDescription().getValue());
            Elements imgElements = doc.select("img");
            if (!imgElements.isEmpty()) {
                return imgElements.first().attr("src");
            }
        }
        return null;
    }


    @Override
    public SyndEntry mapArticleToSyndEntry(Article article) {
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle(article.getTitle());
        entry.setLink(article.getUrl());
        entry.setPublishedDate(Date.from(article.getPublishDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        SyndContent description = new SyndContentImpl();
        description.setType("text/plain");
        description.setValue(article.getDescription());
        entry.setDescription(description);
        SyndEnclosure enclosure = new SyndEnclosureImpl();
        enclosure.setUrl(article.getImageLink());
        entry.setEnclosures(List.of(enclosure));
        return entry;
    }

    @Override
    public Article mapNytArticleToArticle(NytResponse.Response.NYTAtricles nytArticle) {
        Article article = new Article();
        article.setUrl(nytArticle.getWebUrl());
        article.setTitle(nytArticle.getAbstractText());
        article.setDescription(nytArticle.getLeadParagraph());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        LocalDateTime localDateTime = LocalDateTime.parse(nytArticle.getPubDate(), formatter);
        article.setPublishDate(localDateTime.toLocalDate());
        if (nytArticle.getMultimedia() != null && !nytArticle.getMultimedia().isEmpty()) {
            String url = nytArticle.getMultimedia().get(0).getUrl();
            if (url.startsWith("images")) {
                url = "https://www.nytimes.com/" + url;
            }
            article.setImageLink(url);
        }
        return article;
    }

}
