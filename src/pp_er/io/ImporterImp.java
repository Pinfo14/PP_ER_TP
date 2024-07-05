/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.io;

import com.estg.core.Institution;
import com.estg.core.exceptions.InstitutionException;
import com.estg.io.Importer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
 */
public class ImporterImp implements Importer {
   private JsonReader data;

   /**
    * Constructor for ImporterImp.
    *
    * @param data the JsonReader instance used to read data from JSON files
    */
    public ImporterImp(JsonReader data) {
        this.data = data;
    }
    
    /**
     * Imports data into the specified Institution.
     * This method reads aid boxes, types, and vehicles from JSON files and adds them to the institution.
     *
     * @param instn the Institution to import data into
     * @throws FileNotFoundException if a JSON file is not found
     * @throws IOException           if an I/O error occurs
     * @throws InstitutionException  if the institution is null or any data reading/parsing issue occurs
     */
    @Override
    public void importData(Institution instn) throws FileNotFoundException, IOException, InstitutionException {
       try {
           if (instn == null) {
               throw new InstitutionException("Institution is null");
           }
           data.readAidBoxes(instn);
           data.readTypes(instn);
           data.readVehicles(instn);
       } catch (ParseException ex) {
          ex.printStackTrace();
       }
    }
}