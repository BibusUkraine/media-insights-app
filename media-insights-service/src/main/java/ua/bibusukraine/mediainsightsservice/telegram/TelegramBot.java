package ua.bibusukraine.mediainsightsservice.telegram;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.bibusukraine.mediainsightsservice.model.Article;
import ua.bibusukraine.mediainsightsservice.service.ArticleService;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final Logger logger = LoggerFactory.getLogger(TelegramBot.class);
    private final ArticleService articleService;
    private static final String START_MESSAGE = "Hi, it's bot from the Bibus Ukraine team, to search something new type \"/search \" and the new you are interested in :)";

    @Value("${telegram.bot-name}")
    private String botUserName;

    public TelegramBot(@Value("${telegram.bot-token}") String botToken, ArticleService articleService) {
        super(botToken);
        this.articleService = articleService;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            if (message != null) {
                if (message.getText().contains("/start")) {
                    SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), START_MESSAGE);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        logger.error("Error while sending message to chatId: {}", update.getMessage().getChatId(), e);
                    }
                } else if (message.getText().contains("/search")) {
                    String query = message.getText().replace("/search", "").trim();
                    List<Article> articles = articleService.findAllByQueryBetweenDates(query, null, null, 0);
                    if (articles.size() > 5) {
                        articles = articles.subList(0, 5);
                    }
                    if (articles.isEmpty()) {
                        SendMessage sendMessage = new SendMessage(String.valueOf(update.getMessage().getChatId()), "No articles found on requested query");
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            logger.error("Error while sending message to chatId: {}", update.getMessage().getChatId(), e);
                        }
                    }
                    articles.forEach(article -> {
                        try {
                            execute(generateTelegramFormatMessage(article, update));
                        } catch (Exception e) {
                            logger.error("Error while sending message to chatId: {}", update.getMessage().getChatId(), e);
                        }
                    });
                }
            }
        }
    }

    private SendMessage generateTelegramFormatMessage(Article article, Update update) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton1 = InlineKeyboardButton.builder()
                .text("Visit source")
                .url(article.getUrl())
                .build();
        rowInline1.add(inlineKeyboardButton1);
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);

        return SendMessage.builder()
                .text(formatResponseMessage(article.getTitle(), article.getDescription()))
                .chatId(update.getMessage().getChatId().toString())
                .parseMode(ParseMode.HTML)
                .replyMarkup(markupInline)
                .build();
    }

      private String formatResponseMessage(String title, String description) {
          return "<b>" + title + "</b>" + "\n<blockquote>" + description + "</blockquote>";
      }


    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

}
