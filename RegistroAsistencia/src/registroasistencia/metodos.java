package registroasistencia;

import com.mysql.cj.MysqlConnection;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.logging.*;
import javax.swing.JOptionPane;

public class metodos {
    public static void ConeccionSql() throws SQLException
    {
        String usuario = "root"; //nombre del usuario
        String clave= ""; //la clave, por lo general es en vacio
        String url = "jdbc:mysql://localhost:3306/prueba"; //donde se encuentra la base de datos
        Connection con; //importar sql.connection para la conexion
        Statement stmt; //importar sql statemenr
        ResultSet rs; //resultado de las consultas
        
        try {   
            Class.forName("com.mysql.cj.jdbc.Driver"); //hacer la conexion con la base de datos
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        con = DriverManager.getConnection(url,usuario,clave); //montar el servidor
        stmt = con.createStatement(); //inicializar stmt
        stmt.executeUpdate("INSERT INTO Prueba VALUES(null,'Jair Vega','32','2021-12-13','13:13:13')"); //escribir o cargar datos a la DB
        rs = stmt.executeQuery("SELECT * FROM Prueba");
        rs.next();
        do{
            System.out.println(rs.getString("idPrueba")+" Nombre: "+rs.getString("nombre")+" Edad: "+rs.getString("Edad")+" Fecha: "+rs.getString("Fecha")+" Hora: "+rs.getString("Hora"));
        }while(rs.next());
    }
    
    public static int verificar_asistencia(String id, String fecha) {
        int bandera = 0;
        
        try {                                     
            String usuario = "root"; //nombre del usuario
            String clave= ""; //la clave, por lo general es en vacio
            String url = "jdbc:mysql://localhost:3306/registroasistencia"; //donde se encuentra la base de datos
            Connection con; //importar sql.connection para la conexion
            Statement stmt; //importar sql statemenr
            ResultSet rs; //resultado de las consultas
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //hacer la conexion con la base de datos
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistroAsistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            con = DriverManager.getConnection(url,usuario,clave); //montar el servidor
            
            //VERIFICAR SI EL EMPLEADO ESTA EN LA TIENDA
            stmt = con.createStatement(); //inicializar stmt
            rs = stmt.executeQuery("SELECT * FROM RegistroAsistencia WHERE Empleado_IDEmpleado="+id);
            if(rs.next())
            {
                do{
                    if(fecha.equals(rs.getString("FechaRegistro")) && bandera == 0)
                    {
                        System.err.println("ESTA EN LA TIENDA");
                        bandera=1;
                    }
                }while(rs.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bandera;
    }
    public static String fecha_actual() 
    {
        java.util.Date date = new java.util.Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        int month = localDate.getMonthValue();
        int day   = localDate.getDayOfMonth();
        String fecha = year+"-"+month+"-"+day; //Variable que tiene la fecha actual                

        if(fecha.length() != 10)
        {
            fecha = year+"-0"+month+"-0"+day; //Variable que tiene la fecha actual
        }
        return fecha;
    }
    public static String buscar_id(String nombre)
    {   
        String id="";
        try {                                     
            String usuario = "root"; //nombre del usuario
            String clave= ""; //la clave, por lo general es en vacio
            String url = "jdbc:mysql://localhost:3306/registroasistencia"; //donde se encuentra la base de datos
            Connection con; //importar sql.connection para la conexion
            Statement stmt; //importar sql statemenr
            ResultSet rs; //resultado de las consultas
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //hacer la conexion con la base de datos
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RegistroAsistencia.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            con = DriverManager.getConnection(url,usuario,clave); //montar el servidor
            
            //VERIFICAR SI EL EMPLEADO ESTA EN LA TIENDA
            stmt = con.createStatement(); //inicializar stmt
            rs = stmt.executeQuery("SELECT * FROM Empleado WHERE Nombre='"+nombre+"'");
            if(rs.next())
            {
                id = rs.getString("IDEmpleado");
                System.out.println(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
}
