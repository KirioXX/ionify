package com.example.IONify.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ben on 25.06.14.
 */
public class Element {
    private int id;
    private String name;
    private String symbol;
    private String elektronenkonfiguration;
    private double atommasse;
    private double schmelzpunkt;
    private double siedepunkt;
    private String dichte;
    private String schmelzwärme;
    private String spezifischeWärme;
    private List<String> ionisationsenergien;
    private List<String> ladElektronenkonfiguration;
    private List<String> energiepotential = new ArrayList<String>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public String getElektronenkonfiguration(){
        return elektronenkonfiguration;
    }

    public void setElektronenkonfiguration(String elektronenkonfiguration){
        this.elektronenkonfiguration = elektronenkonfiguration;
    }

    public double getAtommasse(){
        return atommasse;
    }

    public void setAtommasse(double atommasse){
        this.atommasse = atommasse;
    }

    public double getSchmelzpunkt(){
        return schmelzpunkt;
    }

    public void setSchmelzpunkt(double schmelzpunkt) {
        this.schmelzpunkt = schmelzpunkt;
    }

    public double getSiedepunkt() {
        return siedepunkt;
    }

    public void setSiedepunkt(double siedepunkt) {
        this.siedepunkt = siedepunkt;
    }

    public String getDichte() {
        return dichte;
    }

    public void setDichte(String dichte) {
        this.dichte = dichte;
    }

    public String getSchmelzwärme() {
        return schmelzwärme;
    }

    public void setSchmelzwärme(String schmelzwärme) {
        this.schmelzwärme = schmelzwärme;
    }

    public String getSpezifischeWärme() {
        return spezifischeWärme;
    }

    public void setSpezifischeWärme(String spezifischeWärme) {
        this.spezifischeWärme = spezifischeWärme;
    }

    /*public String getIonisationsenergien() {
        return ionisationsenergien;
    }*/

    public void setIonisationsenergien(List<String> ionisationsenergien){
        this.ionisationsenergien = ionisationsenergien;

    }
    public String getLadElektronenkonfiguration(int prog){
        return ladElektronenkonfiguration.get(prog);
    }
    public void setLadElektronenkonfiguration(List<String> ladElektronenkonfiguration){
        this.ladElektronenkonfiguration = ladElektronenkonfiguration;
    }
    public String getEnergiepotential(int i){
        if(i==0) {
            return "";
        }else{
            return energiepotential.get(i-1);
        }
    }
    public void setEnergiepotential(List<String> energiepotential){
        this.energiepotential = energiepotential;
    }

    public String getWhitUnit(String value){
        if (value.equals("Name")){
            return name;
        }
        if (value.equals("Symbol")){
            return symbol;
        }
        if (value.equals("Id")){
            return Integer.toString(id);
        }
        if(value.equals("Elektronenkonfiguration")){
            return elektronenkonfiguration;
        }
        if (value.equals("Atommasse")){
            return Double.toString(atommasse);
        }
        if (value.equals("Schmelzpunkt")){
            return Double.toString(schmelzpunkt)+"°K";
        }
        if (value.equals("Siedepunkt")){
            return Double.toString(siedepunkt)+"°K";
        }
        if (value.equals("Dichte")){
            return dichte+" kg·m⁻³";
        }
        if (value.equals("Schmelzwärme")){
            if(schmelzwärme != "-"){
                return schmelzwärme+" kJ/mol";
            }else{
                return schmelzwärme;
            }
        }
        if(value.equals("SpezifischeWärme")){
            return spezifischeWärme+" kJ/mol";
        }


        return "Null";
    }


    @Override
    public String toString() {
        return name;
    }
}
