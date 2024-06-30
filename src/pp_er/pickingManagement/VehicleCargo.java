/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;


import com.estg.core.ContainerType;

public class VehicleCargo {
    
    private ContainerType containerType;
    private double capacity;

    public VehicleCargo(ContainerType containerType, double capacity) {
        this.containerType = containerType;
        this.capacity = capacity;
    }

    public ContainerType getContainerType() {
        return containerType;
    }


    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }
}
