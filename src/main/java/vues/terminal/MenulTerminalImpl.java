package vues.terminal;

import controleur.Controleur;
import vues.Accueil;
import vues.Menu;

import java.util.Scanner;

public class MenulTerminalImpl implements Menu {

    Controleur controleur;





    MenulTerminalImpl(Controleur controleur) {
        this.controleur = controleur;
    }


    @Override
    public void afficher() {
        Scanner scanner = new Scanner(System.in);

        int choix = -1;
        do {
            System.out.println("Menu principal");

            System.out.println("1 - envoyer un message");

            System.out.println("2 - se déconnecter");

            System.out.println("Choix ?");


            choix = scanner.nextInt();
        } while (choix <1 || choix>2);

        controleur.validerChoixMenu(choix);



    }
}
