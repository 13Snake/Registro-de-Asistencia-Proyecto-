/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package registroasistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author snake
 */
public class menuactividades extends javax.swing.JFrame {
String IDEmpleado1;
String IDSeleccionado1;
String name;
ArrayList array= new ArrayList();
DefaultListModel modelo = new DefaultListModel();
    public menuactividades(String IDEmpleado, String IDSeleccion, String nombres) {
        initComponents();
        IDEmpleado1=IDEmpleado;
        IDSeleccionado1=IDSeleccion;
        name = nombres;
        int bandera=0;
        lista_actividad.setModel(modelo);
        String actividad;
        int contador_actividades=1;
        String puesto_empleado;
        
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
            
            //IMPRIMIR DATOS DEL EMPLEADO EN LA INTERFAZ
            stmt = con.createStatement(); //inicializar stmt
            rs = stmt.executeQuery("SELECT * FROM Empleado");
            rs.next();
            do{
                String aux = rs.getString("IDEmpleado");
                System.out.println("ERRORES");
                System.out.println(aux);
                System.err.println(IDSeleccion);
                if(IDSeleccion.equals(aux)) //buscar la matricula
                {
                    nombre.setText(nombre.getText()+" "+rs.getString("Nombre")+" "+rs.getString("Apaterno")+" "+rs.getString(("Amaterno")));
                    titulo.setText(titulo.getText()+" "+ rs.getString("Nombre")+"!");
                    telefono.setText(telefono.getText()+" "+rs.getString("TelefonoEmpleado"));
                    edad.setText(edad.getText()+" "+rs.getString("Edad"));
                    
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery("SELECT * FROM `registroasistencia`.`PuestoEmpleado` WHERE `IDPuesto` = "+rs.getString("PuestoEmpleado_IDPuesto"));
                    rs2.next();
                    puesto.setText(puesto.getText()+" "+rs2.getString("TipoPuesto"));
                    
                    stmt2 = con.createStatement();
                    rs2 = stmt2.executeQuery("SELECT * FROM `registroasistencia`.`HorarioEmpleado` WHERE `IDHorario` = "+rs.getString("HorarioEmpleado_IDHorario"));
                    rs2.next();
                    horaentrada.setText(horaentrada.getText()+" "+rs2.getString("HoraEntrada"));
                    horasalida.setText(horasalida.getText()+" "+rs2.getString("HoraSalida"));
                    //titulo.setText(titulo.getText() + rs.getString("Nombre")+"!");
                    break;
                }
            }while(rs.next());            
            
            //IMPRIMIR SI ESTA O NO EN LA TIENDA
            metodos metodo = new metodos();
            String fecha = metodo.fecha_actual();
            if(metodo.verificar_asistencia(IDSeleccion,fecha)==1)
            {
                asistencia.setText(asistencia.getText()+" SI");
            }else{
                asistencia.setText(asistencia.getText()+" NO");
            }
            
            //IMPRIMIR LAS ACTIVIDADES DEL EMPLEADO EN LA INTERFAZ
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDSeleccion);
//            rs2.next();
            if(rs2.next())
            {
                do{
                //System.out.println("IDACTIVDAD: "+rs2.getString("IDActividad")+" DESCRIPCION: "+rs2.getString("DescripcionActividad")+" FECHA: "+rs2.getString("FechaActividad")+" IDEMPLEADO: "+rs2.getString("Empleado_IDEmpleado"));
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
                array.add("NINGUNA ACTIVIDAD ASIGNADA");
                modelo.removeAllElements();
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

        titulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        edad = new javax.swing.JLabel();
        puesto = new javax.swing.JLabel();
        horaentrada = new javax.swing.JLabel();
        horasalida = new javax.swing.JLabel();
        asistencia = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_actividad = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setText("Menu de actividades de ");

        jLabel2.setText("INFORMACION GENERAL");

        nombre.setText("Nombre: ");

        telefono.setText("Telefono: ");

        edad.setText("Edad");

        puesto.setText("Puesto: ");

        horaentrada.setText("Hora Entrada: ");

        horasalida.setText("Hora Salida: ");

        asistencia.setText("Registro Asistencia hoy: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(asistencia)
                    .addComponent(horasalida)
                    .addComponent(horaentrada)
                    .addComponent(puesto)
                    .addComponent(edad)
                    .addComponent(telefono)
                    .addComponent(nombre)
                    .addComponent(jLabel2))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(nombre)
                .addGap(18, 18, 18)
                .addComponent(telefono)
                .addGap(18, 18, 18)
                .addComponent(edad)
                .addGap(18, 18, 18)
                .addComponent(puesto)
                .addGap(18, 18, 18)
                .addComponent(horaentrada)
                .addGap(18, 18, 18)
                .addComponent(horasalida)
                .addGap(18, 18, 18)
                .addComponent(asistencia)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(lista_actividad);

        jLabel7.setText("ACTIVIDADES ASIGNADAS");

        jButton1.setText("Asignar Actividad");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Eliminar Actividad");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Regresar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(375, 375, 375)
                .addComponent(titulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(241, 241, 241)
                .addComponent(jButton2)
                .addGap(39, 39, 39))
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(325, 325, 325))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton2)
                                    .addComponent(jButton3))
                                .addGap(38, 38, 38))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        int bandera=0;
        lista_actividad.setModel(modelo);
        String actividad;
        int contador_actividades=1;
        String puesto_empleado;
        
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
            
            //IMPRIMIR LAS ACTIVIDADES DEL EMPLEADO EN LA INTERFAZ
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDSeleccionado1);
            if(rs2.next())
            {
                eliminaractividad eliminar = new eliminaractividad(IDSeleccionado1, name);
                eliminar.setVisible(true);
            }else{
                JOptionPane.showMessageDialog(null, "No se tiene ninguna actividad registrada", "ACTIVIDADES REGISTRADAS", JOptionPane.WARNING_MESSAGE);
                this.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.setVisible(false);
        selecionarempleado selec = new selecionarempleado(IDEmpleado1);
        selec.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        int bandera=0;
        lista_actividad.setModel(modelo);
        String actividad;
        int contador_actividades=1;
        String puesto_empleado;
        
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
            
            //IMPRIMIR DATOS DEL EMPLEADO EN LA INTERFAZ
            stmt = con.createStatement(); //inicializar stmt
            rs = stmt.executeQuery("SELECT * FROM Empleado WHERE Nombre='"+name+"'");
            if(rs.next())
            {
                IDSeleccionado1 = rs.getString("IDEmpleado");
                System.out.println(IDSeleccionado1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        asignaractividad asignar = new asignaractividad(IDSeleccionado1,name);
        asignar.setVisible(true);
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
            java.util.logging.Logger.getLogger(menuactividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menuactividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menuactividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menuactividades.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel asistencia;
    private javax.swing.JLabel edad;
    private javax.swing.JLabel horaentrada;
    private javax.swing.JLabel horasalida;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lista_actividad;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel puesto;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
