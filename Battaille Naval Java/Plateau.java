import java.util.ArrayList;
import java.util.Scanner;

public class Plateau {

    private int ordonnees;  // Nombre de lignes du plateau
    private int abscisse;   // Nombre de colonnes du plateau
    private String[][] grille;  // La grille du plateau de jeu
    private String[] tempX;     // Tableau temporaire pour initialiser la grille
    private ArrayList<Integer> fullCoo;  // Liste des coordonnées utilisées par les bateaux
    private ArrayList<Integer> dejaTire; // Liste des coordonnées déjà tirées

    // Constructeur de la classe Plateau
    public Plateau(int abscisse, int ordonnees) {    
        this.abscisse = abscisse;
        this.ordonnees = ordonnees;
        this.grille = initPlateau();
        this.fullCoo = new ArrayList<>();
        this.dejaTire = new ArrayList<>();
    }

    // Méthode pour initialiser la grille du plateau avec des points "."
    private String[][] initPlateau() {
        String[][] grillePlateau = new String[ordonnees][abscisse];

        for (int y = 0; y < ordonnees; y++) {
            tempX = new String[ordonnees];
            for (int x = 0; x < abscisse; x++) {
                tempX[x] = ".";
            }
            grillePlateau[y] = tempX;
        }

        return grillePlateau;
    }

    // Méthode pour ajouter un bateau à la grille du plateau
    public String[][] ajouteBateau(Bateau navire) {
        if (navire.getDirection().equals("h")) {
            for (int i = 0; i < navire.getTaille(); i++) {
                grille[navire.getCoordonneesY() + i][navire.getCoordonneesX()] = navire.getNameF();
            }
        } else {
            for (int i = 0; i < navire.getTaille(); i++) {
                grille[navire.getCoordonneesY()][navire.getCoordonneesX() + i] = navire.getNameF();
            }
        }
        return grille;
    }

    // Méthode pour afficher le plateau sous forme de chaîne de caractères
    public String toString() {
        String result = " ";
        for (int i = 0; i < grille.length; i++) {
            result = result + " " + i;
        }
        result = result + "\n";
        for (int i = 0; i < grille.length; i++) {
            result = result + i;
            for (int j = 0; j < grille.length; j++) {
                result = result + " " + grille[i][j];
            }
            result = result + "\n";
        }
        return result;
    }

    // Méthode pour obtenir le nombre de lignes du plateau
    public int getOrdonnees() {
        return ordonnees;
    }

    // Méthode pour obtenir le nombre de colonnes du plateau
    public int getAbscisse() {
        return abscisse;
    }

    // Méthode pour obtenir la grille du plateau
    public String[][] getGrille() {
        return grille;
    }

    // Méthode pour obtenir la liste des coordonnées utilisées par les bateaux
    public ArrayList<Integer> renvoieCooUtiliser() {
        return fullCoo;
    }

    // Méthode pour saisir les choix du joueur (coordonnées de tir)
    public int[] choixJoueur() {
        Scanner scanner = new Scanner(System.in);
        int userInput;
        int choix;

        do {
            System.out.print("Veuillez saisir un nombre entre 0 et 9 pour l'axe horizontal: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un nombre valide.");
                scanner.next();
            }
            userInput = scanner.nextInt();
        } while (userInput < 0 || userInput > 9);

        do {
            System.out.print("Veuillez saisir un nombre entre 0 et 9 pour l'axe vertical: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Veuillez saisir un nombre valide.");
                scanner.next();
            }

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
            } else {
                System.out.println("Fin de l'entrée. Veuillez saisir à nouveau.");
                choix = -1;
            }
        } while (choix < 0 || choix > 9);

        int[] listeChoix = {userInput, choix};
        return listeChoix;
    }

    // Méthode pour vérifier si les choix du joueur ont déjà été utilisés
    public int[] verifieChoixDejaUtiliser() {
        int ver = 0;
        int[] listeChoix = {0, 0};
        while (ver == 0) {
            listeChoix = choixJoueur();
            ver = 1;
            for (int i = 0; i < dejaTire.size(); i += 2) {
                if (listeChoix[0] == dejaTire.get(i) && listeChoix[1] == dejaTire.get(i + 1)) {
                    ver = 0;
                    System.out.println("vous avez déjà tiré à cette endroit");
                }
            }
        }
        dejaTire.add(listeChoix[0]);
        dejaTire.add(listeChoix[1]);
        return listeChoix;
    }

    // Méthode pour vérifier si le tir a touché ou raté un bateau
    public int[] verifTouche() {
        int[] listeChoix = verifieChoixDejaUtiliser();
        int[] listeVerif = {listeChoix[0], listeChoix[1], 0};
        for (int i = 0; i < fullCoo.size(); i += 2) {
            if (listeChoix[0] == fullCoo.get(i) && listeChoix[1] == fullCoo.get(i + 1)) {
                System.out.println("touché");
                listeVerif[2] = 1;
            }
        }
        if (listeVerif[2] == 0) {
            System.out.println("raté");
        }
        return listeVerif;
    }

    // Méthode pour ajouter un tir sur la grille du plateau
    public String[][] ajouteTire(Plateau attaque){
        int[] listeVerif = verifTouche();
        if(listeVerif[2]==1){
            grille[listeVerif[0]][listeVerif[1]] = grille[listeVerif[0]][listeVerif[1]].toLowerCase();
            attaque.getGrille()[listeVerif[0]][listeVerif[1]] = "?";
        }
        else{
            grille[listeVerif[0]][listeVerif[1]] = "X";
            attaque.getGrille()[listeVerif[0]][listeVerif[1]] = "W";
        }
        return grille;
    }

    public boolean estBateauCoule(Bateau bateau) {
        int[][] coordonneesBateau = bateau.getCoordonneesFull();
        String minus = bateau.getLetter();

        for (int i = 0; i < bateau.getTaille(); i++) {
            int y = coordonneesBateau[i][0];
            int x = coordonneesBateau[i][1];
            if (!grille[y][x].equals(minus)) {
                return false;
            }
        }
        return true;
    }
}