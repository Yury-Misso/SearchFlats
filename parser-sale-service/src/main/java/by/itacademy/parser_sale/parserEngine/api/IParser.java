package by.itacademy.parser_sale.parserEngine.api;

import by.itacademy.parser_sale.dao.entity.FlatEntityRaw;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IParser {
    List<FlatEntityRaw> getFlatEntitiesRaw(String pageUrl);
}
