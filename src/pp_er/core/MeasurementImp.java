/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.Measurement;
import java.time.LocalDateTime;
import java.util.Objects;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: LEIT4
*/

/**
 * Implementation of the {@link Measurement} interface representing a measurement with a date and value.
 */
public class MeasurementImp implements Measurement {

    private LocalDateTime date;
    private double value;

    /**
     * Constructs a new MeasurementImp instance with the specified date and value.
     *
     * @param date the date of the measurement
     * @param value the value of the measurement
     */
    public MeasurementImp(LocalDateTime date, double value) {
        this.date = date;
        this.value = value;
    }

    /**
     * Getter for the measurement date.
     *
     * @return the date of the measurement
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Getter for the measurement value.
     *
     * @return the value of the measurement
     */
    @Override
    public double getValue() {
        return this.value;
    }

    /**
     * Creates and returns a copy of this measurement.
     *
     * @return a copy of this measurement
     */
    public MeasurementImp copyMeasurement() {
        return new MeasurementImp(this.date, this.getValue());
    }

    /**
     * Generates a hash code for the measurement.
     *
     * @return a hash code value for this measurement
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.date);
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise
     */
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
        final MeasurementImp other = (MeasurementImp) obj;
        return Objects.equals(this.date, other.date);
    }

    /**
     * Returns a string representation of this measurement.
     *
     * @return a string representation of this measurement
     */
    @Override
    public String toString() {
        return "MeasurementImp{" + "date=" + date + ", value=" + value + '}';
    }
}
