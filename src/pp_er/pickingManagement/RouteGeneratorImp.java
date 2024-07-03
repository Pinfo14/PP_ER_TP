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
import pp_er.core.ContainerImp;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
 */
public class RouteGeneratorImp implements RouteGenerator {

    @Override
    public Route[] generateRoutes(Institution instn) {

        Vehicle[] vehicles = instn.getVehicles();
        AidBox[] aidBoxes = instn.getAidBoxes();
        Route[] routes = new Route[vehicles.length];

        for (int i = 0; i < vehicles.length; i++) {
            Vehicle vehicle = vehicles[i];
            routes[i] = new RouteImp(vehicle);
            for (AidBox aidBox : aidBoxes) {
                if (validateAidBoxForVehicle(aidBox, vehicle)) {
                    try {
                        routes[i].addAidBox(aidBox);
                    } catch (RouteException ex) {
                       // Logger.getLogger(RouteGeneratorImp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
             ((RouteImp)routes[i]).rearrengeAidBoxes();
        }

        return routes;
    }

 private boolean validateAidBoxForVehicle(AidBox aidBox, Vehicle vehicle) {
        if (aidBox == null || vehicle == null) {
            return false;
        }

        Container[] containers = aidBox.getContainers();
        VehicleCargo[] cargo = ((VehicleImp) vehicle).getCargo();

        for (VehicleCargo vc : cargo) {
            CapacityTrack capTrack = new CapacityTrack(vc.getContainerType());
            capTrack.setCapacity(vc.getCapacity());

            for (Container container : containers) {
                if (container.getType().equals(capTrack.getType())) {
                    while (((ContainerImp) container).getLastMeasurement().getValue() / container.getCapacity() >= 0.8) {
                        capTrack.setCapacity(capTrack.getEmptyContainers() - 1);
                        if (capTrack.getEmptyContainers() < 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
