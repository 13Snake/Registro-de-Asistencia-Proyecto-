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
public class eliminaractividad extends javax.swing.JFrame {
String IDEmpleado1;
String name;
String matricula;
ArrayList array= new ArrayList();
DefaultListModel modelo = new DefaultListModel();
    /**
     * Creates new form eliminaractividad
     */
    public eliminaractividad(String IDEmpleado, String nombres) {
        initComponents();
        IDEmpleado1 = IDEmpleado;
        name = nombres;
        matricula = IDEmpleado;
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
                System.out.println(aux);
                System.out.println(matricula);
                if(IDEmpleado.equals(aux)) //buscar la matricula
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
                    break;
                }
            }while(rs.next());            
            
            //IMPRIMIR SI ESTA O NO EN LA TIENDA
            metodos metodo = new metodos();
            String fecha = metodo.fecha_actual();
            if(metodo.verificar_asistencia(IDEmpleado,fecha)==1)
            {
                asistencia.setText(asistencia.getText()+" SI");
            }else{
                asistencia.setText(asistencia.getText()+" NO");
            }
            
            //IMPRIMIR LAS ACTIVIDADES DEL EMPLEADO EN LA INTERFAZ
            array.clear();
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDEmpleado);
            //rs2.next();
            if(rs2.next())
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
                array.add("NINGUNA ACTIVIDAD ASIGNADA");
                modelo.removeAllElements();
                modelo.addElement(array.get(0));
                eliminar.setVisible(false);
            }
            
            //COLOCAR LAS ACTIVIDADES EN EL COMBOBOX
            array.clear();
            array.add("------SELECCIONE LA ACTIVIDAD-------");
            contador_actividades=1;
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDEmpleado1);
            if(rs2.next())
            {
                do{
                    actividad="Num.Act: "+contador_actividades+" "+rs2.getString("DescripcionActividad")+" Fecha: "+rs2.getString("FechaActividad");
                    array.add(actividad);
                    contador_actividades++;
                }while(rs2.next());
                combobox.removeAllItems();
                for(int x=0; x<array.size(); x++)
                {
                    combobox.addItem(array.get(x).toString());
                }
            }else{
                array.clear();
                array.add("NINGUNA ACTIVIDAD ASIGNADA");
                combobox.removeAllItems();
                combobox.addItem(array.get(0).toString());
                array.clear();
                eliminar.setVisible(false);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        lista_actividad = new javax.swing.JList<>();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        edad = new javax.swing.JLabel();
        puesto = new javax.swing.JLabel();
        horaentrada = new javax.swing.JLabel();
        horasalida = new javax.swing.JLabel();
        asistencia = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        combobox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        eliminar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(lista_actividad);

        jLabel7.setText("ACTIVIDADES ASIGNADAS");

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
                .addContainerGap(148, Short.MAX_VALUE))
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

        titulo.setText("Eliminando actividades a ");

        combobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxActionPerformed(evt);
            }
        });

        jLabel1.setText("SELECCIONAR ACTIVIDAD A ELIMINAR");

        eliminar.setText("Eliminar actividad");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                        .addComponent(jLabel7)
                                        .addGap(297, 297, 297))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(357, 357, 357)
                                .addComponent(titulo)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(321, 321, 321))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(182, 182, 182))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(eliminar)
                .addGap(115, 115, 115))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxActionPerformed
        
    }//GEN-LAST:event_comboboxActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        menuactividades menu = new menuactividades(matricula,IDEmpleado1, name);
        menu.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int bandera=0;        
        String actividad;
        int contador_actividades=1;
        array.clear();
        
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
            
            //ELIMINAR LA ACTIVIDAD SELECCIONADA
            stmt2 = con.createStatement();
            String act = combobox.getSelectedItem().toString();
            if(act.equals("------SELECCIONE LA ACTIVIDAD-------"))
            {
                JOptionPane.showMessageDialog(null, "SELECCIONE UNA ACTIVIDAD CORRECTA", "ERROR SELECCION DE ACTIVIDAD", JOptionPane.WARNING_MESSAGE);
            }else{
                String act2= act.substring(11,act.length()-18);
                System.out.println(act2);
                if(JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la actividad?","ADVERTENCIA",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                {
                    stmt2.executeUpdate("DELETE FROM Actividades WHERE DescripcionActividad = '"+act2+"' AND Empleado_IDEmpleado="+IDEmpleado1);
                    JOptionPane.showMessageDialog(null, "Actividad eliminada", "ADMINISTRACION DE ACTIVIDADES", JOptionPane.WARNING_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "Actividad no eliminada", "ADMINISTRACION DE ACTIVIDADES", JOptionPane.WARNING_MESSAGE);
                }
            }
            
            //IMPRIMIR LAS ACTIVIDADES DEL EMPLEADO EN LA INTERFAZ
            array.clear();
            modelo.removeAllElements();
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDEmpleado1);
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
                int x=0;
                array.clear();
                array.add("NINGUNA ACTIVIDAD ASIGNADA");
                modelo.addElement(array.get(x));
                array.clear();
            }
            //COLOCAR LAS ACTIVIDADES EN EL COMBOBOX
            array.clear();
            array.add("------SELECCIONE LA ACTIVIDAD-------");
            contador_actividades=1;
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDEmpleado1);
            if(rs2.next())
            {
                do{
                    actividad="Num.Act: "+contador_actividades+" "+rs2.getString("DescripcionActividad")+" Fecha: "+rs2.getString("FechaActividad");
                    array.add(actividad);
                    contador_actividades++;
                }while(rs2.next());
                combobox.removeAllItems();
                for(int x=0; x<array.size(); x++)
                {
                    combobox.addItem(array.get(x).toString());
                }
            }else{
                array.clear();
                array.add("NINGUNA ACTIVIDAD ASIGNADA");
                combobox.removeAllItems();
                combobox.addItem(array.get(0).toString());
                array.clear();
                eliminar.setVisible(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_eliminarActionPerformed

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
            java.util.logging.Logger.getLogger(eliminaractividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(eliminaractividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(eliminaractividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(eliminaractividad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> combobox;
    private javax.swing.JLabel edad;
    private javax.swing.JButton eliminar;
    private javax.swing.JLabel horaentrada;
    private javax.swing.JLabel horasalida;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
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
