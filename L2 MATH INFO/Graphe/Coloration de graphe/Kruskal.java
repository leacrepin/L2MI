package tp;

import java.util.Stack;
import java.util.Vector;

import tp.Prim.Arete;

public class Kruskal {

		private Graphe arbreCouvrant;
		private Arete[] lstAreteTriee;
		private int poids;
		private int source;
		private int taille;//nombre d'aretes
		private int suiv;
		
		
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

		public Kruskal(int [][] mat){
			Graphe g = new Graphe(mat);
			arbreCouvrant=new Graphe(g.nbSommet());
			lstAreteTriee= new Arete[1000];
			int a=0;
			taille=0;//nombre d'aretes
			for(int i=0; i<g.nbSommet();i++){//on remplie le tableau des aretes
				for(int j=0; j<g.nbSommet(); j++){
					if(mat[i][j]!=Graphe.Alpha_NotDef){
						lstAreteTriee[a] = new Arete(i, j, mat[i][j]);
						taille++;
						a++;
					}
				}
			}
			triLstArete();//on trie
			//Algorithme de Kruskal du cours
			int k=0;
			Vector<Stack<Integer>> C = new Vector<Stack<Integer>>();
			for(int i=0;i<g.nbSommet();i++){
				C.add(new Stack<Integer>());
				C.get(i).push(i);
				arbreCouvrant.ajoutSommet(i);
			}
			Arete x=AreteSuivante();
			while(k<taille-1 && x!=null){
				if(!(C.get(x.depart)).contains(x.arrivé)){
					arbreCouvrant.ajoutArc(x.depart, x.arrivé, x.val);
					Stack<Integer> B=C.get(x.arrivé);
					while(!B.isEmpty()){
						int b=B.pop();
						if(!(C.get(x.depart)).contains(b)){
							(C.get(x.depart)).push(b);
						}
					}
					for(int i=0;i<g.nbSommet();i++){
						if((C.get(x.depart)).contains(i)){
							Stack<Integer> D=C.get(x.depart);
							while(!D.isEmpty()){
								int b=D.pop();
								if(!(C.get(i)).contains(b)){
									(C.get(i)).push(b);
								}
							}
						}
					}
					k++;
				}
				x=AreteSuivante();
			}
			
			
		}
		
		private void triLstArete(){
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
			if(suiv<taille){
				int i=suiv;
				suiv++;
				return lstAreteTriee[i];
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
			Kruskal CoutLigneTelephonique = new Kruskal(matcout);
			CoutLigneTelephonique.voir();
		}

	}
