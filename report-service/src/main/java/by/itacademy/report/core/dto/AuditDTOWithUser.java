package by.itacademy.report.core.dto;





public class AuditDTOWithUser {

    String uuid;
    Long dt_create;
    UserDTO user;
    String text;
    String type;
    String id;

    public AuditDTOWithUser() {
    }

    public AuditDTOWithUser(String uuid, Long dt_create,
                            UserDTO user, String text,
                            String type, String id) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class UserDTO{

        private String uuid;
        private String mail;
        private String fio;
        private String role;

        public UserDTO() {
        }

        public UserDTO(String uuid, String mail,
                       String fio, String role) {
            this.uuid = uuid;
            this.mail = mail;
            this.fio = fio;
            this.role = role;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
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

    }
}
