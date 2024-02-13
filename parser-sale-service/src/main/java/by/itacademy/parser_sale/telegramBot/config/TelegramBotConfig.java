package by.itacademy.parser_sale.telegramBot.config;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotConfig {
    @Value("${telegram.bot.token}")
    private String token;

    @Value("${telegram.bot.mychatid}")
    private Long myChatId;

    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot(token);
    }

    @Bean
    public Long myChatId() {
        return myChatId;
    }

}
