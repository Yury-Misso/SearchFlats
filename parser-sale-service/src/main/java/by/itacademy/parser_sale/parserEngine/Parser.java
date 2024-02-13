package by.itacademy.parser_sale.parserEngine;

import by.itacademy.parser_sale.core.DTO.FlatDTO;
import by.itacademy.parser_sale.core.converter.FlatDTOToFlatEntityConverter;
import by.itacademy.parser_sale.dao.entity.FlatEntityRaw;
import by.itacademy.parser_sale.parserEngine.api.IParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlElement;
import org.htmlunit.html.HtmlPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Parser implements IParser {
    private final WebClient webClient;
    private final Long waitForBackgroundJavaScript;
    private final String firstByXPath;

    private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

    private static final Logger logger = LoggerFactory.getLogger(Parser.class);

    public Parser(WebClient webClient, Long waitForBackgroundJavaScript, String firstByXPath) {
        this.webClient = webClient;
        this.waitForBackgroundJavaScript = waitForBackgroundJavaScript;
        this.firstByXPath = firstByXPath;
    }

    @Override
    public List<FlatEntityRaw> getFlatEntitiesRaw(String pageUrl) {
        List<FlatEntityRaw> list = new ArrayList<>();
        try {
            list = getFlatDTO(pageUrl).stream()
                    .map(flatDTO -> {
                        try {
                            return FlatDTOToFlatEntityConverter.convertDTOToEntity(flatDTO);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
            return list;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return list;
    }


    private List<FlatDTO> getFlatDTO(String pageUrl) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        return getFlatDTOList(objectMapper.readTree(getJsonByXPath(pageUrl)));
    }


    private List<FlatDTO> getFlatDTOList(JsonNode node) throws JsonProcessingException {
        List<FlatDTO> flatDTOList = new ArrayList<>();
        findAndProcessObjects(node, flatDTOList);
        return flatDTOList;
    }


    private void findAndProcessObjects(JsonNode node, List<FlatDTO> flatDTOList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                if ("objects".equals(field.getKey()) && field.getValue().isArray()) {
                    JsonNode flats = field.getValue();
                    for (JsonNode flat : flats) {
                        String flatString = flat.toString();
                        FlatDTO flatDTO = objectMapper.readValue(flatString, FlatDTO.class);
                        flatDTOList.add(flatDTO);
                    }
                }
                findAndProcessObjects(field.getValue(), flatDTOList);
            }
        } else if (node.isArray()) {
            for (JsonNode arrayItem : node) {
                findAndProcessObjects(arrayItem, flatDTOList);
            }
        }
    }

    private String getJsonByXPath(String pageUrl) {
        String jsonText = null;

        try {
            HtmlPage htmlPage = webClient.getPage(pageUrl);
            LOGGER.error("Status response: " + htmlPage.getWebResponse().getStatusCode());
            this.webClient.waitForBackgroundJavaScript(this.waitForBackgroundJavaScript);
            HtmlElement scriptElement = htmlPage.getFirstByXPath(this.firstByXPath);
            jsonText = scriptElement.getTextContent();
        } catch (Exception e) {
            logger.error(Arrays.toString(e.getStackTrace()));
        }

        return jsonText;
    }


}
