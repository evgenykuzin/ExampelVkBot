package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.modules.GayList;
import vk.VKManager;

import java.util.ArrayList;
import java.util.Random;

public class GayChat extends Command {
    public GayChat(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        int id = message.getUserId();
        new VKManager().sendMessage("ищем собеседника...", id);
        GayList.addUserToWaitingChatList(id);
        boolean friendFound = false;
        int friendId;
       // while (!friendFound) {
            try {
                if (GayList.getChatsList().containsValue(id)){
                    friendId = GayList.getKeyPairFromChat(id);
                    friendFound = friendId != 0;
                } else {
                    friendId = getChatFriendId(id);
                    friendFound = friendId != 0;
                    GayList.addPairToChat(id, friendId);
                }
                if (friendFound) {
                    new VKManager().sendMessage("вы нашли собеседника!", friendId);
                    //break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
       // }
    }

    @Override
    public String description() {
        return "запускает чат со случайным анонимом. (напиши 'выйти', чтобы выйти)";
    }

    private int getChatFriendId(int thisId) {
        Random random = new Random();
        ArrayList<Integer> chatList = GayList.getWaitingChatList();
        System.out.println("chatList = " + chatList.toString());
        long startTime = System.currentTimeMillis();
        while (true) {
            int friendId = chatList.get(random.nextInt(chatList.size()));
            if (friendId != thisId) {
                return friendId;
            }
            if (System.currentTimeMillis() - startTime >= 2*60*1000){
                new VKManager().sendMessage("никто с тобой не хочет болтать( напиши позже...", thisId);
                return 0;
            }
        }
    }


}
