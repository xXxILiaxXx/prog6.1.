package file;

import io.*;
import collection.*;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.Date;

/**
 * Класс, читающий данные для объекта HumanBeing
 */
public class OrganizationReader {
    /**
     * Ссылка на объект типа User
     */
    static User user;
    /**
     * Менеджер коллекции
     */
    CollectionManager collectionManager = new CollectionManager();

    /**
     * Конструктор класса
     *
     * @param user присваивает переданное значение полю user
     */
    public OrganizationReader(User user) {
        this.user = user;
    }

    /**
     * Метод, читающий данные из консоли.
     * Ввод полей происходит в строго определенном порядке
     *
     * @return возвращает объект типа HumanBeing
     */
    public Organization read() {
        Instant time = Instant.now();
        return new Organization(
                id(),
                readname(),
                readcoordinates(),
                readcreationDate(),
                readannualTurnover(),
                reademployeesCount(),
                readtype(),
                readPostalAddress()
        );
    }

    public int id() {
        return collectionManager.generateNewId();
    }

    /**
     * Метод читает name типа String из потока, указанного в User
     * При неправильном вводе запрашивает ввод поля снова
     *
     * @return значение name
     */
    public String readname() {
        String name;

        while (true) {
            user.printCommand("имя (не null):");
            name = user.readLine().trim();
            if (name.equals("")) {
                user.printError("Значение поля не может быть пустым или null");
            } else {
                return name;
            }
        }
    }

    /**
     * Метод, читающий координату X типа Float объекта Organization
     *
     * @return значение X
     */
    public Float readCoordinateX() {
        Float x;
        while (true) {
            try {
                user.printCommand("координата X (с плавающей запятой, не null):");
                x = Float.parseFloat(user.readLine().trim());
                if (x == null) {
                    throw new NullPointerException();
                } else {
                    return x;
                }
            } catch (NumberFormatException e) {
                System.err.println("Число должно быть типа Float");
            } catch (NullPointerException e) {
                System.out.println("Координата X не может быть null");
            }
        }
    }

    /**
     * Метод, читающий координату Y типа int объекта Organization
     *
     * @return значение Y
     */
    public int readCoordinateY() {
        int y;
        while (true) {
            try {
                user.printCommand("координата Y (целое число):");
                y = Integer.parseInt(user.readLine().trim());
                return y;
            } catch (NumberFormatException e) {
                System.err.println("Число должно быть целым");
            }
        }
    }

    /**
     * Метод, читающий координаты X и Y
     *
     * @return значение Coordinates
     */
    public Coordinates readcoordinates() {
        return new Coordinates(readCoordinateX(), readCoordinateY());
    }


    public Date readcreationDate() {
        while (true) {
            try {
                return new Date();
            } catch (DateTimeException e) {
                user.printError("временная проблема (проблема со временем)");
            }
        }
    }


    /**
     * Метод устанавливает годовой оборот организации.
     */
    public long readannualTurnover() {
        long annualTurnover;
        try {
            user.printCommand("Введите годовой оборот (целое число):");
            String string = user.readLine().trim();
            annualTurnover = Long.parseLong(string);
        } catch (NumberFormatException e) {
            System.err.println("Число должно быть целым");
            annualTurnover = 0; // Возвращаем значение по умолчанию в случае ошибки
        }
        return annualTurnover;
    }

    /**
     * Метод считывает количество сотрудников организации
     * @return количество сотрудников
     */
    public Long reademployeesCount() {
        Long count;
        while (true) {
            try {
                user.printCommand("количество сотрудников (целое число, не null, больше 0):");
                count = Long.parseLong(user.readLine().trim());
                if (count <= 0) {
                    throw new IllegalArgumentException();
                } else {
                    return count;
                }
            } catch (NumberFormatException e) {
                System.err.println("Число должно быть целым и не null");
            } catch (IllegalArgumentException e) {
                System.out.println("Количество сотрудников должно быть больше 0");
            }
        }
    }

    /**
     * Метод считывает тип организации
     * @return тип организации
     */
    public OrganizationType readtype() {
        String string;
        OrganizationType type = null;
        while (true) {
            try {
                user.printCommand("выбери тип организации из списка: \n " +
                        "коммерческая, государственная, акционерное общество: ");
                string = user.readLine().trim();
                if (string.equals("")) {
                    throw new IllegalArgumentException();
                } else {
                    if (string.equals("коммерческая"))
                        type = OrganizationType.COMMERCIAL;
                    else if (string.equals("государственная"))
                        type = OrganizationType.PUBLIC;
                    else if (string.equals("акционерное общество"))
                        type = OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                    return type;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("тип организации не может быть пустым и null");
            }
        }
    }

    public Address readPostalAddress() {
        String zipCode = null;
        Location town = null;

        try {
            user.printCommand("Введите почтовый индекс: ");
            zipCode = user.readLine().trim();

            int x, z;
            Float y;

            user.printCommand("Введите координату x: ");
            x = Integer.parseInt(user.readLine().trim());

            user.printCommand("Введите координату y: ");
            y = Float.parseFloat(user.readLine().trim());

            user.printCommand("Введите координату z: ");
            z = Integer.parseInt(user.readLine().trim());

            town = new Location(x, y, z);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка при чтении координат.");
        }

        return new Address(zipCode, town);
    }
}











