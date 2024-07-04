/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.io;

import com.estg.pickingManagement.Vehicle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pp_er.core.ContainerTypes;
import pp_er.pickingManagement.VehicleImp;

/**
 *
 * @author emanu
 */
public class JsonReader {
 
     private JSONParser parser;

  
    public JsonReader() {
        this.parser =  new JSONParser();
    }
    
    public void readTypes(ContainerTypes containerTypes) throws FileNotFoundException, IOException, ParseException{
     FileReader reader = new FileReader(".\\files\\types.json");
    
           Object obj= parser.parse(reader);
           JSONObject jobj= (JSONObject)obj;
           
          JSONArray array =  (JSONArray)jobj.get("types");
          
   
    
    }

        
}
