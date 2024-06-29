/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.core;

/**
 *
 * @author emanu
 */
public class Path {
    
    private String aidBoxCode;
    private double distance;
    private double duration;

    public Path(String aidBoxCode,double duration,double distance) {
       this.aidBoxCode = aidBoxCode;
       this.distance=distance;
       this.duration=duration;
    }

    public double getDistance() {
        return this.distance;
    }

    public double getDuration() {
        return this.duration;
    }
    public String getAidBoxCode() {
        return this.aidBoxCode;
    }
    
}
