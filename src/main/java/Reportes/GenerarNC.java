/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import BaseDeDatos.ConexionMySQL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.net.URL;

/**
 *
 * @author gustavo
 */
public class GenerarNC {
            ConexionMySQL mysql= new ConexionMySQL();
            Connection cn= mysql.Conectar();
            
   
     public void reporteNC(int idCierre)
    {
        try {
            JasperReport reportes = JasperCompileManager.compileReport("C:\\PlanesPotigian\\Reportes\\ReporteNC.jrxml");
            Map parametro= new HashMap();
            parametro.clear();
            parametro.put("idCierre", idCierre);
            JasperPrint j = JasperFillManager.fillReport(reportes, parametro, this.cn);
            JasperViewer jv= new JasperViewer(j, false);
            jv.setTitle("Nota de Crédito");
            jv.setVisible(true);          
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar el reporte " + e);
        
        } 
    }
     public void reporteNCF(int idCierre)
    {
        try {
            JasperReport reportes = JasperCompileManager.compileReport("C:\\PlanesPotigian\\Reportes\\ReporteNCF.jrxml");
            Map parametro= new HashMap();
            parametro.clear();
            parametro.put("idCierre", idCierre);
            JasperPrint j = JasperFillManager.fillReport(reportes, parametro, this.cn);
            JasperViewer jv= new JasperViewer(j, false);
            jv.setTitle("Nota de Crédito");
            jv.setVisible(true);          
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error al mostrar el reporte " + e);
        
        } 
    }
    
}


