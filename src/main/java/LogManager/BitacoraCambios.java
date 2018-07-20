/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LogManager;

import BaseDeDatos.ConexionMySQL;
import Modelos.Articulos;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class BitacoraCambios {
    /*public BitacoraCambios(String tabla){
        insertData(tabla);
    }*/
    public void insertData(String tabla){
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        //PREPARO CONSULTA PARA OBTENER FECHA
        String queryAP="insert into data_updates values(now(),'"+tabla+"', null,1)";
        try {
            Statement st= cn.createStatement();
            int rs2 = st.executeUpdate(queryAP);
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
