package org.example;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        try {
            LocateRegistry.createRegistry(1099);
            IAnnuaire annuaire = new Annuaire();
            java.rmi.Naming.rebind("rmi://localhost:1099/AnnuaireService", annuaire);
            System.out.println("Serveur Annuaire démarré.");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}