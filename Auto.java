/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CruceMagallanes;

/**
 *
 * @author Maria Mendez
 */
public class Auto implements Runnable {

    GestorFerry ferry;

    public Auto(GestorFerry gf) {
        this.ferry = gf;
    }

    @Override
    public void run(){
        ferry.subirAutoFerry();
        System.out.println("Subio al Ferry el "+ Thread.currentThread().getName());
        ferry.bajarAutoFerry();
        System.out.println("Bajo del Ferry el "+ Thread.currentThread().getName());
    }
}
