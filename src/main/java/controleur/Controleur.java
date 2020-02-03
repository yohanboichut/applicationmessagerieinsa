package controleur;

import facade.FabriqueFacadeApplicationMessagerie;
import facade.FacadeApplicationMessagerie;
import facade.MessageLight;
import facade.UtilisateurLight;
import facade.exceptions.*;
import modele.*;
import vues.*;

import java.util.Collection;

public class Controleur {



    FabriqueVues fabriqueVues;

    FacadeApplicationMessagerie facadeApplicationMessagerie;


    Inscription vueInscription;
    Accueil vueAccueil;

    Connexion vueConnexion;

    Menu vueMenu;


    EnvoiMessage vueEnvoiMessage;

    private long identifiant;
    private ReceptionMessage vueReceptionMessage;

    public Controleur(FabriqueFacadeApplicationMessagerie fabriqueFacadeApplicationMessagerie,
                      FabriqueVues fabriqueVues) {

        this.fabriqueVues = fabriqueVues;
        this.facadeApplicationMessagerie = fabriqueFacadeApplicationMessagerie.creer();
        this.fabriqueVues.setControleur(this);
    }


    public void run() {
        this.vueAccueil = this.fabriqueVues.creerAccueil();
        this.vueAccueil.afficher();


    }

    public void validerChoixAccueil(int choix) {

        switch (choix) {
            case 1 : {
                vueInscription = fabriqueVues.creerInscription();
                vueInscription.afficher();
                break;
            }

            case 2: {
                vueConnexion = fabriqueVues.creerConnexion();
                vueConnexion.afficher();

            }
        }


    }

    public void inscription(String login, String password) {

        try {
            facadeApplicationMessagerie.inscription(login,password);
            this.vueAccueil.afficher();

        } catch (LoginDejaPrisException e) {
            vueInscription.erreur("le login "+login+ " est déjà pris !!");
            vueInscription.afficher();

        } catch (InformationsNonConformesException e) {
            vueInscription.erreur("Les informations saisies ne sont pas valides");
            vueInscription.afficher();
        }



    }

    public void connexion(String login, String password) {
        try {
            this.identifiant = facadeApplicationMessagerie.connexion(login,password);
            this.vueMenu = this.fabriqueVues.creerMenu();
            this.goToMenu();

        } catch (LoginDejaPrisException e) {
            this.vueConnexion.erreur("le login "+login+ " est déjà pris !!");
            this.vueAccueil.afficher();

        }
        catch (InformationsNonConformesException e) {
            this.vueConnexion.erreur("Les informations saisies ne sont pas valides");
            this.vueAccueil.afficher();

        } catch (UtilisateurDejaConnecteException e) {
            this.vueConnexion.erreur("L'utilisateur "+login+ " est déjà connecté !!");
            this.vueAccueil.afficher();
        }

    }

    public void validerChoixMenu(int choix) {

        switch (choix){
            case 1: {
                this.vueEnvoiMessage = fabriqueVues.creerEnvoiMessage();
                this.vueEnvoiMessage.afficher();
            }


            case 2 : {
                this.vueReceptionMessage = fabriqueVues.creerReceptionMessage();
                vueReceptionMessage.afficher();
            }
            case 3 : {
                this.deconnexion();
            }

        }
    }

    public Collection<UtilisateurLight> getUtilisateurs() throws UtilisateurNonConnecteException, UtilisateurInexistantException {
        return this.facadeApplicationMessagerie.getListeDesInscrits(this.identifiant);
    }

    public void envoyerMessage(long id, String message) {
        try {
            this.facadeApplicationMessagerie.envoyerUnMessage(this.identifiant,id,message);
            this.vueEnvoiMessage.confirmation();
            this.goToMenu();
        } catch (UtilisateurNonConnecteException e) {
            this.vueEnvoiMessage.erreur("L'utilisateur n'est pas connecté");
            this.deconnexion();
        } catch (UtilisateurInexistantException e) {
            this.vueEnvoiMessage.erreur("Le destinataire spécifié n'existe pas ("+id+")");
            this.goToMenu();
        }
    }

    public void deconnexion() {
        try {
            this.facadeApplicationMessagerie.deconnexion(this.identifiant);
            this.vueAccueil.afficher();
        } catch (InformationsNonConformesException e) {

        }
    }

    public Collection<MessageLight> getMesMessages() throws UtilisateurNonConnecteException {
        return this.facadeApplicationMessagerie.getMesMessages(this.identifiant);
    }

    public void goToMenu() {
        this.vueMenu.afficher();
    }
}
