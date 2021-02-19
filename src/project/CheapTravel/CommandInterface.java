package project.CheapTravel;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandInterface {
    
    public void doYourJob(Update update, String command);
    
    public void doYourJob(Update update);
    
}
