package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.CommandManager;
import core.modes.ModesManager;
import vk.VKManager;

public class CommandsInfo extends Command {
    public CommandsInfo(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage(comandsList(), message.getUserId());

    }

    @Override
    public String description() {
        return "выводит список команд";
    }

    private String comandsList(){
        CommandManager commandManager = new CommandManager(new ModesManager());
        StringBuilder list = new StringBuilder();
        for (Command command : commandManager.getCommands()){
            if (!(command.name.equals("начать")) && !command.name.equals("") && !command.name.equals("unknown")) {
                list.append(command.name).append(" -> ").append(command.description()).append("\n");
            }
        }
        return list.toString();
    }
}
