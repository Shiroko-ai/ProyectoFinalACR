/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Cliente.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author LAST_
 */
public class ObjetoRemoto extends UnicastRemoteObject implements SolicitudesServidor {

    ArrayList<Usuario> users = new ArrayList<>();

    //Constructor
    public ObjetoRemoto() throws RemoteException {
        super();
        try {
            //Bufers de Datos
            BufferedReader bfu = new BufferedReader(new FileReader("usuarios.txt"));
            BufferedReader bfc = new BufferedReader(new FileReader("contrasenas.txt"));
            BufferedReader bfn = new BufferedReader(new FileReader("nombres.txt"));
            BufferedReader bfa = new BufferedReader(new FileReader("apellidos.txt"));
            BufferedReader bfd = new BufferedReader(new FileReader("dinero.txt"));
            
            int contador = 0;     
            String c = bfc.readLine();
            String u = bfu.readLine();
            String n = bfn.readLine();
            String a = bfa.readLine();
            String d = bfd.readLine();
            
            //Rellenar la tabla de usuarios
            while (u != null) {
                users.add(new Usuario(contador, n, a, Float.parseFloat(d), u, c));

                c = bfc.readLine();
                u = bfu.readLine();
                n = bfn.readLine();
                a = bfa.readLine();
                d = bfd.readLine();
                contador++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }

    }

    public boolean darAcceso(String usuario, String contrasena) throws RemoteException {
        boolean acceso = false;
        try {
            boolean validacionUsuario = false;
            boolean validacionContrasena = false;

            int contador = 0;

            //Buffers de Usuario y contraseñas
            BufferedReader bfu = new BufferedReader(new FileReader("usuarios.txt"));
            BufferedReader bfc = new BufferedReader(new FileReader("contrasenas.txt"));
            String u = bfu.readLine();
            String c = null;
            
            while (u != null) {//recorre los posibles usuarios a ver si hay un match
                if (usuario.compareTo(u) == 0) {
                    validacionUsuario = true;
                    break;
                } else {
                    u = bfu.readLine();
                    contador++;
                }

            }
            
            System.out.println(validacionUsuario);
            bfu.close();
            if (validacionUsuario) {
                for (int i = 0; i <= contador; i++) {//recorre las contrasenas hasta llegar a la asignada
                    c = bfc.readLine();
                }

                bfc.close();
                if (contrasena.compareTo(c) == 0) {
                    validacionContrasena = true;
                }
                if (validacionContrasena && validacionUsuario) {
                    acceso = true;
                }
            } else {
                acceso = false;
            }
        } catch (IOException e) {

        }

        return acceso;
    }

    public Usuario buscarUsuario(String nombre, String contrasena) throws RemoteException {
        Usuario u = null;

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).nombre_usuario.compareTo(nombre) == 0 && users.get(i).contra.compareTo(contrasena) == 0) {
                u = users.get(i);
            }
        }
        return u;
    }

    public String retirarDinero(float cantidad, Usuario u) throws RemoteException {
        String retiro = null;
        try {
            BufferedReader bfd = new BufferedReader(new FileReader("dinero.txt"));
            
            if (u.dinero > cantidad) {
                BufferedWriter writer = new BufferedWriter(new FileWriter("dinero.txt"));
                users.get(u.ID).dinero -= cantidad;

                for (int i = 0; i < this.users.size(); i++) {
                    writer.write("" + users.get(i).dinero);
                    System.out.println("Cuenta " + i + ": " + users.get(i).dinero);
                    writer.newLine();
                    writer.flush();
                }
                writer.close();
                retiro = "La transaccion ha sido exitosa, su saldo actual es de: " + users.get(u.ID).dinero;
            } else {
                retiro = "Lo sentimos, no cuenta con el dinero para esa transaccion";
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            return retiro;
        }
    }

    public String depositarDinero(float cantidad, Usuario u) throws RemoteException {
        String retiro = null;
        try {
            BufferedReader bfd = new BufferedReader(new FileReader("dinero.txt"));

            BufferedWriter writer = new BufferedWriter(new FileWriter("dinero.txt"));
            users.get(u.ID).dinero += cantidad;

            for (int i = 0; i < this.users.size(); i++) {
                writer.write("" + users.get(i).dinero);
                System.out.println("Cuenta " + i + ": " + users.get(i).dinero);
                writer.newLine();
                writer.flush();
            }
            writer.close();
            retiro = "La transaccion ha sido exitosa, su saldo actual es de: " + users.get(u.ID).dinero;
        } catch (IOException e) {
            retiro = "La transaccion ha sido rechazada";
            e.printStackTrace();
        } finally {
            return retiro;
        }
    }

    public String getNombre(int ID) throws RemoteException {
        return this.users.get(ID).nombre;
    }

    public String getApellido(int ID) throws RemoteException {

        return this.users.get(ID).apellido;
    }

    public float getDinero(int ID) throws RemoteException {

        return this.users.get(ID).dinero;
    }

    public String getNombre_usuario(int ID) throws RemoteException {

        return this.users.get(ID).nombre_usuario;
    }

    public int getID(int ID) throws RemoteException {
        return ID;
    }

    public String getContra(int ID) throws RemoteException {
        return this.users.get(ID).contra;
    }
}
