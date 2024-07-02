/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.Container;
import com.estg.core.ContainerType;
import com.estg.pickingManagement.Vehicle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanu
 */
public class CapacityTrack {
 
    private Vehicle vehicle;
    private Container[] containers;
    private int freeContainersNumber;
    private int initContainersFree;

    public CapacityTrack(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    
    public Vehicle getVehicle() {
        try {
            return ((VehicleImp)this.vehicle).clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(CapacityTrack.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getInitContainersFree() {
        return this.initContainersFree;
    }
    
    public boolean addContainerToFree(ContainerType ct){
        for(int i=0; i<this.containers.length;i++){
           if (this.containers[i]==null){
               return false;
           }
            if(this.containers[i].getType().equals(ct)){ //ve se e do msm tipo que o contentor recebido
                this.containers[i]=null;// ocupa o espaço
                this.freeContainersNumber--;// decrementa
                return true;
            }
        }
        
        return false;
    }
    
}
