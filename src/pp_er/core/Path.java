/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.AidBox;

/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: LEIT4
 * 
 * This class represents a path with an aid box, distance, and duration.
 */
public class Path {

    private AidBox aidbox;
    private double distance;
    private double duration;

    /**
     * Constructs a new Path instance with the specified aid box, distance, and duration.
     *
     * @param aidbox the aid box associated with the path
     * @param distance the distance of the path
     * @param duration the duration of the path
     */
    public Path(AidBox aidbox, double distance, double duration) {
        this.aidbox = aidbox;
        this.distance = distance;
        this.duration = duration;
    }

    /**
     * Getter for the distance of the path.
     *
     * @return the distance of the path
     */
    public double getDistance() {
        return this.distance;
    }

    /**
     * Getter for the duration of the path.
     *
     * @return the duration of the path
     */
    public double getDuration() {
        return this.duration;
    }

    /**
     * Getter for the aid box associated with the path.
     *
     * @return the aid box associated with the path
     */
    public AidBox getAidBox() {
        return this.aidbox;
    }

    /**
     * Returns a string representation of this path.
     *
     * @return a string representation of this path
     */
    @Override
    public String toString() {
        return "Path{aidBox=" + aidbox + ", distance=" + distance + ", duration=" + duration + "}";
    }
}
