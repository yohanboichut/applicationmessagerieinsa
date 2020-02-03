package vues.terminal;

import controleur.Controleur;
import vues.*;

public class FabriqueVuesTerminalImpl implements FabriqueVues {

    Controleur controleur;



    public FabriqueVuesTerminalImpl() {

    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    public FabriqueVuesTerminalImpl(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public Accueil creerAccueil() {
        return new AccueilTerminalImpl(controleur);
    }

    @Override
    public Inscription creerInscription() {
        return new InscriptionTerminalImpl(controleur);
    }

    @Override
    public Connexion creerConnexion() {
        return new ConnexionTerminalImpl(controleur);
    }

    @Override
    public Menu creerMenu() {
        return new MenulTerminalImpl(controleur);
    }

    @Override
    public EnvoiMessage creerEnvoiMessage() {
        return new EnvoiMessageTerminalImpl(controleur);
    }
}
