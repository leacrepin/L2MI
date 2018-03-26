package tp;

public class ListeGene<T> {
	
	private class Chainon{
		public T val;
		public Chainon suiv;
	}
	
	private Chainon tete;
	private Chainon queue;
	private Chainon ec;
	
	public ListeGene(){
		tete=null;
		queue=null;
		ec=null;
	}
	
	public ListeGene(int n){
		for(int i=0; i<=n;i++){
			ajoutd(null);
		}
	}
	
	public ListeGene (ListeGene<Integer> e){
		tete=null;
		queue=null;
		ec=null;
		e.entete();
		while(!e.horsListe()){
			enqueue();
			T x=(T) (e.ec).val;
			ajoutd(x);
			e.succ();
		}
	}
	
	public boolean listeVide(){
		return(tete==null);
	}
	
	public void entete(){
		ec=tete;
	}
	
	public void enqueue(){
		ec=queue;
	}
	
	public boolean horsListe(){
		return(ec==null);
	}
	
	public T valec(){
		if (!horsListe()){
		return(ec.val);
		}else{
			System.out.print("probleme valec");
			System.exit(-1);
			return null;
		}
	}
	
	public void modifec(T x){
		if (!horsListe()){
			ec.val=x;
			}else{
				System.out.print("probleme modifec");
				System.exit(-1);
			}
	}
	
	public void succ(){
		if (!horsListe()){
			ec=ec.suiv;
			}else{
				System.out.print("probleme succ");
				System.exit(-1);
			}
	}
	
	public void pred(){
		if(ec==tete){
			ec=null;
		}else{
			Chainon ac=ec;
			entete();
			while (ec.suiv!=ac){
				succ();
			}
		}
	}
	
	public int nbElement(){
		Chainon x=ec;
		entete();
		int i =0;
		while(!horsListe()){
			succ();
			i++;
		}
		ec=x;
		return(i);
	}
	
	public void ajoutd(T x){
		Chainon a=new Chainon();
		if (!listeVide()){
			if(!horsListe()){
				a.val=x;
				a.suiv=ec.suiv;
				ec.suiv=a;
				if (ec==queue){
					queue=a;
				}
			}
		}else{
			a.val=x;
			a.suiv=null;
			ec=a;
			queue=a;
			tete=a;
		}
	}
	
	public void ajoutg(T x){
		Chainon a=new Chainon();
		if (!listeVide()){
			if(!horsListe()){
				Chainon t=ec;
				a.val=x;
				a.suiv=t;
				pred();
				ec.suiv=a;
				if (t==tete){
					tete=a;
				}
			}
		}else{
			a.val=x;
			a.suiv=null;
			ec=a;
			queue=a;
			tete=a;
		}
	}
	
	public void oterec(){
		if (!listeVide()){
			if(!horsListe()){
				if (ec==tete){
					tete=ec.suiv;
					ec=tete;
				}else{
					pred();
					if (ec.suiv==queue){
						queue=ec;
					}else{
						ec.suiv=(ec.suiv).suiv;
					}
				}
			}
		}
	}
	
	public void viderListe(){
		tete=null;
		queue=null;
		ec=null;
	}
	
	public int ithelement(T i){
		if(!listeVide()){
			return -1;
		}else{
			int f=0;
			Chainon ac=ec;
			entete();
			while (ec.suiv!=null){
				if (ec.val==i){
					return f;
				}
				f++;
				succ();
			}
			ec=ac;
		}	
		return 0;
	}
	
	public boolean egal(ListeGene<Integer> l){
		l.entete();
		entete();
		if (nbElement()==l.nbElement()){
			while (ec.suiv!=null){
				if (ec.val==l.valec()){
					succ();
					l.succ();
				}else{
					return false;
				}
			}
			if ((ec).val!=l.valec()){
				return false;
			}
		return true;
		}else{
			return false;
		}
	}
	
	public String toString(){
		String p="[";
		entete();
		while (ec!=queue){
			p=p+""+ec.val+",";
			succ();
		}
		if (ec==queue){
			if (!listeVide()){
			p=p+ec.val+"]";
			}else{
				p=p+"]";
			}
		}
		return p;
	}

	public static void main(String[] args) {
		ListeGene<Integer> l = new ListeGene<Integer>(); //test premier constructeur (fonctionne)
		System.out.println("l ="+l.toString());
		ListeGene<Integer> f = new ListeGene<Integer>(5); //test second constructeur (fonctionne)
		System.out.println("f ="+f.toString());
		ListeGene<Integer> g = new ListeGene<Integer>(f); // test troisieme constructeur (fonctionne)
		System.out.println("g ="+g.toString());
		f.viderListe();// test vider f=[0,0,0,0,0,0] (fonctionne )
		System.out.println("f vide ="+f.toString());
		f.ajoutd(5);
		l.ajoutd(5);
		System.out.println("f ="+f.toString());
		System.out.println("l ="+l.toString());
		System.out.println("l et f sont egaux ? "+l.egal(f));// l et g sont egaux ?
		l.ajoutd(6);
		System.out.println("l ="+l.toString());
		System.out.println("f ="+f.toString());
		System.out.println("et maintenant ? "+l.egal(f));// l et g sont egaux ?
		f.ajoutd(0);
		System.out.println("l ="+l.toString());
		System.out.println("f ="+f.toString());
		System.out.println("et maintenant ? "+l.egal(f));// l et g sont egaux ?
	}

}
