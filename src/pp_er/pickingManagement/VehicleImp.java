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
public class VehicleImp implements Vehicle, Cloneable {

    private String code;
    private VehicleCargo[] vehiclecargo;
    private VehicleState state;
    
    
    public VehicleImp (String code,VehicleCargo[] vehiclecargo){
        this.code = code;
        this.state = VehicleState.ENABLE;
        this.vehiclecargo = vehiclecargo;
    }
    
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public double getCapacity(ContainerType ct) {
        for(int i=0; i<this.vehiclecargo.length;i++){
            this.vehiclecargo[i].equals(ct);
             return this.vehiclecargo[i].getCapacity();
        }
       return -1;
    }
    
    public void setState(VehicleState state){
        this.state=state;
    }
    
    public VehicleState getState(){
        return this.state;
    }
    
    public VehicleCargo[] getCargo(){
        return this.vehiclecargo;
    }

      @Override
    public Vehicle clone() throws CloneNotSupportedException {
        return (Vehicle) super.clone();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.code);
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
        final VehicleImp other = (VehicleImp) obj;
        return Objects.equals(this.code, other.code);
    }
    
    
}
