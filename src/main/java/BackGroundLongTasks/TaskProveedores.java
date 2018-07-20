/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackGroundLongTasks;

import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.conexionSQLServer;
import LogManager.BitacoraCambios;
import Modelos.Articulos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author Usuario
 */
public class TaskProveedores extends SwingWorker<Integer,String> {
    private JLabel lblProgreso;
    private JLabel lblProgresoP;
    private JLabel lblProgresoAP;
    private JProgressBar pbAvance;
    private Integer avance=0;
    
     @Override
  protected Integer doInBackground() throws Exception {
    // Start
    publish("Ejecutando proceso...");
    publish("INICIANDO");
    setProgress(1);
    publish("Ejecutando proceso en background...");
    refreshP();
    publish("FINALIZADO");
    setProgress(100);
    return 1;
    }
    @Override
    protected void process(List< String> chunks) {
      // Messages received from the doInBackground() (when invoking the publish() method)
        for (final String string : chunks) {
            //if(string.equals("INICIANDO")){
            //    avance=0;
            //}else
            if(string.equals("FINALIZADO")){
                avance=100; 
                lblProgresoP.setText("ACTUALIZADO");
                BitacoraCambios bc = new BitacoraCambios();
                bc.insertData("proveedores");
                lblProgresoAP.setText("ACTUALIZANDO...");
            }else{
              avance=avance+10;
            }    
            lblProgreso.setText(string);
            pbAvance.setValue(avance);
        }
    }
    public TaskProveedores(JLabel etiqueta,JLabel etiquetaP, JLabel etiquetaAP,JProgressBar barraProg){
      this.lblProgreso=etiqueta;
      this.pbAvance=barraProg;
      this.lblProgresoP=etiquetaP;
      this.lblProgresoAP=etiquetaAP;
    }
    private void refreshP(){
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        
        //PREPARO CONSULTA PARA OBTENER FECHA
        publish("Vaciando PROVEEDOR...");
        String queryAP="truncate table proveedor";
        //OBTENGO FECHA Y LA CARGO EN EL LABEL
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(queryAP);
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        publish("Tabla proveedores limpia");
        try {
            copyP();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //queryAP="TRAIGO tOdos lOS DATOS DEL SQL y los inserto en mysql";
    }
    public void copyP() throws SQLException {
        conexionSQLServer sqlFrom = new conexionSQLServer();
        Connection from = sqlFrom.Conectar();
        ConexionMySQL mysqlTo= new ConexionMySQL();
        Connection to= mysqlTo.Conectar();
        try (
                //TRAER LOS DATOS CORRECTOS DEL PROVEEDOR
         PreparedStatement s1 = from.prepareStatement("select IDProveedor as idproveedor, " +
                                                        "RazonSocial as razon_social, " +
                                                        "Dir_Calle as direccion_calle, " +
                                                        "null as direccion_nro, " +
                                                        "null as cod_postal, " +
                                                        "Localidad as localidad, " +
                                                        "CUIT " +
                                                        "from Proveedores");
         ResultSet rs = s1.executeQuery()) {
         ResultSetMetaData meta = rs.getMetaData();

        List<String> columns = new ArrayList<>();
        for (int i = 1; i <= meta.getColumnCount(); i++)
            columns.add(meta.getColumnName(i));

        try (PreparedStatement s2 = to.prepareStatement(
                "INSERT INTO proveedor("
              + columns.stream().collect(Collectors.joining(", "))
              + ") VALUES ("
              + columns.stream().map(c -> "?").collect(Collectors.joining(", "))
              + ")"
        )) {
            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++)
                    s2.setObject(i, rs.getObject(i));

                s2.addBatch();
            }
            s2.executeBatch();
        }
    }
    }
    protected void done() {
                Integer bStatus = 0;
                try {
                    bStatus = get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Proceso finalizado con estado " + bStatus);
                if (bStatus==1){
                    System.out.println("Debo ejecutar la actualizacion de art-prov");
                    //JOptionPane.showMessageDialog(null, "Articulos actualizado correctamente!");
                    TaskArtProv tap = new TaskArtProv(lblProgreso,lblProgresoAP,pbAvance);
                    tap.execute();
                }else{
                    JOptionPane.showMessageDialog(null, "Articulos actualizado con error!");
                }
            }
}
