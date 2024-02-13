package by.itacademy.user.aop.eaudit;

public enum Text {

    FIND_FLATS("Поиск квартир"),
    REGISTRATION("Регистрация пользователя"),
    VERIFICATION("Верификация пользователя"),
    LOGIN("Авторизация пользователя"),
    ABOUT_ME("Информация о себе"),
    ABOUT_ALL("Информация о пользователях"),
    ABOUT_USER("Информация о пользователе"),
    CREATE_USER("Создан пользователь"),
    UPDATE_USER("Обновлен пользователь");

    private String description;

    Text(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }


}
