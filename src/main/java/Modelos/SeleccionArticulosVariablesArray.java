/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BaseDeDatos.ConexionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author usuario
 */
public class SeleccionArticulosVariablesArray extends javax.swing.JFrame {

    /**
     * Creates new form SeleccionArticulos
     */
    private String IdProv;
    private List<String> listaArt=new ArrayList<String>();;
    
    DefaultListModel<String> modelolista= new DefaultListModel<>();
    public SeleccionArticulosVariablesArray(String IdProveedor, List<String> listaArticulos) {
        initComponents();
        IdProv=IdProveedor;
        jListArticulos.setModel(modelolista);
        jListArticulos.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        txtIdProveedor.setText(IdProveedor);
        jlistaArticulosClase();
        txtIdProveedor.setEnabled(false);
        listaArt=listaArticulos;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtIdProveedor = new javax.swing.JTextField();
        jBfiltrarArticulos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jListArticulos = new javax.swing.JList<>();
        jBaceptar = new javax.swing.JButton();
        jBcancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtIdArticulo = new javax.swing.JTextField();
        jBListarArticulos1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Selección de Artículos");

        jLabel1.setText("idProveedor");

        txtIdProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdProveedorActionPerformed(evt);
            }
        });

        jBfiltrarArticulos.setText("Filtrar Articulo");
        jBfiltrarArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBfiltrarArticulosActionPerformed(evt);
            }
        });

        jListArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListArticulosMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jListArticulosMouseReleased(evt);
            }
        });
        jListArticulos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jListArticulosValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jListArticulos);

        jBaceptar.setText("Confirmar");
        jBaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBaceptarActionPerformed(evt);
            }
        });

        jBcancelar.setText("Cancelar");
        jBcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBcancelarActionPerformed(evt);
            }
        });

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtIdArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdArticuloActionPerformed(evt);
            }
        });

        jBListarArticulos1.setText("Mostrar Articulos");
        jBListarArticulos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBListarArticulos1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jBaceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jBcancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBListarArticulos1)
                                .addGap(30, 30, 30)
                                .addComponent(txtIdArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jBfiltrarArticulos)
                                .addGap(0, 35, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBfiltrarArticulos)
                    .addComponent(txtIdArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBListarArticulos1))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBaceptar)
                    .addComponent(jBcancelar)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jListArticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListArticulosMouseClicked

    }//GEN-LAST:event_jListArticulosMouseClicked

    private void jListArticulosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListArticulosValueChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jListArticulosValueChanged

    private void txtIdProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdProveedorActionPerformed

    private void jBfiltrarArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBfiltrarArticulosActionPerformed
        jfiltraArticulosClase();
    }//GEN-LAST:event_jBfiltrarArticulosActionPerformed

     private void jlistaArticulosClase (){
        
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        
       ///ingresamos la consulta
        String sSQL="";
        String idProv=txtIdProveedor.getText();
         modelolista.clear();
        
        //sSQL= "select CONCAT (idArticulo,'- ',descripcion) as descripcion from articulo where articulo.proveedor_idproveedor like  '%"+idProv+"%'";
        sSQL="select CONCAT (idArticulo,'- ',descripcion) as descripcion from articulo where idArticulo in (select idArticulo " +
                "from articulo_proveedor " +
                "where idProveedor="+IdProv+");";
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
             
              modelolista.addElement(rs.getString("descripcion"));
                        
            }   
            
        }
        catch (SQLException ex) {
            String mensaje= "No existe Proveedor";
            JOptionPane.showMessageDialog(null, mensaje);
        }
    
    }
     
     private void jfiltraArticulosClase () {
        
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        
       ///ingresamos la consulta
        String sSQL="";
        String idProv=txtIdProveedor.getText();
        String idArt=txtIdArticulo.getText();
         modelolista.clear();
        
        //sSQL= "select CONCAT (idArticulo,'- ',descripcion) as descripcion from articulo where articulo.proveedor_idproveedor like  '%"+idProv+"%'";
        sSQL="select CONCAT (idArticulo,'- ',descripcion) as descripcion from articulo where idArticulo in (select idArticulo " +
                "from articulo_proveedor " +
                "where idProveedor="+idProv+" and idArticulo="+idArt+" )";
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
             
              modelolista.addElement(rs.getString("descripcion"));
                        
            }   
            
        }
        catch (SQLException ex) {
            String mensaje= "No existe Proveedor";
            JOptionPane.showMessageDialog(null, mensaje);
        }
    
    }
    
    private void aceptar() {
        
        int index= 0;
        String articulo="";
        //conexión a la bdd
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        //STRING A UTILIZAR
        String ultimoId="", idArt="";
        String sSQL="",sSQLid="";
        String mensaje;

        ////Guardamos consulta para ultimo id.
        sSQLid="SELECT LAST_INSERT_ID(idPlan_Descuento) AS ProximoIdAInsertar FROM plan_descuento ORDER BY idPlan_Descuento DESC LIMIT 1";
        ///Guardamos la consulta para insertar el dato
        sSQL="INSERT INTO plan_articulo (idPlan_Descuento, idArticulo) " +
        "VALUES (?,?)";
        mensaje="Operación Satisfactoria";

        ///ARRANCA

        try
        {
            PreparedStatement pst= cn.prepareStatement(sSQLid);
            ResultSet resultado= pst.executeQuery();
            while (resultado.next()) { //Es mas correcto poner el next en el while, te hace lo mismo que tenias en tu antiguo codigo pero en menos lineas y mas limpio
                ultimoId = resultado.getString("ProximoIdAInsertar");
            }
            System.out.println(ultimoId);

            ///obtenemos longitud de los elementos seleccionados.
            index=jListArticulos.getSelectedValuesList().size();
            System.out.println(index);
            for (int i=0; i< index; i++ ){

                articulo=jListArticulos.getSelectedValuesList().get(i);
                String cortado[]=articulo.split("-");
                idArt= cortado[0];
                
                listaArt.add(idArt);
               

               /* PreparedStatement pst2= cn.prepareStatement(sSQL);
                pst2.setString(1, ultimoId);
                pst2.setString(2, idArt);
                pst2.executeUpdate();*/

            }
            JOptionPane.showMessageDialog(null, mensaje);
            
            modelolista.clear();
            System.out.println(listaArt);

        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        this.setVisible(false);
    }
    private void jBaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBaceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_jBaceptarActionPerformed

    private void jBcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBcancelarActionPerformed
        modelolista.clear();
    }//GEN-LAST:event_jBcancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        SeleccionArticulosVariablesArray.this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jListArticulosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListArticulosMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jListArticulosMouseReleased

    private void txtIdArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdArticuloActionPerformed

    private void jBListarArticulos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBListarArticulos1ActionPerformed
        jlistaArticulosClase ();
       
    }//GEN-LAST:event_jBListarArticulos1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SeleccionArticulosVariablesArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SeleccionArticulosVariablesArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SeleccionArticulosVariablesArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SeleccionArticulosVariablesArray.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            String IdProveedor;
            private List<String> listaArti;
            public void run() {
                new SeleccionArticulosVariablesArray(IdProveedor,listaArti).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBListarArticulos1;
    private javax.swing.JButton jBaceptar;
    private javax.swing.JButton jBcancelar;
    private javax.swing.JButton jBfiltrarArticulos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListArticulos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtIdArticulo;
    private javax.swing.JTextField txtIdProveedor;
    // End of variables declaration//GEN-END:variables
}
