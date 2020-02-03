package vues.terminal;

import controleur.Controleur;
import vues.Connexion;
import vues.Inscription;

import java.util.Scanner;

public class ConnexionTerminalImpl implements Connexion {

    Controleur controleur;





    ConnexionTerminalImpl(Controleur controleur) {
        this.controleur = controleur;
    }


    @Override
    public void afficher() {
        Scanner scanner = new Scanner(System.in);

        String login = null;
        String password = null;
        System.out.println("Application messagerie du futur -- connexion");
        System.out.println("Saisir votre login");
        login = scanner.nextLine();

        System.out.println("Saisir votre mot de passe");
        password = scanner.nextLine();

        controleur.connexion(login,password);



    }

    @Override
    public void erreur(String s) {
        System.err.println(s);
    }
}
