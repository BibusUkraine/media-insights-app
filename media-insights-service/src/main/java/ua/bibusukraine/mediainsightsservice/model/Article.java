package ua.bibusukraine.mediainsightsservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.time.LocalDate;
import java.util.Objects;

@Document(indexName = "articles")
@Setting(settingPath = "/articles-analyzer.json")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    @Id
    private String uuid;

    @Field(type = FieldType.Text, name = "url")
    private String url;

    @Field(type = FieldType.Text, name = "title", analyzer = "custom_analyzer")
    private String title;

    @Field(type = FieldType.Text, name = "description", analyzer = "custom_analyzer")
    private String description;

    @Field(type = FieldType.Date, name = "publish_date")
    private LocalDate publishDate;

    @Field(type = FieldType.Text, name = "image_link")
    private String imageLink;

    @Field(type = FieldType.Text, name = "logo_link")
    private String logoLink;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;

        return Objects.equals(url, article.url);
    }

    @Override
    public int hashCode() {
        return url != null ? url.hashCode() : 0;
    }
}
