package tp;

public class EnsembleArray implements Ensemble{
	private int[] tab;
	private int indice;
	
	public EnsembleArray(){
		indice=-1;
		tab = new int[10];
	}
	public EnsembleArray(int taille){
		tab = new int [taille];
		indice=-1;
	}
	public EnsembleArray(EnsembleArray p){
		indice= p.indice;
		int t= p.indice;
		tab = new int[indice+1];
		for (int i=0;i<t+1;i++){
			tab[i]=p.tab[i];
		}
	}	
	
	public int getElement(int indice){
		return (tab[indice]);
	}
	
  public boolean estVide(){
  	if (indice==-1){
  		return true;
  	}
  	return false;	
  }
  
  public boolean estPlein(){
	  if (indice == tab.length-1){
		  return true;
	  }
	  return false;
  }
    
  public int nbElement(){
	  return indice+1;
  }
  
  public int element(){
	  return tab[0];
  }

  public boolean appartient(int element){
	  for (int i=0;i<tab.length;i++){
		  if(tab[i]==element){
			  return true;
		  }
	  }
	  return false;
  }
  
  public int getIndice(int element){
	for (int i=0;i<tab.length;i++){
		if(tab[i]==element){
			return i;
		}
	}		
	return -1;
  }
  
  public void ajoutElt(int element){
	  if (estPlein()==false){
		  tab[indice+1]=element;
		  indice++;
	  }else{
		  System.out.println("l'ensemble est plein");
	  }
  }
  
  public void oterElt(int element){
	  if (estVide()==false){
		  int a= getIndice(element);
		  tab[a]=tab[indice];
		  indice --;
	  }else{
		  System.out.println("l'ensemble est vide");
	  }
  }
  
	public Ensemble union(Ensemble e){
		EnsembleArray g = (EnsembleArray) e;
		EnsembleArray f= new EnsembleArray(g);
		for (int i=0;i<e.nbElement();i++){
			if(!f.appartient(tab[i])){
				f.ajoutElt(tab[i]);
			}
		}
		return (f);
	}
	
	public Ensemble intersection(Ensemble e){
		EnsembleArray g = (EnsembleArray) e;
		EnsembleArray f= new EnsembleArray(g);
		for (int i=0;i<e.nbElement();i++){
			if(!appartient(f.getElement(i))){
				f.oterElt(f.getElement(i));
			}
		}
		return f;
	}
	
	public Ensemble diff(Ensemble e){
		EnsembleArray g = (EnsembleArray) e;
		EnsembleArray f= new EnsembleArray(g);
		for (int i=0;i<e.nbElement();i++){
			if(f.appartient(tab[i])){
				f.oterElt(tab[i]);
			}
		}
		return f;
	}
	
	public boolean egal(Ensemble e){
		boolean b=false;
		if (e.nbElement()==nbElement()){
			Ensemble a = intersection(e);
			if (a.nbElement()==nbElement()){
				b=true;
			}
		}
		return b;
	}
	
	public boolean disjoints(Ensemble e){
		EnsembleArray z=(EnsembleArray) e;
		int a=0;int b=0;
		if (egal(z)==true){
			return false;
		}else{
			if (z.nbElement()<nbElement()){
				for (int i=0;i<z.nbElement();i++){
					if (appartient(z.tab[i])==true){
						a++;b=i;
					}
				}
			}else{
				for (int i=0;i<nbElement();i++){
					if (z.appartient(tab[i])==true){
						a++;b=i;
					}
				}
			}
		}
		if (a==b){
			return false;
		}else{
			return true;
		}
	}

	public boolean inclus(Ensemble e){
		EnsembleArray z=(EnsembleArray) e;
		int a=0;int b=0;
		if (egal(z)==true){
			return false;
		}else{
			if (z.nbElement()<nbElement()){
				for (int i=0;i<z.nbElement();i++){
					if (appartient(z.tab[i])==true){
						a++;b=i;
					}
				}
			}else{
				for (int i=0;i<nbElement();i++){
					if (z.appartient(tab[i])==true){
						a++;b=i;
					}
				}
			}
		}
		if (a==b){
			return true;
		}else{
			return false;
		}
	}
    
  public String toString(){
	  for (int i =indice-1;i>-1;i--){
		  System.out.println(""+tab[i]+""); 
	  }
	return null;
  }
      
	public static void main (String[] args){
		System.out.println("test");
		Ensemble p = new EnsembleArray();
		p.ajoutElt(3);
		p.toString();


	}	
}