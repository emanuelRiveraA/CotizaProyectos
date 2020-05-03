/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizaproyectos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sun.misc.FpUtils;

/**
 *
 * @author Ricardo
 */
public class DetalleProyecto implements ActionListener, KeyListener {

    Connection conn = ClaseSingleton.getConnection();
    private Statement st;
    ClaseSingleton tbl = new ClaseSingleton();

    private String proyecto;
    private int idclie;
    private int idcliente;
    private frmProyectos fp;
    private int idproyecto;
    private int idmaterial;
    private int cantidad;
    private double precio;
    private int iddetalle;
    private DetalleProyecto dproyecto;

    public int getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(int iddetalle) {
        this.iddetalle = iddetalle;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    public int getIdmaterial() {
        return idmaterial;
    }

    public void setIdmaterial(int idmaterial) {
        this.idmaterial = idmaterial;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public int getIdclie() {
        return idclie;
    }

    public void setIdclie(int idclie) {
        this.idclie = idclie;
    }

    public DetalleProyecto(int idmaterial, int cantidad, double precio, int idproyecto, int idcliente) {
        this.idproyecto = idproyecto;
        this.idmaterial = idmaterial;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idcliente = idcliente;
    }

    public DetalleProyecto(frmProyectos fp) {

        this.fp = fp;
        iniciarFp();
    }

    public DetalleProyecto(int iddetalle) {
        this.iddetalle = iddetalle;
    }

  

    public DetalleProyecto(String proyecto, int idclie) {
        this.proyecto = proyecto;
        this.idclie = idclie;
    }

    public void llamaBotonFpr() {

        fp.getBtnAceptar().addActionListener(this);
        fp.getBtnAgregar().addActionListener(this);
        fp.getBtnConsultar().addActionListener(this);
        fp.getBtnEliminar().addActionListener(this);

    }

    private void iniciarFp() {

        fp.setVisible(true);
        fp.setLocationRelativeTo(null);
        llamaBotonFpr();
        llamaCajaTextoFp();
        fp.llenarTcliente();
        fp.llenarTmateriales();
        fp.llenarTaddMaterial();
    }

    public void iniciar() {

    }

    public void llamaCajaTextoFp() {
//llamamos los eventos de la caja de texto
        fp.getTxtProyecto().addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fp.getBtnAceptar()) {
            aceptar();
        }
        if (e.getSource() == fp.getBtnAgregar()) {
            agregar();
        }
        if (e.getSource() == fp.getBtnConsultar()) {
            consultarP();
        }
          if (e.getSource() == fp.getBtnEliminar()) {
            eliminarMat();
        }
    }

    public void agregar() {

        if (fp.txtCantidad.getText().equals("") || fp.txtidcliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");

            if (fp.txtCantidad.getText().equals("")) {
                fp.txtCantidad.setBackground(Color.red);
            }
            if (fp.txtidcliente.getText().equals("")) {
                fp.txtidcliente.setBackground(Color.red);
            }

        } else {

            dproyecto = new DetalleProyecto(Integer.parseInt(fp.getTxtidMaterial().getText()),
                    Integer.parseInt(fp.getTxtCantidad().getText()),
                    Double.parseDouble(fp.getTxtPrecio().getText()),
                    Integer.parseInt(fp.getTxtIdproyecto().getText()),
                    Integer.parseInt(fp.getTxtidcliente().getText()));

            dproyecto.ingresarDetalleProyecto();

            JOptionPane.showMessageDialog(null, "Proyecto  guardado correctamente ");
            fp.LimpiarTablaAddMaterial();
             consultarP();

        }

    }

    public void aceptar() {

        if (fp.txtProyecto.getText().equals("") || fp.txtidcliente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");

            if (fp.txtProyecto.getText().equals("")) {
                fp.txtProyecto.setBackground(Color.red);
            }
            if (fp.txtidcliente.getText().equals("")) {
                fp.txtidcliente.setBackground(Color.red);
            }

        } else {

            dproyecto = new DetalleProyecto(fp.getTxtProyecto().getText(),
                    Integer.parseInt(fp.getTxtidcliente().getText()));

            dproyecto.ingresarProyecto();

            JOptionPane.showMessageDialog(null, "Proyecto  guardado correctamente ");
            fp.LimpiarTablaProy();
            fp.llenarTproyecto();

        }

    }

    public void consultarP() {
        
fp.LimpiarTablaAddMaterial();
        try {
            tbl.llenarTabla(fp.tbladdMaterial, "select dp.iddetalle,ma.material,ma.precio,"
                    + " dp.cantidad,dp.cantidad*dp.precio as subtotal,cl.nombre as cliente,"
                    + "dp.idproyecto  from detalle_proyecto dp inner join materiales ma"
                    + " on ma.idmaterial=dp.idmaterial inner join cliente cl on cl.idcliente=dp.idcliente"
                    + " where idproyecto=" + Integer.parseInt(fp.getTxtIdproyecto().getText())+" and "
                            + "dp.idcliente="+Integer.parseInt(fp.getTxtidcliente().getText()));
            
     fp.LimpiarTablaTotal();
     tbl.llenarTabla(fp.tblTotal,"select sum(precio*cantidad) as Total from detalle_proyecto where idproyecto=" + Integer.parseInt(fp.getTxtIdproyecto().getText())+" and "
                            + "idcliente="+Integer.parseInt(fp.getTxtidcliente().getText()));
         //  fp.txtTotal.setText(tbl.llenarTextbox("select sum(precio*cantidad) as Total from detalle_proyecto where idproyecto=7 and idcliente=14")); 
        } catch (Exception ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        quitarColor(e);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void quitarColor(KeyEvent e) {
        if (e.getSource() == fp.getTxtProyecto()) {
            fp.txtProyecto.setBackground(Color.white);
        }

        if (e.getSource() == fp.getTxtidcliente()) {
            fp.txtidcliente.setBackground(Color.white);
        }
        if (e.getSource() == fp.getTxtCantidad()) {
            fp.txtCantidad.setBackground(Color.white);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void ingresarProyecto() {

        try {
            st = conn.createStatement();

            st.execute("insert into proyecto values(null"
                    + ",'" + proyecto + "'"
                    + ",'" + idclie + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ingresarDetalleProyecto() {

        try {
            st = conn.createStatement();

            st.execute("insert into detalle_proyecto values(null"
                    + ",'" + idmaterial + "'"
                    + ",'" + cantidad + "'"
                    + ",'" + precio + "'"
                    + ",'" + idproyecto + "'"
                    + ",'" + idcliente + "')");
        } catch (SQLException ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 public void eliminarMat() {

        try {
            dproyecto = new DetalleProyecto(Integer.parseInt(fp.getTxtiddetalle().getText()) );
            dproyecto.eliminarMaterial();
            
            JOptionPane.showMessageDialog(null, "Producto eliminado satisfactoriamente");
            fp.LimpiarTablaAddMaterial();
            
            
            
            
           consultarP();
        } catch (Exception ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void eliminarMaterial() {

        try {
            st = conn.createStatement();

            st.execute("delete from detalle_proyecto where iddetalle=" + iddetalle);

        } catch (SQLException ex) {
            Logger.getLogger(DetalleProyecto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
