package commands;

import collection.CollectionManager;
import collection.Organization;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;
import file.OrganizationReader;

import java.io.PrintStream;
import java.util.ArrayList;

public class AddCommand extends Command {

    private CollectionManager collectionManager;

    private OrganizationReader organizationReader;

    public AddCommand(CollectionManager collectionManager, OrganizationReader organizationReader) {
        this.collectionManager = collectionManager;
        this.organizationReader = organizationReader;
    }

    public AddCommand(OrganizationReader organizationReader) {
        super("add");
        this.organizationReader = organizationReader;
    }

    @Override
    public void execute(String[] arguments, InvocationStatus invocationEnum, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationEnum.equals(InvocationStatus.CLIENT)) {
            result = new ArrayList<>();
            if (arguments.length > 0) {
                throw new CannotExecuteCommandException("У этой команды нет аргументов");
            }
            Organization organization = organizationReader.read();
            super.result.add(organization);
        } else if (invocationEnum.equals(InvocationStatus.SERVER)) {

            Organization organization = (Organization) this.getResult().get(0);
            collectionManager.add(organization);
        }
    }

    @Override
    public String getDescription() {
        return "добавляет новый элемент в коллекцию";
    }
}
