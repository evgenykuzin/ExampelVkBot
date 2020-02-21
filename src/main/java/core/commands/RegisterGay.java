package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.modules.GayList;
import core.modules.MyJSONParser;
import vk.VKManager;

public class RegisterGay extends Command {
    GayList gayList;
    public RegisterGay(String name, GayList gayList) {
        super(name);
        this.gayList = gayList;
    }

    @Override
    public void exec(Message message) {
        Integer userID = message.getUserId();
        gayList.addGay(userID);
        new VKManager().sendMessage("вы (https://vk.com/id" +
                message.getUserId() +
                ") зарегистрированы", message.getUserId());
        System.out.println("новый пользователь: " + MyJSONParser.getUserRealNameById(message.getUserId()+""));
        gayList.printUserNames();
    }

    @Override
    public String description() {
        return "регистрирует в рассылке";
    }
}
