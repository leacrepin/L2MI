package tp;

import java.util.LinkedList;

public class ArbreGene<Element> implements Arbre<Element> {

	private Element element;
	private LinkedList<ArbreGene<Element>> fils;
	
	public ArbreGene(){
		element=null;
		fils=new LinkedList<ArbreGene<Element>> ();
	}
	
	public ArbreGene(Element e){
		element=e;
		fils=new LinkedList<ArbreGene<Element>> ();
	}
	
	public ArbreGene(Arbre<Element> a){
		int b=a.getDegree();
		element=a.getElement();
		fils=new LinkedList<ArbreGene<Element>> ();
		for(int i=0;i<b;i++){
			addNode(new ArbreGene<Element>(a.getNode(i)));
		}
	}

	public void addNode(Arbre<Element> a) {
		if (isEmpty()){
			element=a.getElement();
			fils=new LinkedList<ArbreGene<Element>> ();
			for(int i=0;i<a.getDegree();i++){
				addNode(new ArbreGene<Element>(a.getNode(i)));
			}
		}else{
		fils.add(new ArbreGene<Element>(a));
		}
	}

	public void deleteNode(int i) {
		if(i<0 || i>=fils.size()){
			System.exit(-1);
		}
		fils.remove(i);
	}

	public boolean equals(Arbre<Element> a) {
		boolean test=true;
		if(element!=a.getElement()){return false;}
		if(isLeaf()!=a.isLeaf()){return false;}
		if(getDegree()!=a.getDegree()){return false;}
		int i=0;
		while(test && i<getDegree()){
			ArbreGene<Element> f = new ArbreGene<Element>(getNode(i));
			int j=0;
			test=false;
			while(!test && j<a.getDegree()){
				test=f.equals(a.getNode(j));
				j++;
			}
			i++;
		}return(test);
	}

	public int getDegree() {
		return fils.size();
	}

	public Element getElement() {
		return(element);
	}

	public int getHeight() {
		int h=0;int c = 0;
		for (int i=0;i<getDegree();i++){
			h=1+getNode(i).getHeight();
			if(h>c){c=h;}
		}
		return c;
	}

	public Arbre<Element> getNode(int i) {
		if(i<0 || i>=fils.size()){
			System.exit(-1);
		}
		return(fils.get(i));
	}

	public boolean isEmpty() {
		return(element==null);
	}

	public boolean isLeaf() {
		return(fils.size()==0);
	}

	public void setElement(Element e) {
		element=e;
	}
	
	public String toString(){
		String s="{";
		if(!isEmpty()){s=s+element;}
		if(!isLeaf()){
			for(int i=0;i<fils.size();i++){
				s=s+","+getNode(i).toString();
			}
		}
		s=s+"}";
		return s;
	}
	
	public static void main(String[] args) {
		System.out.println("test constructeur ArbreGene(element) avec a: BON");
		ArbreGene<String> a=new ArbreGene<String>("10");
		System.out.println("a="+a);
		System.out.println("test ajout avec a (ses fils b et c): BON");
		ArbreGene<String> b=new ArbreGene<String>("1");
		ArbreGene<String> c=new ArbreGene<String>("0");
		a.addNode(b);
		a.addNode(c);
		System.out.println("a="+a);
		System.out.println("test constructeur ArbreGene(Arbre) avec a et d: BON ");
		ArbreGene<String> d=new ArbreGene<String>(a);
		System.out.println("d="+d);
		System.out.println("test equals avec a et d: BON");
		System.out.println("a et d egaux ? :"+a.equals(d));
		System.out.println("test vide et leaf avec b: BON");
		System.out.println("b est vide ?:"+b.isEmpty()+" , b est une feuille ?:"+b.isLeaf());
		System.out.println("test hauteur avec a: BON");
		System.out.println("hauteur de a: "+a.getHeight());
		System.out.println("test delete dans a 0 et changer element 10 en 1: BON");
		a.deleteNode(1);
		a.setElement("1");
		System.out.println("a="+a);
		System.out.println("test constructeur ArbreGene() avec e: BON");
		ArbreGene<String> e=new ArbreGene<String>();
		System.out.println("(BON) e est vide? = "+e.isEmpty());
		e.addNode(b);//quand e est vide et que l'on veut ajouter une famille pour eviter de faire une famille sans racine je fais comme le constructeur ArbreGene(Arbre)
		e.addNode(c);
		e.addNode(a);
		e.addNode(d);
		System.out.println("(BON) e apres ajout de toute les familles précédentes= "+e);
	}
}
