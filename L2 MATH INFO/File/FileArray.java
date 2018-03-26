package tp;

class FileArray implements File{
	
	private int [] mem;
	private int tete;
	private int queue;
	
	private int suivant(int i){
		return((i+1)%(mem.length));
	}
	
	public FileArray(){
		mem=new int [1000];
		tete=0;
		queue=0;
	}
	
	public FileArray(int taille){
		mem=new int [taille];
		tete=0;
		queue=0;
	}
	
	public FileArray(FileArray f){
		tete= 0;
		queue= 0;int[] b = f.mem;
		int taille = b.length;
		mem = new int[taille];
		while (!(f.fileVide())){
			ajoutFile(f.oteFile());
		}
	}
	
	public FileArray(File f){
		tete= 0;
		queue= 0;
		int taille = (f.nbElement())+3;
		mem = new int[taille];
		while (!(f.fileVide())){
			ajoutFile(f.oteFile());
		}
	}
	
	public void ajoutFile(int e){
		if(!filePleine()){
			mem[queue]=e;
			queue=suivant(queue);
		}else{
			System.out.println("File:ajoutFile:Erreur la file est pleine");
			System.exit(-1);
		}
	}
	
	public int oteFile(){
		if(!fileVide()){
			int a=mem[tete];
		    tete=suivant(tete);
	     	return(a);
		}else{
			System.out.println("File:oteFile:Erreur la file est vide");
			System.exit(-1);
		}
		return -1;
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
					str+= mem[i]+",";
				}
			}
		}
		return str;
	}
	
	public int ithelement(int i){
		int a=i;int b=tete;
		while (a!=0){
			b=suivant(b);
			a--;
		}
		return mem[b];
	}
	
	public boolean egal(File f){
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
		FileArray a = new FileArray();
		a.ajoutFile(1);
		a.ajoutFile(2);
		System.out.println("FileArray a:"+a.toString()+"");//Test 1er constructeur
		FileArray b = new FileArray(a);
		b.ajoutFile(0);
		System.out.println("FileArray b:"+b.toString()+"");//Test 3eme constructeur
		File c = new FileArray();
		c.ajoutFile(5);
		c.ajoutFile(5);
		System.out.println("File c:"+c.toString()+"");
		FileArray d = new FileArray(c);
		d.ajoutFile(0);
		System.out.println("FileArray d:"+d.toString()+"");//Test 4eme constructeur
		FileArray e = new FileArray(100);
		c.ajoutFile(5);//c a perdu ses élèments lors du 4eme constructeur 
		c.ajoutFile(5);
		e.ajoutFile(5);
		e.ajoutFile(5);
		System.out.println("FileArray e:"+e.toString()+"");//Test 2eme constructeur
		System.out.println("FileArray e et File c sont egaux ?:"+e.egal(c)+"");
	}
}
