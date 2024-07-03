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
    private int measurementsCount;

    public ContainerImp(String code, double capacity, ContainerType type) {
        this.code = code;
        this.capacity = capacity;
        this.type = type;
        this.measurements = new Measurement[INIT_MEASUREMENTS_SIZE];
        this.measurementsCount = 0;
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

    public Measurement[] getMeasurements() {
        Measurement[] result = new Measurement[this.measurementsCount];
        for (int i = 0; i < this.measurementsCount; i++) {
            result[i] = ((MeasurementImp) measurements[i]).copyMeasurement();
        }
        return result;
    }

    @Override
    public Measurement[] getMeasurements(LocalDate date) {
        Measurement[] temp = new Measurement[this.measurementsCount];
        int count = 0;
        for (int i = 0; i < this.measurementsCount; i++) {
            if (measurements[i].getDate().toLocalDate().equals(date)) {
                temp[count++] = ((MeasurementImp) measurements[i]).copyMeasurement();
            }
        }
        Measurement[] result = new Measurement[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    @Override
    public boolean addMeasurement(Measurement msrmnt) throws MeasurementException {
        if (msrmnt == null) {
            throw new MeasurementException("Measurement is null");
        }
        if (msrmnt.getValue() < 0) {
            throw new MeasurementException("Measurement value is less than 0");
        }
        if (this.measurementsCount > 0 && msrmnt.getDate().isBefore(this.measurements[measurementsCount - 1].getDate())) {
            throw new MeasurementException("Measurement date is before the last date");
        }

        if (this.measurementsCount == this.measurements.length) {
            expandMeasurements();
        }

        this.measurements[this.measurementsCount++] = msrmnt;
        return true;
    }
    
    

    private void expandMeasurements() {
        Measurement[] temp = new Measurement[this.measurementsCount * GROWTH];

        for (int i = 0; i < this.measurementsCount; i++) {
            temp[i] = this.measurements[i];
        }

        this.measurements = temp;
    }
    
    public Measurement getLastMeasurement(){
        return this.measurements[this.measurementsCount-1];
    }

    public Container clone() throws CloneNotSupportedException {
        ContainerImp clone = (ContainerImp) super.clone();
        clone.measurements = this.getMeasurements();

        return clone;
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

    @Override
    public String toString() {
        return "ContainerImp{" + "code=" + code + ", capacity=" + capacity + ", type=" + type + ", measurements=" + measurements + ", measurementsCount=" + measurementsCount + '}';
    }

}
