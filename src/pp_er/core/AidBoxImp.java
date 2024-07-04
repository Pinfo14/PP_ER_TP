/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.AidBox;
import com.estg.core.Container;
import com.estg.core.ContainerType;
import com.estg.core.exceptions.AidBoxException;
import com.estg.core.exceptions.ContainerException;
import java.util.Objects;
import pp_er.exepcions.PathExeption;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: LEIT4
 */


/**
 * Implementation of the {@link AidBox} interface representing an aid box with containers and paths.
 */
public class AidBoxImp implements AidBox {

    public static final int INIT_CONTAINER_SIZE = 5;
    public static final int INIT_PATHS_SIZE = 5;
    public static final int GROWTH = 2;

    private String code;
    private String zone;
    private Path[] paths;
    private int pathCount;
    private Container[] containers;
    private int containersCount;

    /**
     * Constructs a new AidBoxImp instance with the specified code.
     *
     * @param code the code of the aid box
     */
    public AidBoxImp(String code) {
        this.code = code;
        this.containers = new Container[INIT_CONTAINER_SIZE];
        this.containersCount = 0;
        this.paths = new Path[INIT_PATHS_SIZE];
        this.pathCount = 0;
    }

    /**
     * Getter for the AidBox code.
     *
     * @return the AidBox code.
     */
    @Override
    public String getCode() {
        return this.code;
    }

    /**
     * Getter for the AidBox zone.
     *
     * @return the AidBox zone.
     */
    @Override
    public String getZone() {
        return this.zone;
    }

    /**
     * Getter for the number of containers in the AidBox.
     *
     * @return the number of containers.
     */
    public int getNumberContainers() {
        return this.containersCount;
    }

    /**
     * Gets the distance to another {@link AidBox}.
     *
     * @param aidbox the aid box to which the distance is measured
     * @return the distance to the specified aid box
     * @throws AidBoxException if the path to the specified aid box is not found
     */
    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        for (int i = 0; i < this.pathCount; i++) {
            if (this.paths[i].getAidBox().equals(aidbox)) {
                return this.paths[i].getDistance();
            }
        }
        return 0;
    }

    /**
     * Gets the duration to another {@link AidBox}.
     *
     * @param aidbox the aid box to which the duration is measured
     * @return the duration to the specified aid box
     * @throws AidBoxException if the path to the specified aid box is not found
     */
    @Override
    public double getDuration(AidBox aidbox) throws AidBoxException {
        for (int i = 0; i < this.pathCount; i++) {
            if (this.paths[i].getAidBox().equals(aidbox)) {
                return this.paths[i].getDuration();
            }
        }
        throw new AidBoxException("Path to AidBox not found");
    }

    /**
     * Expands the capacity of the container array.
     */
    private void expandContainers() {
        Container[] temp = new Container[this.containersCount * GROWTH];
        for (int i = 0; i < this.containersCount; i++) {
            temp[i] = this.containers[i];
        }
        this.containers = temp;
    }

    /**
     * Adds a new container to the aid box.
     *
     * @param cntnr the container to add
     * @return {@code true} if the container was added, {@code false} if a container of the same type already exists
     * @throws ContainerException if the container is null
     */
    @Override
    public boolean addContainer(Container cntnr) throws ContainerException {
        if (cntnr == null) {
            throw new ContainerException("Container is null");
        }

        if (this.getContainer(cntnr.getType()) != null) {
            return false;
        }

        if (this.containersCount == this.containers.length) {
            expandContainers();
        }

        this.containers[this.containersCount++] = cntnr;
        return true;
    }

    /**
     * Gets the container of the specified type.
     *
     * @param ct the type of the container
     * @return the container of the specified type, or {@code null} if no such container exists
     */
    @Override
    public Container getContainer(ContainerType ct) {
        for (int i = 0; i < this.containersCount; i++) {
            if (this.containers[i].getType().equals(ct)) {
                return this.containers[i];
            }
        }
        return null;
    }

    /**
     * Gets all containers in the aid box.
     *
     * @return an array of containers in the aid box
     */
    @Override
    public Container[] getContainers() {
        Container[] result = new Container[this.containersCount];
        for (int i = 0; i < this.containersCount; i++) {
            result[i] = this.containers[i];
        }
        return result;
    }

    /**
     * Finds the index of the specified container.
     *
     * @param cntnr the container to find
     * @return the index of the container, or -1 if the container is not found
     */
    private int found(Container cntnr) {
        for (int i = 0; i < this.containersCount; i++) {
            if (this.containers[i].equals(cntnr)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes the specified container from the aid box.
     *
     * @param cntnr the container to remove
     * @throws AidBoxException if the container is null or does not exist in the aid box
     */
    @Override
    public void removeContainer(Container cntnr) throws AidBoxException {
        int index = found(cntnr);

        if (cntnr == null) {
            throw new AidBoxException("Container is null");
        }

        if (index == -1) {
            throw new AidBoxException("Container doesn't exist");
        }

        for (int i = index; i < this.containersCount - 1; i++) {
            this.containers[i] = this.containers[i + 1];
        }

        this.containers[--this.containersCount] = null;
    }

    /**
     * Expands the capacity of the paths array.
     */
    private void expandPaths() {
        Path[] temp = new Path[this.pathCount * GROWTH];
        for (int i = 0; i < this.pathCount; i++) {
            temp[i] = this.paths[i];
        }
        this.paths = temp;
    }

    /**
     * Clones the aid box.
     *
     * @return a clone of the aid box
     * @throws CloneNotSupportedException if the aid box cannot be cloned
     */
    public AidBox clone() throws CloneNotSupportedException {
        AidBoxImp clone = (AidBoxImp) super.clone();
        clone.containers = this.getContainers();
        return clone;
    }

    /**
     * Adds a path to the aid box.
     *
     * @param path the path to add
     * @throws PathExeption if the path is null
     */
    public void addPath(Path path) throws PathExeption {
        if (path == null) {
            throw new PathExeption("path is null");
        }

        if (this.pathCount == this.paths.length) {
            expandPaths();
        }
        this.paths[this.pathCount++] = path;
    }

    /**
     * Gets all paths of the aid box.
     *
     * @return an array of paths
     */
    public Path[] getPaths() {
        Path[] result = new Path[this.pathCount];
        for (int i = 0; i < this.pathCount; i++) {
            result[i] = this.paths[i];
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AidBoxImp other = (AidBoxImp) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {

        String s = "AidBoxImp{";
        s += "code='" + code + '\'';
        s += ", zone='" + zone + '\'';
        s += ", containersCount=" + containersCount;
        s += ", containers=[";

        for (int i = 0; i < containersCount; i++) {
            s += containers[i];
            if (i < containersCount - 1) {
                s += ", ";
            }
        }

        s += "], pathCount=" + pathCount;
        s += ", paths=[";

        for (int i = 0; i < pathCount; i++) {
            s += paths[i];
            if (i < pathCount - 1) {
                s += ", ";
            }
        }

        s += "]}";
        return s;

    }

}
