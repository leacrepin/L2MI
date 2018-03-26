package tp;

import java.util.Stack;

public class ColorationGlouton {
	
	private int[] couleur;
	private int[] SommetTriee;
	private int[] degresommet;
	private Graphe g;
	
	public ColorationGlouton(int [][] mat){
		couleur=new int[1000];
		SommetTriee=new int[1000];
		degresommet=new int[1000];
		g=new Graphe(mat);
		ListeDegree();
		TrieSommet();
		AttribueCouleur();
	}
	
	public void ListeDegree(){
		for(int x=0;x<g.nbSommet();x++){
			if(g.existeSommet(x)){
				degresommet[x]=g.degre(x);
			}
		}
	}
	
	public void TrieSommet(){
		int tampx = g.nbSommet()+1; int tamp = 0;
		int a=0;
		Stack<Integer> A=new Stack<Integer>();
		while (A.size()!=g.nbSommet()){
			tampx = g.nbSommet()+1;
			tamp = 0;
			for(int x=0;x<g.nbSommet();x++){
				if(g.existeSommet(x)){
					if(tamp<g.degre(x) && !A.contains(x)){
						tamp=g.degre(x);
						tampx=x;
					}
				}
			}
			A.push(tampx);
			SommetTriee[a]=tampx;
			a++;
		}
		System.out.print("Sommets triés: "+A+"\n");
	}
	
	public void AttribueCouleur(){
		Boolean encore = true;
		for(int x=0;x<couleur.length;x++){
			couleur[x]=0;
		}
		while(encore==true){//tant qu'on a modifié on recommence encore
			encore=false;
			for(int x=0;x<g.nbSommet();x++){
				for(int y=0;y<g.nbSommet();y++){
					if(g.existeArc(SommetTriee[x], SommetTriee[y]) || g.existeArc(SommetTriee[y], SommetTriee[x])){
						if(couleur[SommetTriee[x]]==couleur[SommetTriee[y]]){
							couleur[SommetTriee[y]]++;
							encore=true;
						}
					}
				}
			}
		}
	}
	
	public String afficheColo(){											
    	String str ="";
    	int a=0;
    	int b=0;
    	for(int x=0;x<g.nbSommet();x++){
    		str= str+"Sommet ("+x+") avec la couleur ("+(couleur[x])+")";
    		str= str+"\n";
    	}
    	for(int x=0;x<g.nbSommet();x++){
    		if(couleur[x]>a){
    			a=couleur[x];
    		}
    	}
    	a++;
    	str= str+"Il y a "+a+" couleur(s)";
		str= str+"\n";
		while(b!=a){
			str= str+"La couleur ("+b+") possède comme sommet(s): ";
			for(int x=0;x<g.nbSommet();x++){
				if(couleur[x]==b){
					str= str+"("+x+")";
				}
			}
			str= str+"\n";
			b++;
		}
    	
    	return str;
	}
	
	public static void main(String[] args) {
		int N =Graphe.Alpha_NotDef;
		
		int [][] matDuree = {{N,23,6,15},
							{23,N,10,N},
							{6,10,N,N},
							{15,N,N,N}};
		ColorationGlouton A = new ColorationGlouton(matDuree);
		System.out.println(A.afficheColo());
		
		int[][] matCoul = {
				/*      N Pi Mn Ch Br Bn  F Lo Al Pl Ce Bo  C Pc Li Au Ra Aq Mp Lr Pa Co*/
				/*N */ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Pi*/ { 1,-1, 1, 1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Mn*/ {-1, 1,-1,-1,-1, 1, 1,-1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Ch*/ {-1, 1,-1,-1,-1,-1, 1, 1,-1,-1,-1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Br*/ {-1,-1,-1,-1,-1, 1,-1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Bn*/ {-1,-1, 1,-1, 1,-1,-1,-1,-1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*F */ {-1, 1, 1, 1,-1,-1,-1,-1,-1,-1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Lo*/ {-1,-1,-1, 1,-1,-1,-1,-1, 1,-1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Al*/ {-1,-1,-1,-1,-1,-1,-1, 1,-1,-1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Pl*/ {-1,-1,-1,-1, 1, 1,-1,-1,-1,-1, 1,-1,-1, 1,-1,-1,-1,-1,-1,-1,-1,-1},
				/*Ce*/ {-1,-1, 1,-1,-1, 1, 1,-1,-1,-1,-1, 1,-1, 1, 1, 1,-1,-1,-1,-1,-1,-1},
				/*Bo*/ {-1,-1,-1, 1,-1,-1, 1,-1,-1,-1, 1,-1, 1,-1,-1, 1, 1,-1,-1,-1,-1,-1},
				/*C */ {-1,-1,-1, 1,-1,-1,-1, 1, 1,-1,-1, 1,-1,-1,-1,-1, 1,-1,-1,-1,-1,-1},
				/*Pc*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1,-1,-1,-1, 1,-1,-1, 1,-1,-1,-1,-1},
				/*Li*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1,-1, 1,-1, 1,-1,-1, 1,-1,-1,-1},
				/*Au*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1,-1,-1, 1,-1, 1,-1,-1, 1,-1,-1},
				/*Ra*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1,-1,-1, 1,-1,-1,-1, 1, 1,-1},
				/*Aq*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1,-1,-1,-1, 1,-1,-1,-1},
				/*Mp*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1, 1,-1, 1,-1,-1},
				/*Lr*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1,-1, 1,-1, 1,-1},
				/*Pa*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1,-1,-1, 1,-1,-1},
				/*Co*/ {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
					};
		for(int x=0;x<matCoul.length;x++){
			for(int y=0;y<matCoul.length;y++){
				if(matCoul[x][y]==-1){
					matCoul[x][y]=Graphe.Alpha_NotDef;
				}
			}
		}

		ColorationGlouton test = new ColorationGlouton(matCoul);
		System.out.println(test.afficheColo());
		
	}
	
	
}
