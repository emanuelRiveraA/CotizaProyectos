/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizaproyectos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class Cliente extends Persona {

    Connection conn = ClaseSingleton.getConnection();
    private Statement st;
    private int idclie;
    private String nombre;
    private String telefono;
    private String rfc;
    private String direccion;
    
    public int getIdclie() {
        return idclie;
    }

    public void setIdclie(int idclie) {
        this.idclie = idclie;
    }

    public Cliente(int idclie) {
        this.idclie = idclie;
    }

    public Cliente(String nombre, String direccion, String telefono, String rfc,int idclie) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.rfc = rfc;
        this.direccion = direccion;
          this.idclie = idclie;
    }

    public Cliente() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public void ingresarCliente() {
        
        Encriptacion encriptacion = new Encriptacion();
        
        String nombre_encriptado = encriptacion.recibirCadena(nombre);
        encriptacion.recibirCadenaEncriptada(nombre_encriptado);
        
        try {
            st = conn.createStatement();

            st.execute("call insertarCliente('" + nombre_encriptado + "'" + ",'" + direccion + "'" + ",'" + telefono + "','" + rfc + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void eliminarCliente() {

        try {
            st = conn.createStatement();

            st.execute("call eliminarCliente('" + idclie + "')");

        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modificarCliente() {

        try {
            st = conn.createStatement();

            st.execute("call modificarCliente('"+ nombre + "','" + direccion +"','" + telefono+ "','" + rfc + "','" + idclie+"')");

        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
