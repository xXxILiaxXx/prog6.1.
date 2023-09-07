//package org.example.Command;
//erick privet
//import org.example.data.CollectionManager;
//
///**
// * Класс команды, которая считает количество элементов, значение поля type которых больше заданного
// */
//public class CountGreaterThanTypeCommand implements CommandWithArguments {
//    /**
//     * Ссылка на объект класса CollectionManager
//     */
//    private CollectionManager collectionManager;
//    /**
//     * Поле хранит массив аргументов команды
//     */
//    private String[] commandArguments;
//
//    /**
//     * Конструктор класса
//     * @param collectionManager - менеджер коллекции с которым работает команда
//     */
//    public CountGreaterThanTypeCommand(CollectionManager collectionManager) {
//        this.collectionManager = collectionManager;
//    }
//
//    /**
//     * Метод, приводящий команду в действие
//     */
//    @Override
//    public void execute() {
//        try {
//            if (commandArguments.length < 1) {
//                System.err.println("Не указан аргумент команды");
//                return;
//            }
//
//            String type = commandArguments[0];
//            int count = collectionManager.countGreaterThanType(type);
//            System.out.println("Количество элементов с полем type больше " + type + ": " + count);
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }
//
//    /**
//     * Метод, получающий аргументы команды
//     * @param commandArguments Аргументы команды.
//     */
//    @Override
//    public void getCommandArguments(String[] commandArguments) {
//        this.commandArguments = commandArguments;
//    }
//
//    /**
//     * @return описание команды
//     */
//    @Override
//    public String getDescription() {
//        return "вывести количество элементов, значение поля type которых больше заданного";
//    }
//}