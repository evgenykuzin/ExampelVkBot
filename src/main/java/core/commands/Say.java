package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import vk.VKManager;

public class Say extends Command {
    public Say(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {

        new VKManager().sendMessage(say(message), message.getUserId());
    }

    @Override
    public String description() {
        return "повторяет за вами ";
    }

    private String say(Message message){
        return message.getBody().substring(name.length());
    }
}
