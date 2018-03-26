package tp;

public class ListeChaine implements Liste{
	
	private class Chainon{
		public int val;
		public Chainon suiv;
	}
	
	private Chainon tete;
	private Chainon queue;
	private Chainon ec;
	
	public ListeChaine(){
		tete=null;
		queue=null;
		ec=null;
	}
	
	public ListeChaine(int n){
		for(int i=0; i<=n;i++){
			ajoutd(0);
		}
	}
	
	public ListeChaine (ListeChaine e){
		tete=null;
		queue=null;
		ec=null;
		e.entete();
		while(!e.horsListe()){
			enqueue();
			ajoutd(e.valec());
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
	
	public int valec(){
		if (!horsListe()){
		return(ec.val);
		}else{
			System.out.print("probleme valec");
			System.exit(-1);
			return -1;
		}
	}
	
	public void modifec(int x){
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
	
	public void ajoutd(int x){
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
	
	public void ajoutg(int x){
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
	
	public int ithelement(int i){
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
	
	public boolean egal(Liste l){
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
	
	public static void main(String[] args){
		ListeChaine l = new ListeChaine(); //test premier constructeur (fonctionne)
		l.ajoutd(5);
		System.out.println("l ="+l.toString());
		ListeChaine f = new ListeChaine(5); //test second constructeur (fonctionne)
		System.out.println("f ="+f.toString());
		ListeChaine g = new ListeChaine(l); // test troisieme constructeur (fonctionne)
		System.out.println("g ="+g.toString());
		f.viderListe();// test vider f=[0,0,0,0,0,0] (fonctionne )
		System.out.println("f vide ="+f.toString());
		System.out.println("l et g sont egaux ? "+l.egal(g));// l et g sont egaux ?
		l.ajoutd(6);
		System.out.println("l ="+l.toString());
		System.out.println("et maintenant ? "+l.egal(g));// l et g sont egaux ?
		g.ajoutd(0);
		System.out.println("l ="+l.toString());
		System.out.println("g ="+g.toString());
		System.out.println("et maintenant ? "+l.egal(g));// l et g sont egaux ?
	}

}
