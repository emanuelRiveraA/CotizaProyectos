/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizaproyectos;

/**
 *
 * @author Ricardo
 */
public abstract class Persona {

    protected String nombre;
    protected String telefono;
    protected String rfc;
    protected String direccion;

    public Persona(String nombre, String telefono, String rfc, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.rfc = rfc;
        this.direccion = direccion;
    }

    public Persona() {
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


//utilizamos la clase absracta para asignar los metodos usados en las diferentes clases 
   
//metodos abstractos
    public abstract void ingresarCliente();

    public abstract void eliminarCliente();

    public abstract void modificarCliente();

    public abstract void consultar();

}
