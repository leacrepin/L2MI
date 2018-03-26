package tp;

import java.util.LinkedList;
import java.util.Stack;


public class Foulkes {
	
	//Warshall
	public class Warshall {
		private Graphe g;
		private Graphe gbis;
		
		public Warshall(Graphe gr){
			g=gr;
			Graphe g2 = g;
			for(int x=0;x<g.nbSommet();x++){
				for(int y=0;y<g.nbSommet();y++){
					if(g.existeArc(y, x)){
						int v1 = g.getValArc(y,x);
						for(int z=0;z<g.nbSommet();z++){
							if(g2.existeArc(x, z)){
								int v2=g2.getValArc(x,z);
								if(g2.existeArc(y, z)){
									int w=g2.getValArc(y,z);
									if(v1+v2<w){
										g2.supprimeArc(y, z);
										g2.ajoutArc(y, z, v1+v2);
									}
								}else{
										g2.ajoutArc(y, z, v1+v2);
									}
							}
						}
					}
				}
			}
			gbis=g2;
		}
	}
	
	//FOULKES
	LinkedList<Stack<Integer>> C;
	Stack<Integer> SommetsTraités;
	private Graphe g;
	private Graphe g2;
	LinkedList<Stack<Integer>> tableauassociation;
	
	public Foulkes(int [][] mat){
		C = new LinkedList<Stack<Integer>>();
		g=new Graphe(mat);
		SommetsTraités = new Stack<Integer>();
		Warshall warshallgrapheg= new Warshall(g);
		g=new Graphe(mat);
		g2=warshallgrapheg.gbis;
		for(int x=0;x<g2.nbSommet();x++){
			C.add(new Stack<Integer>());
		}
		for(int x=0;x<g2.nbSommet();x++){
			if(!SommetsTraités.contains(x)){
				C.get(x).push(x);
				if(g2.existeArc(x,x)){
					for(int y=x+1;y<g2.nbSommet();y++){
						if(g2.existeArc(x, y) && g2.existeArc(y, x)){
							if(!C.get(x).contains(y) && x!=y){
								C.get(x).push(y);
							}
						}
					}
					for(int y=0;y<g2.nbSommet();y++){
						if(C.get(x).contains(y) && x!=y){
							C.remove(y);
							C.add(y, new Stack<Integer>());
							for(int w=0;w<g2.nbSommet();w++){
								if(C.get(x).contains(w) && !C.get(y).contains(w)){
									C.get(y).push(w);
								}
							}
						}
					}
				}
				for(int y=0;y<g2.nbSommet();y++){
					if(C.get(x).contains(y) && x!=y){
						if(!SommetsTraités.contains(y)){
							SommetsTraités.push(y);
						}
					}
				}
			}
		}
		
	}
	
	public Foulkes(Graphe a){
		C = new LinkedList<Stack<Integer>>();
		g=new Graphe(a);
		SommetsTraités = new Stack<Integer>();
		Warshall warshallgrapheg= new Warshall(g);
		g=new Graphe(a);
		g2=warshallgrapheg.gbis;
		for(int x=0;x<g2.nbSommet();x++){
			C.add(new Stack<Integer>());
		}
		for(int x=0;x<g2.nbSommet();x++){
			if(!SommetsTraités.contains(x)){
				C.get(x).push(x);
				if(g2.existeArc(x,x)){
					for(int y=x+1;y<g2.nbSommet();y++){
						if(g2.existeArc(x, y) && g2.existeArc(y, x)){
							if(!C.get(x).contains(y) && x!=y){
								C.get(x).push(y);
							}
						}
					}
					for(int y=0;y<g2.nbSommet();y++){
						if(C.get(x).contains(y) && x!=y){
							C.remove(y);
							C.add(y, new Stack<Integer>());
							for(int w=0;w<g2.nbSommet();w++){
								if(C.get(x).contains(w) && !C.get(y).contains(w)){
									C.get(y).push(w);
								}
							}
						}
					}
				}
				for(int y=0;y<g2.nbSommet();y++){
					if(C.get(x).contains(y) && x!=y){
						if(!SommetsTraités.contains(y)){
							SommetsTraités.push(y);
						}
					}
				}
			}
		}
		
	}
	
	public String afficheCompo(){											
    	String str ="";
    	int b=0;
    	Stack<Integer> A = new Stack<Integer>();
    	for(int x=0;x<g.nbSommet();x++){
    		if(!A.contains(x) && C.get(x)!=null){
    			b++;
    			for(int w=0;w<g.nbSommet();w++){
					if(C.get(x).contains(w) && !A.contains(w)){
						A.push(w);
					}
				}
    		}
    	}
    	str= str+"Nombre de composantes fortement connexes: "+b+".";
    	str= str+"\n";
    	str= str+"Composantes fortement connexes: ";
    	A = new Stack<Integer>();
    	for(int x=0;x<g.nbSommet();x++){
    		if(!A.contains(x) && C.get(x)!=null){
    			str= str+C.get(x);
    			b--;
    			if(b>0){
    				str=str+" , ";
    			}else{
    				str=str+".";
    			}
    			for(int w=0;w<g.nbSommet();w++){
					if(C.get(x).contains(w) && !A.contains(w)){
						A.push(w);
					}
				}
    		}
    	}
    	return str;
	}
	
	public Graphe GrapheReduit(){
		System.out.println("Graphe de départ: \n"+g);
		Graphe g3=new Graphe(g.nbSommet());
		int[] equiv=new int[g.nbSommet()];
		for(int x=0;x<g.nbSommet();x++){
    		equiv[x]=C.get(x).peek();
    	}
		for(int x=0;x<g.nbSommet();x++){
    		for(int y=0;y<g.nbSommet();y++){
    			if(g.existeArc(x, y)){
    				if(equiv[x]!=equiv[y]){
    					g3.ajoutSommet(equiv[x]);
    					g3.ajoutSommet(equiv[y]);
    					g3.ajoutArc(equiv[x], equiv[y], 1);
    				}
    			}
    		}
    	}
		Graphe g4=new Graphe(g.nbSommet());/*on décale les sommets pour ne pas avoir de trous 
		(si le decalage n'est pas fait il y a un risque avec les for utilisant le nombre de sommet)*/
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
		//QUESTION 4
		int[] equivfinal=new int[g.nbSommet()];
		for(int x=0;x<g.nbSommet();x++){
    		equivfinal[x]=equiv2[equiv[x]];
    	}
		tableauassociation = new LinkedList<Stack<Integer>>();
		for(int x=0;x<g.nbSommet();x++){
			tableauassociation.add(new Stack<Integer>());
		}
		for(int x=0;x<g.nbSommet();x++){
			for(int y=0;y<g.nbSommet();y++){
				if(x==equivfinal[y]){
					for(int w=0;w<g.nbSommet();w++){
						if(C.get(y).contains(w) && !tableauassociation.get(x).contains(w)){
							tableauassociation.get(x).push(w);
						}
					}
				}
	    	}
    	}
		System.out.println("Graphe réduit: \n"+g4);
		return g4;
	}
	
	public LinkedList<Stack<Integer>> GetTableau(){//Permet d'avoir le tableau d'association
		return tableauassociation;
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
		Foulkes B = new Foulkes(A.GrapheReduit());
		System.out.println(B.afficheCompo());
	}
}
