/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.pickingManagement.Report;
import java.time.LocalDateTime;

/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: Turma 4
 * 
 * Implementation of the {@link Report} interface representing a report with various metrics.
 */
public class ReportImp implements Report {

    private LocalDateTime date;
    private int freeVehicles;
    private int usedVehicles;
    private int containersPicked;
    private int containersNonPicked;
    private double totDuration;
    private double totDistance;

    /**
     * Constructs a new ReportImp instance with the specified metrics.
     *
     * @param freeVehicles the number of free vehicles
     * @param usedVehicles the number of used vehicles
     * @param containersPicked the number of picked containers
     * @param containersNonPicked the number of non-picked containers
     * @param totDuration the total duration
     * @param totDistance the total distance
     */
    public ReportImp(int freeVehicles, int usedVehicles, int containersPicked, int containersNonPicked, double totDuration, double totDistance) {
        this.date = LocalDateTime.now();
        this.freeVehicles = freeVehicles;
        this.usedVehicles = usedVehicles;
        this.containersPicked = containersPicked;
        this.containersNonPicked = containersNonPicked;
        this.totDuration = totDuration;
        this.totDistance = totDistance;
    }

    /**
     * Getter for the number of used vehicles.
     *
     * @return the number of used vehicles
     */
    @Override
    public int getUsedVehicles() {
        return this.usedVehicles;
    }

    /**
     * Getter for the number of picked containers.
     *
     * @return the number of picked containers
     */
    @Override
    public int getPickedContainers() {
        return this.containersPicked;
    }

    /**
     * Getter for the total distance.
     *
     * @return the total distance
     */
    @Override
    public double getTotalDistance() {
        return this.totDistance;
    }

    /**
     * Getter for the total duration.
     *
     * @return the total duration
     */
    @Override
    public double getTotalDuration() {
        return this.totDuration;
    }

    /**
     * Getter for the number of non-picked containers.
     *
     * @return the number of non-picked containers
     */
    @Override
    public int getNonPickedContainers() {
        return this.containersNonPicked;
    }

    /**
     * Getter for the number of free vehicles.
     *
     * @return the number of free vehicles
     */
    @Override
    public int getNotUsedVehicles() {
        return this.freeVehicles;
    }

    /**
     * Getter for the date of the report.
     *
     * @return the date of the report
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }
}
