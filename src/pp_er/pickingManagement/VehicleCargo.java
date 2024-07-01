/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;


import com.estg.core.ContainerType;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.containerType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VehicleCargo other = (VehicleCargo) obj;
        return Objects.equals(this.containerType, other.containerType);
    }
    
    
}
