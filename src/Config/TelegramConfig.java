package Config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TelegramConfig {
    private String botToken;
    private long chatId;
    private String msg;
    private String url;
    public String getUrl(){return url;}
    public void setUrl(){this.url = url;}
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
