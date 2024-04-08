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
        bot.setUpdatesListener(updates ->{
            for(Update update : updates){
                Message msg = update.message();
                    if(update.inlineQuery().query() != null){
                        String query = update.inlineQuery().query();
                        System.out.println(query);
                        if(query.equals("cars")){
                            config.setCarsUrl(config.getCarsUrl());
                            bot.execute(new SendMessage(msg.chat().id(), "Searching in: " + config.getCarsUrl()));
                        }else if(query.equals("bikes")){
                            config.setBikesUrl(config.getBikesUrl());
                            bot.execute(new SendMessage(msg.chat().id(),"Searching in: " + config.getBikesUrl()));
                        }
                }
            }

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
            },e ->{
            if(e.response() != null){
                e.response().errorCode();
                e.response().description();
            }else{
                e.printStackTrace();
            }
        });
    }
}
