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
* Turma: Turma 4
*/

public class MeasurementImp implements Measurement {
    
    private  LocalDateTime date;
    private  double value;
    
    
    public MeasurementImp(LocalDateTime date, double value) {
        this.date = date;
        this.value = value;
    }
     
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public double getValue() {
        return this.value;
    }

     public MeasurementImp copyMeasurement() {
       
        return new MeasurementImp(this.date, this.getValue());
    }
     
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.date);
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
        final MeasurementImp other = (MeasurementImp) obj;
        return Objects.equals(this.date, other.date);
    }

    @Override
    public String toString() {
        return "MeasurementImp{" + "date=" + date + ", value=" + value + '}';
    }
    
}
