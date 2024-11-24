package org.example;

import java.rmi.Naming;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            Annuaire annuaire = (Annuaire) Naming.lookup("rmi://localhost:1099/AnnuaireService");

            // Ajouter des membres (administrateur uniquement)
            annuaire.ajouterMembre("Doe", "John", "professeur", null, "john.doe@example.com", "555-1234", "Informatique");
            annuaire.ajouterMembre("Smith", "Alice", "étudiant", "12345", "alice.smith@example.com", null, null);

            // Lister les membres
            System.out.println("Liste des professeurs :");
            List<String> professeurs = annuaire.listerMembresParCategorie("professeur");
            professeurs.forEach(System.out::println);

            // Rechercher un membre
            System.out.println("\nRecherche de 'Doe John' :");
            System.out.println(annuaire.rechercherMembre("Doe John"));

            // Mettre sur liste rouge
            annuaire.mettreSurListeRouge("Doe John");
            System.out.println("\nAprès ajout de 'Doe John' à la liste rouge :");
            professeurs = annuaire.listerMembresParCategorie("professeur");
            professeurs.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
