package by.itacademy.report.repository.entity;

import by.itacademy.report.repository.converter.StringArrayConverter;
import jakarta.persistence.*;

@Entity
@Table(schema = "flatsapp", name = "report")
public class ReportEntity {
    @Id
    private String uuid;
    private Long dt_create;
    private Long dt_update;
    private String status;
    private String type;
    private String description;
    @Convert(converter = StringArrayConverter.class)
    private String[] user_uuid;
    private Long from;
    private Long to;
    private String file;

    public ReportEntity() {
    }

    public ReportEntity(String uuid, Long dt_create, Long dt_update, String status, String type, String description, String[] user_uuid, Long from, Long to, String file) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.status = status;
        this.type = type;
        this.description = description;
        this.user_uuid = user_uuid;
        this.from = from;
        this.to = to;
        this.file = file;
    }

    public String getUuid() {
        return uuid;
    }

    public ReportEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Long getDt_create() {
        return dt_create;
    }

    public ReportEntity setDt_create(Long dt_create) {
        this.dt_create = dt_create;
        return this;
    }

    public Long getDt_update() {
        return dt_update;
    }

    public ReportEntity setDt_update(Long dt_update) {
        this.dt_update = dt_update;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ReportEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getType() {
        return type;
    }

    public ReportEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReportEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String[] getUser_uuid() {
        return user_uuid;
    }

    public ReportEntity setUser_uuid(String[] user_uuid) {
        this.user_uuid = user_uuid;
        return this;
    }

    public Long getFrom() {
        return from;
    }

    public ReportEntity setFrom(Long from) {
        this.from = from;
        return this;
    }

    public Long getTo() {
        return to;
    }

    public ReportEntity setTo(Long to) {
        this.to = to;
        return this;
    }

    public String getFile() {
        return file;
    }

    public ReportEntity setFile(String file) {
        this.file = file;
        return this;
    }
}
