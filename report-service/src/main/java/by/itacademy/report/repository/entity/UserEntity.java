package by.itacademy.report.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "flatsapp", name = "user")
public class UserEntity {

    @Id
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "dt_create")
    private Long dt_create;
    @Column(name = "dt_update")
    private Long dt_update;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Column(name = "role")
    private String role;
    @Column(name = "status")
    private String status;
    @Column(name = "password")
    private String password;

    public UserEntity() {

    }

    public UserEntity(String uuid, Long dt_create, Long dt_update, String mail, String fio, String role, String status, String password) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getDt_create() {
        return dt_create;
    }

    public void setDt_create(Long dt_create) {
        this.dt_create = dt_create;
    }

    public Long getDt_update() {
        return dt_update;
    }

    public void setDt_update(Long dt_update) {
        this.dt_update = dt_update;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uuid='" + uuid + '\'' +
                ", dt_create=" + dt_create +
                ", dt_update=" + dt_update +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
