/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.ContainerType;
import com.estg.core.Institution;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteGenerator;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
*/

public class RouteGeneratorImp implements RouteGenerator {


    @Override
    public Route[] generateRoutes(Institution instn) {
        
        AidBox[] aidBoxes = instn.getAidBoxes();
        Vehicle[] vehicles = instn.getVehicles();

        AidBoxTrack[] aidBoxTracks = new AidBoxTrack[aidBoxes.length];
        CapacityTrack[] vehicleTracks = new CapacityTrack[vehicles.length];
//          for (int i = 0; i < aidBoxes.length; i++) {
//            aidBoxTracks[i] = new AidBoxTrack(aidBoxes[i]);
//        }

        for (int i = 0; i < vehicles.length; i++) {
            vehicleTracks[i] = new CapacityTrack(vehicles[i]);
        }

        Route[] routes = new Route[vehicles.length];
        
    }
    
    
    public 
   
}
