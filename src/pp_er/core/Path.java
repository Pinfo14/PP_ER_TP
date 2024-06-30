/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

import com.estg.core.AidBox;


/**
 * Nome: Emanuel Jose Teixeira Pinto
 * Número: 8230371
 * Turma: Turma 4
 */
public class Path {
    
    private AidBox aidbox;
    private double distance;
    private double duration;

    
    public Path(AidBox aidbox, double distance, double duration) {
        this.aidbox = aidbox;
        this.distance = distance;
        this.duration = duration;
    }

    
    public double getDistance() {
        return this.distance;
    }

    public double getDuration() {
        return this.duration;
    }

    public AidBox getAidBoxCode() {
        return this.aidbox;
    }
}
