/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.tests;
import pp_er.core.*;
import pp_er.core.ContainerTypeImp;
import com.estg.core.*;
import com.estg.core.exceptions.*;
import com.estg.pickingManagement.Report;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.RouteGenerator;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
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
  public static void main(String[] args) throws RouteException {

        try {
            // Create ContainerTypes
            ContainerType waterType = new ContainerTypeImp("Water");
            ContainerType foodType = new ContainerTypeImp("Food");

            // Create Containers
            Container waterContainer = new ContainerImp("C1", 100.0, waterType);
            Container foodContainer = new ContainerImp("C2", 50.0, foodType);

            // Create Measurements
            Measurement waterMeasurement1 = new MeasurementImp(LocalDateTime.now(), 30.0);
            Measurement waterMeasurement2 = new MeasurementImp(LocalDateTime.now().plusDays(1), 35.0);
            Measurement foodMeasurement = new MeasurementImp(LocalDateTime.now(), 40.0);

            // Add Measurements to Containers
            waterContainer.addMeasurement(waterMeasurement1);
            waterContainer.addMeasurement(waterMeasurement2);
            foodContainer.addMeasurement(foodMeasurement);

            // Create AidBoxes
            AidBoxImp aidBox1 = new AidBoxImp("AB1", "Zone1");
            AidBoxImp aidBox2 = new AidBoxImp("AB2", "Zone2");
            AidBoxImp aidBox3 = new AidBoxImp("AB3", "Zone3");

            // Add Containers to AidBoxes
            aidBox1.addContainer(waterContainer);
            aidBox1.addContainer(foodContainer);
            aidBox2.addContainer(waterContainer);
            aidBox3.addContainer(foodContainer);

            // Create Paths between AidBoxes
            Path path1to2 = new Path(aidBox2, 80.0, 40.0);
            Path path2to3 = new Path(aidBox3, 10.0, 10.0);
            Path path1to3 = new Path(aidBox3,2.0, 5.0);
            Path path2to1 = new Path(aidBox3,50.0, 25.0);
            Path path3to1 = new Path(aidBox3,8.0, 5.0);
            Path path3to2 = new Path(aidBox3,10.0, 2.0);
            Path path3to3 = new Path(aidBox3,0.0, 0.0);
            Path path2to2 = new Path(aidBox3,0, 0.0);
            Path path1to1 = new Path(aidBox3,0, 0.0);

            aidBox1.addPath(path1to2);
            aidBox1.addPath(path1to3);
            aidBox2.addPath(path2to3);
            aidBox2.addPath(path2to1);
            aidBox3.addPath(path3to1);
            aidBox3.addPath(path3to2);
            aidBox1.addPath(path1to1);
            aidBox2.addPath(path2to2);
            aidBox3.addPath(path3to3);
            
            

            // Create VehicleCargo
            VehicleCargo waterCargo = new VehicleCargo(waterType, 100);
            VehicleCargo foodCargo = new VehicleCargo(foodType, 100);

            // Create Vehicles
            Vehicle vehicle1 = new VehicleImp("V1", new VehicleCargo[]{waterCargo, foodCargo});
            Vehicle vehicle2 = new VehicleImp("V2", new VehicleCargo[]{waterCargo});

            // Create Institution
            Institution institution = new InstitutionImp("My Institution");
            institution.addAidBox(aidBox1);
            institution.addAidBox(aidBox2);
            institution.addAidBox(aidBox3);
            institution.addVehicle(vehicle1);
            institution.addVehicle(vehicle2);

            // Generate Routes
            RouteGenerator routeGenerator = new RouteGeneratorImp();
            Route[] routes = routeGenerator.generateRoutes(institution);

            // Print Routes
            for (Route route : routes) {
                System.out.println("Vehicle: " + route.getVehicle().getCode());
                for (AidBox aidBox : route.getRoute()) {
                    System.out.println("  AidBox: " + aidBox.getCode() + " in " + aidBox.getZone());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

