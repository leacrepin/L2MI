package tp;

import java.util.Stack;

public class Prim {
	
	private Graphe arbreCouvrant;
	private Arete[] lstAreteTriee;
	private int poids;
	private int source;
	
	
	public class Arete{//La classe Arete
		private int depart;
		private int arrivé;
		private int val;
		
		public Arete(int i,int j,int k){
			depart=i;
			arrivé=j;
			val=k;
		}
	}

	public Prim(int [][] mat){
		Graphe g = new Graphe(mat);
		System.out.println("Arbre: \n"+g+"\n");
		arbreCouvrant=new Graphe(g.nbSommet());
		lstAreteTriee= new Arete[1000];
		int a=0;
		int taille=0;//nombre d'aretes
		for(int i=0; i<g.nbSommet();i++){//on remplie le tableau des aretes
			for(int j=0; j<g.nbSommet(); j++){
				if(mat[i][j]!=Graphe.Alpha_NotDef){
					lstAreteTriee[a] = new Arete(i, j, mat[i][j]);
					taille++;
					a++;
				}
			}
		}
		triLstArete(taille);//on trie
		//Algorithme de PRIM du cours
		arbreCouvrant.ajoutSommet(source);
		while (arbreCouvrant.nbSommet() <mat.length ){	
			Arete x=AreteSuivante();
			if(arbreCouvrant.existeSommet(x.depart)){
				arbreCouvrant.ajoutSommet(x.arrivé);
			}
			else{
				arbreCouvrant.ajoutSommet(x.depart);
			}
			if(!arbreCouvrant.existeArc(x.depart, x.arrivé) && !arbreCouvrant.existeArc(x.arrivé, x.depart)){
				arbreCouvrant.ajoutArc(x.depart, x.arrivé, x.val);
				arbreCouvrant.ajoutArc(x.arrivé, x.depart, x.val);
				poids =poids+ x.val;
			}
		}
	}
	
	private void triLstArete(int taille){
		int début;
		int plus_petite_val;
		Arete tamp;
		for(int i=0; i<taille; i++){
			plus_petite_val=lstAreteTriee[i].val;
			début=i;
			for(int j=i+1; j<taille; j++){
				if(lstAreteTriee[j].val<plus_petite_val){
					début=j;
					plus_petite_val=lstAreteTriee[j].val;
				}
			}
			tamp = lstAreteTriee[i];
			lstAreteTriee[i] = lstAreteTriee[début];
			lstAreteTriee[début] = tamp;
		}
	}
	
	private Arete AreteSuivante(){	
		Arete x = null;
		for(int i = 0; i < lstAreteTriee.length; i++){
			if(arbreCouvrant.existeSommet(lstAreteTriee[i].depart)&& !arbreCouvrant.existeSommet(lstAreteTriee[i].arrivé) || arbreCouvrant.existeSommet(lstAreteTriee[i].arrivé)&& !arbreCouvrant.existeSommet(lstAreteTriee[i].depart)){
				return lstAreteTriee[i];
			}
		}
		return x;
	}
	
	
	public void voir(){
		System.out.println("ArbreCouvrant: \n"+arbreCouvrant+"\n");
		System.out.println("Poids: "+poids);
	}
	
	public static void main(String[] args) {
		int N =Graphe.Alpha_NotDef;
		
		int[][] matcout=	{										
				/* 0      0   1   2   3   4   5   6   7   8   9  10*/	
				/* 0 */ {N, N, N, N, N, N, N, N, N, N, N},
				/* 1 */ {40, N, N, N, N, N, N, N, N, N, N},
				/* 2 */ {19, 55, N, N, N, N, N, N, N, N, N},
				/* 3 */ {20, 21, 35, N, N, N, N, N, N, N, N},
				/* 4 */ {85, 90, 55, 70, N, N, N, N, N, N, N},
				/* 5 */ {28, 35, 25, 15, 54, N, N, N, N, N, N},
				/* 6 */ {70, 90, 45, 75, 18, 60, N, N, N, N, N},
				/* 7 */ {45, 125,25, 80, 35, 32, 24,  N, N, N, N},
				/* 8 */ {50, 23, 50, 22, 75, 21, 80, 65, N, N, N},
				/* 9 */ {62, 90, 45, 92, 26, 49, 16, 17, 72, N, N},
				/* 10*/ {85, 55, 75, 65, 26, 52, 27, 35, 51, 35, N},	 
								};
		Prim CoutLigneTelephonique = new Prim(matcout);
		CoutLigneTelephonique.voir();
	}


}
