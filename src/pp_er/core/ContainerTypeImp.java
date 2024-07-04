/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;
import com.estg.core.ContainerType;
import java.util.Objects;

/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: LEIT4
 * 
 * Implementation of the {@link ContainerType} interface representing a container type with a name.
 */
public class ContainerTypeImp implements ContainerType, Cloneable {

    private String name;

    /**
     * Constructs a new ContainerTypeImp instance with the specified name.
     *
     * @param name the name of the container type
     */
    public ContainerTypeImp(String name) {
        this.name = name;
    }

    /**
     * Getter for the container type name.
     *
     * @return the name of the container type
     */
  
    public String getName() {
        return name;
    }

    /**
     * Setter for the container type name.
     *
     * @param name the name to set for the container type
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of this container type.
     *
     * @return a string representation of this container type
     */
    @Override
    public String toString() {
        return "ContainerTypeImp{" + "name=" + name + '}';
    }

    /**
     * Generates a hash code for the container type.
     *
     * @return a hash code value for this container type
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.name);
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
        final ContainerTypeImp other = (ContainerTypeImp) obj;
        return Objects.equals(this.name, other.name);
    }

    /**
     * Creates and returns a deep copy of this container type.
     *
     * @return a clone of this container type
     * @throws CloneNotSupportedException if the container type cannot be cloned
     */
    @Override
    public ContainerType clone() throws CloneNotSupportedException {
        return (ContainerType) super.clone();
    }
}
