package tp;

/**
 * Interface Ensemble.
 *
 * Cette classe permet de manipuler un ensemble d'entiers.
 * Plusieurs procédures permettent de rajouter, d'enlever, d'obtenir un
 * élément ou bien de retourner le nombre d'éléments d'un ensemble.
 *
 * @author Marc Chaumont
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface Ensemble { 

	/**
 	 * Retourne l'élément présent à l'indice 'indice'.
 	 *
 	 * @param int indice : l'indice de l'elément retourné.
 	 * @return int : élement retourné.
 	 */ 
	public int getElement(int indice);
	
		
	/**
 	 * Retourne vrai si l'ensemble est vide et faux sinon.
 	 *
 	 * @param aucun.
 	 * @return boolean.
 	 */ 
	public boolean estVide();

	/**
 	 * Retourne vrai si l'ensemble est plein et faux sinon.
 	 *
 	 * @param aucun.
 	 * @return boolean.
 	 */ 
	public boolean estPlein();
	/**
 	 * Retourne le nombre d'éléments de l'ensemble.
 	 *
 	 * @param aucun.
 	 * @return int.
 	 */	 
	public int nbElement();


	/**
 	 * Retourne un élement de l'ensemble.
 	 *
 	 * @param aucun.
 	 * @return int.
 	 */ 
	public int element(); 

	/**
 	 * La méthode appartient retourne vrai si l'entier passé en paramètre est 
 	 * dans l'ensemble et faux sinon.
 	 *
 	 * @param int element : entier dont on souhaite savoir s'il appartient à 
 	 * l'ensemble.
 	 * @return boolean.
 	 */ 
	public boolean appartient (int element);
	
	/**
 	 * La méthode getIndice est une méthode qui devrait être privée 
 	 * (elle ne doit pas être utilisée depuis l'extérieur), elle retourne 
 	 * l'indice d'un élement s'il est présent dans le tableau T et -1 sinon.
 	 *
 	 * @param int element : entier dont on souhaite connaître l'indice.
 	 * @return int : indice de l'entier.
 	 */ 
     public int getIndice(int element);
	 

	/**
 	 * La méthode ajoutelt permet d'ajouter un élément dans l'ensemble. 
 	 *
 	 * @param int element : entier à ajouter.
 	 * @return aucun.
 	 */ 
	public void ajoutElt(int element);

	/**
 	 * Méthode permettant d'ôter un élément présent dans un ensemble.
 	 *
 	 * @param int.
 	 * @return aucun.
 	 */ 
	public void oterElt(int element);

	
	/**
 	 * Méthode retournant une chaîne de caractère caractérisant l'ensemble.
 	 *
 	 * @return String : chaîne de caractère retournée.
 	 */ 	
	public String toString();
	
	/**
 	 * Méthode retournant l'union de l'ensemble courant et l'ensemble passé en 
 	 * paramètre. Exemple d'utilisation : <br>
 	 * Ensemble e1 = new Ensemble();<br>
 	 * Ensemble e2 = new Ensemble();<br>
 	 * Ensemble e3 = e1.union(e2); 
 	 *
 	 * @param Ensemble e : l'ensemble avec lequel est effectuée l'union.
 	 * @return Ensemble : le résultat de l'union.
 	 */ 	
	public Ensemble union(Ensemble e);
	
	/**
 	 * Méthode retournant l'intersection de l'ensemble courant et l'ensemble 
 	 * passé en paramètre. Exemple d'utilisation : <br>
 	 * Ensemble e1 = new Ensemble();<br>
 	 * Ensemble e2 = new Ensemble();<br>
 	 * Ensemble e3 = e1.intersection(e2); 
 	 *
 	 * @param Ensemble e : l'ensemble avec lequel est effectuée l'intersection.
 	 * @return Ensemble : le résultat de l'intersection.
 	 */ 	
	public Ensemble intersection(Ensemble e);
	
	/**
 	 * Méthode retournant l'ensemble issu de la soustraction d'un ensemble à 
 	 * un autre. Exemple d'utilisation :<br> 
 	 * Ensemble e1 = new Ensemble();<br>
 	 * Ensemble e2 = new Ensemble();<br>
 	 * Ensemble e3 = e1.diff(e2); // e3 = e1 - e2
 	 *
 	 * @param Ensemble e : l'ensemble à soustraire.
 	 * @return Ensemble : le résultat de la différence.
 	 */ 	
	public Ensemble diff(Ensemble e);

    /**
 	 * Méthode retournant vrai si l'ensemble courant et l'ensemble passé en paramètre sont égaux. Exemple d'utilisation : <br>
 	 * Ensemble e1 = new Ensemble();<br>
 	 * Ensemble e2 = new Ensemble();<br>
 	 * boolean   b = e1.egal(e2);
 	 *
 	 * @param Ensemble e : l'ensemble avec lequel on teste l'égalité.
 	 * @return boolean : vrai si les deux ensembles sont égaux.
 	 */ 	
	public boolean egal(Ensemble e);

    /**
 	 * Méthode retournant vrai si l'ensemble courant et l'ensemble passé en paramètre ont une intersection vide. Exemple d'utilisation :<br> 
 	 * Ensemble e1 = new Ensemble();<br>
 	 * Ensemble e2 = new Ensemble();<br>
 	 * boolean   b = e1.disjoints(e2);
 	 *
 	 * @param Ensemble e : l'ensemble avec lequel on teste l'intersection.
 	 * @return boolean : vrai si les deux ensembles sont disjoints.
 	 */ 	
	public boolean disjoints(Ensemble e);

    /**
 	 * Méthode retournant vrai si l'ensemble courant est inclus ou égal à l'ensemble passé en paramètre. Exemple d'utilisation :<br> 
 	 * Ensemble e1 = new Ensemble();<br>
 	 * Ensemble e2 = new Ensemble();<br>
 	 * boolean   b = e1.inclus(e2);
 	 *
 	 * @param Ensemble e : l'ensemble avec lequel on teste l'intersection.
 	 * @return boolean : vrai si l'ensemble courant est inclus dans l'ensemble passé en paramètre.
 	 */ 	
	public boolean inclus(Ensemble e);

} //Fin de l'interface Ensemble