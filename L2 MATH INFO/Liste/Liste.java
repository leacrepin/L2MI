package tp;

interface Liste {
  public boolean listeVide();
  public void entete();
  public void enqueue();
  public boolean horsListe();
  public int valec();
  public void modifec(int x);
  public void succ();
  public void pred();
  public int nbElement();
  public String toString();    
  public void ajoutd(int x);
  public void ajoutg(int x);
  public void oterec() ;
  public void viderListe();
  public int ithelement(int i);
  public boolean egal(Liste l);
}