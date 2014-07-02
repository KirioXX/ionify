package com.example.IONify.model;

import java.sql.Blob;
import java.util.jar.Attributes;

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

    public String getWhitUnit(String value){
        if (value == "Name"){
            return name;
        }
        if (value == "Symbol"){
            return symbol;
        }
        if (value == "Id"){
            return Integer.toString(id);
        }
        if(value == "Elektronenkonfiguration"){
            return elektronenkonfiguration;
        }
        if (value == "Atommasse"){
            return Double.toString(atommasse);
        }
        if (value == "Schmelzpunkt"){
            return Double.toString(schmelzpunkt)+"°K";
        }
        if (value == "Siedepunkt"){
            return Double.toString(siedepunkt)+"°K";
        }
        if (value == "Dichte"){
            return dichte+" kg·m⁻³";
        }
        if (value == "Schmelzwärme"){
            if(schmelzwärme != "-"){
                return schmelzwärme+" kJ/mol";
            }else{
                return schmelzwärme;
            }
        }
        if(value == "SpezifischeWärme"){
            return spezifischeWärme+" kJ/mol";
        }

        return "Null";
    }

    @Override
    public String toString() {
        return name;
    }
}
