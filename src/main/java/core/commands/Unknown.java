package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.CommandManager;
import core.modes.ModesManager;
import vk.VKManager;

/**
 * @author Arthur Kupriyanov
 */
public class Unknown extends Command {

    public Unknown(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {

        new VKManager().sendMessage("неизвестная команда! вот список команд:\n"+comandsList(), message.getUserId());
    }

    @Override
    public String description() {
        return "неизвестная команда";
    }
    private String comandsList(){
        CommandManager commandManager = new CommandManager(new ModesManager());
        StringBuilder list = new StringBuilder();
        for (Command command : commandManager.getCommands()){
            if (!(command.name.equals("начать"))) {
                list.append(command.name).append(" -> ").append(command.description()).append("\n");
            }
        }
        return list.toString();
    }
}
