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
import pp_er.core.Path;
import pp_er.exepcions.PathExeption;
import pp_er.pickingManagement.VehicleCargo;
import pp_er.pickingManagement.VehicleImp;

/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: LEIT4
 */
public class JsonReader {

    private JSONParser parser;

    /**
     * Constructor for JsonReader initializes the JSONParser.
     */
    public JsonReader() {
        this.parser = new JSONParser();
    }

    /**
     * Reads container types from a JSON file and adds them to the institution.
     *
     * @param instn the institution to add container types to
     * @throws FileNotFoundException if the JSON file is not found
     * @throws IOException           if an I/O error occurs
     * @throws ParseException        if the JSON parsing fails
     */
    public void readTypes(Institution instn) throws FileNotFoundException, IOException, ParseException {
        FileReader reader = new FileReader("src/pp_er/files/types.json");

        Object obj = parser.parse(reader);
        JSONObject jobj = (JSONObject) obj;

        JSONArray array = (JSONArray) jobj.get("types");
        ContainerTypes cts = new ContainerTypes();

        for (int i = 0; i < array.size(); i++) {
            String newType = (String) array.get(i);
            ContainerType ct = new ContainerTypeImp(newType);
            cts.addType(ct);
        }
        ((InstitutionImp) instn).addContainerTypes(cts);
    }

    /**
     * Helper method to get containers from an aid box.
     *
     * @param aidbox the aid box to get containers from
     * @return an array of containers
     */
    private Container[] getContainers(AidBox aidbox) {
        return aidbox.getContainers();
    }

    /**
     * Reads aid boxes from a JSON file and adds them to the institution.
     *
     * @param instn the institution to add aid boxes to
     * @throws FileNotFoundException if the JSON file is not found
     * @throws IOException           if an I/O error occurs
     * @throws ParseException        if the JSON parsing fails
     */
    public void readAidBoxes(Institution instn) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/aidBoxes.json");

        JSONArray array = (JSONArray) parser.parse(reader);

        for (Object obj : array) {
            JSONObject aidBox = (JSONObject) obj;
            String code = (String) aidBox.get("code");
            JSONArray containers = (JSONArray) aidBox.get("containers");
            AidBox newAidBox = new AidBoxImp(code);

            readContainers(containers, newAidBox);
            readDistances(newAidBox);
        }
    }

    /**
     * Reads vehicles from a JSON file and adds them to the institution.
     *
     * @param instn the institution to add vehicles to
     * @throws FileNotFoundException if the JSON file is not found
     * @throws IOException           if an I/O error occurs
     * @throws ParseException        if the JSON parsing fails
     */
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
                ((VehicleImp) vehicleObj).addCargo(vehicleCargo);
            }
            try {
                instn.addVehicle(vehicleObj);
            } catch (VehicleException ex) {
                // Handle the exception as needed
            }
        }
    }

    /**
     * Reads containers from a JSON file and adds them to the specified AidBox.
     *
     * @param containersArray the array of containers to update with readings
     * @param aidBox the AidBox to add containers to
     * @throws FileNotFoundException if the JSON file is not found
     * @throws IOException           if an I/O error occurs
     * @throws ParseException        if the JSON parsing fails
     */
    public void readContainers(JSONArray containersArray, AidBox aidBox) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/containers.json");
        JSONArray array = (JSONArray) parser.parse(reader);

        for (Object obj : containersArray) {
            JSONObject container = (JSONObject) obj;
            String containerCode = (String) container.get("code");

            for (Object objCont : array) {
                JSONObject containerPrime = (JSONObject) objCont;
                String code = (String) containerPrime.get("code");

                if (containerCode.equals(code)) {
                    int capacity = ((Long) containerPrime.get("capacity")).intValue();
                    String type = (String) containerPrime.get("type");
                    ContainerType containerType = new ContainerTypeImp(type);
                    Container containerObj = new ContainerImp(code, containerType, capacity);

                    try {
                        aidBox.addContainer(containerObj);
                    } catch (ContainerException ex) {
                        ex.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    /**
     * Reads readings from a JSON file and adds measurements to the appropriate containers.
     *
     * @param containersArray the array of containers to update with readings
     * @param istn the institution to update with readings
     * @throws FileNotFoundException if the JSON file is not found
     * @throws IOException           if an I/O error occurs
     * @throws ParseException        if the JSON parsing fails
     */
    public void readReadings(JSONArray containersArray, Institution istn) throws FileNotFoundException, IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader("src/pp_er/files/readings.json");
        JSONArray array = (JSONArray) parser.parse(reader);

        for (Object obj : array) {
            JSONObject reading = (JSONObject) obj;
            String containerCode = (String) reading.get("contentor");
            int value = ((Long) reading.get("valor")).intValue();
            LocalDateTime date = LocalDateTime.parse((String) reading.get("data"));
            Measurement measurement = new MeasurementImp(date, value);
            for (Object container : containersArray) {
                JSONObject containerJson = (JSONObject) container;
                String containerJsonCode = (String) containerJson.get("code");

                if (containerCode.equals(containerJsonCode)) {
                    // Assuming getContainerFromJson is a method to get a Container object from a JSONObject
                    Container containerObj = getContainerFromJson(containerJson);
                    try {
                        containerObj.addMeasurement(measurement);
                    } catch (MeasurementException e) {
                        // Handle the exception as needed
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Reads distances from a JSON file and adds paths to the specified AidBox.
     *
     * @param fromAidBox the AidBox to add paths to
     * @throws FileNotFoundException if the JSON file is not found
     * @throws IOException           if an I/O error occurs
     * @throws ParseException        if the JSON parsing fails
     */
    public void readDistances(AidBox fromAidBox) throws FileNotFoundException, IOException, ParseException {
        String filePath = "src/pp_er/files/distances.json";

        JSONParser parser = new JSONParser();

        FileReader reader = new FileReader(filePath);
        JSONObject jsonObject = (JSONObject) parser.parse(reader);

        JSONArray distancesArray = (JSONArray) jsonObject.get("distances");

        for (Object o : distancesArray) {
            JSONObject distanceEntry = (JSONObject) o;

            String to = (String) distanceEntry.get("to");
            double distance = (double) distanceEntry.get("distance");
            double duration = (double) distanceEntry.get("duration");

            AidBox toAidBox = new AidBoxImp(to);

            Path path = new Path(toAidBox, distance, duration);
            try {
                ((AidBoxImp) fromAidBox).addPath(path);
            } catch (PathExeption ex) {
                // Handle the exception as needed
                // Logger.getLogger(JsonReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Helper method to convert a JSON object to a Container instance.
     *
     * @param containerJson the JSON object representing a container
     * @return the Container instance
     */
    private Container getContainerFromJson(JSONObject containerJson) {
        String code = (String) containerJson.get("code");
        ContainerType type = new ContainerTypeImp((String) containerJson.get("type"));
        double capacity = ((Long) containerJson.get("capacity")).doubleValue();
        return new ContainerImp(code, type, capacity);
    }
}
