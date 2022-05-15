package com.example.ensias;

public class Etudiants {
   String nom,prenom,telephone,email,branche;

    public String getBranche() {
        return branche;
    }

    public String getEmail() {
        return email;
    }

    public String getNom() {
        return nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }
}
