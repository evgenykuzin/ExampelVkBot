package core;

import com.vk.api.sdk.objects.messages.Message;
import core.commands.*;
import core.modes.Mode;
import core.modes.ModesManager;
import core.modules.EmailSender;
import core.modules.GayList;
import vk.VKCore;
import vk.VKManager;

import java.util.HashSet;

/**
 * @author Arthur Kupriyanov
 */
public class CommandManager {
    private HashSet<Command> commands = new HashSet<>();
    private ModesManager modesManager;
    public CommandManager(ModesManager modesManager){
        this.modesManager = modesManager;
        GayList gayList = new GayList();
        commands.add(new CommandsInfo("команды"));
        commands.add(new CommandsInfo("начать"));
        commands.add(new Unknown("unknown"));
        commands.add(new Weather("погода"));
        commands.add(new Say("скажи"));
        commands.add(new RegisterGay("регистрация", gayList));
        commands.add(new GayGame("рассылка", gayList));
        commands.add(new GayChat("чат"));
        commands.add(new Command("пользователи") {
            @Override
            public void exec(Message message) {
                for (String user : gayList.getUserNames()){
                    new VKManager().sendMessage(user, message.getUserId());
                }
            }

            @Override
            public String description() {
                return "выводит список пользователей";
            }
        });
        commands.add(new Command("письмо") {
            @Override
            public void exec(Message message) {
                String addressee = message.getBody().split(" ")[1];
                String text = message.getBody().split(" ")[2];
                EmailSender.send(addressee, text);
            }

            @Override
            public String description() {
                return "отправить письмо адресату";
            }
        });
        commands.add(new Command("инфа") {
            @Override
            public void exec(Message message) {
                new VKManager().sendMessage("enter name:", message.getUserId());
                Mode mode = new Mode() {
                    StringBuilder info = new StringBuilder();
                    @Override
                    public void action(Message message) {
                        info.append("name is ").append(message.getBody()).append("\n");
                        System.out.println(info);
                        new VKManager().sendMessage("enter age:", message.getUserId());
                    }
                    @Override
                    public Mode nextMode() {
                        return new Mode() {
                            @Override
                            public void action(Message message) {
                                info.append("age is ").append(message.getBody()).append("\n");
                                System.out.println(info);
                                new VKManager().sendMessage(info.toString(), message.getUserId());
                            }

                            @Override
                            public Mode nextMode() {
                                return null;
                            }
                        };
                    }
                };
                modesManager.setMode(mode);
            }

            @Override
            public String description() {
                return "сбор информации";
            }
        });
    }

    public  HashSet<Command> getCommands(){
        return commands;
    }
    public  void addCommand(Command command) { commands.add(command);}
}
