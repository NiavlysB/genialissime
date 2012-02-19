package fr.um2.projetl3.tarotandroid.jeu;
import java.lang.Math;

public class Donne
{

	private static Carte donneAvant[];
	private static Main mainsDesJoueurs[];
	private static Carte chien[];
	private static Contrat contratEnCours;
	private static Joueur preneur;
	private static Carte plisEnCours[] = new Carte[4];
	private static Carte plisPrecedent[] = new Carte[4];

	/**
	 * @author JB
	 * 
	 * methode de disbrution des cartes
	 * 
	 * sauf erreur de ma part les calcules devrait �tre bon, je verifir� avec quelqu'un
	 * 
	 *  // ? je ne sais pas ou mettre les constantes
	 *  
	 *  // ! Chose � modifier :
	 *  		mettre les constantes au bon endroit
	 *  		changer la valeur 6 par Chien.getNombreDeCartes() 
	 *  		� la fin il faut affecter les mains qui sont dans le tableau aux joeur
	 *  			=> suivant le sens des aiguille d'une montre ou non 
	 *  	
	 */
	
	/**/
	 public static void distribution()
	 {
		 int nombreDeJoueurs = Partie.getNombreDeJoueurs(); 
		 int numeroDuJoueur = 0; // ! j'en ai besoin pour savoir � quel joueur je vais donner les cartes
		 
		 int possibilitesMisesAuChien = 0;		 
		 int nombreDeCartesMisesAuChien = 0;
		 int nombreDeCartesPourLeChien = Partie.getnombreDeCartesPourLeChien();
		
		 donneAvant = new Carte[Constantes.NOMBRE_CARTES_TOTALES-1]; // ??? Tableau de 77 cartes ?
		 mainsDesJoueurs = new Main[nombreDeJoueurs];
		 chien = new Carte[nombreDeCartesPourLeChien];
		 /*
		  * à voir pour la donne précedente les cartes seront distribué par rapport à l'indice j du tableau de la donne précedente
		  */
		 
 
		 int randomMin = 1;
		 int randomMax;
		 // random(Min/Max) permette de savoir sur quel intervalle on doit faire le random
		 
		 int j=0,l,k=0;
		 		 
		 possibilitesMisesAuChien = (( Constantes.NOMBRE_CARTES_TOTALES - nombreDeCartesPourLeChien ) / Constantes.CARTES_DISTRIBU_PAR_JOUEUR) ;
		 
		 
		 while(( nombreDeCartesPourLeChien - nombreDeCartesMisesAuChien ) == 0) 
		 {
			 randomMax = possibilitesMisesAuChien - ( nombreDeCartesPourLeChien - nombreDeCartesMisesAuChien );
			 
			 randomMin = randomMin + (int)(Math.random() * ((randomMax - randomMin)+1));
			 // ! il faut que la valeur de retour soit comprise entre ]randomMin,randomMax] !
			 
			 //nombreDeCartesMisesAuChien = (int) Math.random()*100 % 3; 
			 
			 while(j<=(randomMin*Constantes.CARTES_DISTRIBU_PAR_JOUEUR))
			 {
				 if (numeroDuJoueur == nombreDeJoueurs)
				 {
					 numeroDuJoueur = 0;
				 }
				 
				 mainsDesJoueurs[numeroDuJoueur].addCarte(donneAvant[j++]);
				 mainsDesJoueurs[numeroDuJoueur].addCarte(donneAvant[j++]);
				 mainsDesJoueurs[numeroDuJoueur].addCarte(donneAvant[j++]);				 
				 // l'incrementation du j doit se faire avant l'affectation au tableau
				 numeroDuJoueur++;
			 }
			 for(l=0;l<=nombreDeCartesMisesAuChien;l++)
			 {

				 chien[k]=donneAvant[j];
				 j++;
				 k++;
				 randomMin++;
			 }

			 
		}
		 while(j<Constantes.NOMBRE_CARTES_TOTALES-1)
		 {
			 if (numeroDuJoueur == nombreDeJoueurs) 
			 {
				 numeroDuJoueur = 0;	
			 }
			 
			 mainsDesJoueurs[numeroDuJoueur].addCarte(donneAvant[j++]);
			 mainsDesJoueurs[numeroDuJoueur].addCarte(donneAvant[j++]);
			 mainsDesJoueurs[numeroDuJoueur].addCarte(donneAvant[j++]);	
			 // l'incrementation du j doit se faire avant
			 
			 numeroDuJoueur++;
		 }
		 
		 // affectation des mains aux joueurs
	 }

