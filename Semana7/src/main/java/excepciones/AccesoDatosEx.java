/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author carlo
 */
public class AccesoDatosEx extends Exception {
    String mensaje;
    
    public AccesoDatosEx(String msg){
        this.mensaje = msg;
    }
}
