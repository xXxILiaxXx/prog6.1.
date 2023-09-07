package commands;

/**
 * Класс команды, которая удаляет из коллекции один элемент, значение поля type которого эквивалентно заданному
 */
//public class RemoveAnyByTypeCommand implements CommandWithArguments {
//    /**
//     * Ссылка на объект класса CollectionManager
//     */
//    private CollectionManager collectionManager;
//    /**
//     * Поле хранит массив аргументов команды
//     */
//    private String[] commandsWithoutArguments;
//
//    /**
//     * Конструктор класса
//     * @param collectionManager - менеджер коллекции с которым работает команда
//     */
//    public RemoveAnyByTypeCommand(CollectionManager collectionManager) {
//        this.collectionManager = collectionManager;
//    }
//
//    /**
//     * Метод, приводящий команду в действие
//     */
//    @Override
//    public void execute() {
//        try {
//            if (commandsWithoutArguments.length < 1) {
//                System.err.println("Не указан аргумент команды");
//                return;
//            }
//
//            String type = commandsWithoutArguments[0];
//            Organization removedOrganization = collectionManager.removeAnyByType(type);
//
//            if (removedOrganization != null) {
//                System.out.println("Удален элемент с полем type эквивалентным " + type);
//            } else {
//                System.out.println("Элемент с полем type эквивалентным " + type + " не найден в коллекции");
//            }
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
//        this.commandsWithoutArguments = commandArguments;
//    }
//
//    /**
//     * @return описание команды
//     */
//    @Override
//    public String getDescription() {
//        return "удалить из коллекции один элемент, значение поля type которого эквивалентно заданному";
//    }
//}