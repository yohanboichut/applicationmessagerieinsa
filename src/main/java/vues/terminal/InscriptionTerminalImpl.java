package vues.terminal;

import controleur.Controleur;
import vues.Accueil;
import vues.Inscription;

import java.util.Scanner;

public class InscriptionTerminalImpl implements Inscription {

    Controleur controleur;





    InscriptionTerminalImpl(Controleur controleur) {
        this.controleur = controleur;
    }


    @Override
    public void afficher() {
        Scanner scanner = new Scanner(System.in);

        String login = null;
        String password = null;

        do {
            System.out.println("Application messagerie du futur");

            System.out.println("Saisir un login");
            login = scanner.nextLine();

            System.out.println("Saisir un mot de passe");
            password = scanner.nextLine();

        } while (login.length() <3 || password.length() < 3);

        controleur.inscription(login,password);



    }

    @Override
    public void erreur(String s) {
        System.err.println(s);
    }
}
