package collection;

import io.User;

import java.io.Serializable;
import java.util.Date;

/**
 * Класс, представляющий сущность организации
 */
public class Organization implements Comparable<Organization>, Serializable {
    /**
     * Уникальный id организации.
     * Поле не может быть null,
     * Значение поля должно быть больше 0,
     * Значение этого поля должно быть уникальным,
     * Значение этого поля должно генерироваться автоматически
     */
    private Integer id;

    private User user;

    /**
     * Название организации.
     * Поле не может быть null,
     * Строка не может быть пустой
     */
    private String name;

    /**
     * Координаты организации.
     * Поле не может быть null
     */
    private Coordinates coordinates;

    /**
     * Время создания организации.
     * Поле не может быть null,
     * Значение этого поля должно генерироваться автоматически
     */
    private Date creationDate;

    /**
     * Годовой оборот организации.
     * Значение поля должно быть больше 0
     */
    private long annualTurnover;

    /**
     * Количество сотрудников организации.
     * Поле не может быть null,
     * Значение поля должно быть больше 0
     */
    private Long employeesCount;

    /**
     * Тип организации.
     * Поле не может быть null
     */
    private OrganizationType type;

    /**
     * Почтовый адрес организации.
     * Поле не может быть null
     */
    private Address postalAddress;

    /**
     * Конструктор класса организации
     *
     * @param id             уникальный id организации
     * @param name           название организации
     * @param coordinates    координаты организации
     * @param creationDate   время создания организации
     * @param annualTurnover годовой оборот организации
     * @param employeesCount количество сотрудников организации
     * @param type           тип организации
     * @param postalAddress  почтовый адрес организации
     */
    public Organization(
            Integer id,
            String name,
            Coordinates coordinates,
            Date creationDate,
            long annualTurnover,
            Long employeesCount,
            OrganizationType type,
            Address postalAddress
    ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.annualTurnover = annualTurnover;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    // Геттеры и сеттеры для всех полей

    /**
     * Метод, выводящий все значения организации
     *
     * @return значения организации
     */
    @Override
    public String toString() {
        return "=============\n" +
                "id:\n " + id +
                ",\nname: \n" + name +
                ",\ncoordinates: \n " + coordinates +
                ",\ncreationDate: \n " + creationDate +
                ",\nannualTurnover: \n " + annualTurnover +
                ",\nemployeesCount: \n " + employeesCount +
                ",\ntype: \n " + type +
                ",\npostalAddress: \n " + postalAddress;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public long getAnnualTurnover() {
        return annualTurnover;
    }

    public Long getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getPostalAddress() {
        return postalAddress;
    }

    @Override
    public int compareTo(Organization other) {
        return this.id.compareTo(other.getId());
    }
}