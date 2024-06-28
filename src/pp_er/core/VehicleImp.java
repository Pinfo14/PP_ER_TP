/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.ContainerType;
import com.estg.pickingManagement.Vehicle;

/**
 *
 * @author emanu
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
