package tp;

import java.util.Stack;

public class Warshall {
	private Graphe g;
	private Graphe gbis;
	private int[][] R;
	
	public Warshall(Graphe gr){
		g=gr;
		Graphe g2 = g;
		R= new int[g.nbSommet()][g.nbSommet()];
		initRoutage();
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
									R[y][z]=R[x][z];
								}
							}else{
									g2.ajoutArc(y, z, v1+v2);
									R[y][z]=R[x][z];
								}
						}
					}
				}
			}
		}
		gbis=g2;
	}
	
	public void initRoutage(){
		for(int x=0;x<g.nbSommet();x++){
			for(int y=0;y<g.nbSommet();y++){
				R[x][y]=distanceDsGraphe(x,y);
			}
		}
	}
	
	
	public int distanceDsGraphe(int i,int j){
		if(g.existeArc(i,j)){
			return i;
		}else{
			return 1000;
		}
	}
	
	public void Voir(){
		int[][] a = R;
		String ch="R"+" :[";
		for (int j=0; j< a.length-1;j++){
			ch=ch+"[";
			for (int i=0; i< a[j].length-1;i++){
				ch=ch+a[j][i]+",";
			}
			ch=ch+"]";
		}
		System.out.println(ch+"]");
		System.out.println(gbis);
	}
	
	public int longueurChemin(int depart,int arrivé){
		return gbis.getValArc(depart, arrivé);
	}
	
	public String afficheChemin(int depart,int arrivée){				
		int x = 0;								
    	String str = arrivée + "]";
		int sommet = R[depart][arrivée];
		while(g.nbSommet() >= x){
			if(sommet != depart){
			str = sommet + "," + str;
			sommet = R[depart][sommet];}			
			x++;								
		}
		if (g.nbSommet() <= x){						
			str = "[" + depart + "," + str ;
			str= "Le chemin est: " + str;
			return str;
		}
		else{									
			System.out.println("Chemin impossible");	
			return null;
		}
	}

	public static void main(String[] args) {
		int N =Graphe.Alpha_NotDef;
		
		int [][] matDuree = {{N,4,N,11,N,N,3,N,N,10,N},
							 {N,N,N,N,N,N,N,N,N,N,N},
							 {N,N,N,N,N,N,N,N,N,N,N},
							 {N,N,N,N,N,N,N,N,N,2,N},
							 {N,N,N,2,N,N,N,2,5,N,N},
							 {N,N,N,N,N,N,N,N,N,N,N},
							 {N,N,N,N,5,N,N,N,N,N,5},
							 {N,N,N,N,2,N,N,N,N,N,6},
							 {N,N,N,N,N,6,N,N,N,N,N},
							 {N,N,4,N,N,N,N,N,5,N,N},
							 {N,N,N,N,N,N,N,7,N,N,N}};
		Graphe go =new Graphe(matDuree);
		Warshall beaulieuAutresStations = new Warshall(go);
		beaulieuAutresStations.Voir();
		System.out.println(beaulieuAutresStations.afficheChemin(0,3));
		int duree= beaulieuAutresStations.longueurChemin(0,3);
		System.out.println("Le temps mini pour aller de Beaulieu à Clemenceau est : "+duree);
	}

}
