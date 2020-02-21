package core;

import com.vk.api.sdk.objects.messages.Message;
import core.commands.ChatMessage;
import core.commands.Unknown;
import core.modes.Mode;
import core.modes.ModesManager;
import core.modules.GayList;

import java.util.Collection;

/**
 * Определяет команду
 *
 * @author Артур Куприянов
 * @version 1.1.0
 */
public class CommandDeterminant {


    public static Command getCommand(Collection<Command> commands, Message message) {
        String body = message.getBody();

        for (Command command : commands) {
            if (command.name.equals(body.split(" ")[0].toLowerCase())) {
                return command;
            }
        }
        if (GayList.userInChat(message.getUserId())){
            int id = 1;
            int friendId = 1;
            if (GayList.getChatsList().containsValue(message.getUserId())){
                id = message.getUserId();
                friendId = GayList.getKeyPairFromChat(id);
            } else if ((GayList.getChatsList().containsKey(message.getUserId()))){
                id = message.getUserId();
                friendId = GayList.getValuePairFromChat(id);
            }
            return new ChatMessage(id+":"+friendId);
        } else return new Unknown("unknown");
    }

}
