package tp;

import java.util.Stack;

public class Bellmann {
	
	private Graphe g;
	private int source;
	private int[] D;
	private int[] R;
	
	public Bellmann(int depart,Graphe gr){
		g=gr;
		D= new int[g.nbSommet()];
		R= new int[g.nbSommet()];
		source=depart;
		initDistMin();
		int k=1;
		boolean stable = false;
		while ((!stable)&&(k<=g.nbSommet())){
			k=k+1;
			stable=true;
			int[] D_anc = D;
			for(int i=0;i<g.nbSommet();i++){
				for(int j=0;j<g.nbSommet();j++){
					if(g.existeArc(j, i)){
						int cout=D_anc[j]+g.getValArc(j,i);
						if (cout<D[i]){
							D[i]=cout;
							R[i]=j;
							stable=false;
						}
					}
				}
			}
		}
	}
	
	public void initDistMin(){
		for(int i=0;i<g.nbSommet();i++){
			D[i]=distanceDsGraphe(source,i);
			R[i]=source;
		}
	}
	
	
	public int distanceDsGraphe(int i,int j){
		if(g.existeArc(i,j)){
			return g.getValArc(i,j);
		}else{
			return 1000;
		}
	}
	
	
	public String afficheChemin(int sommet){				
		int x = 0;								
    	String str = sommet + "]";			
		sommet = R[sommet];
		while(sommet != source || g.nbSommet() >= x){
			if(sommet != source){
			str = sommet + "," + str;	
			sommet = R[sommet];}			
			x++;								
		}
		if (g.nbSommet() <= x){						
			str = "[" + source + "," + str ;
			str= "Le chemin est: " + str;
			return str;
		}
		else{									
			System.out.println("Chemin impossible");	
			return null;
		}
	}
	
	public int longueurChemin(int sommet){
		return D[sommet];
	}
	
	public void Voir(){
		int[] a = R;
		String ch="R"+" :[";
		if (a.length==0){System.out.println(ch+"]");}
		for (int j=0; j< a.length-1;j++){
			ch=ch+a[j]+",";
		}
		ch=ch+a[a.length-1]+"]";
		System.out.println(ch);
		a = D;
		ch="D"+" :[";
		if (a.length==0){System.out.println(ch+"]");}
		for (int j=0; j< a.length-1;j++){
			ch=ch+a[j]+",";
		}
		ch=ch+a[a.length-1]+"]";
		System.out.println(ch);
	}
	
	public String toString(){
		String str= "";
		for (int i=0; i<D.length; i++){
			if(D[i]!=1000){
			str= str+"Du sommet ("+source+") au sommet ("+(i)+"):["+"<Temps: "+D[i]+">"+" meilleur sommet précédent " + R[i];
			str= str+"]\n";}
		}
		return str;
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
		Bellmann beaulieuAutresStations = new Bellmann(0,go);
		beaulieuAutresStations.Voir();
		System.out.println(beaulieuAutresStations.toString());
		int duree= beaulieuAutresStations.longueurChemin(3);
		System.out.println("Le temps mini pour aller de Beaulieu à Clemenceau est : "+duree);
		System.out.println(beaulieuAutresStations.afficheChemin(3));

	}

}
