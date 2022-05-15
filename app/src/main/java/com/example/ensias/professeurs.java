package com.example.ensias;

import java.util.ArrayList;

public class professeurs {

    public void setLast(String last) {
        this.nom = last;
    }

    String nom,prenom,telephone,dateDeNaissance;



    public professeurs() {
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setNumero(String numero) {
        this.telephone = numero;
    }

    public professeurs(String first , String last, String numero, String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        this.prenom=first;
        this.telephone=numero;
        this.nom=last;
    }
    public String getNumero() {
        return telephone;
    }

    public String getFirst() {
        return prenom;
    }

    public String getLast() {
        return nom;
    }

    public void setFirst(String first) {
        this.prenom = first;
    }
}
