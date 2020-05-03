/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizaproyectos;

import java.awt.EventQueue;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class CotizaProyectos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                new Controlador(new frmCliente());
                new Materiales(new frmMaterial());
                new DetalleProyecto(new frmProyectos());

            }

        });

    }

}
