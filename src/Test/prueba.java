/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author LAST_
 */
public class prueba {
    public boolean darAcceso(String usuario, String contrasena){
         boolean acceso = false;
        try{
        boolean validacionUsuario = false;
        boolean validacionContrasena = false;
       
        int contador = 0;
        
        BufferedReader bfu = new BufferedReader(new FileReader("C:/Users/LAST_/Downloads/usuarios.txt"));
        BufferedReader bfc = new BufferedReader(new FileReader("C:/Users/LAST_/Downloads/contrasenas.txt"));
        String u = bfu.readLine();
        String c = null;
            while(u!=null){//recorre los posibles usuarios a ver si hay un match
            if(u.equals(usuario)){
                validacionUsuario = true;
                break;
            }else{
                u = bfu.readLine();
                contador++;
            }
          
            }
           System.out.println(validacionUsuario); 
            bfu.close();
            if(validacionUsuario){
            for(int i = 0;i<=contador;i++){//recorre las contrasenas hasta llegar a la asignada
                c = bfc.readLine();
            }
           
            bfc.close();
            if(c.equals(contrasena)){
                validacionContrasena = true;
            }
            if(validacionContrasena && validacionUsuario){
                acceso = true;
            }}
            else{
                acceso = false;
            }
             }catch(IOException e){
            
            }
            
        return acceso;
    }
    public static void main(String []as){
        prueba p = new prueba();
        System.out.println(p.darAcceso("usuario1","contrasena1"));
    }
}
