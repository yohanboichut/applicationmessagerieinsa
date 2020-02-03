package programme;

import controleur.Controleur;
import modele.FabriqueFacadeApplicationMessagerie;
import vues.FabriqueVues;
import vues.terminal.FabriqueVuesTerminalImpl;

public class Main {
    public static void main(String[] args) {

        FabriqueVues fabriqueVues = new FabriqueVuesTerminalImpl();
        FabriqueFacadeApplicationMessagerie facadeApplicationMessagerie = new FabriqueFacadeApplicationMessagerieImpl();

        Controleur controleur = new Controleur(facadeApplicationMessagerie,fabriqueVues);

        controleur.run();

    }
}
