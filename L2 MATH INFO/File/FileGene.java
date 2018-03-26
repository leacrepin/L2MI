package tp;

public class FileGene<T> {
	private T[] mem;
	private int tete;
	private int queue;
	
	private int suivant(int i){
		return((i+1)%(mem.length));
	}
	@SuppressWarnings("unchecked")
	public FileGene(){
		mem=(T[])new Object [1000];
		tete=0;
		queue=0;
	}
	
	@SuppressWarnings("unchecked")
	public FileGene(int taille){
		mem = (T[]) new Object[taille];
		tete=0;
		queue=0;
	}
	
	public FileGene(FileGene<integer> f){
		tete= 0; 
		queue= 0;T[] b = (T[]) f.mem;
		int taille = b.length;
		mem = (T[]) new Object[taille];
		for(int i=tete;!(suivant(i)==queue+1);i=suivant(i)){
			mem[i]=b[i];
		}
	}
	
	public void ajoutFile(T e){
		if(!filePleine()){
			mem[queue]=e;
			queue=suivant(queue);
		}else{
			System.out.println("File:ajoutFile:Erreur la file est pleine");
			System.exit(-1);
		}
	}
	
	public T oteFile(){
		if(!fileVide()){
			T a=mem[tete];
		    tete=suivant(tete);
	     	return(a);
		}else{
			System.out.println("File:oteFile:Erreur la file est vide");
			System.exit(-1);
		}
		return null;
	}
	
	public int nbElement(){
		return((queue+mem.length-tete)%(mem.length));
	}
	
	public boolean fileVide(){
		return(queue==tete);
	
	}
	
	public boolean filePleine(){
		return(nbElement()==mem.length);
	}
	
	public String toString(){
		String str="[";
		if(fileVide()){
			str+="]";
		}else{
			for(int i=tete;!(suivant(i)==queue+1);i=suivant(i)){
				if (suivant(i)==queue){
					str+=mem[i]+"]";
				}else{
					str+=mem[i]+",";
				}
			}
		}
		return str;
	}
	
	public T ithelement(int i){
		int a=i-1;int b=tete;
		while (a!=0){
			b=suivant(b);
			a--;
		}
		return mem[b];
	}
	
	public boolean egal(FileGene<integer> f){
		int a=0;
		if (nbElement()==f.nbElement()){
			for (int i=0;i<nbElement();i++){
				if (ithelement(i)==f.ithelement(i)){
					a++;
				}else{
					return false;
				}
			}
			if (a==nbElement()){
				return true;
			}
		}else{
			return false;
		}
		return false;
	}
	
	public static void main(String[] args){
		FilePile<integer> a = new FileGene<integer>();
		a.ajoutFile(1);
		a.ajoutFile(2);
		System.out.println("FileArray a:"+a.toString()+"");//Test 1er constructeur

	}
}
