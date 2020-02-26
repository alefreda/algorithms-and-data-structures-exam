/*
	Alessandro Freda - 772546 - alessandro.freda2@studio.unibo.it

	Per l'implementazione dell'algoritmo ho deciso di utilizzare per il calcolo dei cammini minimi l'algoritmo Floyd e Warshall che memorizza tutti i cammini
	minimi in una matrice al costo di O(n^3). Dato che il grafo contiene pesi negativi avrei anche potuto utilizzare il Bellman-Ford che ha costo nettamente inferiore
	(O(n*nm)) tuttavia avrei dovuto applicarlo ripetutamente (dato che ci troviamo in un caso di sorgente multipla). Tale scelta sarebbe stata controproducente
	in quanto nel caso di un grafo denso il costo può arrivare ad un O(n^4), per tale motivo la scelta è ricaduta sul Floyd e Warshall. Lo spazio utilizzato è quello della matrice. 
	Il codice è ampiamente commentato.

 */

import java.io.*;
import java.util.Scanner;
import java.util.Locale;

public class Esercizio3{

	int INFINITO = 99999; // non è possibile utilizzare Integer.MAX_VALUE in quanto tale valore deve essere modificato

	int N; //numero di nodi
	int M; //numero di archi
	int X; //possibili nodi di uscita dal formicaio di partenza
	int Y; //possibili nodi di entrata nel formicaio di destinazione

	int[][] next; //matrice di successione utile per ricostruire i camminimi minimi
	int[][] matrice; //matrice nella quale vengono memorizzati i cammini minimi fra tutti i nodi

	String file_Output;


	//costruttore
	public Esercizio3(String fileInput,String fileOutput){
		

		file_Output = fileOutput;
		Locale.setDefault(Locale.US);
        try {
            Scanner scanner = new Scanner( new FileReader( fileInput ) );
            N = scanner.nextInt();
            M = scanner.nextInt();
            X = scanner.nextInt();
            Y = scanner.nextInt();
           
            //popolo le matrici
			next = new int[N][N];
	        matrice = new int[N][N];
			for (int i = 0;i < N;i++ ) {
				for (int j = 0;j < N  ;j++ ) {
					if (i == j) {
						matrice[i][j] = 0;
						next[i][j] = -1;
					} else {
						matrice[i][j] = INFINITO; 
						next[i][j] = -1;
					}
				}
			} //endfor

            int a= 0; 
            int b = 0;
            int peso = 0;
            //modifica matrice in base ai dati in input per ogni riga            
            for (int i = 0; i <M ;i++ ) {
            	a = scanner.nextInt();
            	b = scanner.nextInt();
            	peso = scanner.nextInt();

            	matrice[a][b] = peso;
            	next[a][b] = b;

            }

        } catch ( IOException ex ) {
            System.err.println(ex);
            System.exit(1);
        }
	} // fine costruttore



	public void algoritmo(){
		int i, j, k, x , y;
		int percorsoMinimo = INFINITO;
		int nodoIniziale = 0;
		int nodoFinale = 0;

		//modifica la matrice next utilizzata per ricostruire i cammini
		for (i =0 ;i <N ;i++ ) {
			for (j = 0;j < N ;j++ ) {
				if (next[i][j]!=INFINITO && next[i][j] !=-1) {
					next[i][j] = j;
				}
			}
		}
	
		/*
			Ciclo per k vincolo, successivamente per tutte le coppie di nodi controlla 
			se passando per k ottiene un miglioramento, in questo caso
			modifica il valore della matrice inserendo il miglioramento verificato 
		 */

		for (k = 0; k < N; k++)
        {
            for (i = 0; i < N; i++)
            {
                for (j = 0; j < N; j++)
                {
                   
                    if (matrice[i][k] + matrice[k][j] < matrice[i][j]){
                        matrice[i][j] = matrice[i][k] + matrice[k][j];
                    	next[i][j] = next[i][k];
                    }
                }
            }
        } 

        //controllo per cicli negativi
        for(int r=0; r<N; r++) {
	       for(int c=0; c<N; c++){
	       		//controlla cicli negativi solo per i vertici di uscita
	       		//dal formicaio di partenza ed entrata da quello di destinazione
	       		if (r==c && matrice[r][c] < 0 && (r <= X || r >= Y)) 
	       			cicliNegativi();
	       			//System.exit(0);
	       		
	       }     
	    } //endfor controllo cicli negativi


       /*Calcola il percoso più breve tra tutte le possibili
       	 coppie X-Y di vertici(uscita-entrata). Memorizza in nodoIniziale il nodo
       	 di partenza del cammino minimo e in nodoFinale il nodo di destinazione dello stesso
       	 percorso */
		for (x =0;x <= X ;x++ ) {
			for (y = Y;y < N   ;y++ ) {
				if (matrice[x][y] < percorsoMinimo) {
					percorsoMinimo = matrice[x][y];
					nodoIniziale = x;
					nodoFinale = y;
				}
			}
		}
	
		stampaPercorso(nodoIniziale,nodoFinale,next);
	}

	private void stampaPercorso(int nodoIniziale, int nodoFinale, int[][] next){
		try {

        	PrintWriter outputStream = new PrintWriter(new FileOutputStream(file_Output));
        	Locale.setDefault(Locale.US);
        	outputStream.println(nodoIniziale);
				while( nodoIniziale != nodoFinale){
					nodoIniziale = next[nodoIniziale][nodoFinale];
					outputStream.println(nodoIniziale);
				}
	        outputStream.close();
           
        } catch ( FileNotFoundException e ) {
            System.out.println("Errore nell'apertura del file: "+ file_Output);
            System.exit(0);
        } 
	}

	//stampa -1 in output nel caso in cui sia presente un ciclo negativo per i nodi entranti nella destinazione
	private void cicliNegativi(){
		try {
        	PrintWriter outputStream = new PrintWriter(new FileOutputStream(file_Output));
        	Locale.setDefault(Locale.US);
        	outputStream.println(-1);	
	        outputStream.close();
	        System.exit(0);
        } catch ( FileNotFoundException e ) {
            System.out.println("Errore nell'apertura del file: "+ file_Output);
            System.exit(0);
        }
	}




	public static void main(String[] args) {
		if ( args.length != 2 ) {
            System.err.println("Scrivere il nome di un file di input e di output");
            System.exit(1);
        } else {
            Esercizio3 formichina = new Esercizio3(args[0],args[1]);
            formichina.algoritmo();
        }
	}
}