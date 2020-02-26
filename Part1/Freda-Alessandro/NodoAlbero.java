import java.util.LinkedList;
public class NodoAlbero {
   

    private int chiave;
    private LinkedList<Integer> lista;
    private NodoAlbero father, left, right;

      public NodoAlbero(int chiave){
            this.chiave = chiave;
            this.father = null;
            lista = new LinkedList<>();
            left = null;
            right = null;
    }

    public NodoAlbero(int chiave, NodoAlbero father){
            this.chiave = chiave;
            this.father = father;
            lista = new LinkedList<>();
            left = null;
            right = null;
    }

    public int getChiave() {
        return chiave;
    }


    public NodoAlbero getFather() {
        return father;
    }

    public NodoAlbero getLeft() {
        return left;
    }

    public NodoAlbero getRight() {
        return right;
    }

    public void setChiave(int chiave) {
        this.chiave = chiave;
    }

    public void setFather(NodoAlbero father) {
        this.father = father;
    }

    public void setLeft(NodoAlbero left) {
        this.left = left;
    }

    public void setRight(NodoAlbero right) {
        this.right = right;
    }

    /**
     * Aggiunge un nuovo codice alla lista
     */
    public void aggiungiCodice(int codice) {
        lista.add(codice);
    }

    public LinkedList<Integer> getCodici() {
            return lista;
    }

    public boolean searchCodice(int codice){
    	int c = 0;
    	for(int i=0; i < lista.size(); i++){
      	if (lista.get(i)==codice) {
      		c=1;;
      	} else{
      		c=-1;
      	}
      }
      if (c == 1) {
      	return true;
      } else{
      	return false;
      }

    }




}