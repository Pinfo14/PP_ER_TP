/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.io;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.ContainerType;
import com.estg.core.Institution;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.core.exceptions.VehicleException;
import com.estg.pickingManagement.Vehicle;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pp_er.core.AidBoxImp;
import pp_er.core.ContainerImp;
import pp_er.core.ContainerTypeImp;
import pp_er.core.ContainerTypes;
import pp_er.core.InstitutionImp;
import pp_er.core.MeasurementImp;
import pp_er.pickingManagement.VehicleCargo;
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
    
    public void readTypes(Institution instn) throws FileNotFoundException, IOException, ParseException{
     FileReader reader = new FileReader("src/pp_er/files/types.json");
    
           Object obj= parser.parse(reader);
           JSONObject jobj= (JSONObject)obj;
           
          JSONArray array =  (JSONArray)jobj.get("types");
          ContainerTypes cts = new ContainerTypes();
          
          for(int i=0;i<array.size();i++){
              String newType = (String)array.get(i);
               ContainerType ct = new ContainerTypeImp(newType);
               cts.addType(ct);
               
          }
          ((InstitutionImp)instn).addContainerTypes(cts);

    }

    private AidBox[] getAidBoxesFrominst(Institution instn){
        return instn.getAidBoxes();
    }

    private Container[] getContainers(AidBox aidbox){    
        return aidbox.getContainers();
}

    public void readAidBoxes(Institution instn) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/aidBoxes.json");

        JSONArray array = (JSONArray) parser.parse(reader);

        for (Object obj : array) {
            JSONObject aidBox = (JSONObject) obj;
            String code = (String) aidBox.get("code");
            JSONArray containers = (JSONArray) aidBox.get("containers");

            for (Object containerCode : containers) {
                String container = (String) containerCode;
                AidBox aidBoxObj = new AidBoxImp(code); 
                try {
                    instn.addAidBox(aidBoxObj);
                } catch (AidBoxException ex) {
                    //Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void readVehicles(Institution instn) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/vehicles.json");

        JSONArray array = (JSONArray) parser.parse(reader);

        for (Object obj : array) {
            JSONObject vehicle = (JSONObject) obj;
            String code = (String) vehicle.get("code");
            JSONObject capacities = (JSONObject) vehicle.get("capacity");

            Vehicle vehicleObj = new VehicleImp(code); 
            for (Object key : capacities.keySet()) {
                String type = (String) key;
                int capacity = ((Long) capacities.get(type)).intValue();
                ContainerType containerType = new ContainerTypeImp(type);
                VehicleCargo vehicleCargo = new VehicleCargo(containerType, capacity);
                ((VehicleImp)vehicleObj).addCargo(vehicleCargo);
            }
            try {
                instn.addVehicle(vehicleObj);
            } catch (VehicleException ex) {
                Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void readContainers(Institution instn) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/containers.json");
            AidBox[] aidboxes = getAidBoxesFrominst(instn);
        JSONArray array = (JSONArray) parser.parse(reader);

        for (Object obj : array) {
            JSONObject container = (JSONObject) obj;
            String code = (String) container.get("code");
            int capacity = ((Long) container.get("capacity")).intValue();
            String type = (String) container.get("type");
            ContainerType containerType = new ContainerTypeImp(type);
            Container containerObj = new ContainerImp(code, containerType,capacity); 
            for(AidBox aidbox: aidboxes){
                try {
                    aidbox.addContainer(containerObj);
                } catch (ContainerException ex) {
                   // Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
          
        }
    }

     public void readReadings(Institution instn) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/readings.json");
 AidBox[] aidboxes = getAidBoxesFrominst(instn);
        JSONArray array = (JSONArray) parser.parse(reader);
Container [] containers;
        for (Object obj : array) {
            JSONObject reading = (JSONObject) obj;
            String containerCode = (String) reading.get("contentor");
            LocalDateTime date = LocalDateTime.parse((String) reading.get("data"));
            int value = ((Long) reading.get("valor")).intValue();
            Measurement measurement = new MeasurementImp(date, value); // Assuming MeasurementImp is an implementation of Measurement
           for(AidBox aidbox:aidboxes ){
                containers = getContainers(aidbox);
                for(Container cntr: containers){
                    try {
                        instn.addMeasurement(measurement, cntr);
                    } catch (ContainerException ex) {
                        //Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MeasurementException ex) {
                        //Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
           }
        }
    }

    public void readDistances(Institution instn) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/distances.json");
 AidBox[] aidboxes = getAidBoxesFrominst(instn);
        JSONArray array = (JSONArray) parser.parse(reader);

        for (Object obj : array) {
            JSONObject distanceObj = (JSONObject) obj;
            String from = (String) distanceObj.get("from");
            JSONArray toArray = (JSONArray) distanceObj.get("to");

            for (Object toObj : toArray) {
                JSONObject to = (JSONObject) toObj;
                String toName = (String) to.get("name");
                int distance = ((Long) to.get("distance")).intValue();
                int duration = ((Long) to.get("duration")).intValue();
                
                for(AidBox aidbox: aidboxes){
               
            }
                
                instn.addDistance(from, toName, distance, duration);
            }
        }
    }
}
