package ua.bibusukraine.mediainsightsservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "rss_feed")
@Getter
@Setter
public class RssFeed {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false, updatable = false)
  private Integer id;

  @Column(name = "link", nullable = false)
  private String link;

  @Column(name = "logo_link", nullable = false)
  private String logoLink;

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;

    RssFeed rssFeed = (RssFeed) object;

    return Objects.equals(id, rssFeed.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

}
