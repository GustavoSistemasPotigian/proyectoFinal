/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import BaseDeDatos.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class Usuarios extends javax.swing.JFrame {

    DefaultTableModel modelo;
    //// create new usuarios
    public Usuarios() {
        initComponents();
        CargarTablaUsuarios("");
        ///cargo los valores de los items desplegables
        cboPermisos.addItem("Admin");
        cboPermisos.addItem("Users");
       //al iniciar la ventana queda inhabilitado hasta darle click en nuevo.
        inhabilitar();
    }
    //carga tabla usuarios
    void CargarTablaUsuarios(String valor){
        String sSQL="";
       
        ///configuramos la tabla.
        String [] titulos= {"idUsuario","nombre","apellido","users","clave","cargo","Permiso_IdPermiso"};
        String [] registro= new String[7];
        modelo = new DefaultTableModel(null,titulos);
        ///realizamos la conexion con la bdd.
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        
       ///ingresamos la consulta
        sSQL="SELECT idUsuario, nombre, apellido,users, clave, cargo, Permiso_IdPermiso FROM usuario " +
                "WHERE CONCAT (nombre,' ',apellido,' ', users) LIKE '%"+valor+"%'";
        
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while (rs.next())
            {
                registro[0]=rs.getString("idUsuario");
                registro[1]=rs.getString("nombre");
                registro[2]=rs.getString("apellido");
                registro[3]=rs.getString("users");
                registro[4]=rs.getString("clave");
                registro[5]=rs.getString("cargo");
                registro[6]=rs.getString("Permiso_IdPermiso");
                
                modelo.addRow(registro);
              }
            tblConsultaUsuario.setModel(modelo);
                        
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
  }
    
   void BuscarUsuarioEliminar(String idUser)
   {
       String sSQL="";
       
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        
       ///ingresamos la consulta
        
        sSQL="DELETE FROM usuario " +
             "WHERE idUsuario= " + idUser;
        String mensaje="";
       
        try 
        {
            PreparedStatement pps= cn.prepareStatement(sSQL);
            int  n= pps.executeUpdate();
            
            if (n>0)
            {    
                mensaje="El usuario ha sido eliminado correctamente";
                JOptionPane.showMessageDialog(null, mensaje);
                CargarTablaUsuarios("");
            }
                       
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    
    }
   
   ///variable para buscar variable a editar
    String id_actualizar="";
   
    void BuscarUsuarioEditar(String idUser){
        String sSQL="";
        String nom="",apell="",users="",clave="",cargo="",idPermiso="";
       
        ///realizamos la conexion con la bdd.
        
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        
       ///ingresamos la consulta
        
        sSQL="SELECT idUsuario, nombre, apellido,users, clave, cargo, Permiso_IdPermiso FROM usuario " +
             "WHERE idUsuario= " + idUser;
        
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {                
                nom=rs.getString("nombre");
                apell=rs.getString("apellido");
                users=rs.getString("users");
                clave=rs.getString("clave");
                cargo=rs.getString("cargo");
                idPermiso=rs.getString("Permiso_IdPermiso");
                             
              }
            txtNombre.setText(nom);
            txtApellido.setText(apell);
            txtUsers.setText(users);
            txtContraseña.setText(clave);
            txtCargo.setText(cargo);
            
            id_actualizar = idUser;
           
            
                        
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    
    } 
    
    void habilitar()
    {
          //habilita cada opción
    txtNombre.setEnabled(true);
    txtApellido.setEnabled(true);
    txtUsers.setEnabled(true);
    txtContraseña.setEnabled(true);
    txtCargo.setEnabled(true);
    cboPermisos.setEnabled(true);
    
    //vacia los campos en ""
    txtNombre.setText("");
    txtApellido.setText("");
    txtUsers.setText("");
    txtContraseña.setText("");
    txtCargo.setText("");
    btnCrearUsuario.setEnabled(true);
    btnCancelar.setEnabled(true);
    
    txtNombre.requestFocus();
        
        
    }
    ///metodo para cancelar la carga de datos
    void inhabilitar(){
        //deshabilita cada opción
    txtNombre.setEnabled(false);
    txtApellido.setEnabled(false);
    txtUsers.setEnabled(false);
    txtContraseña.setEnabled(false);
    txtCargo.setEnabled(false);
    cboPermisos.setEnabled(false);
    
    //vacia los campos en ""
    txtNombre.setText("");
    txtApellido.setText("");
    txtUsers.setText("");
    txtContraseña.setText("");
    txtCargo.setText("");
    btnCrearUsuario.setEnabled(false);
    btnCancelar.setEnabled(false);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnEditar = new javax.swing.JMenuItem();
        mnEliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUsers = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        txtCargo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cboPermisos = new javax.swing.JComboBox<>();
        btnCrearUsuario = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnBuscarUsuario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultaUsuario = new javax.swing.JTable();

        mnEditar.setText("Modificar");
        mnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnEditar);

        mnEliminar.setText("Eliminar");
        mnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Potigian Compras-Pagos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "- Gestión de Usuarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellido:");

        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

        jLabel3.setText("Users:");

        txtUsers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsersActionPerformed(evt);
            }
        });

        jLabel4.setText("Contraseña:");

        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });

        txtCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCargoActionPerformed(evt);
            }
        });

        jLabel5.setText("Cargo:");

        jLabel6.setText("Permiso:");

        cboPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPermisosActionPerformed(evt);
            }
        });

        btnCrearUsuario.setText("Guardar");
        btnCrearUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearUsuarioActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                                    .addComponent(txtApellido)
                                    .addComponent(txtUsers)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(cboPermisos, 0, 172, Short.MAX_VALUE)
                                    .addComponent(txtContraseña, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCargo, javax.swing.GroupLayout.Alignment.LEADING)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCrearUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUsers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCargo, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cboPermisos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCrearUsuario)
                    .addComponent(btnCancelar)
                    .addComponent(btnNuevo)
                    .addComponent(btnSalir))
                .addGap(72, 72, 72))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Consulta de Usuarios"));

        jLabel7.setText("Buscar Usuario:");

        btnBuscarUsuario.setText("Buscar");
        btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarUsuarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBuscarUsuario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(btnBuscarUsuario))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tblConsultaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblConsultaUsuario.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tblConsultaUsuario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(132, 132, 132))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(48, 48, 48))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE))
                .addGap(228, 228, 228))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        txtNombre.transferFocus();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtUsersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsersActionPerformed
        txtUsers.transferFocus();
    }//GEN-LAST:event_txtUsersActionPerformed

    private void txtCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCargoActionPerformed
        txtCargo.transferFocus();
    }//GEN-LAST:event_txtCargoActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        habilitar();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void cboPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPermisosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPermisosActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
       this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inhabilitar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        txtApellido.transferFocus();
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        txtContraseña.transferFocus();
    }//GEN-LAST:event_txtContraseñaActionPerformed
    
    String accion="Insertar";
    
    private void btnCrearUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearUsuarioActionPerformed
        //CONEXION A LA BDD
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        ///STRING A UTILIZAR
        String nom,ape,us,cla,carg,perm;
        String sSQL="";
        String mensaje;
        nom=txtNombre.getText();
        ape=txtApellido.getText();
        us=txtUsers.getText();
        cla=txtContraseña.getText();
        carg=txtCargo.getText();
        perm= cboPermisos.getSelectedItem().toString();
        ///buscamos el tipo de permiso
        int idPermiso=0;
        if ( perm=="Admin"){
            idPermiso=1;
                    }
        if (perm=="Users"){
            idPermiso=2;
        }
        
        
        ///creamos la consulta sql
        if (accion.equals("Insertar"))
        {
            sSQL="INSERT INTO usuario(nombre, apellido, users, clave, cargo,Permiso_idPermiso) "+
                "VALUES (?,?,?,?,?,?)";
           mensaje="Operación Satisfactoria";
        }
        else if (accion.equals("Modificar"))
        {
            sSQL="UPDATE usuario " +
                 "SET nombre = ?, " +
                 "apellido= ?, "+
                 "users= ?, " +
                 "clave= ?, " +
                 "cargo= ? ," +
                 "Permiso_idpermiso= ? "+   
                 "WHERE idUsuario= " + id_actualizar;
            mensaje="Operación Satisfactoría";
        }
        
        
        try 
        {
            PreparedStatement pst= cn.prepareStatement(sSQL);
            pst.setString(1, nom);
            pst.setString(2, ape);
            pst.setString(3,us);
            pst.setString(4, cla);
            pst.setString(5, carg);
            pst.setInt(6, idPermiso);
            
            int n = pst.executeUpdate();
            
            if (n>0)
            {    
                mensaje="Operación Satisfactoria";
                JOptionPane.showMessageDialog(null, mensaje);
                CargarTablaUsuarios("");
                inhabilitar();
                
            }
            
            
            
        } 
        
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
             
    }//GEN-LAST:event_btnCrearUsuarioActionPerformed

    private void btnBuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarUsuarioActionPerformed
        String valor=txtBuscar.getText();
        
        CargarTablaUsuarios(valor);
    }//GEN-LAST:event_btnBuscarUsuarioActionPerformed

    private void mnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEditarActionPerformed
        int filasel;
        String idUser;
        
        try
           {
               filasel=tblConsultaUsuario.getSelectedRow();
               
               if (filasel==-1)
               {
                   JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
               }
               else
               {
                   accion="Modificar";
                   modelo= (DefaultTableModel) tblConsultaUsuario.getModel();
                   idUser= (String) modelo.getValueAt(filasel, 0);
                   habilitar();
                   BuscarUsuarioEditar(idUser);          
               }     
           }
        catch (Exception e)
                {
                    
                }
    }//GEN-LAST:event_mnEditarActionPerformed

    private void mnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEliminarActionPerformed
        int filasel;
        String idUser;
        
        try
           {
               filasel=tblConsultaUsuario.getSelectedRow();
               
               if (filasel==-1)
               {
                   JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
               }
               else
               {
                   //accion="Modificar";
                   modelo= (DefaultTableModel) tblConsultaUsuario.getModel();
                   idUser= (String) modelo.getValueAt(filasel, 0);
                   habilitar();
                   BuscarUsuarioEliminar(idUser);          
               }     
           }
        catch (Exception e)
                {
                    
                }
    }//GEN-LAST:event_mnEliminarActionPerformed

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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Usuarios ventanaUsuario= new Usuarios();
                //ventanaUsuario.setExtendedState(MAXIMIZED_BOTH);
                ventanaUsuario.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarUsuario;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCrearUsuario;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cboPermisos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mnEditar;
    private javax.swing.JMenuItem mnEliminar;
    private javax.swing.JTable tblConsultaUsuario;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUsers;
    // End of variables declaration//GEN-END:variables
}
