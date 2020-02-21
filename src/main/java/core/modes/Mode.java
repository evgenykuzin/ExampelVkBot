package core.modes;

import com.vk.api.sdk.objects.messages.Message;

public interface Mode {
    void action(Message message);
    Mode nextMode();
}
