public class AlberoBinario{


    NodoAlbero root;

    //operazioni elementari 
    private static int ricerca;
    private static int inserimento;
    private static int eliminazione;

    private static int linkedListRicerca;

    public static void resetElemOp() {
      ricerca = 0; inserimento = 0; eliminazione = 0; linkedListRicerca=0;
   }

   // Stampa le operazioni elementari divise per tipologia
   public static void printElemOp() {
      System.out.println("[R:" + ricerca + "|I:" + inserimento + "|E:" + eliminazione + "]");
   }

   public static void printElemOpLista() {
      System.out.println("|RR: "+ linkedListRicerca);
   }


    public AlberoBinario(NodoAlbero root){
        this.root = root;
    }
    
    public AlberoBinario(){
        this.root = null;
    }

    public NodoAlbero getRoot(){
            return root;
    }

    //stampa l'albero a partire dal metodo di visita 
    public void print(){

            inVisit(root);
            System.out.println();
    }


    private void inVisit(NodoAlbero tmp){
            if (tmp != null){
                    inVisit(tmp.getLeft());
                    System.out.print(" "+tmp.getChiave());
                    inVisit(tmp.getRight());
            }
    }

    private NodoAlbero min(NodoAlbero tmp){
            if (tmp != null){
                    if (tmp.getLeft() == null){
                            return tmp;
                    }
                    return min(tmp.getLeft());
            }
            return null;
    }
    
    public NodoAlbero min(){
            if (root == null){
                return null;
            }else{
                return min(root.getLeft());
            }
    }

    private NodoAlbero max(NodoAlbero tmp){
            if (tmp != null){
                    if (tmp.getRight() == null){
                            return tmp;
                    }
                    return max(tmp.getRight());
            }
            return null;
    }

    public NodoAlbero pred(NodoAlbero node){
            if (node != null){
                    if (node.getLeft() != null){
                            return max (node.getLeft());
                    }
                    NodoAlbero tmp = node;
                    NodoAlbero tmpFather = tmp.getFather();
                    while (tmpFather != null && tmp != tmpFather.getRight()){
                            tmp = tmpFather;
                            tmpFather = tmpFather.getFather();
                    }
                    return tmpFather;
            }
            return null;
    }

    public NodoAlbero succ(NodoAlbero node){
            if (node != null){

                    // primo caso: minimo del sottoalbero destro 
                    if (node.getRight() != null){
                            return min (node.getRight());
                    }

                    // secondo caso: primo avo per cui u si trovi nel sottoalbero sinistro
                    NodoAlbero tmp = node;
                    NodoAlbero tmpFather = tmp.getFather();
                    while (tmpFather != null && tmp != tmpFather.getLeft()){
                            tmp = tmpFather;
                            tmpFather = tmpFather.getFather();
                    }
                    return tmpFather;
            }
            return null;
    }

    /*
    Metodo che ricerca un codice nella lista corrispondente a un nodo della chiave
     */

    public int ricercaCodice(NodoAlbero nodo, int codice){
    	int verifica =0;
        linkedListRicerca++;
    	if (nodo!=null) { 
    		for (int i =0;i< nodo.getCodici().size() ;i++ ) {
                linkedListRicerca++;
    			if (nodo.getCodici().get(i) == codice) {
    				 verifica = 1; //se trova il codice nella lista setta verifica a 1
    			}
    		}
            linkedListRicerca++;
    		if (verifica == 1) {
    			return 1; //System.out.println("Elemento trovato");
    		} else{
    			return 0; //System.out.println("Elemento NON trovato");
    		}
    	}else{
    		return -1; //System.out.println("Chiave non trovata");//se il nodo è null
    	}
    }

	/*
		Metodi che cercano a partire dalla chiave il nodo, restituiscono il nodo 
	 */

    public NodoAlbero searchRic(int chiave){
        ricerca++;
        return searchRic(chiave,root);
        
    }

    private NodoAlbero searchRic(int chiave, NodoAlbero node){
            ricerca++;
            if (node == null){
                    return null;
            }
            ricerca++;
            if (chiave == (node.getChiave())){
                    return node;
            }else {
                    ricerca++;
                    if (chiave < (node.getChiave())){
                            return searchRic(chiave, node.getLeft());
                    }else{
                            return searchRic(chiave, node.getRight());
                    }
            }
    }

