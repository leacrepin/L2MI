package tp;

public class PileGene<T> {
	private T[] tab;
	private int indice;
	private int sommet; 
	
	public PileGene(){
		indice=10;sommet=-1;
		tab = (T[]) new Object [10];
	}
	public PileGene(int taille){
		tab = (T[]) new Object [taille];
		indice=taille; sommet=-1;
	}
	public PileGene(PileGene p){
		sommet= p.sommet;
		indice= p.indice;
		int t= p.sommet;
		tab = (T[]) new Object[indice];
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
    
  public void empiler (T x){
	  if (estPleine()==false){
		  tab[sommet+1]=x;
		  sommet++; 
	  }else{
		  System.out.println("error pile pleine");
	  }
	 
  }
  
  public T depiler(){
	  if (estVide()==false){
		 T x=tab[sommet];
	  sommet--;
	  return x; 
	  }else{
		  System.out.println("error pile vide");
		  return null;
	  }
	  
  }
    
  public T sommetPile(){
	  if (estVide()==false){
		  return(tab[sommet]);
	  }else{
		  System.out.println("error pile vide");
		  return null;
	  }
  }

  public boolean appartient(T e){
	  for (int i=0;i<sommet+1;i++){
		  if(tab[i]==e){
			  return true;
		  }
	  }
	  return false;
  }

  public void inverse (){
	  T[] newtab = (T[]) new Object[indice];
	  for (int i =sommet;i>-1;i--){
		  newtab[sommet-i]=tab[i];
	  }
	  tab= newtab;
  }
    
  public String toString(){
	  for (int i =indice-1;i>-1;i--){
		  System.out.println(""+(int)tab[i]+""); 
	  }
	return null;
  }
      
	public static void main (String[] args){
		System.out.println("1er test");
		PileGene<integer> p = new PileGene<integer>();
		p.toString();

		
		System.out.println("2eme test");
		PileGene<integer> q = new PileGene<integer>(20);
		q.toString();
		
		System.out.println("3eme test");
		PileGene<integer> r = new PileGene<integer>();;
		r.empiler(1);r.empiler(2);r.empiler(3);
		PileGene<integer> b = new PileGene<integer>(r);;
		b.toString();
	}

}
