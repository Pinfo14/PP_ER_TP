/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

import com.estg.pickingManagement.PickingMap;
import com.estg.pickingManagement.Route;
import java.time.LocalDateTime;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: LEIT4
*/

/**
 * Implementation of the {@link PickingMap} interface representing a picking map with routes and a date.
 */
public class PickingMapImp implements PickingMap {
    
    private LocalDateTime date;
    private Route[] routes;
    private int numRoutes;

    /**
     * Constructs a new PickingMapImp instance with the specified routes and date.
     *
     * @param routes the routes for the picking map
     * @param date the date of the picking map
     */
    public PickingMapImp(Route[] routes, LocalDateTime date) {
        this.date = date;
        this.routes = routes;
        this.numRoutes = routes.length;
    }

    /**
     * Getter for the date of the picking map.
     *
     * @return the date of the picking map
     */
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    /**
     * Getter for the routes of the picking map.
     *
     * @return an array of routes
     */
    @Override
    public Route[] getRoutes() {
        return this.routes;
    }
}
