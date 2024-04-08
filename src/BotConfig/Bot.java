package BotConfig;

import Config.TelegramConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.InlineQuery;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.File;
import java.io.IOException;
public class Bot {
    static TelegramConfig config;

    public static String loadToken(String filePath){
        try{
            config = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(new File(filePath), TelegramConfig.class);
            return config.getBotToken();
        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
    }
    public void createBot() {
        File file = new File("src/Config/Json/g90-2b9g7b4w[o0ngs.json");
        if(!file.exists() && file.length() == 0){
            System.out.println("No token/chatId in file: " + file.getAbsolutePath());
            System.out.println("Or\nFile is not exists");
        }
        TelegramBot bot = new TelegramBot(loadToken("src/Config/Json/g90-2b9g7b4w[o0ngs.json"));

        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.addRow(
                new InlineKeyboardButton("\uD83D\uDE99 Cars").callbackData("cars"),
                new InlineKeyboardButton("\uD83C\uDFCD Bikes").callbackData("bikes")
        );
        bot.execute(new SendMessage(config.getChatId()
                ,"Choose:").replyMarkup(markup));
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
                } else if (update.inlineQuery() != null) {
                    Message msg = update.message();
                    String query = update.inlineQuery().query();
                    Long chatId = msg.chat().id();

                    String responseMessage = "";
                    if ("cars".equals(query)) {
                        config.setUrl(config.getCarsUrl());
                        config.setCategory(config.getCarsCategory());
                        responseMessage = "Searching in: " + config.getUrl();
                    } else if ("bikes".equals(query)) {
                        config.setUrl(config.getBikesUrl());
                        config.setCategory(config.getBikesCategory());
                        responseMessage = "Searching in: " + config.getUrl();
                    }
                    bot.execute(new SendMessage(chatId, responseMessage));
                    if(responseMessage.contains("Searching in: ")){
                        InlineKeyboardMarkup markUP = new InlineKeyboardMarkup();
                       /* markUP.addRow(
                          new InlineKeyboardButton(JsoupConnection.getList()).callbackData("category")
                        );*/
                    }
                }
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
