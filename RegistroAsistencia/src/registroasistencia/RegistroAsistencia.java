package registroasistencia;
import com.mysql.cj.MysqlConnection;
import java.sql.*;
import java.util.logging.*;
public class RegistroAsistencia {
    public static void main(String[] args) throws SQLException {
        inicio pantalla_incio= new inicio(); //objeto de la pantalla de inicio
        metodos metodo = new metodos();
        
        //metodo.ConeccionSql();
        pantalla_incio.setVisible(true);
    }
    
}