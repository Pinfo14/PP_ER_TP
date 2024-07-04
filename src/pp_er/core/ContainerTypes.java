/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.ContainerType;

/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: LEIT4
 * 
 * This class manages an array of {@link ContainerType} objects.
 */
public class ContainerTypes {
    
    private static final int INIT_TYPES = 5; // Initial capacity for the container types array
    
    private ContainerType[] containerType;
    private int size;

    /**
     * Constructs a new ContainerTypes instance with the initial capacity.
     */
    public ContainerTypes() {
        this.containerType = new ContainerType[INIT_TYPES];
        this.size = 0;
    }

    /**
     * Gets the array of container types.
     *
     * @return the array of container types
     */
    public ContainerType[] getContainerType() {
        return this.containerType;
    }

    /**
     * Adds a new container type to the array.
     * Expands the array if it is full.
     *
     * @param type the container type to add
     * @return {@code true} if the container type was added successfully
     */
    public boolean addType(ContainerType type) {
        if (this.size == this.containerType.length) {
            expandContainerTypes();
        }
        this.containerType[this.size++] = type;
        return true;
    }

    /**
     * Expands the capacity of the container types array.
     */
    private void expandContainerTypes() {
        ContainerType[] newContainerTypes = new ContainerType[this.containerType.length * 2];
        for (int i = 0; i < this.containerType.length; i++) {
            newContainerTypes[i] = this.containerType[i];
        }
        this.containerType = newContainerTypes;
    }
}
