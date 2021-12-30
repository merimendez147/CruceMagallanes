/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CruceMagallanes;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez
 */
public class GestorFerry {

    Semaphore mutexAutos; //mutex para contar la cantidad de autos que estan en el ferry
    Semaphore ingresarFerry; //semaforo que usa el auto para subir al ferry
    Semaphore bajarFerry; //semaforo que usa el auto para bajar del ferry
    int cantAutosFerry; //cantidad de autos que el ferry puede transpotar
    int autos = 0; //autos que estan en el ferry
    Semaphore ferryLleno; //semaforo que usa el ferry para comenzar a cruzar
    Semaphore volverVacio; //semaforo que usa el ferry para volver vacio

    public GestorFerry(int CA) {
        mutexAutos = new Semaphore(1);
        this.cantAutosFerry = CA;
        ingresarFerry = new Semaphore(0);
        bajarFerry = new Semaphore(0);
        ferryLleno = new Semaphore(0);
        volverVacio = new Semaphore(0);
    }

    public void habilitarIngreso() {
        ingresarFerry.release(cantAutosFerry);
    }

    public void subirAutoFerry() {
        try {
            ingresarFerry.acquire();
            mutexAutos.acquire();
            autos++;
            mutexAutos.release();
            bajarFerry.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFerry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cruzarLleno() {
        try {
            bajarFerry.acquire(cantAutosFerry);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFerry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void habilitarSalida() {
        ferryLleno.release();
    }

    public void bajarAutoFerry() {
        try {
            ferryLleno.acquire();
            mutexAutos.acquire();
            autos--;
            mutexAutos.release();
            ferryLleno.release();//el auto que sale habilita al siguiente, salen uno detras del otro
            volverVacio.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFerry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void volverVacio() {
        try {
            volverVacio.acquire(cantAutosFerry);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFerry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
