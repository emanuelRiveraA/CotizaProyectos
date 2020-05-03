package cotizaproyectos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Ricardo
 */
public class ClaseSingleton {

    private static Connection conn = null;
    private String driver;
    private String url;
    private String usuario;
    private String password;
    private ResultSet rs;
    // private Connection cn;

    private PreparedStatement ps;
    private ResultSetMetaData rsm;
    DefaultTableModel dtm;
public String result; 
    // Constructor de clase donde pasamos todos los parametros de la conexion
    ClaseSingleton() {

        url = "jdbc:mysql://localhost:3306/proyectos?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
        driver = "com.mysql.cj.jdbc.Driver";
        usuario = "root";
        password = "root";

        try {

            Class.forName(driver);

            conn = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException e) {
        }
    } // Fin constructor

    // Método que verifica si ya existe una conexion a la base de datos
    public static Connection getConnection() {

        if (conn == null) {
            ClaseSingleton claseSingleton = new ClaseSingleton();
        }

        return conn;
    }
//metodo para llenar las tablas que tengamos en el proyecto
    public void llenarTabla(JTable tabla, String stmSQL) throws Exception {
        ps = conn.prepareStatement(stmSQL);
        rs = ps.executeQuery();
        rsm = rs.getMetaData();
        ArrayList<Object[]> datos = new ArrayList<>();
        while (rs.next()) {
            Object[] filas = new Object[rsm.getColumnCount()];
            for (int i = 0; i < filas.length; i++) {
                filas[i] = rs.getObject(i + 1);

            }
            datos.add(filas);
        }
        dtm = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < datos.size(); i++) {
            dtm.addRow(datos.get(i));
        }

      
    }
//metodo para limpiar las tablas y volverlas a llenar co resulset
    public void reiniciarJTable(javax.swing.JTable Tabla) {
        DefaultTableModel modelo = (DefaultTableModel) Tabla.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

        
    }
 

 }