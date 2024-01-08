public class Bateau {

    private String name;

    private int[][] coordonnees;  // Coordonnées du bateau

    private int taille;  // Taille du bateau

    private String direction;  // Direction du bateau (horizontal ou vertical)



    public Bateau(String name, int taille, Plateau grilles) {    
        this.name = name; 
        this.taille = taille;  
        this.direction = defDirection();
        this.coordonnees = initCoordonnees(grilles); 
    }

    // Méthode pour définir aléatoirement la direction du bateau (horizontal ou vertical)
    private String defDirection() {
        int x = (int) (Math.random() * (2));
        if (x == 1) {
            return "h";  // horizontal
        } else {
            return "v";  // vertical
        }
    }

    // Méthode pour initialiser les coordonnées du bateau sur le plateau de jeu
    private int[][] initCoordonnees(Plateau grilles) {

        int verifie = 0;
        int[][] coo = new int[taille][2];

        while (verifie == 0) {
            if (direction.equals("h")) {  // Si la direction est horizontale
                int x = (int) (Math.random() * (grilles.getAbscisse()));
                int y = (int) (Math.random() * (grilles.getOrdonnees() - taille));
                for (int u = 0; u < taille; u++) {
                    coo[u][0] = y + u;
                    coo[u][1] = x;
                }
            } else {  // Si la direction est verticale
                int x = (int) (Math.random() * (grilles.getAbscisse() - taille));
                int y = (int) (Math.random() * (grilles.getOrdonnees()));
                for (int u = 0; u < taille; u++) {
                    coo[u][0] = y;
                    coo[u][1] = x + u;
                }
            }

            verifie = 1;

            int yy = 0;
            int xx = 0;
            for (int u = 0; u < taille; u++) {
                for (int i = 0; i < grilles.renvoieCooUtiliser().size(); i += 2) {
                    yy = grilles.renvoieCooUtiliser().get(i);
                    xx = grilles.renvoieCooUtiliser().get(i + 1);
                    if (yy == coo[u][0] && xx == coo[u][1]) {
                        verifie = 0;
                    }
                }
            }
        }

        // Ajout des coordonnées du bateau à la liste des coordonnées utilisées sur le plateau
        for (int u = 0; u < taille; u++) {
            grilles.renvoieCooUtiliser().add(coo[u][0]);
            grilles.renvoieCooUtiliser().add(coo[u][1]);
        }

        return coo;
    }

    // Méthode pour obtenir le nom du bateau
    public String getName() {
        return name;
    }

    // Méthode pour obtenir le nom du bateau avec la première lettre en majuscule
    public String getNameF() {
        String majus = name.substring(0, 1);
        String maju = majus.toUpperCase();
        return maju;
    }

    // Méthode pour obtenir la première lettre du nom du bateau en minuscule
    public String getLetter() {
        String minus = name.substring(0, 1);
        String minu = minus.toLowerCase();
        return minu;
    }

    // Méthode pour obtenir la première coordonnée Y du bateau
    public int getCoordonneesY() {
        return coordonnees[0][0];
    }

    // Méthode pour obtenir la première coordonnée X du bateau
    public int getCoordonneesX() {
        return coordonnees[0][1];
    }

    // Méthode pour obtenir les coordonnées complètes du bateau
    public int[][] getCoordonneesFull() {
        return coordonnees;
    }

    // Méthode pour obtenir la taille du bateau
    public int getTaille() {
        return taille;
    }

    // Méthode pour obtenir la direction du bateau
    public String getDirection() {
        return direction;
    }

    // Méthode pour définir la direction du bateau
    public void setDirection(String direction) {
        this.direction = direction;
    }
}