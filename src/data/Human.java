package data;

import java.time.LocalDate;

/**
 * Source class
 */
public class Human {
    private final String name; //Поле не может быть null, Строка не может быть пустой
    private final Double height; //Значение поля должно быть больше 0
    private final java.time.LocalDate birthday;

    public Human(String name, Double height){
        this.name = name;
        this.height = height;
        this.birthday = java.time.LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public Double getHeight() {
        return height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }
}
