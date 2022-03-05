/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.game;

import java.util.*;

public class Agente {

    private int nAgente;
    private Scacchiera mappa;
    private int energia;
    private Casella posizione;
    private ArrayList<Casella> territorioConquistato;
    private int stoffe;

    public Agente(int n, Casella pos, int stof) {
        this.nAgente = n;
        this.energia = 100;
        this.aumentaStoffe(stof);
        this.posizione.setNBandiera(this.nAgente);
        this.posizione = pos;
        this.posizione.cambiaStatoOccupazione();
        this.territorioConquistato.add(pos);
    }

    public int getNAgente() {
        return this.nAgente;
    }

    public Casella getPosizione() {
        return this.posizione;
    }

    public void togliCasella(Casella rimuovi) {
        this.territorioConquistato.remove(rimuovi);
    }

    public void aggiungiCasella(Casella aggiungi) {
        this.territorioConquistato.add(aggiungi);
    }

    public void setPosizione(Casella nuovaPos) {
        this.posizione = nuovaPos;
    }

    public int getStoffe() {
        return stoffe;
    }

    public void aumentaStoffe(int stoffe) {
        this.stoffe += stoffe;
    }

    public void diminuisciStoffe(int stoffe) {
        this.stoffe -= stoffe;
    }

    public int getEnergia() {
        return this.energia;
    }

    public void aumentaEnergia(int e) {
        if (this.energia < 100) {
            if (this.energia + e > 100) {
                this.energia = 100;
            } else {
                this.energia += e;
            }
        }

    }

    public void diminuisciEnergia(int e) {
        this.energia -= e;
    }

    public void sconfitta() {
        this.diminuisciStoffe(5);
        this.diminuisciEnergia(10);
        // ALGORITMO PERCHÉ SI RITIRI IN UNA CASELLA DA LUI GIÁ OCCUPATA da implementare
        // this.posizione=null; con modifica casella-poszione territorio ecc
        if (this.posizione.tipoRisorsa() == null) {// Se non é prov-risorsa non é mai stata incorporata nel territorio,
            // previene null exception
            this.togliCasella(this.posizione);
        }
    }

    private void movimento(Casella newCasella) {
        if (!this.territorioConquistato.contains(newCasella)) {// Se non fa parte del territorio
            // Modifica risorse in base a casella nuova occupata
            if (newCasella.statoOccupazione()) {// Se c'é un nemico
                this.diminuisciEnergia(2);// COMBATTIMENTO
                if (Math.random() * 99 > 50) {// Vittoria
                    this.diminuisciEnergia(4);
                    this.mappa.avvisoSconfitta(newCasella.getNBandiera());// Avvisa agente sconfitto della perdita
                    this.posizione.cambiaStatoOccupazione();
                    this.posizione = newCasella;// Non invoco cambia stato occupazione perché rimane occupata
                    if (this.posizione.tipoRisorsa() == null) {// Se non ha risorse allora la includi e usi stoffa
                        this.aggiungiCasella(this.posizione);// Includi nel tuo territorio
                        this.diminuisciStoffe(4);// Usa stoffa
                        this.posizione.setNBandiera(this.nAgente);// Metti la tua bandiera
                    }
                } else {// Sconfitta
                    this.diminuisciStoffe(5);
                    this.diminuisciEnergia(10);
                }
            } else {// Se non c'é un nemico
                this.posizione.cambiaStatoOccupazione();
                if (this.posizione.tipoRisorsa() == null) {// Se non ha risorse
                    this.mappa.bandieraPersa(newCasella, newCasella.getNBandiera());
                    this.diminuisciStoffe(4);// Usa stoffa
                    newCasella.setNBandiera(this.nAgente);
                    this.aggiungiCasella(newCasella);
                }
                this.posizione = newCasella;
                this.diminuisciEnergia(4);
                this.posizione.cambiaStatoOccupazione();// Ora occupata
            }
        } else {// Se fa parte giá del territorio
            this.posizione.cambiaStatoOccupazione();
            newCasella.cambiaStatoOccupazione();
            this.posizione = newCasella;
            this.diminuisciEnergia(4);
        }
        switch (this.posizione.tipoRisorsa()) {// Prendere eventuali risorse
            case "STOFFA":
                if (this.posizione.getTotRisorsa() > 0) {
                    int n = (int) (Math.random() * 9);// Tot sotffa
                    if (n > this.posizione.getTotRisorsa()) {// Se n é maggior di ció che é rimasto, prendi ció che
                        // rimane
                        n = this.posizione.getTotRisorsa();
                    }
                    this.posizione.setTotRisorsa(this.posizione.getTotRisorsa() - n);// Raccogli
                    this.aumentaStoffe(n);// Tieni stoffa
                }
                break;
            case "ENERGIA":
                this.aumentaEnergia(10);// Raccogli 10 energia
                break;
            default:
                break;
        }
    }

    public void muovi() {// Trova casella vicina e verifica stato
        // Eventuale raccolta degli spostamenti possibli N, S, E, O , diagonale
        Casella spostaN = this.mappa.getNcasella(this.posizione);
        Casella spostaS = this.mappa.getSCasella(this.posizione);
        Casella spostaE = this.mappa.getSCasella(this.posizione);
        Casella spostaO = this.mappa.getSCasella(this.posizione);
        if (spostaN != null && this.attack()) {// Se la casella esiste e non e giá occupata
            this.movimento(spostaN);
        }
    }

    private boolean attack() {// Metodo per decidere se attaccare
        // Valuta eventualmente se i costi sono accettabili (da implementare)
        return true;// Da modificare!!!
    }

}
