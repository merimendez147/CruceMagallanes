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
public class Main {

    public static void main(String[] args) {
        int cantAutosFerry = 15;
        int cantAutos = 60;
        GestorFerry gestorFerry = new GestorFerry(cantAutosFerry);
        Thread ferry = new Thread(new Ferry(gestorFerry));
        ferry.setName("Austral Broom");
        ferry.start();
        Thread[] autos = new Thread[cantAutos];
        for (int i = 0; i < cantAutos; i++) {
            autos[i] = new Thread(new Auto(gestorFerry));
            autos[i].setName("Auto" + (i + 1));
            autos[i].start();
        }
    }
}
