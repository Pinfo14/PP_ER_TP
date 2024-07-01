/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.Institution;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteGenerator;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanu
 */
public class RouteGeneratorImp implements RouteGenerator {

    @Override
    public Route[] generateRoutes(Institution instn) {
        Vehicle[] vehicles = instn.getVehicles();
        AidBox[] aidBoxes = instn.getAidBoxes();
        Route[] routes = new Route[vehicles.length];

        for (int i = 0; i < vehicles.length; i++) {
            routes[i] = new RouteImp(vehicles[i]);
        }
  
    for(int i = 0; i<aidBoxes.length;i++){
            try {
                routes[i].addAidBox(aidBoxes[i]);
            } catch (RouteException ex) {
                Logger.getLogger(RouteGeneratorImp.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

        return routes;
    }

}