    //dato un nodo inserisce un codice nella lista corrispondente
    public void inserisciCodiceLista(NodoAlbero nodo,int codice){
    		nodo.aggiungiCodice(codice);
    		System.out.println("Inserimento riuscito!");
    }

    /*
    	Metodi per l'inserimento di un nuovo nodo a partire da una chiave
     */

    public void insertRic(int chiave){
            inserimento+=2;
    		if (searchRic(chiave) != null) {
    		 	System.out.println("Chiave già presente");
    		 } 

           	else if (root==null){
                    root = new NodoAlbero(chiave,null);
            }else{
                    insertRic(root, chiave);
            }
    }

    private void insertRic(NodoAlbero node, int chiave){
            inserimento++;
            if (chiave < (node.getChiave())){
                    inserimento++;
                    if (node.getLeft()==null)
                            // inserisce il nodo come foglia 
                            node.setLeft(new NodoAlbero(chiave,node));
                    else
                            insertRic(node.getLeft(), chiave);

            }else{
                    inserimento++;
                    if (node.getRight()==null)
                            // inserisce il nodo come foglia 
                            node.setRight(new NodoAlbero(chiave,node));
                    else
                            insertRic(node.getRight(),chiave);
            }
    }


	/*
		Elimina un nodo a partire da una chiave, non c'è nessun metodo per eliminare
		la lista associata in quanto in java quando si elimina il riferimento al nodo
		di una qualsiasi lista, avviene il processo di garbage collection. Dunque non 
		vi è spreco di memoria.
	 */
    public void elimina(int chiave){
    	delete(searchRic(chiave));
        eliminazione++;
    }

    private void delete (NodoAlbero node){
            eliminazione++;
            if (node == null){
                    return;
            }
            eliminazione++;
            // PRIMO CASO: il nodo da eliminare e' una foglia
            if (node.getLeft() == null && node.getRight() == null){

                    eliminazione+=2;
                    if (node == root){
                            root = null;
                    // figlio sinistro
                    }else if (node.getFather().getLeft() == node){
                            node.getFather().setLeft(null);
                    }else{
                            node.getFather().setRight(null);
                    }
                    return;
            }

            // SECONDO CASO: il nodo da cancellare ha solo un figlio
            eliminazione+=2;
            if (node.getLeft() == null){
                    eliminazione++;
                    if (node == root){
                            root = node.getRight();
                            return;
                    }
                    eliminazione++;
                    if (node.getFather().getLeft() == node){
                            node.getFather().setLeft(node.getRight());//aggiorno il rifermento al figlio
                            node.getRight().setFather(node.getFather());//aggiorno il riferimento al padre
                    }else{
                            node.getFather().setRight(node.getRight());//aggiorno il rifermento al figlio
                            node.getRight().setFather(node.getFather());//aggiorno il riferimento al padre
                    }
                    return;

            }else if (node.getRight() == null){ 
                    eliminazione++;
                    if (node == root){
                            root = node.getLeft();
                            return;
                    }
                    eliminazione++;
                    if (node.getFather().getLeft() == node){
                            node.getFather().setLeft(node.getLeft());//aggiorno il rifermento al figlio
                            node.getLeft().setFather(node.getFather());//aggiorno il riferimento al padre
                    }else{
                            node.getFather().setRight(node.getLeft());//aggiorno il rifermento al figlio
                            node.getLeft().setFather(node.getFather());//aggiorno il riferimento al padre
                    }
                    return;
            }

            // TERZO CASO: il nodo ha due figli
            // cerco il predecessore
            NodoAlbero pred = pred(node);
            System.out.println("pred > "+pred.getChiave());
            // lo elimino (modificando i puntatori del padre)
            pred.getFather().setRight(pred.getLeft()); // il predecessore ha al piu il figlio sinistro
            // lo inserisco nella posizione di u
            pred.setFather(node.getFather());
            pred.setRight(node.getRight());
            pred.setLeft( node.getLeft());
            eliminazione++;
            if (node == root){
                    root = pred;
            }

            return;
    }

}
