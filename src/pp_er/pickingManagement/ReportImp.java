/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.pickingManagement.Report;
import java.time.LocalDateTime;

/**
 *
 * @author emanu
 */
public class ReportImp implements Report {

    private LocalDateTime date;
    private int freeVehicles;
    private int usedVehicles;
    private int containersPicked;
    private int containersNonPicked;
    private double totDuration;
    private double totDistance;

    public ReportImp(LocalDateTime date, int freeVehicles, int usedVehicles, int containersPicked, int containersNonPicked, double totDuration, double totDistance) {
        this.date = date;
        this.freeVehicles = freeVehicles;
        this.usedVehicles = usedVehicles;
        this.containersPicked = containersPicked;
        this.containersNonPicked = containersNonPicked;
        this.totDuration = totDuration;
        this.totDistance = totDistance;
    }
    
    
    
    
    @Override
    public int getUsedVehicles() {
       return this.usedVehicles;
    }

    @Override
    public int getPickedContainers() {
       return this.containersPicked;
    }

    @Override
    public double getTotalDistance() {
        return this.totDistance;
    }

    @Override
    public double getTotalDuration() {
       return this.totDuration;
    }

    @Override
    public int getNonPickedContainers() {
        return this.containersNonPicked;
    }

    @Override
    public int getNotUsedVehicles() {
         return this.freeVehicles;
    }

    @Override
    public LocalDateTime getDate() {
         return this.date;
    }
    
}
