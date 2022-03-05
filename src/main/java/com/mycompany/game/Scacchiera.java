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
public class Scacchiera {

    private Casella[][] mappa = new Casella[100][100];// Mappa su cui si svolge il gioco
    private Agente[] giocatori = new Agente[18];

    public Scacchiera(int energie, int stoffe) {
        int n;
        for (int i = 0; i < mappa.length; ++i) {
            for (int j = 0; j < mappa[i].length; ++j) {
                n = (int) (Math.random() * 10000);
                if (n <= energie) {
                    mappa[i][j] = new Casella(i, j, "ENERGIA");
                } else if (n > energie && n < stoffe) {
                    mappa[i][j] = new Casella(i, j, "STOFFA");
                } else {
                    mappa[i][j] = new Casella(i, j, null);
                }
            }
        }
    }

    public Casella getNcasella(Casella pos) {
        if (pos.getX() - 1 < 0) {
            return null;
        } else {
            return mappa[pos.getX() - 1][pos.geY()];
        }
    }

    public Casella getSCasella(Casella pos) {
        if (pos.getX() + 1 >= this.mappa.length) {
            return null;
        } else {
            return mappa[pos.getX() + 1][pos.geY()];
        }
    }

    public Casella getEcasella(Casella pos) {
        if (pos.geY() + 1 >= 100) {
            return null;
        } else {
            return mappa[pos.getX()][pos.geY() + 1];
        }
    }

    public Casella getOCasella(Casella pos) {
        if (pos.geY() - 1 < 0) {
            return null;
        } else {
            return mappa[pos.getX()][pos.geY() - 1];
        }
    }

    public void avvisoSconfitta(int nSconfitto) {
        this.giocatori[nSconfitto].sconfitta();// Ritirata agente
    }

    public void bandieraPersa(Casella persa, int nSconfitto) {
        this.giocatori[nSconfitto].togliCasella(persa);
    }

}
