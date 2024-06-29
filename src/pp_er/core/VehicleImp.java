/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.ContainerType;
import com.estg.pickingManagement.Vehicle;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
*/
public class VehicleImp implements Vehicle {

    private String code;
    private double capacity;
    
    
    public VehicleImp (String code, double capacity){
        this.capacity = capacity;
        this.code = code;
    }
    
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public double getCapacity(ContainerType ct) {
        return this.capacity;
    }
    
}
