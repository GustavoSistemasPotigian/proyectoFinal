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
/**
 *
 * @author Usuario
 */
public class TaskArticulos extends SwingWorker<Integer,String>{
     private JLabel lblProgreso;
     private JLabel lblProgresoA;
     private JLabel lblProgresoP;
     private JLabel lblProgresoAP;
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
       lblProgresoP.setText("ACTUALIZANDO...");
     }else{
        avance=avance+10;
     }
     lblProgreso.setText(string);
     pbAvance.setValue(avance);
    }
  }
  public TaskArticulos(JLabel etiqueta, JLabel etiquetaA, JLabel etiquetaP, JLabel etiquetaAP, JProgressBar barraProg){
      this.lblProgreso=etiqueta;
      this.pbAvance=barraProg;
      this.lblProgresoA=etiquetaA;
      this.lblProgresoP=etiquetaP;
      this.lblProgresoAP=etiquetaAP;
  }

  private void refreshA(){
      System.out.println("Ejecutando Refresh, truncate de tabla articulos");
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        //PREPARO CONSULTA PARA OBTENER FECHA
        String queryAP="truncate table articulo";
        //lblProgreso.setText("FUNCIONA");
        publish("Eliminando datos antiguos");
        //OBTENGO FECHA Y LA CARGO EN EL LABEL
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(queryAP);
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("Invocando CopyA que coneecta a sql");
            setProgress(10);
            copyA();
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        //queryAP="TRAIGO tOdos lOS DATOS DEL SQL y los inserto en mysql";
    }
    public void copyA() throws SQLException {
        conexionSQLServer sqlFrom = new conexionSQLServer();
        Connection from = sqlFrom.Conectar();
        ConexionMySQL mysqlTo= new ConexionMySQL();
        Connection to= mysqlTo.Conectar();  
        System.out.println("Entrando el try");
        try (
         PreparedStatement s1 = from.prepareStatement("select art.IDProducto as idArticulo," +
                    "art.Desc_Producto as descripcion, " +
                    "art.IDContenidoUnidaddeVenta as unidad_de_venta," +
                    "art.FechaAlta as fecha_ingreso_inicial," +
                    "art.FechaCambioPrecio as fecha_ultimo_ingreso," +
                    "null as precio_costo," +
                    "null as precio_vigente," +
                    "null as margen," +
                    "null as proveedor_idproveedor ," +
                    "null as cantidad_total," +
                    "art.IDRubro as idSector " +
                    "from productos art " + 
                    "where art.IDSituacionProducto='AC' " +
                    "order by 1");
         ResultSet rs = s1.executeQuery()) {
         ResultSetMetaData meta = rs.getMetaData();
         
        List<String> columns = new ArrayList<>();
        for (int i = 1; i <= meta.getColumnCount(); i++)
            columns.add(meta.getColumnName(i));
           setProgress(50);
           publish("Obteniendo datos...");
           System.out.println("Obteniendo datos");
        try (PreparedStatement s2 = to.prepareStatement(
                "INSERT INTO articulo("
              + columns.stream().collect(Collectors.joining(", "))
              + ") VALUES ("
              + columns.stream().map(c -> "?").collect(Collectors.joining(", "))
              + ")"
        )) {
            publish("Actualizando datos de Articulos...");
            System.out.println("Entrando al actualizando art");
            setProgress(80);
            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++)
                    s2.setObject(i, rs.getObject(i));

                s2.addBatch();
            }
            s2.executeBatch();
        }
        //publish("FINALIZADO");
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
                    System.out.println("Debo ejecutar la actualizacion de proveedores");
                    //JOptionPane.showMessageDialog(null, "Articulos actualizado correctamente!");
                    //LLAMAR A ACTUALIZAR PROVEEDORES
                    TaskProveedores tp = new TaskProveedores(lblProgreso,lblProgresoP,lblProgresoAP,pbAvance);
                    tp.execute();
                }else{
                    JOptionPane.showMessageDialog(null, "Articulos actualizado con error!");
                }
            }
}
