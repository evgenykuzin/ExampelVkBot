package core.modules;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vk.api.sdk.exceptions.ClientException;
import vk.VKServer;

public class MyJSONParser {

    public static int getUserIdByScreenName(String screenName) {
        String userJson;
        String id = "";
        try {
            userJson = VKServer
                    .vkCore
                    .getVk()
                    .users()
                    .get(VKServer.vkCore.getActor())
                    .userIds(screenName).executeAsString();
            JsonObject jobj = new JsonParser().parse(userJson).getAsJsonObject();
            JsonArray jarr = jobj.getAsJsonArray("response");
            id = jarr.get(0).getAsJsonObject().get("id").getAsString();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(id);
    }

    public static String getUserRealNameById(String id){
        String userJson;
        String name = "";
        try {
            userJson = VKServer
                    .vkCore
                    .getVk()
                    .users()
                    .get(VKServer.vkCore.getActor())
                    .userIds(id).executeAsString();
            JsonObject jobj = new JsonParser().parse(userJson).getAsJsonObject();
            JsonArray jarr = jobj.getAsJsonArray("response");
            name = jarr.get(0).getAsJsonObject().get("first_name").getAsString() +
                    " " +
                    jarr.get(0).getAsJsonObject().get("last_name").getAsString();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return name;

    }
}
