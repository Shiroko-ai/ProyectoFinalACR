/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import Server.SolicitudesServidor;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAST_
 */
public class PruebaSinGUI {
    public static void main(String []as){
        try {
                    Registry reg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            // CREAR LA REFERENCIA AL OBJ REMOTO
            SolicitudesServidor objRemoto;
            objRemoto = (SolicitudesServidor)reg.lookup("token");
                    String usuario = "usuario112132";
                    String contrasena = "contrasena1231";
                    if(objRemoto.darAcceso(usuario,contrasena)){
                        System.out.println("Entre");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(JFrameLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
        catch(NotBoundException ex){
            ex.printStackTrace();

    }
}}
