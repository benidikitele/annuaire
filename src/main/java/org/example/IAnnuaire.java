package org.example;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IAnnuaire extends Remote {
    List<String> listerMembresParCategorie(String categorie) throws RemoteException;
    List<String> listerProfesseursParDomaine(String domaine) throws RemoteException;
    String rechercherMembre(String nomPrenom) throws RemoteException;
    void ajouterMembre(String nom, String prenom, String categorie, String matricule, String email, String telephone, String domaine) throws RemoteException;
    void supprimerMembre(String nomPrenom) throws RemoteException;
    void mettreSurListeRouge(String nomPrenom) throws RemoteException;
    void enleverDeListeRouge(String nomPrenom) throws RemoteException;

}
