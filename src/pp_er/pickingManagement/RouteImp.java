/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.ContainerType;
import com.estg.core.exceptions.AidBoxException;
import com.estg.pickingManagement.Report;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import pp_er.core.AidBoxImp;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: LEIT4
*/

/**
 * Implementation of the {@link Route} interface representing a route with a vehicle and aid boxes.
 */
public class RouteImp implements Route {

    private static final int INIT_AIDBOX_SIZE = 5;
    private static final int GROWTH = 2;

    private Vehicle vehicle;
    private AidBox[] aidBoxes;
    private int aidBoxCount;

    /**
     * Constructs a new RouteImp instance with the specified vehicle.
     *
     * @param vehicle the vehicle for the route
     */
    public RouteImp(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.aidBoxes = new AidBox[INIT_AIDBOX_SIZE];
        this.aidBoxCount = 0;
    }

    /**
     * Expands the capacity of the aid boxes array.
     */
    private void expandAidBoxes() {
        AidBox[] temp = new AidBox[this.aidBoxCount * GROWTH];
        for (int i = 0; i < this.aidBoxCount; i++) {
            temp[i] = this.aidBoxes[i];
        }
        this.aidBoxes = temp;
    }

    /**
     * Verifies if the aid box is compatible with the vehicle's container types.
     *
     * @param aidbox the aid box to verify
     * @return {@code true} if the aid box is compatible, {@code false} otherwise
     */
    private boolean verifyType(AidBox aidbox) {
        int totalCont = ((AidBoxImp) aidbox).getNumberContainers();
        for (int i = 0; i < totalCont; i++) {
            if (this.vehicle.getCapacity(aidbox.getContainers()[i].getType()) > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an aid box to the route.
     *
     * @param aidbox the aid box to add
     * @throws RouteException if the aid box is null, already in the route, or incompatible
     */
    @Override
    public void addAidBox(AidBox aidbox) throws RouteException {
        if (aidbox == null) {
            throw new RouteException("AidBox is null");
        }

        if (containsAidBox(aidbox)) {
            throw new RouteException("AidBox is already in the route");
        }

        if (this.aidBoxes.length == this.aidBoxCount) {
            expandAidBoxes();
        }
        if (!verifyType(aidbox)) {
            throw new RouteException("AidBox is incompatible");
        }

        this.aidBoxes[this.aidBoxCount++] = aidbox;
    }

    /**
     * Finds the index of an aid box in the route.
     *
     * @param aidbox the aid box to find
     * @return the index of the aid box, or -1 if not found
     */
    private int findAidBox(AidBox aidbox) {
        for (int i = 0; i < this.aidBoxCount; i++) {
            if (this.aidBoxes[i].equals(aidbox)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes an aid box from the route.
     *
     * @param aidbox the aid box to remove
     * @return the removed aid box
     * @throws RouteException if the aid box is null or does not exist in the route
     */
    @Override
    public AidBox removeAidBox(AidBox aidbox) throws RouteException {
        if (aidbox == null) {
            throw new RouteException("AidBox is null");
        }

        int index = findAidBox(aidbox);

        if (index == -1) {
            throw new RouteException("AidBox doesn't exist");
        }

        for (int i = index; i < this.aidBoxCount - 1; i++) {
            this.aidBoxes[i] = this.aidBoxes[i + 1];
        }

        this.aidBoxes[--this.aidBoxCount] = null;
        return aidbox;
    }

    /**
     * Checks if the route contains the specified aid box.
     *
     * @param aidbox the aid box to check
     * @return {@code true} if the aid box is in the route, {@code false} otherwise
     */
    @Override
    public boolean containsAidBox(AidBox aidbox) {
        return findAidBox(aidbox) != -1;
    }

    /**
     * Replaces an aid box in the route with another aid box.
     *
     * @param aidbox the aid box to replace
     * @param aidbox1 the aid box to insert
     * @throws RouteException if any of the aid boxes are null, do not exist, or are incompatible
     */
    @Override
    public void replaceAidBox(AidBox aidbox, AidBox aidbox1) throws RouteException {
        if (aidbox == null || aidbox1 == null) {
            throw new RouteException("AidBox is null");
        }

        int index = findAidBox(aidbox);

        if (index == -1) {
            throw new RouteException("AidBox doesn't exist in route");
        }

        if (containsAidBox(aidbox1)) {
            throw new RouteException("AidBox is already in route");
        }
        if (!verifyType(aidbox1)) {
            throw new RouteException("AidBox is incompatible");
        }
        this.aidBoxes[index] = aidbox1;
    }

    /**
     * Inserts an aid box into the route after a specified aid box.
     *
     * @param aidbox the aid box to insert after
     * @param aidbox1 the aid box to insert
     * @throws RouteException if any of the aid boxes are null, do not exist, or are incompatible
     */
    @Override
    public void insertAfter(AidBox aidbox, AidBox aidbox1) throws RouteException {
        if (aidbox == null || aidbox1 == null) {
            throw new RouteException("AidBox is null");
        }

        int index = findAidBox(aidbox);

        if (index == -1) {
            throw new RouteException("AidBox doesn't exist in route");
        }

        if (containsAidBox(aidbox1)) {
            throw new RouteException("AidBox is already in route");
        }
        if (!verifyType(aidbox1)) {
            throw new RouteException("AidBox is incompatible");
        }

        if (this.aidBoxCount == this.aidBoxes.length) {
            expandAidBoxes();
        }

        for (int i = this.aidBoxCount; i > index; i--) {
            this.aidBoxes[i] = this.aidBoxes[i - 1];
        }

        this.aidBoxes[index + 1] = aidbox1;
        this.aidBoxCount++;
    }

    /**
     * Gets the route as an array of aid boxes.
     *
     * @return an array of aid boxes in the route
     */
    @Override
    public AidBox[] getRoute() {
        AidBox[] copy = new AidBox[this.aidBoxCount];
        for (int i = 0; i < this.aidBoxCount; i++) {
            if (this.aidBoxes[i] instanceof AidBoxImp) {
                AidBoxImp temp = (AidBoxImp) this.aidBoxes[i];
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
     * Gets the vehicle assigned to the route.
     *
     * @return the vehicle assigned to the route
     */
    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    /**
     * Gets the total distance of the route.
     *
     * @return the total distance of the route
     */
    @Override
    public double getTotalDistance() {
        double distance = 0;
        for (int i = 0; i < this.aidBoxCount - 1; i++) {
            try {
                distance += this.aidBoxes[i].getDistance(this.aidBoxes[i + 1]);
            } catch (AidBoxException ex) {
                // Handle the exception
            }
        }
        return distance;
    }

    /**
     * Gets the total duration of the route.
     *
     * @return the total duration of the route
     */
    @Override
    public double getTotalDuration() {
        double duration = 0;
        for (int i = 0; i < this.aidBoxCount - 1; i++) {
            try {
                duration += this.aidBoxes[i].getDuration(this.aidBoxes[i + 1]);
            } catch (AidBoxException ex) {
                // Handle the exception
            }
        }
        return duration;
    }

    /**
     * Rearranges the aid boxes in the route based on their distances.
     */
    public void rearrengeAidBoxes() {
        for (int i = 0; i < this.aidBoxCount - 1; i++) {
            for (int j = 0; j < this.aidBoxCount - i - 1; j++) {
                try {
                    double distance1 = this.aidBoxes[j].getDistance(this.aidBoxes[j + 1]);
                    double distance2 = this.aidBoxes[j + 1].getDistance(this.aidBoxes[j]);
                    if (distance1 > distance2) {
                        AidBox temp = this.aidBoxes[j];
                        this.aidBoxes[j] = this.aidBoxes[j + 1];
                        this.aidBoxes[j + 1] = temp;
                    }
                } catch (AidBoxException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets the report for the route.
     *
     * @return the report for the route
     * @throws UnsupportedOperationException currently not supported
     */
    @Override
    public Report getReport() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns a string representation of this route.
     *
     * @return a string representation of this route
     */
    @Override
    public String toString() {
        return "RouteImp{" + "vehicle=" + vehicle + ", aidBoxes=" + aidBoxes + ", aidBoxCount=" + aidBoxCount + '}';
    }
}