	 /**
	  * 
	  * @author JB
	  * méthode inachever car j'etais fatigué
	  * 
	  * @param tableauContenantLePlis
	  * @return l'indice du tableau ou se trouve la carte qui remporte le plis grâce à ça on peut retrouver qui remporte le plis
	  */
	 public int vainqueurDuPlis(Carte[] tableauContenantLePlis)
	 {
		int indice = -1;
		Couleur couleurDemander = null;
		
		if(tableauContenantLePlis[1].isExcuse())
		{			
			couleurDemander = ((CarteCouleur)tableauContenantLePlis[2]).getCouleur();
		}
		else if(! tableauContenantLePlis[2].isAtout())
		{
			couleurDemander = ((CarteCouleur)tableauContenantLePlis[2]).getCouleur();
		}
		
		// ici il faut faire en sorte de renvoyer l'indice de latout le plus fort sinon la carte la plus forte de mla couleur demander

		return indice;
		 
	 }


	/**
	
	public void donne4jouers()
=======

	/*
	public static Contrat annonces() // ? faut-il renvoyer un Contrat ou void et setter des attributs (Contrat, preneur…)
									 // (ou encore rajouter une classe Prise, contenant Contrat, preneur…)
>>>>>>> .r43
	{
		//while(!annoncesFinies()) // si tout le monde a passé, ou 
		int ordreJoueur = 0; // 0 pour le premier à parler
						 	 // on peut rotater le tableau joueurs de sorte que
							 // le premier élément soit le premier à parler
		Contrat annonceTempJoueur;
		boolean annonceTempValide = false;
		Contrat annonceMaximale = Contrat.PASSE;
		
		while(true) // boucle sur les joueurs
		{
			while(!annonceTempValide)
			{
				annonceTempJoueur = Partie.getJoueurs()[ordreJoueur].demanderAnnonce();
				
				if(annonceTempJoueur.getPoids() == 0 || annonceTempJoueur.getPoids()>annonceMaximale.getPoids())
				{
					System.out.println("Annonce "+annonceTempJoueur+" valide ");
					annonceTempValide = true;
				}
				else
				{
					System.out.println("Annonce "+annonceTempJoueur+" invalide, veuillez réessayer.");
				}
			}
			// l’annonce est valide
			// …
		}
		
	}*/
	/**/
	public void jeuDeLaCarte()
	{
		// TODO
	}
	
	public static void main(String[] args)
	{
		
	}
	public static Contrat getContratEnCours() {
		return contratEnCours;
	}
	public static void setContratEnCours(Contrat contratEnCours) {
		Donne.contratEnCours = contratEnCours;
	}
	
	public static Joueur getPreneur() {
		return preneur;
	}
	public static void setPreneur(Joueur preneur) {
		Donne.preneur = preneur;
	}
	
	public static Carte[] getPlisPrecedent() {
		return plisPrecedent;
	}
	public static void setPlisPrecedent(Carte plisPrecedent[]) {
		Donne.plisPrecedent = plisPrecedent;
	}
	
	public static Carte[] getPlisEnCours() {
		return plisEnCours;
	}
	public static void setPlisEnCours(Carte plisEnCours[]) {
		Donne.plisEnCours = plisEnCours;
	}

}
