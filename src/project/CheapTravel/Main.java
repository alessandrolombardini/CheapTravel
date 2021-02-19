package project.CheapTravel;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.ApiContextInitializer;


public class Main {

    public static void main(final String[] args) {
        
        final TelegramBotsApi botsApi = new TelegramBotsApi();
        ApiContextInitializer.init();
        
        try {
            botsApi.registerBot(new CheapTravelBot());
        } catch (TelegramApiRequestException e) {
            System.out.println(e.toString() + " - bot non available!");
        }
    }

}
