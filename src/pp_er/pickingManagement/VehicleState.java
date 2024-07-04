/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pp_er.pickingManagement;

/* 
* Nome: Emanuel Jose Teixeira Pinto
* Número: 8230371
* Turma: LEIT4
*/

/**
 * Enum representing the state of a vehicle.
 */
public enum VehicleState {
    ENABLE,
    DISABLE;

    /**
     * Returns a string representation of the vehicle state.
     *
     * @return "Enable" if the state is ENABLE, "Disable" if the state is DISABLE
     */
    @Override
    public String toString() {
        switch (this) {
            case ENABLE:
                return "Enable";
            case DISABLE:
                return "Disable";
            default:
                return null;
        }
    }
}
