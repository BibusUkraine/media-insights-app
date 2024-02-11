package ua.bibusukraine.mediainsightsservice.controller;

import com.rometools.rome.feed.synd.SyndFeed;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.service.ArticleService;
import ua.bibusukraine.mediainsightsservice.service.RssFeedService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/articles")
@CrossOrigin
public class ArticleController {

    private final ArticleService articleService;
    private final RssFeedService rssFeedService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get articles by query and date range")
    public List<Article> getArticles(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @PastOrPresent @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam @PositiveOrZero Integer page
    ) {
        return articleService.findAllByQueryBetweenDates(query, startDate, endDate, page);
    }

    @GetMapping("/feed")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get RSS feed by query")
    public SyndFeed getFeed(@RequestParam(required = false) String query) {
        List<Article> articles = articleService.findAllByQueryBetweenDates(query, null, null, 0);
        return rssFeedService.buildRssFeedFromArticles(articles);
    }

}
