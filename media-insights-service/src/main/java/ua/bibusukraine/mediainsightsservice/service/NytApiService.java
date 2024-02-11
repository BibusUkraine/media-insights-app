package ua.bibusukraine.mediainsightsservice.service;

import ua.bibusukraine.mediainsightsservice.model.Article;

import java.util.List;

public interface NytApiService {

    List<Article> getArticlesByYearAndMonth(int year, int month);

}
