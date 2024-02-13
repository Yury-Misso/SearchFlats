package by.itacademy.parser_sale.telegramBot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Component;

@Component
public class MyTelegramBot {
    private final TelegramBot bot;
    private final Long myChatId;

    public MyTelegramBot(TelegramBot telegramBot, Long myChatId) {
        this.bot = telegramBot;
        this.myChatId = myChatId;
    }

    public void sendMessage(String message) {
        this.bot.execute(new SendMessage(this.myChatId, message));
    }

}
