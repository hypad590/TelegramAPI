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
    private String url;
    private String category;
    private String carsCategory;
    private String bikesCategory;
    public String getCarsCategory(){return carsCategory;}
    public void setCarsCategory(String carsCategory){this.carsCategory = carsCategory;}
    public String getBikesCategory(){return bikesCategory;}
    public void setBikesCategory(String bikesCategory){this.bikesCategory = bikesCategory;}
    public String getCategory(){return category;}
    public void setCategory(String category){this.category = category;}
    public String getUrl(){return url;}
    public void setUrl(String url){this.url = url;}
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
