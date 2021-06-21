/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.Serializable;

/**
 *
 * @author LAST_
 */
public class Usuario implements Serializable{
    public String nombre;
    public String apellido;
    public float dinero;
    public String nombre_usuario;
    public int ID;
    public String contra;
    public Usuario(int ID, String nombre, String apellido, float dinero, String nombre_usuario,String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dinero = dinero;
        this.nombre_usuario = nombre_usuario;
        this.ID = ID;
        this.contra= contrasena;
    }

    
    
}
