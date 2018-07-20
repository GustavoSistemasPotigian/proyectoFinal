/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackGroundLongTasks;
import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.conexionSQLServer;
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
import LogManager.BitacoraCambios;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Usuario
 */
public class TaskCierrePlanesFijos extends SwingWorker<Integer,String>{
     private JLabel lblProgreso;
     private JLabel lblProgresoA;
     private JProgressBar pbAvance;
     private Integer avance=0;
     @Override
  protected Integer doInBackground() throws Exception {
    // Start
    publish("Ejecutando proceso...");
    setProgress(1);
    publish("Ejecutando proceso en background...");
    refreshA();
    //publish("Complete");
    publish("FINALIZADO");
    setProgress(100);
    return 1;
  }
  @Override
  protected void process(List< String> chunks) {
    // Messages received from the doInBackground() (when invoking the publish() method)
    for (final String string : chunks) {
     if(string.equals("FINALIZADO")){
       avance=100;
       lblProgresoA.setText("ACTUALIZADO");
       BitacoraCambios bc = new BitacoraCambios();
       bc.insertData("articulos");
     }else{
        avance=avance+10;
     }
     lblProgreso.setText(string);
     pbAvance.setValue(avance);
    }
  }
  public TaskCierrePlanesFijos(JLabel etiqueta, JLabel etiquetaA, JProgressBar barraProg){
      this.lblProgreso=etiqueta;
      this.pbAvance=barraProg;
      this.lblProgresoA=etiquetaA;
  }

  private void refreshA(){
      int rs2=0;
      System.out.println("Comienza Cierre planes fijos");
        setProgress(10);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
        String fechaHoy=dateFormat.format(date);
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        //PREPARO CONSULTA PARA OBTENER FECHA
        setProgress(20);
        String queryPF="insert into cierre select null,c.plan_descuento_idPlan_Descuento as pd, " +
                            "p.tipo_plan, " +
                            "p.Proveedor_idproveedor, " +
                            "now() as fecha_cierre, " +
                            "0, " +
                            "0, " +
                            "c.importe, " +
                            "p.tipo_IVA_idtipo_IVA, " +
                            "c.IVA, " +
                            "c.nro_cuota, " +
                            "p.cuotas " +
                            "from cuota c, plan_descuento p " +
                            "where c.fecha='"+fechaHoy+"' and p.idPlan_Descuento=c.plan_descuento_idPlan_Descuento;";
        
      
        //lblProgreso.setText("FUNCIONA");
        publish("Cierre articulos fijos...");
        //OBTENGO FECHA Y LA CARGO EN EL LABEL
        System.out.println("ENTRO EN ESTE LUGAR");
        System.out.println(queryPF);
        try {
            Statement st= cn.createStatement();
            setProgress(25);
          //  int idplan= 
            CheckIfClosed isClosed = new CheckIfClosed();
            System.out.println(queryPF);
            //System.out.println(isClosed.fixClosed());
            if(!isClosed.fixClosed()){
                    System.out.println(queryPF);
                    rs2= st.executeUpdate(queryPF);
                }else{
                    System.out.println("LOS PLANES FIJOS SE ENCUENTRAN CERRADOS");
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(rs2==1){
                System.out.println("Cierre planes fijos finalizado OK");
                setProgress(100);
            }   
            //copyA();
        
        //queryAP="TRAIGO tOdos lOS DATOS DEL SQL y los inserto en mysql";
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
                    System.out.println("Debo ejecutar la actualizacion de proveedores");
                    TaskCierrePlanesVar taskPV = new TaskCierrePlanesVar(lblProgreso,lblProgresoA,pbAvance);
                    taskPV.execute();
                    //JOptionPane.showMessageDialog(null, "Articulos actualizado correctamente!");
                    //LLAMAR A ACTUALIZAR PROVEEDORES
                    //TaskProveedores tp = new TaskProveedores(lblProgreso,lblProgresoP,lblProgresoAP,pbAvance);
                    //tp.execute();
                }else{
                    JOptionPane.showMessageDialog(null, "Articulos actualizado con error!");
                }
            }
}
