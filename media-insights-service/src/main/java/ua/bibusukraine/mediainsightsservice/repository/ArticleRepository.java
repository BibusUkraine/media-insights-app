package ua.bibusukraine.mediainsightsservice.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ua.bibusukraine.mediainsightsservice.model.Article;


public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

}