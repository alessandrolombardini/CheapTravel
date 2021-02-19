package project.CheapTravel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.http.ParseException;
import org.telegram.telegrambots.meta.api.objects.Update;


public class Maps implements CommandInterface {

    public Maps() { }

    public void doYourJob(Update update) { }
    
    @Override
    public void doYourJob(Update update, String command) {
        String[] message = update.getMessage().getText().split(" ");
        try {
            request(update, message[1], message[2]);
        } catch (ParseException | InterruptedException | IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        } 
    }
    
    public void request(final Update update, final String fromCityName, final String date) throws InterruptedException, ParseException, IOException, org.json.simple.parser.ParseException {
        System.out.println("ciao");
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<WebRequest> list = new ArrayList<>();
        final Integer fromCityID = new JSONAgent().extractID(fromCityName).get();
        
        for(Integer elem: new JSONAgent().exctractLink(fromCityID).get()){
            list.add(new WebRequest("https://shop.flixbus.it/search?departureStation=&arrivalStation=&"
                                    + "departureCity=" + fromCityID.toString()
                                    + "&arrivalCity=" + elem.toString()
                                    + "&rideDate=" + date));
        }
        list.stream().forEach(elem -> executor.submit(elem));
        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            throw new IllegalThreadStateException();
        }
        //new UserAgent().sendMessage("Route: Cesena - Rimini\nPrice: 7,90 EUR\nDeparture time: 12.30\nJourney time: 0.30 hours", update.getMessage().getChatId());
        //new UserAgent().sendMessage("Route: Cesena - Modena\nPrice: 8,20 EUR\nDeparture time: 1.30\nJourney time: 3.30 hours", update.getMessage().getChatId());
        //new UserAgent().sendMessage("Route: Cesena - Verona\nPrice: 8,50 EUR\nDeparture time: 6.30\nJourney time: 4.30 hours", update.getMessage().getChatId());
        //new UserAgent().sendMessage("Route: Cesena - Milano\nPrice: 11,90 EUR\nDeparture time: 16.45\nJourney time: 4.30 hours", update.getMessage().getChatId());
        //new UserAgent().sendMessage("Route: Cesena - Firenze\nPrice: 16,90 EUR\nDeparture time: 19.40\nJourney time: 2.30 hours", update.getMessage().getChatId());
        //new UserAgent().sendMessage("Route: Cesena - Trieste\nPrice: 36,40 EUR\nDeparture time: 5.30\nJourney time: 6.30 hours", update.getMessage().getChatId());
        //new UserAgent().sendMessage("Route: Cesena - Bari\nPrice: 43,90 EUR\nDeparture time: 9.30\nJourney time: 9.30 hours", update.getMessage().getChatId());
        list.stream().map(elem -> elem.getRespone().get()).forEach(elem -> new UserAgent().sendMessage("TO DO", update.getMessage().getChatId()));
        // System.out.println(new HttpParser().getPrice(list.get(0).getRespone().get()).toString());
    }
}
