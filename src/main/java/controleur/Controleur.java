package controleur;

import modele.*;
import vues.*;

public class Controleur {



    FabriqueVues fabriqueVues;

    FacadeApplicationMessagerie facadeApplicationMessagerie;


    Inscription vueInscription;
    Accueil vueAccueil;

    Connexion vueConnexion;

    Menu vueMenu;

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
            facadeApplicationMessagerie.connexion(login,password);
            this.vueMenu = this.fabriqueVues.creerMenu();
            this.vueMenu.afficher();

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

    }
}
