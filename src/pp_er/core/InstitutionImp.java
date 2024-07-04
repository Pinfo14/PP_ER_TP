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
* Turma: Turma 4
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

    public InstitutionImp(String name) {
        this.name = name;
        this.aidBoxes = new AidBox[INIT_AIDBOXES];
        this.aidBoxCount = 0;
        this.vehicles = new Vehicle[INIT_VEHICLES];
        this.vehicleCount = 0;
        this.pickingMaps = new PickingMap[INIT_PICKINGMAPS];
        this.pickingMapCount = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

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
                throw new AidBoxException("AidBox already exist");
            }
        }
        aidBoxes[aidBoxCount++] = aidbox;
        return true;
    }

    private void expandAidBoxes() {
        AidBox[] temp = new AidBox[aidBoxes.length * GROWTH];
        for (int i = 0; i < aidBoxCount; i++) {
            temp[i] = aidBoxes[i];
        }
        aidBoxes = temp;
    }

    private int findContainer(Container cntnr) {
        for (int i = 0; i < this.aidBoxCount; i++) {
            this.aidBoxes[i].getContainers().equals(cntnr);
            return i;
        }
        return -1;
    }

    @Override
    public boolean addMeasurement(Measurement msrmnt, Container cntnr) throws ContainerException, MeasurementException {
        if (msrmnt == null) {
            throw new MeasurementException("Measurement is null");
        }
        if (findContainer(cntnr) == -1) {
            throw new ContainerException("Container doesnt exist");
        }
        if (cntnr == null) {
            throw new ContainerException("Container is null");
        }

        cntnr.addMeasurement(msrmnt);
        return true;
    }

    @Override
    public boolean addPickingMap(PickingMap pickingMap) throws PickingMapException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    private void expandPickingMaps() {

    }

    private int findVehicle(Vehicle vhcl) {
        for (int i = 0; i < this.vehicleCount; i++) {
            if (this.vehicles[i].equals(vhcl)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addVehicle(Vehicle vhcl) throws VehicleException {
        if (vhcl == null) {
            throw new VehicleException("Vehicle is null");
        }

        if (findVehicle(vhcl) != -1) {
            throw new VehicleException("Vehicle allready exist");
        }

        if (this.vehicleCount == this.vehicles.length) {
            expandVehicles();
        }

        this.vehicles[this.vehicleCount++] = vhcl;
        return true;
    }

    private void expandVehicles() {
        Vehicle[] temp = new Vehicle[this.vehicleCount * GROWTH];

        for (int i = 0; i < this.vehicleCount; i++) {
            temp[i] = this.vehicles[i];
        }
        this.vehicles = temp;
    }

    @Override
    public void disableVehicle(Vehicle vhcl) throws VehicleException {
        if (vhcl == null) {
            throw new VehicleException("Vhicle is null");
        }

        if (findVehicle(vhcl) == -1) {
            throw new VehicleException("Vhicle not found");
        }

        if (vhcl instanceof VehicleImp) {
            VehicleImp VehicleTemp = (VehicleImp) vhcl;

            if (VehicleTemp.getState() == VehicleState.DISABLE) {
                throw new VehicleException("Parameter vehicle is already disabled.");
            }

            VehicleTemp.setState(VehicleState.DISABLE);
        }
    }

    @Override
    public void enableVehicle(Vehicle vhcl) throws VehicleException {
        if (vhcl == null) {
            throw new VehicleException("Vhicle is null");
        }
        if (findVehicle(vhcl) == -1) {
            throw new VehicleException("Vhicle not found");
        }

        if (vhcl instanceof VehicleImp) {
            VehicleImp VehicleTemp = (VehicleImp) vhcl;

            if (VehicleTemp.getState() == VehicleState.ENABLE) {
                throw new VehicleException(" vehicle is already enable.");
            }

            VehicleTemp.setState(VehicleState.ENABLE);
        }
    }

    @Override
    public AidBox[] getAidBoxes() {
        AidBox[] result = new AidBox[this.aidBoxCount];
        for (int i = 0; i < this.aidBoxCount; i++) {
            result[i] = this.aidBoxes[i];
        }
        return result;
    }

    @Override
    public Container getContainer(AidBox aidbox, ContainerType ct) throws ContainerException {
        if (aidbox == null) {
            throw new ContainerException("AidBox is null");
        }
        return aidbox.getContainer(ct);
    }

    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        double totalDistance = 0;
        for (int i = 0; i < aidBoxCount; i++) {
            totalDistance += aidBoxes[i].getDistance(aidbox);
        }
        return totalDistance;
    }

    @Override
    public Vehicle[] getVehicles() {
       Vehicle[] copy = new Vehicle[this.vehicleCount];

        for (int i = 0; i < this.vehicleCount; i++) {
            if (this.vehicles[i] instanceof VehicleImp) {
                VehicleImp temp = (VehicleImp) this.vehicles[i];

                try {
                    copy[i] = temp.clone();
                } catch (CloneNotSupportedException e) {
                   
                }
            }
        }

        return copy;
    }

    @Override
    public PickingMap[] getPickingMaps() {
        return this.pickingMaps;
    }

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

    @Override
    public PickingMap getCurrentPickingMap() throws PickingMapException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "InstitutionImp{" + "name=" + name + ", aidBoxes=" + aidBoxes + ", aidBoxCount=" + aidBoxCount + ", vehicles=" + vehicles + ", vehicleCount=" + vehicleCount + ", pickingMaps=" + pickingMaps + ", pickingMapCount=" + pickingMapCount + '}';
    }
    
}
