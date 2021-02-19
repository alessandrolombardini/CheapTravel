package project.CheapTravel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JSONAgent {

    private static final String CITY_ID_FILE_NAME = "cityInfo.json";
    private static final String MAPS_FILE_NAME = "maps.json";
    private static final String RELATIVE_PATH_RES = System.getProperty("file.separator")
                                            + "res"
                                            + System.getProperty("file.separator");
    private static final String PATH_CITY_FILE = System.getProperty("user.dir") + RELATIVE_PATH_RES + CITY_ID_FILE_NAME; 
    private static final String PATH_MAPS_FILE = System.getProperty("user.dir") + RELATIVE_PATH_RES + MAPS_FILE_NAME; 
    
    private final JSONObject jsonObjectCity;
    private final JSONObject jsonObjectMaps;
    
    public JSONAgent() throws FileNotFoundException, IOException, ParseException {
        // Open JSON files and import them
        this.jsonObjectCity = (JSONObject) new JSONParser().parse(new FileReader(PATH_CITY_FILE));
        this.jsonObjectMaps = (JSONObject) new JSONParser().parse(new FileReader(PATH_MAPS_FILE));
    }
    /**
     * 
     * @param id of the city I want link
     * @return all ID of link of city
     * @throws FileNotFoundException file of link is not available
     * @throws IOException error reading file
     * @throws ParseException error parsing JSON file
     */
    final public Optional<List<Integer>> exctractLink(Integer id) throws FileNotFoundException, IOException, ParseException{
        List<Integer> result = new LinkedList<>();
        /*
         * Extract firsts elements instantly reacheble, cities linked directly in JSON with input city
         * 
         * *this file JSON is not made very well, so I have to do very awfull things
         */
        JSONArray cityArray = (JSONArray) jsonObjectMaps.get(id.toString());
        if (cityArray.listIterator().hasNext()) {
            for(Object elem: ((HashMap)cityArray.listIterator().next()).keySet()) {
                result.add(Integer.parseInt(elem.toString()));
            }
        }
        /* 
         * Analise every link of every city of the map and verify if input city is linked with it
         */
        for (Object elem :jsonObjectMaps.keySet()) {
            JSONArray subArray = (JSONArray) jsonObjectMaps.get(elem.toString());
            if (subArray.listIterator().hasNext()) {
                if(((HashMap) subArray.listIterator().next()).keySet().contains(id.toString())) {
                    result.add(Integer.parseInt(elem.toString()));
                }
            }
        }
        return Optional.ofNullable(result);
    }
    
    /**
     * 
     * @param city I want ID
     * @return  return ID of parameter city
     * @throws FileNotFoundException file of city'info is not available
     * @throws IOException  error reading file
     * @throws ParseException   error parsing JSON file
     */
    final public Optional<Integer> extractID(String city) throws FileNotFoundException, IOException, ParseException {
        // Find requested object and return correct id
        JSONArray cityArray = (JSONArray) this.jsonObjectCity.get("city");
        Iterator<JSONObject> iterator = cityArray.iterator();
        while (iterator.hasNext()) {
            JSONObject obj = (JSONObject) iterator.next();
            if(obj.get("name").equals(city)) {
                return Optional.ofNullable(Integer.parseInt((String) obj.get("id")));
            }
        }  
        return Optional.empty();
    }
}
