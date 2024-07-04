/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.ContainerType;
import java.util.Objects;

/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: LEIT4
 * 
 * This class represents the cargo capacity for a specific container type in a vehicle.
 */
public class VehicleCargo {

    private ContainerType containerType;
    private int capacity;

    /**
     * Constructs a new VehicleCargo instance with the specified container type and capacity.
     *
     * @param containerType the type of the container
     * @param capacity the capacity of the container
     */
    public VehicleCargo(ContainerType containerType, int capacity) {
        this.containerType = containerType;
        this.capacity = capacity;
    }

    /**
     * Getter for the container type.
     *
     * @return the container type
     */
    public ContainerType getContainerType() {
        return containerType;
    }

    /**
     * Getter for the capacity.
     *
     * @return the capacity of the container
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * Setter for the capacity.
     *
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Generates a hash code for the vehicle cargo.
     *
     * @return a hash code value for this vehicle cargo
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.containerType);
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
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
