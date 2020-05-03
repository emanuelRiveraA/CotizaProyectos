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
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

/**
 *
 * @author Ricardo
 */
public class Materiales implements ActionListener, KeyListener {

    Connection conn = ClaseSingleton.getConnection();
    ClaseSingleton tbl = new ClaseSingleton();
    private Statement st;

    private String material;
    private double precio;
    private int idcliente;
    private frmMaterial fm;
    private Materiales materiales;
    private frmProyectos fp;

    private int idmaterial;

    public Materiales(String material, double precio, int idmaterial) {
        this.material = material;
        this.precio = precio;
        this.idmaterial = idmaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public Materiales() {

    }

    public Materiales(frmMaterial fm) {
        this.fm = fm;
        iniciarFm();
        // iniciarFp();
    }

    private void iniciarFm() {
        llamaBotonFm();
        llamaCajaTextoFm();
        fm.setVisible(true);
        fm.setLocationRelativeTo(null);
        fm.llenarTabla();

    }

  
    public void llamaBotonFm() {

        fm.getBtnGuardar().addActionListener(this);
        fm.getBtnEliminar().addActionListener(this);
        fm.getBtnModificar().addActionListener(this);
        fm.getBtnNuevo().addActionListener(this);

    }

    public void llamaCajaTextoFm() {
//llamamos los eventos de la caja de texto
        fm.getTxtMaterial().addKeyListener(this);
        fm.getTxtPrecio().addKeyListener(this);
        fm.getTxtidMaterial().addActionListener(this);

    }

    public void aceptar() {

        if (fm.txtMaterial.getText().equals("") || fm.txtPrecio.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");

            if (fm.txtMaterial.getText().equals("")) {
                fm.txtMaterial.setBackground(Color.red);
            }
            if (fm.txtPrecio.getText().equals("")) {
                fm.txtPrecio.setBackground(Color.red);
            }

        } else {

            materiales = new Materiales(fm.getTxtMaterial().getText(),
                    Double.parseDouble(fm.getTxtPrecio().getText()),
                    0);

            materiales.ingresarMaterial();
            // fc.getTxtRfc().getText()
            fm.LimpiarTabla();
            JOptionPane.showMessageDialog(null, "Material ingresado correctamente ");
            fm.llenarTabla();
            limpiarTexto();
            // fm.llenarTabla();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fm.getBtnGuardar()) {
            aceptar();
        }
        if (e.getSource() == fm.getBtnModificar()) {
            modificarMat();
        }
        if (e.getSource() == fm.getBtnEliminar()) {
            eliminarMat();
        }
        if (e.getSource() == fm.getBtnNuevo()) {
            limpiarTexto();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        quitarColor(e);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void quitarColor(KeyEvent e) {
        if (e.getSource() == fm.getTxtMaterial()) {
            fm.txtMaterial.setBackground(Color.white);
        }

        if (e.getSource() == fm.getTxtPrecio()) {
            fm.txtPrecio.setBackground(Color.white);
        }
    }

    public void eliminarMat() {

        materiales = new Materiales(null, 0, Integer.parseInt(fm.getTxtidMaterial().getText()));

        materiales.eliminarMaterial();

        JOptionPane.showMessageDialog(null, "Material eliminado satisfactoriamente");
        fm.LimpiarTabla();
        fm.llenarTabla();
        limpiarTexto();
    }

    public void modificarMat() {

        //  cliente = new Cliente(Integer.parseInt(fc.getTxtIdcliente().getText()));
        materiales = new Materiales(fm.getTxtMaterial().getText(),
                Double.parseDouble(fm.getTxtPrecio().getText()),
                Integer.parseInt(fm.getTxtidMaterial().getText()));

        materiales.modificarMaterial();

        JOptionPane.showMessageDialog(null, "Producto  modificado satisfactoriamente");
        fm.LimpiarTabla();
        fm.llenarTabla();
        limpiarTexto();
    }

    public void ingresarMaterial() {

        try {
            st = conn.createStatement();

            st.execute("insert into materiales values(null"
                    + ",'" + material + "'"
                    + ",'" + precio + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Materiales.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarMaterial() {

        try {
            st = conn.createStatement();

            st.execute("delete from materiales where idmaterial=" + idmaterial);

        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modificarMaterial() {

        try {
            st = conn.createStatement();

            st.execute("update  materiales set material='"
                    + material + "',precio='" + precio + "' where idmaterial=" + idmaterial);

        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void limpiarTexto() {
        fm.txtidMaterial.setText("");
        fm.txtPrecio.setText("");
        fm.txtMaterial.setText("");
        fm.txtMaterial.requestFocus();
    }
}
