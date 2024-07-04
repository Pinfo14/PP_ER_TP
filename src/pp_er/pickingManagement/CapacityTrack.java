/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.core.ContainerType;

/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: LEIT4
 * 
 * This class tracks the capacity of containers for a specific type.
 */
public class CapacityTrack {

    private ContainerType type;
    private int emptyContainers;

    /**
     * Constructs a new CapacityTrack instance with the specified container type.
     *
     * @param type the type of the container
     */
    public CapacityTrack(ContainerType type) {
        this.type = type;
    }

    /**
     * Sets the number of empty containers.
     *
     * @param emptyContainers the number of empty containers to set
     */
    public void setCapacity(int emptyContainers) {
        this.emptyContainers = emptyContainers;
    }

    /**
     * Gets the number of empty containers.
     *
     * @return the number of empty containers
     */
    public int getEmptyContainers() {
        return this.emptyContainers;
    }

    /**
     * Gets the container type.
     *
     * @return the container type
     */
    public ContainerType getType() {
        return type;
    }
}
