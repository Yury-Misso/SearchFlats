package by.itacademy.parser_rent;

import by.itacademy.parser_rent.dao.entity.FlatEntityRaw;
import by.itacademy.parser_rent.parserEngine.Parser;
import by.itacademy.parser_rent.parserEngine.api.IParser;
import by.itacademy.parser_rent.service.FlatService;
import by.itacademy.parser_rent.service.api.IFlatService;
import by.itacademy.parser_rent.telegramBot.MyTelegramBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ParserRealtByRentFlatApplication implements CommandLineRunner {
    private final IParser parser;
    private final String page;
    private final IFlatService flatService;

    private final MyTelegramBot bot;

    public ParserRealtByRentFlatApplication(Parser parser, String page, FlatService flatService, MyTelegramBot bot) {
        this.parser = parser;
        this.page = page;
        this.flatService = flatService;
        this.bot = bot;
    }

    public static void main(String[] args) {
        SpringApplication.run(ParserRealtByRentFlatApplication.class, args);
    }


    @Override
    public void run(String... args) {
        bot.sendMessage("Hi! I started )");
    }


    @Scheduled(cron = "0 0 */3 * * *")
    public void startParse() {

        int total = 0;

        for (int i = 1; i < 21; i++) {

            String url = new StringBuilder(page).append("?page=").append(i).toString();

            bot.sendMessage("Read from page:\n" + url);

            List<FlatEntityRaw> flatEntityRawList = parser.getFlatEntitiesRaw(url);

            boolean isWritten = false;
            int counter = 0;

            for (FlatEntityRaw flatEntityRaw : flatEntityRawList) {

                isWritten = flatService.saveRaw(flatEntityRaw);
                if (isWritten) {
                    counter++;
                }

            }

            total += counter;

            bot.sendMessage("Read flats: " + flatEntityRawList.size());
            bot.sendMessage("Written to base new flats: " + counter);

            try {
                Thread.sleep(300000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        bot.sendMessage("Written total to base: " + total);

    }

}
