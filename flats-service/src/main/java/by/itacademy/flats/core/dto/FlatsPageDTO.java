package by.itacademy.flats.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@JsonPropertyOrder({
        "number",
        "size",
        "totalPages",
        "totalElements",
        "first",
        "numberOfElements",
        "last",
        "content"
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class FlatsPageDTO {

    private Integer number;

    private Integer size;

    @JsonProperty("total_pages")
    private Integer totalPages;

    @JsonProperty("total_elements")
    private Long totalElements;

    private Boolean first;

    @JsonProperty("number_of_elements")
    private Integer numberOfElements;

    private Boolean last;

    private List<FlatDTO> content;

}
