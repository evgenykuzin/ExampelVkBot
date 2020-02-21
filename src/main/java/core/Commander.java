package core;

import com.vk.api.sdk.objects.messages.Message;
import core.modes.Mode;
import core.modes.ModesManager;

public class Commander {

    /**
     * Обработка сообщений, получаемых через сервис Вконтакте. Имеет ряд дополнительной информации.
     * @param message сообщение (запрос) пользователя
     */
    public static void execute(Message message){
        ModesManager modesManager = new ModesManager();
        CommandManager commandManager = new CommandManager(modesManager);
        if (modesManager.hasMode()) {
            Mode mode = modesManager.getMode();
            while (mode != null) {
                mode.action(message);
                mode = mode.nextMode();
            }
        }
        CommandDeterminant.getCommand(commandManager.getCommands(), message).exec(message);

    }

}
