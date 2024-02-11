package ua.bibusukraine.mediainsightsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.bibusukraine.mediainsightsservice.model.RssFeed;

public interface RssFeedRepository extends JpaRepository<RssFeed, Integer> {
}
