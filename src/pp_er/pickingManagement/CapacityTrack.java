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
    private ContainerType type;
    private int emptyContainers;
    
    public CapacityTrack(ContainerType type){
        this.type = type;
    }
    
    public void setCapacity(int emptyContainers){
        this.emptyContainers=emptyContainers;
    }
   
    
    public int getEmptyContainers(){
        return this.emptyContainers;
    }

    public ContainerType getType() {
        return type;
    }
    
    
}
