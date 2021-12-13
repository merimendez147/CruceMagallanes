/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CruceMagallanes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez
 */
public class Ferry implements Runnable {

    GestorFerry gestorFerry;

    public Ferry(GestorFerry ferry) {
        this.gestorFerry = ferry;
    }

    private void cruzar() {
        try {
            System.out.println("El ferry " + Thread.currentThread().getName() + " esta cruzando el estrecho de Magallanes");
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            gestorFerry.habilitarIngreso();
            gestorFerry.cruzarLleno();
            cruzar();
            gestorFerry.habilitarSalida();
            gestorFerry.volverVacio();
        }
    }
}
