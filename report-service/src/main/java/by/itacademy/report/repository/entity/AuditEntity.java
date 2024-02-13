package by.itacademy.report.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "flatsapp", name = "audit")
public class AuditEntity {

    @Id
    String uuid;
    Long dt_create;
    String user_id;
    String text;
    String type;
    String id;

    public AuditEntity() {
    }

    public AuditEntity(String uuid, Long dt_create, String user_id, String text, String type, String id) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.user_id = user_id;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public AuditEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Long getDt_create() {
        return dt_create;
    }

    public AuditEntity setDt_create(Long dt_create) {
        this.dt_create = dt_create;
        return this;
    }

    public String getUser_id() {
        return user_id;
    }

    public AuditEntity setUser(String user_id) {
        this.user_id = user_id;
        return this;
    }

    public String getText() {
        return text;
    }

    public AuditEntity setText(String text) {
        this.text = text;
        return this;
    }

    public String getType() {
        return type;
    }

    public AuditEntity setType(String type) {
        this.type = type;
        return this;
    }

    public String getId() {
        return id;
    }

    public AuditEntity setId(String id) {
        this.id = id;
        return this;
    }
}
