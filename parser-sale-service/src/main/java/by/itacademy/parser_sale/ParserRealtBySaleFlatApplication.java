package by.itacademy.parser_sale;

import by.itacademy.parser_sale.dao.entity.FlatEntityRaw;
import by.itacademy.parser_sale.parserEngine.Parser;
import by.itacademy.parser_sale.parserEngine.api.IParser;
import by.itacademy.parser_sale.service.FlatService;
import by.itacademy.parser_sale.service.api.IFlatService;
import by.itacademy.parser_sale.telegramBot.MyTelegramBot;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ParserRealtBySaleFlatApplication implements CommandLineRunner {
    private final IParser parser;
    private final String page;
    private final IFlatService flatService;

    private final MyTelegramBot bot;

    public ParserRealtBySaleFlatApplication(Parser parser, String page, FlatService flatService, MyTelegramBot bot) {
        this.parser = parser;
        this.page = page;
        this.flatService = flatService;
        this.bot = bot;
    }

    public static void main(String[] args) {
        SpringApplication.run(ParserRealtBySaleFlatApplication.class, args);
    }


    @Override
    public void run(String... args) {
        bot.sendMessage("(SALE) Hi! I started )");
    }


    @Scheduled(cron = "0 0 */3 * * *")
    public void startParse() {

        int total = 0;

        for (int i = 1; i < 21; i++) {

            String url = new StringBuilder(page).append("?page=").append(i).toString();

            bot.sendMessage("(SALE) Read from page:\n" + url);

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

            bot.sendMessage("(SALE) Read flats: " + flatEntityRawList.size());
            bot.sendMessage("(SALE) Written to base new flats: " + counter);

            try {
                Thread.sleep(300000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        bot.sendMessage("(SALE) Written total to base: " + total);

    }

}
