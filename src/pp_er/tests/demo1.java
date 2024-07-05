/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.tests;
import pp_er.core.*;
import pp_er.core.ContainerTypeImp;
import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.io.Importer;
import com.estg.pickingManagement.Report;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteGenerator;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pp_er.io.ImporterImp;
import pp_er.io.JsonReader;

import pp_er.pickingManagement.ReportImp;
import pp_er.pickingManagement.RouteGeneratorImp;
import pp_er.pickingManagement.RouteImp;
import pp_er.pickingManagement.VehicleCargo;
import pp_er.pickingManagement.VehicleImp;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
 */

public class demo1 {
  public static void main(String[] args) throws RouteException, FileNotFoundException, IOException, ParseException {

         JsonReader jsonReader = new JsonReader();
          Institution istn = new InstitutionImp("LA");
          
          Importer importer = new ImporterImp(jsonReader);
          
      try {
          importer.importData(istn);
      } catch (InstitutionException ex) {
          Logger.getLogger(demo1.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
}

