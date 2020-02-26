/*
	Alessandro Freda - 772546 - alessandro.freda2@studio.unibo.it

	Per l'implementazione dell'algoritmo è necessario a mio parere confrontare ogni minerale con ogni scatola disponibile in modo da ottenere la migliore soluzione.
	In questo modo il peso sprecato sarà sempre il minimo in quanto l'algoritmo implementato associa il minerale 
	alla scatola in modo tale che la differenza tra i due pesi sia sempre minima o nulla(nel caso in cui i due pesi
	siano uguali). Il costo computazionale dell'algoritmo è O(n^2) più precisamente M * S dove M è il numero di minerali e S il numero di scatole.
	L'ordine degli accoppiamenti è diverso dell'output fornito dal prof.

 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Esercizio1{

	int S; //numero di scatole
	int M; //numero di minerali
	
	int[] c; //array contente i pesi degli scatoli
	int[] p; // array contenente i pesi dei minerali
	
	ArrayList<Integer> associazione; //arraylist che memorizza le associazioni tra minerale e scatola
	String file_Output;


	//costruttore
	public Esercizio1(String fileInput,String fileOutput){
		file_Output = fileOutput;
		Locale.setDefault(Locale.US);
        try {
            Scanner scanner = new Scanner( new FileReader( fileInput ) );
            S = scanner.nextInt();
            M = scanner.nextInt();
            c = new int[S];
            //popola array contente i pesi degli scatoli
            for ( int i=0; i<S; i++ ) {
                c[i] = scanner.nextInt();
            }
            p = new int[M];
            // popola array contenente i pesi dei minerali
            for (int j = 0;j<M ; j ++ ) {
            	p[j] = scanner.nextInt();
            }
        } catch ( IOException ex ) {
            System.err.println(ex);
            System.exit(1);
        }
	}



	public void associa(){
		int i,j;
		//alla fine dell'algoritmo associazione conterrà sequenzialmente gli indici 
		//rispettivamente l'indice della scatola e del minerale associato
		associazione = new ArrayList<Integer>();

		int indiceOggetto = 0;
		int indiceScatola = 0;
		// temp è uguale al più grande valore di int in questo modo
		// nel primo confronto c[j] risulterà sempre minore di temp
		int temp = Integer.MAX_VALUE; 

		for (i =0;i<M;i++ ) {
			for (j=0;j<S ;j++ ) {

				//caso in cui il peso del minerale sia uguale
				//al peso della scatola quindi esce dal ciclo sui pesi delle scatole
				if (p[i] == c[j]) {
					indiceOggetto = i;
					indiceScatola = j;
					break; // esci dal ciclo annidato 
				} 

				//caso in cui il peso del minerale sia minore del peso della scatola
				//e il peso della scatola sia minore della variabile temporanea
				if (p[i] < c[j] && c[j] < temp ) {
					temp = c[j]; //setto il valore c[j] nella variabile temp per i prossimi cicli
					indiceOggetto = i;
					indiceScatola = j;
				}
			} //end for ciclo scatole

			temp = Integer.MAX_VALUE;
			c[indiceScatola] = 1; // -1 in quanto la scatola è gia stata utilizzata
			//aggiungo gli indici dell'associazione scatola-minerale in un arraylist
			associazione.add(indiceScatola+1); 
			associazione.add(indiceOggetto+1);

		} //end cicli annidati
		
		stampaSoluzione();
	}


		public void stampaSoluzione( ){
        try {

        	PrintWriter outputStream = new PrintWriter(new FileOutputStream(file_Output));
        	Locale.setDefault(Locale.US);
        	for (int a =0; a < associazione.size() ;a++ ) {
        		if ((a%2)==0) {
        			outputStream.print(associazione.get(a));
        		} else {
					outputStream.println(" "+associazione.get(a));
        		}
	        }
	        outputStream.close();
           
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
            Esercizio1 organizatoreOttimizzatore = new Esercizio1(args[0],args[1]);
            organizatoreOttimizzatore.associa();
        }

    }//main


}