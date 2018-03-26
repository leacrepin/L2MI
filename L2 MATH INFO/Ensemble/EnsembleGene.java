package tp;

public class EnsembleGene<T> {
	private T[] tab;
	private int indice;
	
	public EnsembleGene(){
		indice=-1;
		tab = (T[]) new Object [10];
	}
	
	public EnsembleGene(int taille){
		tab = (T[]) new Object [taille];
		indice=-1;
	}
	
	public EnsembleGene(EnsembleGene p){
		indice= p.indice;
		int t= p.indice;
		tab = (T[]) new Object[indice+1];
		for (int i=0;i<t+1;i++){
			tab[i]=(T)p.tab[i];
		}
	}
	
	public T getElement(int indice){
		return ((T) tab[indice]);
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
		  return ((int)tab[0]);
	  }
	  
	  public boolean appartient(T element){
		  for (int i=0;i<tab.length;i++){
			  if(tab[i]==element){
				  return true;
			  }
		  }
		  return false;
	  }
	  
	  public int getIndice(T element){
			for (int i=0;i<tab.length;i++){
				if(tab[i]==element){
					return i;
				}
			}		
			return -1;
	  }
	  
	  public void ajoutElt(T element){
		  if (estPlein()==false){
			  tab[indice+1]=element;
			  indice++;
		  }else{
			  System.out.println("l'ensemble est plein");
		  }
	  }
	  
	  public void oterElt(T element){
		  if (estVide()==false){
			  int a= getIndice(element);
			  tab[a]=tab[indice];
			  indice --;
		  }else{
			  System.out.println("l'ensemble est vide");
		  }
	  }
	  
		public EnsembleGene union(EnsembleGene e){
			EnsembleGene g = (EnsembleGene) e;
			EnsembleGene f= new EnsembleGene(g);
			for (int i=0;i<e.nbElement();i++){
				if(!f.appartient(tab[i])){
					f.ajoutElt(tab[i]);
				}
			}
			return (f);
		}
		
		public EnsembleGene intersection(EnsembleGene e){
			EnsembleGene g = (EnsembleGene) e;
			EnsembleGene f= new EnsembleGene(g);
			for (int i=0;i<e.nbElement();i++){
				if(!appartient(f.getElement(i))){
					f.oterElt(f.getElement(i));
				}
			}
			return f;
		}
		
		public EnsembleGene diff(EnsembleGene e){
			EnsembleGene g = (EnsembleGene) e;
			EnsembleGene f= new EnsembleGene(g);
			for (int i=0;i<e.nbElement();i++){
				if(f.appartient(tab[i])){
					f.oterElt(tab[i]);
				}
			}
			return f;
		}
		
		public boolean egal(EnsembleGene e){
			boolean b=false;
			if (e.nbElement()==nbElement()){
				EnsembleGene a = intersection(e);
				if (a.nbElement()==nbElement()){
					b=true;
				}
			}
			return b;
		}
		
		public boolean disjoints(EnsembleGene e){
			EnsembleGene z=(EnsembleGene) e;
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

		public boolean inclus(EnsembleGene e){
			EnsembleGene z=(EnsembleGene) e;
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

}
