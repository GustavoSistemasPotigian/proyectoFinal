/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoComprasPagos;

import BaseDeDatos.*;
import java.sql.*;
import Modelos.VentanaPrincipalPanel;
import Modelos.IngresoAlSistema;

/**
 *
 * @author usuario
 */
public class ProyectoComprasPagos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ConexionMySQL mysql = new ConexionMySQL();
        Connection cn = mysql.Conectar();
        
        //MODIFICADO POR PANTALLA DE LOGIN
        //VentanaPrincipalPanel ventana = new VentanaPrincipalPanel();
        IngresoAlSistema ventana = new IngresoAlSistema();
        ventana.setVisible(true);

    }

}
