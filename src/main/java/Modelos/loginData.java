/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

/**
 *
 * @author Usuario
 */
public class loginData {
    public String idUsuario, nombre, apellido, DNI, users, cargo, Permiso_idPermiso;
    public loginData(String idUsuarioG, String nombreG, String apellidoG, String DNIG, String usersG, String cargoG, String Permiso_idPermisoG){
        idUsuario=idUsuarioG;
        nombre=nombreG;
        apellido=apellidoG;
        DNI=DNIG;
        users=usersG;
        cargo=cargoG;
        Permiso_idPermiso=Permiso_idPermisoG;
    }
    public String getIDusuario(){
        return idUsuario;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getDni(){
        return DNI;
    }
    public String getUsers(){
        return users;
    }
    public String getCargo(){
        return cargo;
    }
    public String getPermiso(){
        return Permiso_idPermiso;
    }
}
