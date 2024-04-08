package Config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TelegramConfig {
    private String botToken;
    private long chatId;
    private String msg;
    private String carsUrl;
    private String bikesUrl;
    public String getCarsUrl(){return carsUrl;}
    public void setCarsUrl(String carsUrl){this.carsUrl = carsUrl;}
    public String getBikesUrl(){return bikesUrl;}
    public void setBikesUrl(String bikesUrl){this.bikesUrl = bikesUrl;}
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getBotToken(){
        return botToken;
    }
    public void setBotToken(String botToken){
        this.botToken = botToken;
    }
    public Long getChatId(){
        return chatId;
    }
    public void setChatId(long chatId){
        this.chatId = chatId;
    }
}
