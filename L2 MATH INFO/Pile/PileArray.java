package tp;

public class PileArray implements Pile{
	private int[] tab;
	private int indice;
	private int sommet; 
	
	public PileArray(){
		indice=10;sommet=-1;
		tab = new int[10];
	}
	public PileArray(int taille){
		tab = new int [taille];
		indice=taille; sommet=-1;
	}
	public PileArray(PileArray p){
		sommet= p.sommet;
		indice= p.indice;
		int t= p.sommet;
		tab = new int[indice];
		for (int i=0;i<t+1;i++){
			tab[i]=p.depiler();
		}
		inverse();
	}	
	
  public boolean estVide(){
  	if (sommet==-1){
  		return true;
  	}
  	return false;	
  }
  
  public boolean estPleine(){
	  if (sommet == indice-1){
		  return true;
	  }
	  return false;
  }
    
  public int nbElement(){
	  return sommet+1;
  }
    
  public void empiler (int x){
	  if (estPleine()==false){
		  tab[sommet+1]=x;
		  sommet++; 
	  }else{
		  System.out.println("error pile pleine");
	  }
	 
  }
  
  public int depiler(){
	  if (estVide()==false){
		 int x=tab[sommet];
	  sommet--;
	  return x; 
	  }else{
		  System.out.println("error pile vide");
		  return 0;
	  }
	  
  }
    
  public int sommetPile(){
	  if (estVide()==false){
		  return(tab[sommet]);
	  }else{
		  System.out.println("error pile vide");
		  return 0;
	  }
  }

  public boolean appartient(int e){
	  for (int i=0;i<sommet+1;i++){
		  if(tab[i]==e){
			  return true;
		  }
	  }
	  return false;
  }

  public void inverse (){
	  int [] newtab=new int[indice];
	  for (int i =sommet;i>-1;i--){
		  newtab[sommet-i]=tab[i];
	  }
	  tab= newtab;
  }
    
  public String toString(){
	  for (int i =indice-1;i>-1;i--){
		  System.out.println(""+tab[i]+""); 
	  }
	return null;
  }
      
	public static void main (String[] args){
		System.out.println("1er test");
		Pile p = new PileArray();
		p.toString();

		
		System.out.println("2eme test");
		Pile q = new PileArray(20);
		q.toString();
		
		System.out.println("3eme test");
		PileArray r = new PileArray(6);
		r.empiler(1);r.empiler(2);r.empiler(3);
		Pile b = new PileArray(r);
		b.toString();

	}	
}