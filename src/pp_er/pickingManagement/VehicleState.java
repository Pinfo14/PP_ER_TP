/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: Turma 4
*/
public enum VehicleState {
    ENABLE,DISABLE;

    @Override
    public String toString() {
      switch(this){
          case ENABLE:
              return "Enable";
      case DISABLE:
              return "Disable";
      }
      
      return null;
    }
    
    
}
