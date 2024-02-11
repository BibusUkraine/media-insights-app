package ua.bibusukraine.mediainsightsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ua.bibusukraine.mediainsightsservice.mapper.ArticleMapper;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.payload.resonse.NytResponse;
import ua.bibusukraine.mediainsightsservice.service.NytApiService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NytApiServiceImpl implements NytApiService {

    private final ArticleMapper articleMapper;

    @Override
    public List<Article> getArticlesByYearAndMonth(int year, int month) {
        String apiUrl = buildApiUrl(year, month);
        return fetchArticlesFromApi(apiUrl);
    }

    private String buildApiUrl(int year, int month) {
        return String.format("%s%d/%d.json?api-key=%s", "https://api.nytimes.com/svc/archive/v1/", year, month, "K9E27kT43D8aI3jpeRGqgA6ca65xAdLn");
    }

    private List<Article> fetchArticlesFromApi(String apiUrl) {
        RestClient restClient = RestClient.create(apiUrl);
        NytResponse response = restClient.get()
                .retrieve()
                .body(NytResponse.class);

        List<NytResponse.Response.NYTAtricles> docs = Arrays.asList(response.getResponse().getDocs());

        return docs.stream()
                .map(r -> {
                    Article article = articleMapper.mapNytArticleToArticle(r);
                    article.setLogoLink("https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg");
                    if (article.getImageLink() == null) {
                        article.setImageLink("https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg");
                    }
                    return article;
                })
                .collect(Collectors.toList());
    }

}
