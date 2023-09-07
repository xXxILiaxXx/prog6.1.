package commands;

/**
 * Класс команды, которая выводит элементы, значение поля name которых начинается с заданной подстроки
 */
//public class FilterStartsWithNameCommand implements CommandWithArguments {
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
//     *
//     * @param collectionManager - менеджер коллекции с которым работает команда
//     */
//    public FilterStartsWithNameCommand(CollectionManager collectionManager) {
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
//            String substring = commandsWithoutArguments[0];
//            List<Organization> filteredOrganizations = collectionManager.filterStartsWithName(substring);
//            if (filteredOrganizations.isEmpty()) {
//                System.out.println("Нет элементов, значение поля name которых начинается с подстроки: " + substring);
//            } else {
//                System.out.println("Элементы, значение поля name которых начинается с подстроки: " + substring);
//                for (Organization organization : filteredOrganizations) {
//                    System.out.println(organization);
//                }
//            }
//        } catch (Exception e) {
//            System.err.println(e);
//        }
//    }
//
//    /**
//     * Метод, получающий аргументы команды
//     *
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
//        return "вывести элементы, значение поля name которых начинается с заданной подстроки";
//    }
//}