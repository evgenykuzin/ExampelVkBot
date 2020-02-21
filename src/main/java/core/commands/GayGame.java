package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.modules.GayList;
import core.modules.MyJSONParser;
import vk.VKManager;

import java.util.Random;

public class GayGame extends Command {
    GayList gayList;
    public GayGame(String name, GayList gayList) {
        super(name);
        this.gayList = gayList;
    }

    @Override
    public void exec(Message message) {
       for (int id : gayList.getGayList()){
           new VKManager().sendMessage(message.getBody(), id);
       }
    }

    @Override
    public String description() {
        return "рассылает инфоррмацию всем зарегистрированным пользователям";
    }

    public int detect(){
        Random random = new Random();
        return gayList.getGay(random.nextInt(gayList.size()));
    }
}
