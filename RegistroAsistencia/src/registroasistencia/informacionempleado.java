package registroasistencia;

import com.mysql.cj.MysqlConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.*;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class informacionempleado extends javax.swing.JFrame {
    String IDEmpleado1;
    ArrayList array= new ArrayList();
    DefaultListModel modelo = new DefaultListModel();
    /**
     * Creates new form informacionempleado
     */
    public informacionempleado(String IDEmpleado) {
        initComponents();
        IDEmpleado1 = IDEmpleado;
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
                System.err.println("ERROR 2");
                System.out.println(aux);
                System.out.println(IDEmpleado);
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
                    //titulo.setText(titulo.getText() + rs.getString("Nombre")+"!");
                    break;
                }
            }while(rs.next());            
            
            //IMPRIMIR LAS ACTIVIDADES DEL EMPLEADO EN LA INTERFAZ
            stmt2 = con.createStatement();
            rs2 = stmt2.executeQuery("SELECT * FROM registroasistencia.Actividades WHERE Empleado_IDEmpleado = "+IDEmpleado);
            //&rs2.next();
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
                modelo.removeAllElements();
                array.add("NIGUNA ACTIVIDAD ASIGNADA");
                modelo.addElement(array.get(0));
                array.clear();
            }
            
            //MOSTRAR O NO BOTON DE ADMINISTRACION DE ACTIVIDADES
            bandera=0;
            admon_act.setVisible(false);
            stmt2 = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Empleado WHERE PuestoEmpleado_IDPuesto=1 OR PuestoEmpleado_IDPuesto=2 AND IDEmpleado="+IDEmpleado);
            rs.next();
            do{
                puesto_empleado = rs.getString("PuestoEmpleado_IDPuesto");
                String matricula = rs.getString("IDEmpleado");
                if(puesto_empleado.equals("2") && matricula.equals(IDEmpleado.toString()) && bandera==0)
                {
                    bandera=1;
                    System.err.println("ENTRA1");
                }
                if(puesto_empleado.equals("1") && matricula.equals(IDEmpleado.toString()) && bandera==0)
                {
                    bandera=1;
                    System.err.println("ENTRA2");
                }
            }while(rs.next());
            if(bandera == 1)
            {
                admon_act.setVisible(true);
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
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        edad = new javax.swing.JLabel();
        puesto = new javax.swing.JLabel();
        horaentrada = new javax.swing.JLabel();
        horasalida = new javax.swing.JLabel();
        botonsalir = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista_actividad = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        admon_act = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        titulo.setText("¡Bienvenido a casa ");

        jLabel2.setText("Informacion General");

        nombre.setText("Nombre: ");

        telefono.setText("Telefono: ");

        edad.setText("Edad: ");

        puesto.setText("Puesto:");

        horaentrada.setText("HoraEntrada");

        horasalida.setText("HoraSalida");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(nombre)
                    .addComponent(telefono)
                    .addComponent(edad)
                    .addComponent(puesto)
                    .addComponent(horaentrada)
                    .addComponent(horasalida))
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
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
                .addContainerGap(60, Short.MAX_VALUE))
        );

        botonsalir.setText("Salir");
        botonsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonsalirActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lista_actividad);

        jLabel1.setText("ACTIVIDADES ASIGNADAS");

        admon_act.setText("Administrar Actividades");
        admon_act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admon_actActionPerformed(evt);
            }
        });

        jLabel3.setText("Recuerda, tu eres el rostro de nuestra tienda y de quien mas dependemos, ¡Buen Dia!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(botonsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(admon_act, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(titulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel3)))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonsalir)
                    .addComponent(admon_act))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonsalirActionPerformed
         setVisible(false);
         inicio insertid = new inicio();
         insertid.setVisible(true);
    }//GEN-LAST:event_botonsalirActionPerformed

    private void admon_actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admon_actActionPerformed
        this.setVisible(false);
        selecionarempleado selec = new selecionarempleado(IDEmpleado1);
        selec.setVisible(true);
    }//GEN-LAST:event_admon_actActionPerformed

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
            java.util.logging.Logger.getLogger(informacionempleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(informacionempleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(informacionempleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(informacionempleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admon_act;
    private javax.swing.JToggleButton botonsalir;
    private javax.swing.JLabel edad;
    private javax.swing.JLabel horaentrada;
    private javax.swing.JLabel horasalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lista_actividad;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel puesto;
    private javax.swing.JLabel telefono;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
