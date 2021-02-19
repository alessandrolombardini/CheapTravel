package project.CheapTravel;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ThreadCommandRequestAgent implements Runnable {

    /* List of commands */
    final static Map<String, CommandInterface> COMMAND_LIST = new LinkedHashMap<>();
    static {
        COMMAND_LIST.put(new String("/maps"), new Maps());
    }
    /* History of users's command */
    final static Map<Long, Optional<String>> NEXT_COMMAND = new LinkedHashMap<>();
    
    final Update update;
    
    public ThreadCommandRequestAgent(final Update update) {
        this.update = update;
    }

    public void run() {
        final String message = this.update.getMessage().getText();
        final long chatID = this.update.getMessage().getChatId();
        final String possibleCommand = message.split(" ")[0];
        System.out.println(possibleCommand);
        /* If message contains a command it will be executed */
        COMMAND_LIST.get(possibleCommand).doYourJob(this.update, possibleCommand);
        
        /* If message doesn't contain a command maybe this BOT was waiting something, look at the last user' command */
        if(NEXT_COMMAND.containsKey(chatID) && NEXT_COMMAND.get(chatID).isPresent()) {
            String nextCommand = NEXT_COMMAND.get(chatID).get();
            COMMAND_LIST.get(nextCommand).doYourJob(this.update, nextCommand);
        }
        System.out.println("ciao");
        /* Else we say sorry */
    }
}
