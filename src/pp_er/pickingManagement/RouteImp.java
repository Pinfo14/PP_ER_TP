/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.AidBox;
import com.estg.core.exceptions.AidBoxException;
import com.estg.pickingManagement.Report;
import com.estg.pickingManagement.Route;
import com.estg.pickingManagement.Vehicle;
import com.estg.pickingManagement.exceptions.RouteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pp_er.core.AidBoxImp;

/**
 *
 * @author emanu
 */
public class RouteImp implements Route {

    private static final int INIT_AIDBOX_SIZE = 5;
    private static final int GROWTH = 2;

    private Vehicle vehicle;
    private AidBox[] aidBoxes;
    private int aidBoxCount;

    public RouteImp(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.aidBoxes = new AidBox[INIT_AIDBOX_SIZE];
        this.aidBoxCount = 0;
    }

    private void expandAidBoxes() {
        AidBox[] temp = new AidBox[this.aidBoxCount * GROWTH];

        for (int i = 0; i < this.aidBoxCount; i++) {
            temp[i] = this.aidBoxes[i];
        }

        this.aidBoxes = temp;
    }

    @Override
    public void addAidBox(AidBox aidbox) throws RouteException {
        if (aidbox == null) {
            throw new RouteException("AidBox is null");
        }

        if (containsAidBox(aidbox) == true) {
            throw new RouteException("AidBox is alredy in the route");
        }

        if (this.aidBoxes.length == this.aidBoxCount) {
            expandAidBoxes();
        }
//         if (aidbox.getContainer(this.vehicle.getCapacity(ct)) == null) {
//            throw new RouteException("Parameter aid box is incompatible with the vehicle's supply type.");
//        }

        this.aidBoxes[this.aidBoxCount++] = aidbox;
    }
 
    private int findAidBox(AidBox aidbox) {
        for (int i = 0; i < this.aidBoxCount; i++) {
            if (this.aidBoxes[i].equals(aidbox)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public AidBox removeAidBox(AidBox aidbox) throws RouteException {
        if (aidbox == null) {
            throw new RouteException("AidBox is null");
        }

        int index = findAidBox(aidbox);

        if (index == -1) {
            throw new RouteException("AidBox doesnt exist");
        }

        for (int i = index; i < this.aidBoxCount; i++) {
            this.aidBoxes[i] = this.aidBoxes[i + 1];
        }

        this.aidBoxes[--this.aidBoxCount] = null;

        return aidbox; // o que retorna? aidbox?
    }

    @Override
    public boolean containsAidBox(AidBox aidbox) {
        int index = findAidBox(aidbox);

        return index != - 1;
    }

    @Override
    public void replaceAidBox(AidBox aidbox, AidBox aidbox1) throws RouteException {
        if (aidbox == null || aidbox1 == null) {
            throw new RouteException("AidBox is null");
        }

        int index = findAidBox(aidbox);

        if (index == -1) {
            throw new RouteException("AidBox doesnt exist in route");
        }

        if (containsAidBox(aidbox1)) {
            throw new RouteException("AidBox is already  in route");
        }
        //verificar se e do msm tipo 
        this.aidBoxes[index] = aidbox1;

    }

    @Override
    public void insertAfter(AidBox aidbox, AidBox aidbox1) throws RouteException {
        if (aidbox == null || aidbox1 == null) {
            throw new RouteException("AidBox is null");
        }

        int index = findAidBox(aidbox);

        if (index == -1) {
            throw new RouteException("AidBox doesnt exist in route");
        }

        if (containsAidBox(aidbox1)) {
            throw new RouteException("AidBox is already  in route");
        }
        if (this.aidBoxCount == this.aidBoxes.length) {
            expandAidBoxes();
        }

        for (int i = this.aidBoxCount; i < index; i--) {
            this.aidBoxes[i] = this.aidBoxes[i - 1];
        }

        this.aidBoxes[index + 1] = aidbox1;
    }

    @Override
    public AidBox[] getRoute() {
        AidBox[] copy = new AidBox[this.aidBoxCount];

        for (int i = 0; i < this.aidBoxCount; i++) {
            if (this.aidBoxes[i] instanceof AidBoxImp) {
                AidBoxImp temp = (AidBoxImp) this.aidBoxes[i];

                try {
                    copy[i] = temp.clone();
                } catch (CloneNotSupportedException e) {
                  //logger.log(Level.FINEST, e.getMessage(),e);
                }
            }
        }

        return copy;
    }

    @Override
    public Vehicle getVehicle() {
        return this.vehicle;
    }

    @Override
    public double getTotalDistance() {
        double distance=0;
        for(int i =0; i<this.aidBoxCount; i++){
            try {
                distance += this.aidBoxes[i].getDistance(this.aidBoxes[i+1]);
            } catch (AidBoxException ex) {
                Logger.getLogger(RouteImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return distance;
    }

    @Override
    public double getTotalDuration() {
          double duration=0;
        for(int i =0; i<this.aidBoxCount; i++){
            try {
                duration += this.aidBoxes[i].getDuration(this.aidBoxes[i+1]);
            } catch (AidBoxException ex) {
                Logger.getLogger(RouteImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return duration;
    }

    @Override
    public Report getReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    
    
     
}
