package by.itacademy.parser_sale.parserEngine.config;

import org.htmlunit.BrowserVersion;
import org.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ParserConfig {

    @Value("${parser.webClient.browserVersion}")
    private String browserVersionString;

    @Value("${parser.webClient.javaScriptEnabled}")
    private Boolean javaScriptEnabled;

    @Value("${parser.webClient.cssEnabled}")
    private Boolean cssEnabled;

    @Value("${parser.webClient.throwExceptionOnScriptError}")
    private Boolean throwExceptionOnScriptError;

    @Value("${parser.webClient.throwExceptionOnFailingStatusCode}")
    private Boolean throwExceptionOnFailingStatusCode;

    @Value("${parser.webClient.page}")
    private String page;

    @Value("${parser.webClient.waitForBackgroundJavaScript}")
    private Long waitForBackgroundJavaScript;

    @Value("${parser.webClient.firstByXPath}")
    private String firstByXPath;

    @Bean
    public BrowserVersion browserVersion() {

        return switch (browserVersionString.toUpperCase()) {
            case "CHROME" -> BrowserVersion.CHROME;
            case "FIREFOX" -> BrowserVersion.FIREFOX;
            default -> throw new IllegalArgumentException("Unsupported browser version: " + browserVersionString);
        };
    }

    @Bean
    public WebClient webClient(BrowserVersion browserVersion) {
        WebClient webClient = new WebClient(browserVersion);

        webClient.getOptions().setJavaScriptEnabled(javaScriptEnabled);
        webClient.getOptions().setCssEnabled(cssEnabled);
        webClient.getOptions().setThrowExceptionOnScriptError(throwExceptionOnScriptError);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(throwExceptionOnFailingStatusCode);

        return webClient;
    }

    @Bean
    public String page() {
        return page;
    }

    @Bean
    public Long waitForBackgroundJavaScript() {
        return waitForBackgroundJavaScript;
    }

    @Bean
    public String firstByXPath() {
        return firstByXPath;
    }


}
