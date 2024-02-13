package by.itacademy.parser_rent.parserEngine.api;

import by.itacademy.parser_rent.dao.entity.FlatEntityRaw;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IParser {
    List<FlatEntityRaw> getFlatEntitiesRaw(String pageUrl);
}
