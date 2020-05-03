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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ricardo
 */
public class Controlador implements ActionListener, KeyListener {

    //Instancia de la clase singleton
    Connection conn = ClaseSingleton.getConnection();
    ClaseSingleton tbl = new ClaseSingleton();

    private frmCliente fc;
    private Cliente cliente;

    public Controlador(frmCliente fc) {

        this.fc = fc;
        iniciar();

    }

    private void iniciar() {

        llamaCajaTextoFc();//instancia a el evento cajas de texto formacliente
        llamaBotonFc();//instancia al evento de los botones de la formacliente
        fc.setVisible(true);
        fc.setLocationRelativeTo(null);

        fc.llenarTabla();
    }

    public void llamaCajaTextoFc() {
//llamamos los eventos de la caja de texto
        fc.getTxtIdcliente().addKeyListener(this);
        fc.getTxtNombre().addKeyListener(this);
        fc.getTxtDireccion().addKeyListener(this);
        fc.getTxtTelefono().addKeyListener(this);
        fc.getTxtRfc().addKeyListener(this);

    }

    ;
  public void llamaBotonFc() {

        fc.getBtnGuardar().addActionListener(this);
        fc.getBtnNuevo().addActionListener(this);
        fc.getBtnEliminar().addActionListener(this);
        fc.getBtnModificar().addActionListener(this);
    }

    ;
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == fc.getBtnGuardar()) {
            aceptar();

        }
        if (e.getSource() == fc.getBtnNuevo()) {
            limpiarTexto();

        }
        if (e.getSource() == fc.getBtnEliminar()) {
            eliminarClie();

        }
        if (e.getSource() == fc.getBtnModificar()) {
            modificarClie();
        }
    }

//gestionamos el evento para ingresar los datos 
    private void aceptar() {

        if (fc.txtDireccion.getText().equals("") || fc.txtNombre.getText().equals("") || fc.txtTelefono.getText().equals("") || fc.txtRfc.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Favor de llenar todos los campos");

            if (fc.txtDireccion.getText().equals("")) {
                fc.txtDireccion.setBackground(Color.red);
            }
            if (fc.txtNombre.getText().equals("")) {
                fc.txtNombre.setBackground(Color.red);
            }

            if (fc.txtTelefono.getText().equals("")) {
                fc.txtTelefono.setBackground(Color.red);
            }
            if (fc.txtRfc.getText().equals("")) {
                fc.txtRfc.setBackground(Color.red);
            }
        } else {

            cliente = new Cliente(
                    fc.getTxtNombre().getText(),
                    fc.getTxtDireccion().getText(),
                    fc.getTxtTelefono().getText(),
                    fc.getTxtRfc().getText(),
                    Integer.parseInt(fc.getTxtIdcliente().getText())
            );

            cliente.ingresarCliente();
            JOptionPane.showMessageDialog(null, "cliente ingresado con el nombre " + cliente.getNombre());

            fc.LimpiarTabla();
            fc.llenarTabla();

        }

    }

    public void eliminarClie() {

        cliente = new Cliente(Integer.parseInt(fc.getTxtIdcliente().getText()));
        
       
        cliente.eliminarCliente();
        
         JOptionPane.showMessageDialog(null, "cliente  " + cliente.getNombre() + " eliminado satisfactoriamente");
        fc.LimpiarTabla();
        fc.llenarTabla();

    }

    public void modificarClie() {

      //  cliente = new Cliente(Integer.parseInt(fc.getTxtIdcliente().getText()));
cliente = new Cliente(
                    fc.getTxtNombre().getText(),
                    fc.getTxtDireccion().getText(),
                    fc.getTxtTelefono().getText(),
                    fc.getTxtRfc().getText(),Integer.parseInt(fc.getTxtIdcliente().getText())
            );
     
        cliente.modificarCliente();
        
           JOptionPane.showMessageDialog(null, "cliente  modificado satisfactoriamente");
        fc.LimpiarTabla();
        fc.llenarTabla();

    }

    public void limpiarTexto() {
        fc.txtNombre.setText("");
        fc.txtDireccion.setText("");
        fc.txtRfc.setText("");
        fc.txtTelefono.setText("");
        fc.txtIdcliente.setText("");
    }

    ;
     
    private void cerrar() {

        System.exit(0);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        quitarColor(e);

    }

    public void quitarColor(KeyEvent e) {
        if (e.getSource() == fc.getTxtNombre()) {
            fc.txtNombre.setBackground(Color.white);
        }

        if (e.getSource() == fc.getTxtTelefono()) {
            fc.txtTelefono.setBackground(Color.white);
        }
        if (e.getSource() == fc.getTxtRfc()) {
            fc.txtRfc.setBackground(Color.white);
        }
        if (e.getSource() == fc.getTxtDireccion()) {
            fc.txtDireccion.setBackground(Color.white);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
