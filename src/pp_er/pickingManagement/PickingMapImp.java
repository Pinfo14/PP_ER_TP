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
* Turma: Turma 4
*/

public class PickingMapImp implements PickingMap {
    
    private LocalDateTime date;
    private Route[] routes;
    private int numRoutes;

    
    public PickingMapImp (Route[] routes, LocalDateTime date) {
       this.date=date;
        this.routes = routes;
        this.numRoutes = routes.length;
    }
    
    @Override
    public LocalDateTime getDate() {
       return this.date;
    }

    @Override
    public Route[] getRoutes() {
         return this.routes;
    }
    
}
