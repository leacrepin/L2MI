package tp;

import java.util.LinkedList;
import java.util.Stack;

public class Marimont {
	private Graphe g;
	private Graphe gbis;
	private int compteur;
	int[] num ;
	LinkedList<Stack<Integer>> tableauassociation;
		
		public Marimont(Graphe greduit, LinkedList<Stack<Integer>> tableauassoreduit){
			g=greduit;//Graphe reduit
			gbis =new Graphe(g);
			tableauassociation=tableauassoreduit;//tableau association par rapport à l'ancien graphe
		}
		
		public Boolean MarimontAlgo(){
			gbis =new Graphe(g);
			boolean circuit=true;
			int fini=0;
			while(fini!=1){
				if(GrapheVide(gbis)==true){
					fini=1;
					circuit=false;
				}else{
					if(!OtePointsSorties() && !OtePointsEntrees()){
						fini=1;
					}
				}
			}
			gbis = new Graphe(g);
			return circuit;// false si le graphe est convexe
		}
		
		public String AfficheMarimont(){
			Boolean circuit = MarimontAlgo();
			String str ="";
	    	str= str+"g: \n"+g;
	    	str= str+"\n";
	    	if(circuit==false){
	    		str= str+"Le graphe n'a pas de circuit";
	    	}else{
	    		str= str+"Le graphe a un/des circuit(s)";
	    	}
	    	return str;
		}
		
		public Boolean GrapheVide(Graphe g){
			if(g.nbSommet()!=0){
				return false;
			}
			return true;
		}
		
		public Boolean OtePointsSorties(){
			int a=0;
			for(int x=0;x<gbis.nbSommet();x++){
				a=0;
				for(int y=0;y<gbis.nbSommet();y++){
					if(gbis.existeArc(x, y)){
						a++;
					}
				}
				if(a==0){
					gbis.supprimeSommet(x);
					if(gbis.nbSommet()>2){
						gbis=new Graphe(Decalage(gbis));
					}
					return true;
				}
			}
			return false;
		}
		
		public int PointsEntrees(Stack<Integer> E){
			int a=0;
			for(int x=0;x<g.nbSommet();x++){
				a=0;
				if(!gbis.existeSommet(x)){
					a++;
				}
				for(int y=0;y<g.nbSommet();y++){
					if(gbis.existeArc(y, x)){
						a++;
					}
				}
				if(a==0){
					return x;
				}
			}
			return -1;
		}
		
		public Boolean OtePointsEntrees(){
			int a=0;
			for(int x=0;x<gbis.nbSommet();x++){
				a=0;
				for(int y=0;y<gbis.nbSommet();y++){
					if(gbis.existeArc(y, x)){
						a++;
					}
				}
				if(a==0){
					gbis.supprimeSommet(x);
					if(gbis.nbSommet()>2){
						gbis=new Graphe(Decalage(gbis));
					}
					return true;
				}
			}
			return false;
		}
		
		public Graphe Decalage(Graphe g3){//Permet de mettre à jour les sommets pour ne pas avoir de trous
			Graphe g4=new Graphe(g.nbSommet());
			int[] equiv2=new int[g.nbSommet()];
			int a=0;
			for(int x=0;x<g.nbSommet();x++){
				if(g3.existeSommet(x)){
					equiv2[x]=a;
					a++;
				}
	    	}
			for(int x=0;x<g.nbSommet();x++){
	    		for(int y=0;y<g.nbSommet();y++){
	    			if(g3.existeArc(x, y)){
	    				g4.ajoutSommet(equiv2[x]);
	    				g4.ajoutSommet(equiv2[y]);
	    				g4.ajoutArc(equiv2[x], equiv2[y], 1);
	    			}
	    		}
	    	}
			return g4;
		}
		
		public Boolean NumeroConforme(){
			gbis = new Graphe(g);
			Stack<Integer> E = new Stack<Integer>();
			Boolean h_vide = GrapheVide(gbis);
			num = new int[gbis.nbSommet()];
			compteur = 1;
			for(int i=0; i<gbis.nbSommet();i++){
				num[i]=0;
			}
			if(MarimontAlgo()==false){
				if(!h_vide){
					if(PointsEntrees(E)!=-1){
						E.push(PointsEntrees(E));
					}
				}
				Numerote(E);
				while(!h_vide && !E.isEmpty()){
					int[] liste_sommet = new int[g.nbSommet()];
					int a=0;
					for(int i=0; i<g.nbSommet();i++){
						if(E.contains(i)){
							liste_sommet[a]=i;
							a++;
						}
					}
					gbis.sousgraphe(liste_sommet);
					h_vide=GrapheVide(gbis);
					if(!h_vide){
						if(PointsEntrees(E)!=-1){
							E.push(PointsEntrees(E));
						}else{
							h_vide=true;
							for(int i=0; i<g.nbSommet();i++){
								if(gbis.existeSommet(i)){
									E.push(i);
								}
							}
						}
					}
					Numerote(E);
				}
				return true;
			}else{
				return false;
			}
		}
		
		
		
		public void Numerote(Stack<Integer> E){
			for(int i=0; i<g.nbSommet();i++){
				if(E.contains(i) && num[i]==0){
					num[i]=compteur;
					compteur++;
				}
			}
		}
		
		
		public String AfficheNumerotation(){
			String str ="";
	    	int b=0;
	    	Stack<Integer> A = new Stack<Integer>();
	    	for(int x=0;x<g.nbSommet();x++){
	    		if(!A.contains(x) && tableauassociation.get(x)!=null){
	    			b++;
	    			for(int w=0;w<g.nbSommet();w++){
						if(tableauassociation.get(x).contains(w) && !A.contains(w)){
							A.push(w);
						}
					}
	    		}
	    	}
	    	str= str+"Nombre de composantes fortement connexes: "+b+".";
	    	str= str+"\n";
	    	str= str+"Composantes fortement connexes et numérotation conforme associée : \n";
	    	A = new Stack<Integer>();
	    	for(int x=0;x<g.nbSommet();x++){
	    		if(!A.contains(x) && tableauassociation.get(x)!=null){
	    			str= str+x+": "+tableauassociation.get(x)+" -> "+num[x]+"\n";
	    			for(int w=0;w<g.nbSommet();w++){
						if(tableauassociation.get(x).contains(w) && !A.contains(w)){
							A.push(w);
						}
					}
	    		}
	    	}
	    	return str;
		}
		
		
		public static void main(String[] args) {
			int N =Graphe.Alpha_NotDef;
			
			int [][] matDuree = {{N,N,1,N,1,1},
								{N,N,N,N,N,N},
								{N,N,N,1,N,N},
								{N,N,N,N,N,1},
								{N,1,1,N,N,1},
								{N,1,1,N,N,N}};
			Foulkes A = new Foulkes(matDuree);
			System.out.println(A.afficheCompo());
			Graphe B = A.GrapheReduit();
			Marimont C = new Marimont(B,A.GetTableau());
			System.out.println(C.AfficheMarimont());
			System.out.println(C.NumeroConforme());
			System.out.println(C.AfficheNumerotation());
			
		}
}
