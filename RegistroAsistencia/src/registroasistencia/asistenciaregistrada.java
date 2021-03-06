/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package registroasistencia;

import com.mysql.cj.MysqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class asistenciaregistrada extends javax.swing.JFrame {
    ArrayList array= new ArrayList();
    DefaultListModel modelo = new DefaultListModel();
    /**
     * Creates new form asistenciaregistrada
     */
    public asistenciaregistrada(String IDEmpleado, String fecha, String tiempo) {
        initComponents();
        String aux;
        int bandera=0;
        lista_actividad.setModel(modelo);
        String actividad;
        int contador_actividades=1;
        try {                                     
            String usuario = "root"; //nombre del usuario
            String clave= ""; //la clave, por lo general es en vacio
            String url = "jdbc:mysql://localhost:3306/registroasistencia"; //donde se encuentra la base de datos
            Connection con; //importar sql.connection para la conexion
            Statement stmt; //importar sql statemenr
            ResultSet rs; //resultado de las consultas
            ResultSet rs2;
            Statement stmt2;
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //hacer la conexion con la base de datos
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistroAsistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            con = DriverManager.getConnection(url,usuario,clave); //montar el servidor
            stmt = con.createStatement(); //inicializar stmt
            //SENTENCIA QUE INSERTA LOS VALORES DE LA ASISTENCIA EN LA BASE DE DATOS
            stmt.executeUpdate("INSERT INTO `RegistroAsistencia` (`IDRegistro`, `FechaRegistro`, `HoraRegistro`, `Empleado_IDEmpleado`) VALUES (NULL, '"+fecha+"', '"+tiempo+"', '"+IDEmpleado+"')");
            
            //IMPRIME LOS VALORES DE LOS EMPLEADOS EN LA CAJA DE TEXTO
            rs = stmt.executeQuery("SELECT * FROM Empleado");
            rs.next();
            do{
                //System.out.println(rs.getString("IDEmpleado")+" Nombre: "+rs.getString("Nombre")+" Apellido Paterno: "+rs.getString("Apaterno")+" Apalldo Materno: "+rs.getString("Amaterno")+" Puesto: "+rs.getString("Puesto_IDPuesto"));
                aux = rs.getString("IDEmpleado");
                if(IDEmpleado.equals(aux)) //buscar la matricula
                {
                    nombre.setText(nombre.getText()+" "+rs.getString("Nombre")+" "+rs.getString("Apaterno")+" "+rs.getString(("Amaterno")));
                    telefono.setText(telefono.getText()+" "+rs.getString("TelefonoEmpleado"));
                    edad.setText(edad.getText()+" "+rs.getString("Edad"));

                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery("SELECT * FROM `registroasistencia`.`PuestoEmpleado` WHERE `IDPuesto` = "+rs.getString("PuestoEmpleado_IDPuesto"));
                    rs2.next();
                    puesto.setText(puesto.getText()+" "+rs2.getString("TipoPuesto"));
                }
            }while(rs.next());
            
            //IMPRIME EN LA INTERFAS LAS ACTIVIDADES QUE TENGA EL EMPLEADO
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDEmpleado);
            if(rs.next())
            {
                do{
                    actividad="Num.Act: "+contador_actividades+" "+rs2.getString("DescripcionActividad")+" Fecha: "+rs2.getString("FechaActividad");
                    array.add(actividad);
                    contador_actividades++;
                }while(rs2.next());
                modelo.removeAllElements();
                for(int x=0; x<array.size(); x++)
                {
                    modelo.addElement(array.get(x));
                }
            }else{
                array.clear();
                modelo.removeAllElements();
                array.add("NINGUNA ACTIVIDAD ASIGNADA");
                modelo.addElement(array.get(0));
                array.clear();
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        puesto = new javax.swing.JLabel();
        edad = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_actividad = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("??Tu asistencia a sido registrada!");

        jButton1.setText("SALIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Informaci??n general del empleado");

        nombre.setText("Nombre");

        telefono.setText("Telefono:");

        puesto.setText("Puesto:");

        edad.setText("Edad:");

        jLabel7.setText("Activdiades asignadas");

        jScrollPane1.setViewportView(lista_actividad);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(puesto)
                    .addComponent(jLabel2)
                    .addComponent(nombre)
                    .addComponent(telefono)
                    .addComponent(edad)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(nombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edad)
                .addGap(7, 7, 7)
                .addComponent(puesto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel1)
                .addGap(136, 136, 136)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        inicio page_inicio = new inicio();
        page_inicio.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(asistenciaregistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(asistenciaregistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(asistenciaregistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(asistenciaregistrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel edad;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lista_actividad;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel puesto;
    private javax.swing.JLabel telefono;
    // End of variables declaration//GEN-END:variables
}
