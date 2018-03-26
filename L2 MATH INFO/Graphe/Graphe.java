package tp;

public class Graphe {
	private int[][] U;
	private boolean[] valid;
	public static final int Alpha_NotDef=-10000;
	
	public Graphe(){
		U=new int[1000][1000];
		valid= new boolean[1000];
		for (int i=0; i<1000;i++){
			valid[i]=false;
			for (int j=0; j<1000;j++){
				U[i][j]=Alpha_NotDef;
			}
		}
	}
	
	public Graphe(int[][] mat){
		int taille=mat.length;
		int nmax=2*taille+1000;
		U=new int[nmax][nmax];
		valid= new boolean[nmax];
		for (int i=0; i<nmax;i++){
			if (i<taille){
				valid[i]=true;
			}else{
			valid[i]=false;}
			for (int j=0; j<nmax;j++){
				if (i<taille && j<taille){
					U[i][j]=mat[i][j];
				}else{
					U[i][j]=Alpha_NotDef;
				}
			}
		}
	}
	
	public Graphe(int nbs){
		U=new int[nbs][nbs];
		valid= new boolean[nbs];
		for (int i=0; i<nbs;i++){
			valid[i]=false;
			for (int j=0; j<nbs;j++){
				U[i][j]=Alpha_NotDef;
			}
		}
	}
	
	public Graphe(Graphe g){
		U=new int[g.nbSommet()][g.nbSommet()];
		valid= new boolean[g.nbSommet()];
		for (int i=0; i<g.nbSommet();i++){
			valid[i]=true;
			for (int j=0; j<g.nbSommet();j++){
				U[i][j]=g.getValArc(i,j);
			}
		}
	}
	
	public boolean existeSommet(int i){
		if (valid[i]==true && i<U.length){
			return true;
		}else{
			return false;
		}
	}
	
	public int nbSommet(){
		int a=0;
		for (int i=0;i<valid.length;i++){
			if(valid[i]==true){
				a++;
			}
		}
		return (a);
	}
	
	public boolean existeArc(int i,int j){
		if(!existeSommet(j) |!existeSommet(i) | U[i][j]==Alpha_NotDef){
			return false;
		}else{
			return true;
		}
	}
	
	public int getValArc(int i,int j){
		return(U[i][j]);
	}
	
	public boolean ajoutSommet(int i){
		if(existeSommet(i)){return false;}
		else{
			valid[i]=true;
			return true;
		}
	}
	
	public boolean supprimeSommet(int i){
		if(!existeSommet(i)){return false;}
		else{
			valid[i]=false;
			return true;
		}
	}
	
	public boolean ajoutArc(int i, int j, int val){
		if (existeArc(i,j)){return false;}
		else{
			U[i][j]=val;
			return true;
		}
	}
	
	public boolean supprimeArc(int i,int j){
		if (!existeArc(i,j)){return false;}
		else{
			U[i][j]=Alpha_NotDef;
			return true;
		}
	}
	
	public int degreSortant(int i){
		int a=0;
		for (int j=0;j<U.length;j++){
			if(existeArc(i, j) ){
				a++;
			}
		}
		return (a);
	}
	
	public int degreEntrant(int i){
		int a=0;
		for (int j=0;j<U.length;j++){
			if(existeArc(j, i)){
				a++;
			}
		}
		return (a);
	}
	
	public int degre(int i){
		return(degreSortant(i)+degreEntrant(i));
	}
	
	public int[] liste_succ(int i){
		int[] a = new int[degreSortant(i)];
		int b=0;
		for (int j=0;j<U.length;j++){
			if(existeArc(i, j) ){
				a[b]=j;
				b++;
			}
		}
		return a;
	}
	
	public String ToString_liste_succ(int i){
		String ch="[";
		int[] a = liste_succ(i);
		if (a.length==0){return (ch+"]");}
		for (int j=0; j< a.length-1;j++){
			ch=ch+a[j]+",";
		}
		ch=ch+a[a.length-1]+"]";
		return ch;
	}
	
	public int[] liste_pred(int i){
		int[] a = new int[degreEntrant(i)];
		int b=0;
		for (int j=0;j<U.length;j++){
			if(existeArc(j, i) ){
				a[b]=j;
				b++;
			}
		}
		return a;
	}
	
	public String ToString_liste_pred(int i){
		String ch="[";
		int[] a = liste_pred(i);
		if (a.length==0){return (ch+"]");}
		for (int j=0; j< a.length-1;j++){
			ch=ch+a[j]+",";
		}
		ch=ch+a[a.length-1]+"]";
		return ch;
	}
	
