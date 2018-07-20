package Modelos;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DatabaseConfig extends javax.swing.JFrame {

    public static final int MYSQL = 0;
    public static final int MSSQL = 1;
    
    private static final DatabaseConfig instance = new DatabaseConfig();
    
    private JFrame parentFrame;
    private Preferences prefs;
    private Dimension defaultSize;
    private String rdbms;
    private int rdbmsType;
    
    private String[][] defaultValues = {{"localhost", "3306", "potigianbase", "root", ""},
                                        {"192.168.0.50", "1433", "siga2001", "user_siga", "leer"}};
    
    public static DatabaseConfig getInstance(int databaseType, JFrame parent)
    {
        instance.setParentFrame(parent);
        instance.prepareContent(databaseType);
        
        instance.setVisible(true);
        instance.getParentFrame().setEnabled(false);
        
        instance.setSize(instance.getDefaultSize());
        instance.setLocationRelativeTo(null);
                
        return instance;
    }
    
    public static Connection getInstanceConnection(int databaseType) throws ClassNotFoundException, SQLException
    {
        switch (databaseType)
        {
            case MYSQL:
                instance.setRdbms("mysql");
            break;
            case MSSQL:
                instance.setRdbms("mssql");
            break;
            default:
                throw new IllegalArgumentException("Tipo de base inválido");
        }
        return instance.getConnection(databaseType);
    }
    
    public DatabaseConfig() {
        initComponents();
        defaultSize = this.getSize();
        prefs = Preferences.userNodeForPackage(this.getClass());
    }

    private Connection getConnection() throws ClassNotFoundException, SQLException
    {
        return getConnection(rdbmsType);
    }
    
    public void setRdbms(String rdbms)
    {
        this.rdbms = rdbms;
    }
    
    public Connection getConnection(int type) throws ClassNotFoundException, SQLException
    {
        // Queda a cargo del invocador cerrar la conexión
        String[] def = defaultValues[type];
        switch (type)
        {
            case DatabaseConfig.MYSQL:
                Class.forName("org.gjt.mm.mysql.Driver");
                // jdbc:mysql://[host]:[port]/[dbName]
                return DriverManager.getConnection(
                    "jdbc:mysql://" + prefs.get(rdbms + ".hostname", def[0]) + 
                    ":" + prefs.get(rdbms + ".port", def[1]) + 
                    "/" + prefs.get(rdbms + ".database", def[2]),
                    prefs.get(rdbms + ".username", def[3]),
                    prefs.get(rdbms + ".password", def[4]));
                
            case DatabaseConfig.MSSQL:
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                // jdbc:sqlserver://[host]:[port];databaseName=[dbName];
                //     user=[user];password=[pass]
                return DriverManager.getConnection(
                    "jdbc:sqlserver://" + prefs.get(rdbms + ".hostname", def[0]) + 
                    ":" + prefs.get(rdbms + ".port", def[1]) + 
                    ";databaseName=" + prefs.get(rdbms + ".database", def[2]) +
                    ";user=" + prefs.get(rdbms + ".username", def[3]) +
                    ";password=" + prefs.get(rdbms + ".password", def[4]));
            default:
                throw new IllegalArgumentException("Tipo de base inválido"); 
        }
    }
    
    private void setDefaultData()
    {
        int i = 0;
        txfHost.setText(prefs.get(rdbms + ".hostname", defaultValues[rdbmsType][i++]));
        txfPort.setText(prefs.get(rdbms + ".port", defaultValues[rdbmsType][i++]));
        txfDB.setText(prefs.get(rdbms + ".database", defaultValues[rdbmsType][i++]));
        txfUser.setText(prefs.get(rdbms + ".username", defaultValues[rdbmsType][i++]));
        pwfPass.setText(prefs.get(rdbms + ".password", defaultValues[rdbmsType][i++]));
    }
    
    private void savePreferences()
    {
        prefs.put(rdbms + ".hostname", txfHost.getText());
        prefs.put(rdbms + ".port", txfPort.getText());
        prefs.put(rdbms + ".database", txfDB.getText());
        prefs.put(rdbms + ".username", txfUser.getText());
        prefs.put(rdbms + ".password", new String(pwfPass.getPassword()));
        
        JOptionPane.showMessageDialog(this, "Se ha guardado la información", 
                "Guardando info", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void setParentFrame(JFrame parent)
    {
        this.parentFrame = parent;
    }
    
    private JFrame getParentFrame()
    {
        return this.parentFrame;
    }
    
    private Dimension getDefaultSize()
    {
        return defaultSize;
    }
    
    public void prepareContent(int type)
    {
        String friendlyRdbmsName, rdbmsName;
        
        this.rdbmsType = type;
        
        switch (type)
        {
            case MYSQL:
                friendlyRdbmsName = "MySQL";
                rdbms = "mysql";
            break;
            case MSSQL:
                friendlyRdbmsName = "SQL Server";
                rdbms = "mssql";
            break;
            default:
                throw new IllegalArgumentException("Tipo de base inválido");
        }
        lblTitle.setText("Configuración de base de datos " + friendlyRdbmsName);
        setDefaultData();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txfHost = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txfPort = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txfDB = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txfUser = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        pwfPass = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Configuración de base de datos [dbType]");

        jLabel1.setText("Host / IP:");

        jLabel2.setText("Puerto:");

        jLabel3.setText("Base de datos:");

        jLabel4.setText("Usuario:");

        jLabel5.setText("Contraseña:");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pwfPass, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfUser, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfDB, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfPort, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txfHost, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(161, 161, 161))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txfHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txfPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txfDB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txfUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pwfPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.parentFrame.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.savePreferences();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPasswordField pwfPass;
    private javax.swing.JTextField txfDB;
    private javax.swing.JTextField txfHost;
    private javax.swing.JTextField txfPort;
    private javax.swing.JTextField txfUser;
    // End of variables declaration//GEN-END:variables
}
