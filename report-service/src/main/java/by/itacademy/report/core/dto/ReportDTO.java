package by.itacademy.report.core.dto;

import lombok.*;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class ReportDTO {

    private Long dt_create;
    private Long dt_update;
    private String status;
    private String type;
    private String description;
    private String[] user_uuid;
    private Long from;
    private Long to;
    private String file;

}
