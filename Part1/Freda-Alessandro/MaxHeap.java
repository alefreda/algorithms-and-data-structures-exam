public class MaxHeap{
	oggettoArray[] heap;
	int elementNumber; //tiene traccia del numero di elementi correnti dell'array
	int numSwap;

	//dim è la dimensione massima dell'array
	public MaxHeap(int dim){
	    heap = new oggettoArray[dim]; 
	    elementNumber = 0;
	    numSwap = 0;
  	}

	public int elementNumber(){
	    return elementNumber;
	  }

	  public void azzeraContatoreSwap(){
	  	numSwap=0;
	  }

	//costo O(1)
	public boolean isEmpty(){
	    if (elementNumber == 0) {
	      return true;
	    } else {
	      return false;
	    }
	  }

	public boolean isFull() {
	    if (elementNumber == heap.length) {
	      return true;
	    } else {
	      return false;
	    }
	  }


	//metodo per lo swap, sposta tutto l'oggetto
	public void swap(oggettoArray[] a, int i, int j){
		   oggettoArray temp = a[i];
		   a[i] = a[j];
		   a[j] = temp;
		}


	//metodo che restituisce l'indice del padre
	public int f(int i) {
	    if (i != 0) {
		      if (i%2 == 0) {
		        	return ((i/2)-1);
		      } else {
		        	return (i/2);
		      }
	    } else {
	      return i;
	    }

	  }

//metodo per il confronto se oggetto1 è > di oggetto2 ritorna true altrimenti false
	public boolean maggiore(oggettoArray oggetto1, oggettoArray oggetto2){
		if (oggetto1.getCategoria() < oggetto2.getCategoria()) {
			return true;
		} else if (oggetto1.getCategoria() == oggetto2.getCategoria() && oggetto1.getStatus() < oggetto2.getStatus()){
			return true;
		} else {
			return false;
		}
	}


/*
	MAXHEAPBUILD
	Costruisce un albero seguendo i criteri del max heap a partire da coppie
	<categoria, status> date in input, l'ordine di prirità viene associato 
	in base al confronto fra le coppie
 */

	public void maxHeapBuild(char categoria, int status){

			insertHeap(categoria,status);

	}



/*
	INSERTHEAP
	Metodo per l'inserimento di un oggetto<categoria,status>, segue i criteri
	del Max Heap. Costo O(log n)
 */
	 public void insertHeap(char categoria, int status)
	  {
	    if (!isFull()) {
	      //creo un nuovo oggetto
	      heap[elementNumber]=new oggettoArray(categoria,status);
	      int i = elementNumber;
	      while (maggiore(heap[i],heap[f(i)]) == true) {
	        swap(heap, i, f(i));
	        i = f(i);
	        numSwap = numSwap + 1;

	      }
	      elementNumber = elementNumber+1;

	    } else {
	      System.out.println("L'heap è pieno, non è possibile inserire nuovi oggetti");
	    }
	  }


/*
	REMOVEMAX
	Rimuove l'elemento con priorità più elevata, ovvero quello che si trova in
	heap[0], dopo la rimozione riodina l'heap. Costo O(log n)
 */
	public void removeMax()
	  {
	    if (!isEmpty()) {

	      swap(heap, 0,elementNumber-1);
	      numSwap = numSwap + 1; //aggiorna contatore swap
	      elementNumber = elementNumber-1;
	      down(heap, 0, elementNumber);
	    } else {
	      System.out.println("Heap vuoto");
	    }
	  }



	public void down(oggettoArray[] a, int current, int end)
	  {
	    while (((2*current)+1) < end)
	      {
	        int child = (2*current)+1;
	        if (child + 1 < end && maggiore(a[child],(a[child+1])) == false )
	        {
	          child = child+1;
	        }
	        if (maggiore(a[child],(a[current]))==true)
	        {
	          swap(a, current, child);
	          current = child;
	          numSwap = numSwap + 1;//aggiorna contatore swap
	        } else
	        {
	          break;
	        }
	      }
	  }
	  

	  public char valoreRadiceCategoria(){
	  	return heap[0].getCategoria();
	  }

	  public int valoreRadiceStatus(){
	  	return heap[0].getStatus();
	  }

	  public int returnSwap(){
	  	return numSwap;
	  }
	  





	public void print()
	  {
	    System.out.println(heap[0].getStatus());
	    int i = 1;
	    do
	    {
	      for(int j = i; j <= 2*i && j < elementNumber; j++)
	      {
	        System.out.print(heap[j].getStatus() + " ");
	      }
	      System.out.println();
	      i = 2*i+1;
	    } while (i < elementNumber);
	  }


}