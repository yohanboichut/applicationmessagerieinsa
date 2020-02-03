package vues.terminal;

import controleur.Controleur;
import facade.UtilisateurLight;
import facade.exceptions.UtilisateurInexistantException;
import facade.exceptions.UtilisateurNonConnecteException;
import vues.Accueil;
import vues.EnvoiMessage;

import java.util.Collection;
import java.util.Scanner;

public class EnvoiMessageTerminalImpl implements EnvoiMessage {

    Controleur controleur;





    EnvoiMessageTerminalImpl(Controleur controleur) {
        this.controleur = controleur;
    }


    @Override
    public void afficher() {
        Scanner scanner = new Scanner(System.in);


        Collection<UtilisateurLight> utilisateurs = null;
        try {
            utilisateurs = controleur.getUtilisateurs();
            System.out.println("Voici la liste des utilisateurs :");

            for (UtilisateurLight u: utilisateurs) {
                System.out.println(u.getId()+" : "+u.getLogin());
            }


            System.out.println("Saisir l'identifiant du destinataire:");
            long id = scanner.nextLong();


            System.out.println("Saisir votre message :");
            scanner= new Scanner(System.in);
            String message = scanner.nextLine();
            controleur.envoyerMessage(id,message);
        } catch (UtilisateurNonConnecteException e) {
            System.err.println("Bizarre...");
            controleur.deconnexion();
        } catch (UtilisateurInexistantException e) {
            System.err.println("Là aussi... Bizarre...");
            controleur.deconnexion();
        }




    }



    @Override
    public void confirmation() {
        System.out.println("Le message a bien été envoyé !!!");
    }

    @Override
    public void erreur(String s) {
        System.err.println(s);
    }
}
