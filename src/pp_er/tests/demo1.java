/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.tests;
import pp_er.core.*;
import pp_er.core.ContainerTypeImp;
import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import pp_er.pickingManagement.ReportImp;
import pp_er.pickingManagement.RouteImp;
import pp_er.pickingManagement.VehicleImp;

/**
 *
 * @author emanu
 */
public class demo1 {
  public static void main(String[] args) throws RouteException {
      try {
              // Create some container types
            ContainerType perishable = new ContainerTypeImp("Perishable");
            ContainerType nonPerishable = new ContainerTypeImp("Non-perishable");

            // Create some containers
            Container perishableContainer = new ContainerImp("C1", 100, perishable);
            Container nonPerishableContainer = new ContainerImp("C2", 200, nonPerishable);

            // Create some aid boxes
            AidBox aidBox1 = new AidBoxImp("A1", "Zone1");
            aidBox1.addContainer(perishableContainer);
            AidBox aidBox2 = new AidBoxImp("A2", "Zone2");
            aidBox2.addContainer(nonPerishableContainer);

            // Create a vehicle
            VehicleImp vehicle = new VehicleImp("V1", 1000);

            // Create a route
            RouteImp route = new RouteImp(vehicle);

            // Add aid boxes to the route
            route.addAidBox(aidBox1);
            route.addAidBox(aidBox2);

            // Generate a report
         
            // Print vehicle details
            System.out.println("Vehicle: " + vehicle);

            // Print aid box details
            System.out.println("AidBox 1: " + aidBox1);
            System.out.println("AidBox 2: " + aidBox2);

            // Print container details
            System.out.println("Perishable Container: " + perishableContainer);
            System.out.println("Non-Perishable Container: " + nonPerishableContainer);

            // Print route details
            System.out.println("Route:");
            AidBox[] routeAidBoxes = route.getRoute();
            for (AidBox aidBox : routeAidBoxes) {
                System.out.println(aidBox);
            }

        } catch (ContainerException e) {
            e.printStackTrace();
        }
       
        
}
}
