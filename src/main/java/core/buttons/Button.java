package core.buttons;

import com.google.gson.JsonObject;

public abstract class Button {

    public abstract void setAction();

    private void buildButton(){
        StringBuilder sb = new StringBuilder();
        sb.append("{ \n" +
                "    \"one_time\": false, \n" +
                "    \"buttons\": [");
        sb.append(appendInBrakets(appenInQuotes(appenInQuotes("one_time")+":false, \n")));
        JsonObject jsonObject = new JsonObject();
    }

    private String appenInQuotes(String obj){
        return "\""+obj+"\"";
    };

    private String appendInBrakets(String obj){
        return "{ \n "+obj+" \n }";
    }


}