	public boolean estReflexif(){
		int a=0;
		for (int i=0;i<valid.length;i++ ){
			if( valid[i]=true ){
				if(U[i][i]!=Alpha_NotDef){
					a++;
				}
			}
		}
		if(a==nbSommet()){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean estAntiReflexif(){
		int a=0;
		for (int i=0;i<valid.length;i++ ){
			if( valid[i]=true ){
				if(U[i][i]!=Alpha_NotDef){
					a++;
				}
			}
		}
		if(a==0){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean estSymetrique(){
		int taille = U.length;
		for (int i=0;i<taille;i++ ){
			for (int j=0;j<taille;j++ ){
				if(existeArc(i,j)){
					if(!existeArc(j,i)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public boolean estAntiSymetrique(){
		int taille = U.length;
		for (int i=0;i<taille;i++ ){
			for (int j=0;j<taille;j++ ){
				if(existeArc(i,j)){
					if(existeArc(j,i)&& i!=j){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void Transposition(){
		int taille = U.length;
		int[][] R = new int[taille][taille];
		for (int i=0;i<taille;i++ ){
			for (int j=0;j<taille;j++ ){
				R[i][j]=U[j][i];
			}
		}
		U=R;
	}
	
	public boolean estTransitif(){
		int taille = U.length;
		for (int i=0;i<taille;i++ ){
			for (int j=0;j<taille;j++ ){
				for (int k=0;k<taille;k++ ){
					if(existeArc(i,j) && i!=j && j!=k){
						if(existeArc(j,k) && !existeArc(i,k)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean estAntiTransitif(){
		int taille = U.length;
		for (int i=0;i<taille;i++ ){
			for (int j=0;j<taille;j++ ){
				for (int k=0;k<taille;k++ ){
					if(existeArc(i,j) && i!=j && j!=k){
						if(existeArc(j,k) && existeArc(i,k)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	public void union(Graphe g){
		if (g.nbSommet()!= nbSommet()){
			System.out.println("erreur sommet");
		}
		for (int i=0; i<U.length;i++){
			if((existeSommet(i) && !g.existeSommet(i)) || (!existeSommet(i) && g.existeSommet(i))){
				System.out.println("erreur sommet");
				System.exit(-1);
			}
		}
		for (int j=0; j<U.length;j++){
			if(existeSommet(j)){
				for (int k=0; k<U.length; k++){
					if(existeSommet(k)){
						if(g.existeArc(j,k) && !existeArc(j,k)) {
							ajoutArc(j,k,g.getValArc(j, k));
						}
						else{
							if((existeArc(j,k) && g.existeArc(j, k)) && (getValArc(j,k)!=g.getValArc(j, k))){
								System.out.println("erreur valdiff");
								System.exit(-1);
							}
						}
					}
				}
			}	
		}
	}
	
	public void sousgraphe(int [] liste_sommet){
		int taille = liste_sommet.length;
		int[] T = liste_sommet;
		for (int i = 0; i< taille; i++){
			for (int j = 0; j< U.length; j++){
				supprimeArc(T[i],j);supprimeArc(j,T[i]);
			}
			supprimeSommet(T[i]);
		}
	}
	
	public Graphe composition(Graphe g){
		Graphe gr = new Graphe();
		if (g.nbSommet()!= nbSommet()){
			System.out.println("erreur les deux graphes n'ont pas les même sommet");
		}
		int taille = U.length;
		for (int i=0;i<taille;i++ ){
			for (int j=0;j<taille;j++ ){
				for (int k=0;k<taille;k++ ){
					if (existeArc(i, k) && g.existeArc(k,j)){
						gr.ajoutSommet(i);gr.ajoutSommet(j);gr.ajoutArc(i, j, 1);
					}
				}
			}
		}
		return gr;	
	}
	
	public java.lang.String toString(){
		String ch="";
		for (int i=0;i<U.length;i++ ){
			if(existeSommet(i) ){
				ch=ch+"("+i+") : [";
				int[] succ=liste_succ(i);
				if(succ.length>0){
					for (int j=0;j<succ.length;j++ ){
						ch=ch+succ[j]+"<"+getValArc(i,succ[j])+">";
					}
				}
				
				ch=ch+"]  \n";
				
			}
		}
		if (ch==""){
		return ("[vide]");
		}else{
			return ch;
		}
	}
	
	
	
	public static void main(String[] args){
		System.out.println("Test des PRIORITEES: ");
		System.out.println("Test premier constructeur: ");
		Graphe a = new Graphe();
		System.out.println(a);
		System.out.println("BON \n");
		
		int A = Graphe.Alpha_NotDef;
		int [][] b={{A,1,2,3},{A,1,2,3},{A,1,2,3},{A,1,2,3}};
		System.out.println("Test second constructeur: ");
		Graphe B = new Graphe(b);
		System.out.println(B);
		System.out.println("BON \n");
		
		System.out.println("Test existe sommet: (bon)");
		System.out.println(B.existeSommet(4)+"\n");
		
		System.out.println("Test nbSommet: (bon)");
		System.out.println(B.nbSommet()+"\n");
		
		System.out.println("Test existeArc: (bon)");
		System.out.println(B.existeArc(0,0)+"\n");
		
		System.out.println("Test getValArc: (bon)");
		System.out.println(B.getValArc(1,1)+"\n");
		
		System.out.println("Test ajoutSommet: (bon)");
		System.out.println(B.ajoutSommet(4));
		System.out.println(B.existeSommet(4)+"\n");
		
		System.out.println("Test supprimeSommet: (bon)");
		System.out.println(B.supprimeSommet(4));
		System.out.println(B.existeSommet(4)+"\n");
		
		System.out.println("Test ajoutArc: (bon)");
		System.out.println(B.ajoutArc(0,0,45));
		System.out.println(B.getValArc(0,0)+"\n");
		
		System.out.println("Test supprimeArc: (bon)");
		System.out.println(B.supprimeArc(0,0));
		System.out.println(B.existeArc(0, 0)+"\n");
		
		System.out.println("Test degreSortant: (bon)");
		System.out.println(B.degreSortant(1)+"\n");
		
		System.out.println("Test degreEntrant: (bon)");
		System.out.println(B.degreEntrant(0)+"\n");
		
		System.out.println("Test degre: (bon)");
		System.out.println(B.degre(0)+"\n");
		
		System.out.println("Test liste_succ: ");
		System.out.println(B.ToString_liste_succ(0));
		System.out.println("BON \n");
		
		System.out.println("Test liste_pred: ");
		System.out.println(B.ToString_liste_pred(1));
		System.out.println("BON \n");
		
		System.out.println("FIN DU Test des PRIORITEES \n");
		
		System.out.println("AUTRES TESTS: \n");
		
		System.out.println("Test 3eme constructeur: ");
		Graphe C = new Graphe(30);
		System.out.println(C);
		System.out.println("BON \n");
		
		
		System.out.println("Test 4eme constructeur: ");
		Graphe D = new Graphe(B);
		System.out.println(D);
		System.out.println("BON \n");
		
		System.out.println("Test Reflexif: ");
		D.ajoutArc(0, 0, 1);
		System.out.println(D);
		System.out.println(D.estReflexif());
		System.out.println("BON \n");
		
		System.out.println("Test Anti-Reflexif: ");
		D.supprimeArc(1,1);D.supprimeArc(2,2);D.supprimeArc(3,3);D.supprimeArc(0,0);
		System.out.println(D);
		System.out.println(D.estAntiReflexif());
		System.out.println("BON \n");
		
		System.out.println("Test Transposition: ");
		System.out.println(D);D.Transposition();
		System.out.println(D);
		System.out.println("BON \n");
		
		int[][]mat3={{A,A,A,A},{44,A,A,A},{55,A,A,A},{66,A,A,77}};
		int[][]mat4={{A,11,22,33},{A,A,A,A},{A,A,A,A},{A,A,A,77}};
		Graphe g5= new Graphe(mat3);
		Graphe g6= new Graphe(mat4);
		
		System.out.println("Test Transitif: ");
		
		System.out.println(g5);g5.estTransitif();
		System.out.println("BON \n");
		
		System.out.println("Test Anti-Transitif: ");
		
		System.out.println(g5);g5.estAntiTransitif();
		System.out.println("BON \n");
		
		System.out.println("Test Union: ");
		
		System.out.println("g5  \n" + g5);
		System.out.println("g6  \n" + g6);
		g5.union(g6);
		System.out.println("g5 après sont union avec g6 \n" + g5);
		System.out.println("BON \n");
		
		System.out.println("Test Sous graphe: ");
		
		int [] tab={1,2};
		System.out.println("D  \n" + D);
		D.sousgraphe(tab);
		System.out.println("D après avoir créer le sous abre en supprimant les sommet 1 et 2 \n" +D);
		System.out.println("BON \n");
		
		System.out.println("Test Composition: ");
		int[][]mat5={{A,A,1,A,A},{A,A,A,1,A},{A,1,A,A,1},{1,A,1,A,A},{A,A,A,A,A}};
		Graphe g7= new Graphe(mat5);
		System.out.println("g7 \n" + g7);
		System.out.println("la composition de g7 avec lui même \n" + g7.composition(g7));
		System.out.println("BON \n");
	}
}
