package ua.bibusukraine.mediainsightsservice.payload.resonse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class NytResponse {

    private Response response;

    @Data
    public static class Response {

        private NYTAtricles[] docs;

        @Data
        public static class NYTAtricles {

            @JsonProperty("abstract")
            private String abstractText;

            @JsonProperty("web_url")
            private String webUrl;

            @JsonProperty("lead_paragraph")
            private String leadParagraph;

            @JsonProperty("pub_date")
            private String pubDate;

            @JsonProperty("multimedia")
            private List<Multimedia> multimedia;

            @Data
            public static class Multimedia {
                private String url;
            }

        }

    }


}

