package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.modules.GayList;
import vk.VKManager;

public class ChatMessage extends Command {
    private int id = 1;
    private int friendId = 1;
    public ChatMessage(String name) {
        super(name);
        String[] ids = name.split(":");
        this.id = Integer.parseInt(ids[0]);
        this.friendId = Integer.parseInt(ids[1]);
    }

    @Override
    public void exec(Message message) {
        if (message.getBody().toLowerCase().contains("выйти")){
            new VKManager().sendMessage("вы вышли(", id);
            new VKManager().sendMessage("собеседник вышел(", friendId);
            GayList.exitFromChat(id, friendId);
        }

//        new VKManager().sendMessage(MyJSONParser.getUserRealNameById(id+"")+
//                " пишет "+
//                message.getBody().toLowerCase().replace("чат ", ""), friendId);
        new VKManager().sendMessage(message.getBody(), friendId);
    }

    @Override
    public String description() {
        return "";
    }
}
