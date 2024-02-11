package ua.bibusukraine.mediainsightsservice.service;

import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.model.constant.MediaType;

import java.time.LocalDate;
import java.util.List;

public interface ArticleService {

  List<Article> findAllByQueryBetweenDates(
      String query,
      LocalDate startDate,
      LocalDate endDate,
      Integer pageNumber
  );

  Article saveArticle(Article article);

}
