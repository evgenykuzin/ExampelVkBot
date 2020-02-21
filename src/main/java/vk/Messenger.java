package vk;

import com.vk.api.sdk.objects.messages.Message;
import core.Commander;
import core.modules.MyJSONParser;

public class Messenger implements Runnable{

    private Message message;

    public Messenger(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        System.out.println(MyJSONParser.getUserRealNameById(message.getUserId()+"")+" написал команду: "+message.getBody());
        Commander.execute(message);
    }

}
