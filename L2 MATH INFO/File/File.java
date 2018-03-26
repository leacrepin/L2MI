package tp;

interface File {

  public boolean fileVide();
  
  public boolean filePleine();

  public void ajoutFile (int o);

  public int oteFile();
  
  int nbElement();

  public String toString();
  
  public int ithelement(int i);

  public boolean egal(File f);
} //Interface File
