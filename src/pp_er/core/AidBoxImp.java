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
* Turma: Turma 4
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

    public AidBoxImp(String code, String zone) {
        this.code = code;
        this.zone = zone;
        this.containers = new Container[INIT_CONTAINER_SIZE];
        this.containersCount = 0;
        this.paths = new Path[INIT_PATHS_SIZE];
        this.pathCount = 0;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getZone() {
        return this.zone;
    }

    public int getNumberContainers() {
        return this.containersCount;
    }

    @Override
    public double getDistance(AidBox aidbox) throws AidBoxException {
        
        double distance = 0;
        //System.err.println("Finding distance for: " + aidbox);
        for (int i = 0; i < this.pathCount; i++) {
            //System.err.println("Checking path: " + this.paths[i]);
            //System.err.println("AidBox in path: " + this.paths[i].getAidBox());
            if (this.paths[i].getAidBox().equals(aidbox)) {
                distance= this.paths[i].getDistance();
              
                 return distance;
            }
           
        }
        
       // System.err.println("AidBox not found: " + aidbox);
        throw new AidBoxException("Path to AidBox not found");
    }
    @Override
    public double getDuration(AidBox aidbox) throws AidBoxException {
        for (int i = 0; i < this.pathCount; i++) {
            if (this.paths[i].getAidBox().equals(aidbox)) {
                return this.paths[i].getDuration();
            }
        }
        throw new AidBoxException("Path to  AidBox not found");
    }

    private void expandContainers() {
        Container[] temp = new Container[this.containersCount * GROWTH];

        for (int i = 0; i < this.containersCount; i++) {
            temp[i] = this.containers[i];
        }

        this.containers = temp;
    }

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

    @Override
    public Container getContainer(ContainerType ct) {
        for (int i = 0; i < this.containersCount; i++) {
            if (this.containers[i].equals(ct)) {
                return this.containers[i];
            }
        }
        return null;
    }

    @Override
    public Container[] getContainers() {
        Container[] result = new Container[this.containersCount];
        for (int i = 0; i < this.containersCount; i++) {
            result[i] = this.containers[i];
        }
        return result;
    }

    private int found(Container cntnr) {
        for (int i = 0; i < this.containersCount; i++) {
            if (this.containers[i].equals(cntnr)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeContainer(Container cntnr) throws AidBoxException {

        int index = found(cntnr);

        if (cntnr == null) {
            throw new AidBoxException("Container is null");
        }

        if (index == -1) {
            throw new AidBoxException("Container doesnt exist");
        }

        for (int i = index; i < this.containersCount - 1; i++) {
            this.containers[i] = this.containers[i + 1];
        }

        this.containers[--this.containersCount] = null;

    }

    private void expandPaths() {
        Path[] temp = new Path[this.pathCount * GROWTH];

        for (int i = 0; i < this.pathCount; i++) {
            temp[i] = this.paths[i];
        }

        this.paths = temp;
    }

    public AidBox clone() throws CloneNotSupportedException {
        AidBoxImp clone = (AidBoxImp) super.clone();
        clone.containers = this.getContainers();

        return clone;
    }

    //EXECPIONS fazer
    public void addPath(Path path) throws PathExeption {

        if (path == null) {
            throw new PathExeption("path is null");
        }

        if (this.pathCount == this.paths.length) {
            expandPaths();
        }
        this.paths[this.pathCount++] = path;
    }

    public Path[] getPaths() {
        Path[] result = new Path[this.pathCount];
        for (int i = 0; i < this.pathCount; i++) {
            result[i] = this.paths[i];
        }
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.code);
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
        final AidBoxImp other = (AidBoxImp) obj;
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
