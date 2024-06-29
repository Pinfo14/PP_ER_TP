/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.Container;
import com.estg.core.ContainerType;
import com.estg.core.Measurement;
import com.estg.core.exceptions.MeasurementException;
import java.time.LocalDate;
import java.util.Objects;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
*/
public class ContainerImp implements Container {

    public static final int INIT_MEASUREMENTS_SIZE = 5;
    public static final int GROWTH = 2;

    private String code;
    private double capacity;
    private ContainerType type;
    private Measurement[] measurements;
    private int numberOfMeasurements;

    public ContainerImp(String code, double capacity, ContainerType type) {
        this.code = code;
        this.capacity = capacity;
        this.type = type;
        this.measurements = new Measurement[INIT_MEASUREMENTS_SIZE];
        this.numberOfMeasurements = 0;
    }
    
   
    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public double getCapacity() {
        return this.capacity;
    }

    @Override
    public ContainerType getType() {
        return this.type;
    }

    @Override
    public Measurement[] getMeasurements() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Measurement[] getMeasurements(LocalDate ld) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.code);
        hash = 89 * hash + Objects.hashCode(this.type);
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
        final ContainerImp other = (ContainerImp) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return Objects.equals(this.type, other.type);
    }
    

}
