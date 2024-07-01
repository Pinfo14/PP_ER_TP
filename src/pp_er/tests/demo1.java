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

import java.time.LocalDate;
import java.time.LocalDateTime;
import pp_er.pickingManagement.VehicleImp;

/**
 *
 * @author emanu
 */
public class demo1 {
  public static void main(String[] args) {
       try {
            // Creating instances of AidBox
            AidBoxImp aidBox1 = new AidBoxImp("A001", "Zone1");
            AidBoxImp aidBox2 = new AidBoxImp("A002", "Zone2");
            
            
            ContainerType typ1 =new ContainerTypeImp("type1");

            // Creating containers
            Container container1 = new ContainerImp("C001",100,typ1);
            Container container2 = new ContainerImp("C002",100 ,typ1);
            Container container3 = new ContainerImp("C003", 100,typ1);

            // Adding containers to aidBox1
            aidBox1.addContainer(container1);
            aidBox1.addContainer(container2);

            // Displaying containers of aidBox1
            System.out.println("Containers in aidBox1:");
            for (Container container : aidBox1.getContainers()) {
                System.out.println(container);
            }

            // Trying to add a duplicate container
            boolean added = aidBox1.addContainer(container1);
            if (!added) {
                System.out.println("Container " + container1.getType() + " already exists in aidBox1.");
            }

            // Adding a path to aidBox1
           
//            Path path1 = new Path(aidBox2, 10.5, 30.0);
//            aidBox1.addPath(path1);
//
//            // Getting distance and duration to aidBox2 from aidBox1
//            System.out.println("Distance to aidBox2: " + aidBox1.getDistance(aidBox2));
//            System.out.println("Duration to aidBox2: " + aidBox1.getDuration(aidBox2));

            // Removing a container from aidBox1
            aidBox1.removeContainer(container2);
            System.out.println("Container  removed from aidBox1.");

            // Displaying containers of aidBox1 after removal
            System.out.println("Containers in aidBox1 after removal:");
            for (Container container : aidBox1.getContainers()) {
                System.out.println(container);
            }

        } catch (ContainerException | AidBoxException e) {
            System.err.println(e.getMessage());
        }
       
        
}
}
