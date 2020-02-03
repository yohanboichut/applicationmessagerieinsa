package vues.terminal;

import controleur.Controleur;
import vues.Accueil;

import java.util.Scanner;

public class AccueilTerminalImpl implements Accueil {

    Controleur controleur;





    AccueilTerminalImpl(Controleur controleur) {
        this.controleur = controleur;
    }


    @Override
    public void afficher() {
        Scanner scanner = new Scanner(System.in);

        int choix = -1;
        do {
            System.out.println("Application messagerie du futur");

            System.out.println("1 - S'inscrire");

            System.out.println("2 - Se connecter");

            System.out.println("Choix ?");


            choix = scanner.nextInt();
        } while (choix <1 || choix>2);

        controleur.validerChoixAccueil(choix);



    }
}
