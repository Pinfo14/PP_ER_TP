/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.ContainerType;
import com.estg.pickingManagement.Vehicle;
import java.util.Objects;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
*/

/**
 * Implementation of the {@link Vehicle} interface representing a vehicle with a code, cargo, and state.
 */
public class VehicleImp implements Vehicle, Cloneable {

    private static final int INIT_CARGO_SIZE = 5;

    private String code;
    private VehicleCargo[] vehiclecargo;
    private int cargoSize;
    private VehicleState state;

    /**
     * Constructs a new VehicleImp instance with the specified code.
     *
     * @param code the code of the vehicle
     */
    public VehicleImp(String code) {
        this.code = code;
        this.state = VehicleState.ENABLE;
        this.cargoSize = 0;
        this.vehiclecargo = new VehicleCargo[INIT_CARGO_SIZE];
    }

    /**
     * Adds a cargo to the vehicle.
     *
     * @param cargo the cargo to add
     * @return {@code true} if the cargo was added successfully, {@code false} otherwise
     */
    public boolean addCargo(VehicleCargo cargo) {
        if (cargo == null) {
            return false;
        }
        if (this.cargoSize == this.vehiclecargo.length) {
            expandCargos();
        }
        this.vehiclecargo[this.cargoSize++] = cargo;
        return true;
    }

    /**
     * Expands the capacity of the vehicle's cargo array.
     */
    private void expandCargos() {
        VehicleCargo[] newCargos = new VehicleCargo[this.vehiclecargo.length * 2];
        for (int i = 0; i < this.vehiclecargo.length; i++) {
            newCargos[i] = this.vehiclecargo[i];
        }
        this.vehiclecargo = newCargos;
    }

    /**
     * Getter for the vehicle's code.
     *
     * @return the code of the vehicle
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Gets the capacity for a specific container type.
     *
     * @param ct the container type
     * @return the capacity for the specified container type, or -1 if not found
     */
    @Override
    public double getCapacity(ContainerType ct) {
        for (int i = 0; i < this.vehiclecargo.length; i++) {
            if (this.vehiclecargo[i].getContainerType().equals(ct)) {
                return this.vehiclecargo[i].getCapacity();
            }
        }
        return -1;
    }

    /**
     * Sets the state of the vehicle.
     *
     * @param state the state to set
     */
    public void setState(VehicleState state) {
        this.state = state;
    }

    /**
     * Getter for the state of the vehicle.
     *
     * @return the state of the vehicle
     */
    public VehicleState getState() {
        return this.state;
    }

    /**
     * Gets the cargo of the vehicle.
     *
     * @return an array of the vehicle's cargo
     */
    public VehicleCargo[] getCargo() {
        VehicleCargo[] vc = new VehicleCargo[this.cargoSize];
        for (int i = 0; i < this.cargoSize; i++) {
            vc[i] = this.vehiclecargo[i];
        }
        return vc;
    }

    /**
     * Creates and returns a copy of this vehicle.
     *
     * @return a copy of this vehicle
     * @throws CloneNotSupportedException if the vehicle cannot be cloned
     */
    @Override
    public Vehicle clone() throws CloneNotSupportedException {
        return (Vehicle) super.clone();
    }

    /**
     * Generates a hash code for the vehicle.
     *
     * @return a hash code value for this vehicle
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.code);
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
        final VehicleImp other = (VehicleImp) obj;
        return Objects.equals(this.code, other.code);
    }
}
