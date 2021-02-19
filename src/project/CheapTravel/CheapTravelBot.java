package project.CheapTravel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CheapTravelBot extends TelegramLongPollingBot {

    private final static String username = "CheapTravel";
    private final static String token= "IS NOT PUBLIC";
    private final ExecutorService executor;
    
    public CheapTravelBot() { 
        executor = Executors.newCachedThreadPool();
    }

    @Override
    public void onUpdateReceived(final Update update) {
        /* Create a thread that manage input command, one for each user request */
        final ThreadCommandRequestAgent agent = new ThreadCommandRequestAgent(update);
        this.executor.submit(agent);   
    }
    
    @Override
    public String getBotToken() {
        return CheapTravelBot.token;
    }
    
    @Override
    public String getBotUsername() {
        return CheapTravelBot.username;
    }
}
