/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.ContainerType;
import com.estg.core.Institution;
import com.estg.core.Measurement;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import com.estg.core.exceptions.MeasurementException;
import com.estg.core.exceptions.PickingMapException;
import com.estg.core.exceptions.VehicleException;
import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Vehicle;
import java.time.LocalDateTime;
import pp_er.pickingManagement.VehicleImp;
import pp_er.pickingManagement.VehicleState;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: LEIT4
 */

/**
 * Implementation of the {@link Institution} interface representing an institution that manages aid boxes, vehicles, and picking maps.
 */
public class InstitutionImp implements Institution {

    private static final int INIT_AIDBOXES = 10;
    private static final int INIT_VEHICLES = 5;
    private static final int INIT_PICKINGMAPS = 5;
    private static final int GROWTH = 2;

    private String name;
    private AidBox[] aidBoxes;
    private int aidBoxCount;
    private Vehicle[] vehicles;
    private int vehicleCount;
    private PickingMap[] pickingMaps;
    private int pickingMapCount;
    private ContainerTypes containerTypes;

    /**
     * Constructs a new InstitutionImp instance with the specified name.
     *
     * @param name the name of the institution
     */
    public InstitutionImp(String name) {
        this.name = name;
        this.aidBoxes = new AidBox[INIT_AIDBOXES];
        this.aidBoxCount = 0;
        this.vehicles = new Vehicle[INIT_VEHICLES];
        this.vehicleCount = 0;
        this.pickingMaps = new PickingMap[INIT_PICKINGMAPS];
        this.pickingMapCount = 0;
    }

    /**
     * Getter for the institution name.
     *
     * @return the name of the institution
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Adds container types to the institution.
     *
     * @param cts the container types to add
     * @return {@code true} if the container types were added successfully, {@code false} otherwise
     */
    public boolean addContainerTypes(ContainerTypes cts) {
        if (cts == null) {
            return false;
        }
        this.containerTypes = cts;
        return true;
    }

    /**
     * Adds a new aid box to the institution.
     *
     * @param aidbox the aid box to add
     * @return {@code true} if the aid box was added successfully
     * @throws AidBoxException if the aid box is null or already exists
     */
    @Override
    public boolean addAidBox(AidBox aidbox) throws AidBoxException {
        if (aidbox == null) {
            throw new AidBoxException("AidBox is null");
        }
        if (aidBoxCount == aidBoxes.length) {
            expandAidBoxes();
        }
        for (int i = 0; i < aidBoxCount; i++) {
            if (aidBoxes[i].getCode().equals(aidbox.getCode())) {
                throw new AidBoxException("AidBox already exists");
            }
        }
        aidBoxes[aidBoxCount++] = aidbox;
        return true;
    }

    /**
     * Expands the capacity of the aid boxes array.
     */
    private void expandAidBoxes() {
        AidBox[] temp = new AidBox[aidBoxes.length * GROWTH];
        for (int i = 0; i < aidBoxCount; i++) {
            temp[i] = aidBoxes[i];
        }
        aidBoxes = temp;
    }

