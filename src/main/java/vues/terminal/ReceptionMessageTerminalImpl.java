package vues.terminal;

import controleur.Controleur;
import facade.MessageLight;
import facade.exceptions.UtilisateurNonConnecteException;
import modele.*;
import vues.EnvoiMessage;
import vues.ReceptionMessage;

import java.util.Collection;
import java.util.Scanner;

public class ReceptionMessageTerminalImpl implements ReceptionMessage {

    Controleur controleur;





    ReceptionMessageTerminalImpl(Controleur controleur) {
        this.controleur = controleur;
    }


    @Override
    public void afficher() {
        Collection<MessageLight> messages = null;
        try {
            messages = controleur.getMesMessages();
            System.out.println("Voici tous vos messages reçus");

            for (MessageLight u: messages) {
                System.out.println("De "+ u.getEnvoyeur().getLogin()+" : "+u.getContenu());
            }

            this.controleur.goToMenu();

        } catch (UtilisateurNonConnecteException e) {
            System.err.println("Bizarre...");
            controleur.deconnexion();
        }
    }


    @Override
    public void erreur(String s) {
        System.err.println(s);
    }
}
