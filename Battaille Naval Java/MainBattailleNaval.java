public class MainBattailleNaval {
    public static void main(String[] args) {
        // Initialisation des plateaux pour chaque joueur (défensif et d'attaque)
        Plateau coo = new Plateau(10, 10);
        Plateau j2 = new Plateau(10, 10);
        Plateau cooAttaque = new Plateau(10, 10);
        Plateau j2Attaque = new Plateau(10, 10);

        // Initialisation des bateaux pour chaque joueur
        Bateau sousMarin = new Bateau("Sous-Marin", 3, coo);
        Bateau contreTorpilleur = new Bateau("Contre-Torpilleur", 3, coo);
        Bateau croiseur = new Bateau("Croiseur", 4, coo);
        Bateau torpilleur = new Bateau("Torpilleur", 2, coo);
        Bateau porteAvion = new Bateau("Porte-avions", 5, coo);
        Bateau santaMaria = new Bateau("SantaMaria", 6, coo);

        Bateau sousMarin2 = new Bateau("Sous-Marin", 3, j2);
        Bateau contreTorpilleur2 = new Bateau("Contre-Torpilleur", 3, j2);
        Bateau croiseur2 = new Bateau("Croiseur", 4, j2);
        Bateau torpilleur2 = new Bateau("Torpilleur", 2, j2);
        Bateau porteAvion2 = new Bateau("Porte-avions", 5, j2);
        Bateau blackPearl = new Bateau("BlackPearl", 6, j2);

        // Listes des bateaux pour chaque joueur
        Bateau[] listeDesBateaux1 = {sousMarin, contreTorpilleur, croiseur, torpilleur, porteAvion, santaMaria};
        Bateau[] listeDesBateaux2 = {sousMarin2, contreTorpilleur2, croiseur2, torpilleur2, porteAvion2, blackPearl};

        // Placement des bateaux sur les plateaux
        for (int i = 0; i < listeDesBateaux1.length; i++) {
            coo.ajouteBateau(listeDesBateaux1[i]);
            j2.ajouteBateau(listeDesBateaux2[i]);
        }

        // Variables pour suivre l'état des bateaux coulés et le tour de jeu
        int bateauCoule1 = 0;
        int bateauCoule2 = 0;
        int aQui = 0;

        // Boucle principale du jeu
        while (bateauCoule1 == 0 && bateauCoule2 == 0) {
            aQui++;

            // Tour du Joueur 1
            if (aQui % 2 == 1) {
                bateauCoule1 = 1;
                System.out.println("C'est au joueur 1 de jouer");
                System.out.println("Voici votre plateau défensif");
                System.out.println(coo);
                System.out.println("Voici votre plateau d'attaque");
                System.out.println(cooAttaque);

                // Attaque du Joueur 1 sur le Joueur 2
                j2.ajouteTire(cooAttaque);

                // Vérification des bateaux coulés du Joueur 2
                for (int i = 0; i < listeDesBateaux2.length; i++) {
                    if (j2.estBateauCoule(listeDesBateaux2[i])) {
                        System.out.println("Le " + listeDesBateaux2[i].getName() + " du joueur 2 a été coulé !");
                        // Marquage des bateaux coulés sur les plateaux
                        for (int u = 0; u < listeDesBateaux2[i].getTaille(); u++) {
                            int y = listeDesBateaux2[i].getCoordonneesFull()[u][0];
                            int x = listeDesBateaux2[i].getCoordonneesFull()[u][1];
                            j2.getGrille()[y][x] = "!";
                            cooAttaque.getGrille()[y][x] = "!";
                        }
                    } else {
                        bateauCoule1 = 0;
                    }
                }

                System.out.println("Voici votre plateau d'attaque après votre frappe ");
                System.out.println(cooAttaque);
                System.out.println("----------------------------");
            } 
            // Tour du Joueur 2
            else {
                bateauCoule2 = 1;
                System.out.println("C'est au joueur 2 de jouer");
                System.out.println("Voici votre plateau défensif");
                System.out.println(j2);
                System.out.println("Voici votre plateau d'attaque");
                System.out.println(j2Attaque);

                // Attaque du Joueur 2 sur le Joueur 1
                coo.ajouteTire(j2Attaque);

                // Vérification des bateaux coulés du Joueur 1
                for (int i = 0; i < listeDesBateaux1.length; i++) {
                    if (coo.estBateauCoule(listeDesBateaux1[i])) {
                        System.out.println("Le " + listeDesBateaux1[i].getName() + " du joueur 1 a été coulé !");
                        // Marquage des bateaux coulés sur les plateaux
                        for (int u = 0; u < listeDesBateaux1[i].getTaille(); u++) {
                            int y = listeDesBateaux1[i].getCoordonneesFull()[u][0];
                            int x = listeDesBateaux1[i].getCoordonneesFull()[u][1];
                            coo.getGrille()[y][x] = "!";
                            j2Attaque.getGrille()[y][x] = "!";

                        }
                    } else {
                        bateauCoule2 = 0;
                    }
                }

                System.out.println("Voici votre plateau d'attaque après votre frappe ");
                System.out.println(j2Attaque);
                System.out.println("----------------------------");
            }
        }

        // Annonce du résultat
        if (bateauCoule1 == 1) {
            System.out.println("C'est fini ! Le joueur 1 a gagné");
        } else {
            System.out.println("C'est fini ! Le joueur 2 a gagné");
        }
    }
}
            