    /**
     * Finds a container in the aid boxes.
     *
     * @param cntnr the container to find
     * @return the index of the aid box containing the container, or -1 if not found
     */
    private int findContainer(Container cntnr) {
        for (int i = 0; i < this.aidBoxCount; i++) {
            if (this.aidBoxes[i].getContainers().equals(cntnr)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a new measurement to a container.
     *
     * @param msrmnt the measurement to add
     * @param cntnr the container to add the measurement to
     * @return {@code true} if the measurement was added successfully
     * @throws ContainerException if the container does not exist or is null
     * @throws MeasurementException if the measurement is null
     */
    @Override
    public boolean addMeasurement(Measurement msrmnt, Container cntnr) throws ContainerException, MeasurementException {
        if (msrmnt == null) {
            throw new MeasurementException("Measurement is null");
        }
        if (findContainer(cntnr) == -1) {
            throw new ContainerException("Container doesn't exist");
        }
        if (cntnr == null) {
            throw new ContainerException("Container is null");
        }

        cntnr.addMeasurement(msrmnt);
        return true;
    }

    /**
     * Adds a new picking map to the institution.
     *
     * @param pickingMap the picking map to add
     * @return {@code true} if the picking map was added successfully
     * @throws PickingMapException if the picking map is null
     */
    @Override
    public boolean addPickingMap(PickingMap pickingMap) throws PickingMapException {
        if (pickingMap == null) {
            throw new PickingMapException("PickingMap is null");
        }
        if (this.pickingMapCount == this.pickingMaps.length) {
            expandPickingMaps();
        }
        this.pickingMaps[this.pickingMapCount++] = pickingMap;
        return true;
    }

    /**
     * Expands the capacity of the picking maps array.
     */
    private void expandPickingMaps() {
        PickingMap[] newPickingMaps = new PickingMap[pickingMaps.length * 2];
        for (int i = 0; i < pickingMaps.length; i++) {
            newPickingMaps[i] = pickingMaps[i];
        }
        pickingMaps = newPickingMaps;
    }

    /**
     * Finds a vehicle in the institution.
     *
     * @param vhcl the vehicle to find
     * @return the index of the vehicle, or -1 if not found
     */
    private int findVehicle(Vehicle vhcl) {
        for (int i = 0; i < this.vehicleCount; i++) {
            if (this.vehicles[i].equals(vhcl)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a new vehicle to the institution.
     *
     * @param vhcl the vehicle to add
     * @return {@code true} if the vehicle was added successfully
     * @throws VehicleException if the vehicle is null or already exists
     */
    @Override
    public boolean addVehicle(Vehicle vhcl) throws VehicleException {
        if (vhcl == null) {
            throw new VehicleException("Vehicle is null");
        }

        if (findVehicle(vhcl) != -1) {
            throw new VehicleException("Vehicle already exists");
        }

        if (this.vehicleCount == this.vehicles.length) {
            expandVehicles();
        }

        this.vehicles[this.vehicleCount++] = vhcl;
        return true;
    }

    /**
     * Expands the capacity of the vehicles array.
     */
    private void expandVehicles() {
        Vehicle[] temp = new Vehicle[this.vehicles.length * GROWTH];
        for (int i = 0; i < this.vehicleCount; i++) {
            temp[i] = this.vehicles[i];
        }
        this.vehicles = temp;
    }

    /**
     * Disables a vehicle in the institution.
     *
     * @param vhcl the vehicle to disable
     * @throws VehicleException if the vehicle is null, not found, or already disabled
     */
    @Override
    public void disableVehicle(Vehicle vhcl) throws VehicleException {
        if (vhcl == null) {
            throw new VehicleException("Vehicle is null");
        }

        if (findVehicle(vhcl) == -1) {
            throw new VehicleException("Vehicle not found");
        }

        if (vhcl instanceof VehicleImp) {
            VehicleImp vehicleTemp = (VehicleImp) vhcl;

            if (vehicleTemp.getState() == VehicleState.DISABLE) {
                throw new VehicleException("Vehicle is already disabled.");
            }

            vehicleTemp.setState(VehicleState.DISABLE);
        }
    }

    /**
     * Enables a vehicle in the institution.
     *
     * @param vhcl the vehicle to enable
     * @throws VehicleException if the vehicle is null, not found, or already enabled
     */
    @Override
    public void enableVehicle(Vehicle vhcl) throws VehicleException {
        if (vhcl == null) {
            throw new VehicleException("Vehicle is null");
        }
        if (findVehicle(vhcl) == -1) {
            throw new VehicleException("Vehicle not found");
        }

        if (vhcl instanceof VehicleImp) {
            VehicleImp vehicleTemp = (VehicleImp) vhcl;

            if (vehicleTemp.getState() == VehicleState.ENABLE) {
                throw new VehicleException("Vehicle is already enabled.");
            }

            vehicleTemp.setState(VehicleState.ENABLE);
        }
    }

    /**
     * Gets all aid boxes in the institution.
     *
     * @return an array of aid boxes
     */
    @Override
    public AidBox[] getAidBoxes() {
        AidBox[] result = new AidBox[this.aidBoxCount];
        for (int i = 0; i < this.aidBoxCount; i++) {
            result[i] = this.aidBoxes[i];
        }
        return result;
    }

    /**
     * Gets a container from an aid box by its type.
     *
     * @param aidbox the aid box to search
     * @param ct the container type to search for
     * @return the container of the specified type, or {@code null} if not found
     * @throws ContainerException if the aid box is null
     */
    @Override
    public Container getContainer(AidBox aidbox, ContainerType ct) throws ContainerException {
        if (aidbox == null) {
            throw new ContainerException("AidBox is null");
        }
        return aidbox.getContainer(ct);
    }

    /**
     * Gets the total distance to a specified aid box from all aid boxes in the institution.
     *
     * @param aidbox the aid box to measure the distance to
     * @return the total distance to the specified aid box
     * @throws AidBoxException if an error occurs while calculating the distance
     */
    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        double totalDistance = 0;
        for (int i = 0; i < aidBoxCount; i++) {
            totalDistance += aidBoxes[i].getDistance(aidbox);
        }
        return totalDistance;
    }

    /**
     * Gets all vehicles in the institution.
     *
     * @return an array of vehicles
     */
    @Override
    public Vehicle[] getVehicles() {
        Vehicle[] copy = new Vehicle[this.vehicleCount];

        for (int i = 0; i < this.vehicleCount; i++) {
            if (this.vehicles[i] instanceof VehicleImp) {
                VehicleImp temp = (VehicleImp) this.vehicles[i];

                try {
                    copy[i] = temp.clone();
                } catch (CloneNotSupportedException e) {
                    // Handle the exception
                }
            }
        }

        return copy;
    }

    /**
     * Gets all picking maps in the institution.
     *
     * @return an array of picking maps
     */
    @Override
    public PickingMap[] getPickingMaps() {
        return this.pickingMaps;
    }

    /**
     * Gets all picking maps within a specified date range.
     *
     * @param ldt the start date
     * @param ldt1 the end date
     * @return an array of picking maps within the specified date range
     */
    @Override
    public PickingMap[] getPickingMaps(LocalDateTime ldt, LocalDateTime ldt1) {
        int count = 0;
        for (PickingMap map : pickingMaps) {
            if (map != null) {
                LocalDateTime mapDate = map.getDate();
                if (mapDate.isAfter(ldt) && mapDate.isBefore(ldt1)) {
                    count++;
                }
            }
        }

        PickingMap[] result = new PickingMap[count];
        int index = 0;
        for (PickingMap map : pickingMaps) {
            if (map != null) {
                LocalDateTime mapDate = map.getDate();
                if (mapDate.isAfter(ldt) && mapDate.isBefore(ldt1)) {
                    result[index++] = map;
                }
            }
        }

        return result;
    }

    /**
     * Gets the most recent picking map.
     *
     * @return the most recent picking map
     * @throws PickingMapException if no picking maps are available
     */
    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {
        if (this.pickingMapCount == 0) {
            throw new PickingMapException("No PickingMaps available");
        }

        PickingMap mostRecentMap = null;
        for (PickingMap map : this.pickingMaps) {
            if (map != null) {
                if (mostRecentMap == null || map.getDate().isAfter(mostRecentMap.getDate())) {
                    mostRecentMap = map;
                }
            }
        }

        if (mostRecentMap == null) {
            throw new PickingMapException("No valid PickingMap found");
        }

        return mostRecentMap;
    }

    @Override
    public String toString() {
        return "InstitutionImp{" +
                "name='" + name + '\'' +
                ", aidBoxes=" + aidBoxes +
                ", aidBoxCount=" + aidBoxCount +
                ", vehicles=" + vehicles +
                ", vehicleCount=" + vehicleCount +
                ", pickingMaps=" + pickingMaps +
                ", pickingMapCount=" + pickingMapCount +
                '}';
    }
}
