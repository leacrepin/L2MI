public interface Pile {
	 
  public boolean estVide();

  public boolean estPleine();
    
  public int nbElement();
    
  public void empiler (int x);
  
  public int depiler(); 
    
  public int sommetPile();

  public boolean appartient(int e);

  public void inverse ();
    
  public String toString();
      
} // interface Pile

