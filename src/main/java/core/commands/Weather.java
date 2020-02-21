package core.commands;

import com.vk.api.sdk.objects.messages.Message;
import core.Command;
import core.modules.WeatherParser;
import vk.VKManager;

import java.io.IOException;

/**
 * @author Arthur Kupriyanov
 */
public class Weather extends Command implements ServiceCommand {
    public Weather(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        String city = "saint_petersburg";
        if (message.getBody().length() > name.length()) {
            city = message.getBody().substring(name.length() + 1);
            System.out.println("city = " + city);
        }
        new VKManager().sendMessage(getWeather(city), message.getUserId());
    }

    @Override
    public String description() {
        return "высылает погоду на сегодняшний день";
    }

    @Override
    public void service() {

    }

    private String getWeather(String city){
        String weather;
        try {
            if (city.equals("")){
                weather = new WeatherParser().getWeatherTodayDescription();
            } else weather = new WeatherParser(city).getWeatherTodayDescription();
        } catch (IOException e) {
            weather = "не удалось получить погоду";
        }

        return weather;
    }
}
