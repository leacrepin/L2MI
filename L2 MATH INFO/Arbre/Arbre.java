package tp;

//Représentation d'un arbre générique 
public interface Arbre<Element> {

    public Element getElement (); // Retourne la racine
    public int getDegree ();      // Retourne le degré du " nœud " courant                 
    public Arbre<Element> getNode(int i); // Retourne le sous-arbre i 
    public boolean isEmpty ();    // Retourne vrai si l'arbre est vide
    public boolean isLeaf ();     // Retourne vrai si le nœud est une feuille
    
    public void setElement(Element e); // Positionne la valeur courante du nœud racine 
    public void addNode(Arbre<Element> a); // Ajoute un sous-arbre fils (utilisation du constructeur par clonage)
    public void deleteNode(int i);     // Supprime le ième nœud fils

    public String toString();                // Affichage des Elements
    public boolean equals(Arbre<Element> a); // Test d'égalité
    public int getHeight();              // Retourne la hauteur de l'arbre
}
