package BotConfig;

import Config.TelegramConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;

import java.io.File;
import java.io.IOException;

public class Bot {
    private static final String CONFIG_FILE_PATH = "src/Config/Json/g90-2b9g7b4w[o0ngs.json";
    private static TelegramBot bot;
    private static TelegramConfig config;

    public static void main(String[] args) {
        createBot();
    }

    public static void createBot() {
        bot = new TelegramBot(loadToken(CONFIG_FILE_PATH));

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.addRow(
                new InlineKeyboardButton("\uD83D\uDE99 Cars").callbackData("cars"),
                new InlineKeyboardButton("\uD83C\uDFCD Bikes").callbackData("bikes")
        );

        SendResponse response = bot.execute(new SendMessage(config.getChatId(), "Choose:").replyMarkup(markup));

        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                if (update.callbackQuery() != null) {
                    String data = update.callbackQuery().data();
                    Long chatId = update.callbackQuery().message().chat().id();

                    String responseMessage = "";
                    if ("cars".equals(data)) {
                        config.setUrl(config.getCarsUrl());
                        responseMessage = "Searching in: " + config.getUrl();
                    } else if ("bikes".equals(data)) {
                        config.setUrl(config.getBikesUrl());
                        responseMessage = "Searching in: " + config.getUrl();
                    }

                    bot.execute(new SendMessage(chatId, responseMessage));
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

    private static String loadToken(String filePath) {
        try {
            config = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(new File(filePath), TelegramConfig.class);
            return config.getBotToken();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}