package by.itacademy.user.repository.entity;

public class UserEntityBuilder {
    private String uuid;
    private Long dtCreate;
    private Long dtUpdate;
    private String mail;
    private String fio;
    private String role;
    private String status;
    private String password;

    public UserEntityBuilder setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public UserEntityBuilder setDt_create(Long dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public UserEntityBuilder setDt_update(Long dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public UserEntityBuilder setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public UserEntityBuilder setFio(String fio) {
        this.fio = fio;
        return this;
    }

    public UserEntityBuilder setRole(String role) {
        this.role = role;
        return this;
    }

    public UserEntityBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public UserEntityBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity build() {
        return new UserEntity(uuid, dtCreate, dtUpdate, mail, fio, role, status, password);
    }
}