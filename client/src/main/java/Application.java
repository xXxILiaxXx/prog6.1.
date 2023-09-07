
import commands.*;
import io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.NoSuchElementException;

/**
 * Класс, через который производится запуск данного приложения.
 */
public class Application {
    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();
    /**
     * Хранит ссылку на объект, производящий чтение и вывод команд
     */
    User user;
    /**
     * Объект, управляющий выполнением команд
     */
    CommandManager commandManager;

    private final int port;

    /**
     * Конструктор класса Application
     *
     * @param port порт для подключения
     */
    public Application(Integer port) {
        this.port = port;

        user = new User();
        commandManager = new CommandManager(user);
        rootLogger.info("Конструктор класса Application был загружен.");

    }

    /**
     * Метод, выполняющий запуск программы и управляющий её работой.
     */
    public void start() {

        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", port);

            // Создание объектов для работы с клиентским подключением
            ClientConnection clientConnection = new ClientConnection();
            clientConnection.connect(inetSocketAddress);
            ResponseSender responseSender = new ResponseSender(clientConnection.getClientChannel());
            RequestReader requestReader = new RequestReader(clientConnection.getClientChannel());
            CommandProcessor commandProcessor = new CommandProcessor(commandManager);

            rootLogger.info("Клиент готов к чтению команд.");
            boolean isConnected = true;
            boolean isNeedInput = true;
            boolean isCommandAcceptable = false;

            String line = "";

            while (isConnected) {
                if (isNeedInput) {
                    System.out.println("Введите название команды:");
                    user.PrintPreamble();
                    line = user.readLine();
                    isCommandAcceptable =  commandProcessor.executeCommand(line);
                }
                try {
                    if (isCommandAcceptable) {
                        // Отправка команды на сервер
                        responseSender.sendContainer(commandManager.getLastCommandContainer(), inetSocketAddress);
                        rootLogger.info("Данные были отправлены.");

                        // Получение ответа от сервера
                        ByteBuffer byteBuffer = requestReader.receiveBuffer();
                        byteBuffer.flip();
                        rootLogger.info("Данные были получены.");
                        System.out.println(new String(byteBuffer.array(), StandardCharsets.UTF_8).trim() + "\n");

                        isNeedInput = true;
                    }
                } catch (PortUnreachableException | SocketTimeoutException ex) {
                    if (ex instanceof PortUnreachableException) {
                        rootLogger.warn("Порт " + port + " не доступен. Повторить отправку команды? y/n");
                    } else {
                        rootLogger.warn("Сервер не отвечает. Повторить отправку команды? y/n");
                    }
                    String result = user.readLine().trim().toLowerCase(Locale.ROOT).split("\\s+")[0];
                    if (result.equals("n")) {
                        rootLogger.info("Завершение работы клиента");
                        isConnected = false;
                    } else {
                        isNeedInput = false;
                    }
                }
            }
        } catch (NoSuchElementException ex) {
            rootLogger.error("\nАварийное завершение работы.");
        } catch (SocketException ex) {
            rootLogger.error("Ошибка подключения сокета к порту, или сокет не может быть открыт."
                    + ex.getMessage() + "/n" + "localhost" + " ; " + port);
        } catch (IllegalArgumentException ex) {
            rootLogger.error("Порт не принадлежит ОДЗ: " + port);
        } catch (Exception ex) {
            rootLogger.error("Неизвестная ошибка. Следует починить." + ex);
        }
    }
}