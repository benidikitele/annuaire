package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Annuaire extends UnicastRemoteObject implements IAnnuaire{

    private Map<String, Map<String, String>> membres; // Stocke les informations des membres
    private List<String> listeRouge; // Stocke les noms des membres sur la liste rouge


    protected Annuaire() throws RemoteException {
        super();
        membres = new HashMap<>();
        listeRouge = new ArrayList<>();
    }

    @Override
    public List<String> listerMembresParCategorie(String categorie) throws RemoteException {
        List<String> resultat = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> entry : membres.entrySet()) {
            if (entry.getValue().get("categorie").equalsIgnoreCase(categorie)) {
                if (listeRouge.contains(entry.getKey())) {
                    resultat.add(entry.getKey() + " (Liste rouge)");
                } else {
                    resultat.add(entry.getKey() + " - " + entry.getValue());
                }
            }
        }
        return resultat;
    }

    @Override
    public List<String> listerProfesseursParDomaine(String domaine) throws RemoteException {
        List<String> resultat = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> entry : membres.entrySet()) {
            if ("professeur".equalsIgnoreCase(entry.getValue().get("categorie")) &&
                    entry.getValue().get("domaine").equalsIgnoreCase(domaine)) {
                if (listeRouge.contains(entry.getKey())) {
                    resultat.add(entry.getKey() + " (Liste rouge)");
                } else {
                    resultat.add(entry.getKey() + " - " + entry.getValue());
                }
            }
        }
        return resultat;
    }

    @Override
    public String rechercherMembre(String nomPrenom) throws RemoteException {
        if (listeRouge.contains(nomPrenom)) {
            return nomPrenom + " (Liste rouge)";
        }
        return membres.getOrDefault(nomPrenom, null) != null ? membres.get(nomPrenom).toString() : "Non trouvé";
    }

    @Override
    public void ajouterMembre(String nom, String prenom, String categorie, String matricule, String email, String telephone, String domaine) throws RemoteException {
        String nomComplet = nom + " " + prenom;
        Map<String, String> info = new HashMap<>();
        info.put("categorie", categorie);
        info.put("matricule", matricule);
        info.put("email", email);
        info.put("telephone", telephone);
        info.put("domaine", domaine);
        membres.put(nomComplet, info);
    }

    @Override
    public void supprimerMembre(String nomPrenom) throws RemoteException {
        membres.remove(nomPrenom);
        listeRouge.remove(nomPrenom);
    }

    @Override
    public void mettreSurListeRouge(String nomPrenom) throws RemoteException {
        if (membres.containsKey(nomPrenom) && !listeRouge.contains(nomPrenom)) {
            listeRouge.add(nomPrenom);
        }
    }

    @Override
    public void enleverDeListeRouge(String nomPrenom) throws RemoteException {
        listeRouge.remove(nomPrenom);
    }

}
