package project.CheapTravel;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * I extend CheapTravelBot because it extends TelegramLongPollingBot and this is the only way
 * to send a message, photo or something else to users.
 * 
 * This class is made only to send everything I need to users, first of all messages.
 * @author user
 *
 */
public class UserAgent extends CheapTravelBot{
    
    public void sendMessage(final String text, final long chatID) {
        SendMessage message = new SendMessage().setChatId(chatID)
                                               .setText(text);
        try {
            execute(message); 
        } catch (TelegramApiException e) {
            System.out.println("error sending message");
        }
    }
}
