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
public class Risorsa {
    private String risorsa;
    private int totRisorsa;

    public Risorsa(String ris) {// STOFFA; ENERGIA; null
        this.risorsa = ris;
    }

    public String tipoRisorsa() {
        return this.risorsa;
    }

    public void setTipoRisorsa(String nuovo) {
        this.risorsa = nuovo;
    }

    public void setRisorsa(String ris) {
        this.risorsa = ris;
        this.totRisorsa = 100;
    }

    public void setTotRisorsa(int n) {
        this.totRisorsa = n;
    }

    public int getTotRisorsa() {
        return this.totRisorsa;
    }
    
}
