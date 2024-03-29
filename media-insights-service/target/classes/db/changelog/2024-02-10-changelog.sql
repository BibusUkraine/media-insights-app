-- liquibase formatted sql

-- changeset oleksandr_muzyka:1707563117835-1
CREATE TABLE rss_feed
(
    id   INTEGER GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    link TEXT                                     NOT NULL,
    logo_link TEXT                                NOT NULL,
    CONSTRAINT pk_rss_feed PRIMARY KEY (id)
);

-- changeset oleksandr_muzyka:1707563117835-2
INSERT INTO rss_feed (link, logo_link)
VALUES
    ('https://dou.ua/feed/', 'https://upload.wikimedia.org/wikipedia/uk/9/97/%D0%9B%D0%BE%D0%B3%D0%BE%D1%82%D0%B8%D0%BF_%D1%81%D0%B0%D0%B9%D1%82%D1%83_dou.ua.png'),
    ('https://www.pravda.com.ua/rss/', 'https://img.pravda.com/images/up_for_fb.png'),
    ('https://euromaidanpress.com/feed/', 'https://d25bxi3v5ifga8.cloudfront.net/uploads/base64img/20220411/20ec1e3eaf58afcfae1924abc8f76f90.png'),
    ('https://www.independent.co.uk/topic/ukraine/rss', 'https://logovtor.com/wp-content/uploads/2021/10/the-independent-logo-vector.png'),
    ('https://en.interfax.com.ua/news/last.rss', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/Interfax.svg/2560px-Interfax.svg.png'),
    ('https://unn.ua/rss/news_uk.xml', 'https://assets.bizclikmedia.net/576/da94cc8087c30a31a6af804a0c7bfb59:5c0a3543ec18fb978a7ffebd63d68af5/un.PNG'),
    ('https://ukurier.gov.ua/uk/feed/', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTN_ZNFChMYzhRz3SIC8yfi83TBTKO7XliTosITu72aaA&s'),
    ('https://theguardian.com/world/ukraine/rss', 'https://assets-legacy.floridarrc.com/2023/01/the-guardian-logo.jpeg'),
    ('https://www.forbes.com/innovation/feed2', 'https://cdn.worldvectorlogo.com/logos/forbes-2.svg'),
    ('https://feeds.bbci.co.uk/news/world/europe/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/world/asia/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/world/africa/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/world/latin_america/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/world/middle_east/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/world/us_and_canada/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/england/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/northern_ireland/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/scotland/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://feeds.bbci.co.uk/news/wales/rss.xml', 'https://ichef.bbci.co.uk/images/ic/1920x1080/p09xtmrp.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/World.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Africa.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Americas.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/AsiaPacific.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Europe.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/MiddleEast.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/US.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Education.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Politics.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Upshot.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Business.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/EnergyEnvironment.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/SmallBusiness.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Economy.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Dealbook.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/MediaandAdvertising.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/YourMoney.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/PersonalTech.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Sports.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Baseball.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/CollegeBasketball.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/CollegeFootball.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Golf.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Hockey.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/ProBasketball.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/ProFootball.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Soccer.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Tennis.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Science.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Climate.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Space.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Health.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Well.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Arts.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/ArtandDesign.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Books/Review.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Dance.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Movies.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Music.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Television.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Theater.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/FashionandStyle.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/DiningandWine.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Weddings.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/tmagazine.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Jobs.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/RealEstate.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/Automobiles.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/MostShared.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('https://rss.nytimes.com/services/xml/rss/nyt/MostViewed.xml', 'https://upload.wikimedia.org/wikipedia/commons/4/40/New_York_Times_logo_variation.jpg'),
    ('http://rss.cnn.com/rss/cnn_topstories.rss', 'https://www.logodesignlove.com/wp-content/uploads/2010/06/cnn-logo-white-on-red.jpg'),
    ('https://moxie.foxnews.com/google-publisher/latest.xml', 'https://upload.wikimedia.org/wikipedia/commons/thumb/6/67/Fox_News_Channel_logo.svg/1024px-Fox_News_Channel_logo.svg.png'),
    ('https://feeds.npr.org/1001/rss.xml', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/National_Public_Radio_logo.svg/2560px-National_Public_Radio_logo.svg.png'),
    ('https://nypost.com/feed/', 'https://assets.stickpng.com/images/60915263f9f208000443659b.png'),
    ('https://www.nbcchicago.com/?rss=y', 'https://media.nbcchicago.com/2020/10/NBC_Chicago.png?fit=1200%2C628&quality=85&strip=all');