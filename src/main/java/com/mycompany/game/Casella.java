/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.game;

/**
 *
 * @author FRANCESCO.VEGGIS
 */
public class Casella extends Risorsa {

    private boolean occupato = false;// Se é occupato da un agente
    private int[] coordinata = new int[2];
    private int nBandiera = -1;

    public Casella(int x, int y, String ris) {
        super(ris);
        this.coordinata[0] = x;
        this.coordinata[1] = y;
    }

    public void getRisorsa(int n) {// Chiamato solo nelle province stoffa
        this.setTotRisorsa(this.getTotRisorsa() - n);
        if (this.getTotRisorsa() == 0) {
            this.setTipoRisorsa(null);// Se le stoffe son finite, la provincia diventa conquistabile
            // Nessuna bandiera piantata, per ora
        }
    }

    public void setNBandiera(int n) {
        this.nBandiera = n;
    }

    public int getNBandiera() {
        return this.nBandiera;
    }

    public boolean statoOccupazione() {
        return this.occupato;
    }

    public int getX() {
        return this.coordinata[0];
    }

    public int geY() {
        return this.coordinata[1];
    }

    public int[] ottieniPosizione() {
        return this.coordinata;
    }

    public void cambiaStatoOccupazione() {
        this.occupato = !this.occupato;
    }

}
