package core.modes;

import com.vk.api.sdk.objects.messages.Message;
import vk.VKManager;

import java.lang.reflect.Member;
import java.util.ArrayList;

public class ModesManager {
    private ArrayList<Mode> modes;
    private Mode mode = null;
    public ModesManager(){
        modes = new ArrayList<>();

    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Mode getMode(){
        return mode;
    }
    public boolean hasMode(){
        return mode != null;
    }

}
