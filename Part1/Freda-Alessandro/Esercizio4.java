import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.util.Random;
import java.lang.Math;

public class Esercizio4 {
	private static String fileInput1;//lettura in Input1Es4.txt
	private static String fileOutput3;//scrittura in Input1Es4.txt
	private static String fileOutput4;//scrittura in Output1Es4.txt
    private static String fileOutput5;//scrittura in Output2Es4.txt

	public static void main(String[] args) {

		if (args.length < 3) {
			System.out.println("Scrivere file di input e output come argomenti.");
		System.exit(0);
		}

		//file input
		fileInput1 = args[0];
		BufferedReader inputStream1 = null;
		FileReader reader1 = null;


		// file output
		fileOutput3 = args[0];
		PrintWriter outputStream3 = null;
		FileOutputStream writer3 = null;

		fileOutput4 = args[1];
		PrintWriter outputStream4 = null;
		FileOutputStream writer4 = null;

      	fileOutput5 = args[2];
      	PrintWriter outputStream5 = null;
      	FileOutputStream writer5 = null;

		   try {
         reader1 = new FileReader(fileInput1);
         inputStream1 = new BufferedReader(reader1);

      	} catch (FileNotFoundException e) {
         System.out.println("FileNotFoundException nello Stream Input.");
      	}

      	try{

      	writer3 = new FileOutputStream(fileOutput3, true);
         outputStream3 = new PrintWriter(writer3);

         writer4 = new FileOutputStream(fileOutput4, true);
         outputStream4 = new PrintWriter(writer4);

         writer5 = new FileOutputStream(fileOutput5, true);
         outputStream5 = new PrintWriter(writer5);
      	} catch (FileNotFoundException e) {
         System.out.println("FileNotFoundException nello Stream Output.");
      	}

/*
 * Dati da inserire nel file Input1Es4.txt, il quale verrà utilizzato per inizializzare il dizionario 
 */

               int seme = 772546;
               Random random = new Random(seme);
               //numero di chiavi da inserire nell'albero
               int j = 10;
               int n = 20-j;
               // k = numero di chiavi
               int k = random.nextInt(n)+j;//il numero di chiavi è compreso tra 10 e 20


               //valori delle chiavi
               for (int i =0;i <k ;i++ ) {
                  double potenza = Math.pow(2.0, 21.0);
                  int totale = (int) potenza;
                  int a = totale;
                  int b = random.nextInt(totale);//Valori compresi tra 0 e 2^21

                  //numero di elementi contenuti nella lista relativa alla chiave k
                  int o = 3;
                  int p = 7-o;
                  int l = random.nextInt(o)+p;//Valori compresi tra 20 e 40
                  //outputStream3.print(b+", "+l+", ");
                  for (int we=0;we<l ;we++ ) {
                     int x = random.nextInt();
                     //outputStream3.print(x+", ");
                     }
                  //outputStream3.println();
                  } //fine ciclo for valori delle chiavi


               //costruisce la matrice di adiacenza
               int[][] matrice = new int[k][k];

               for (int riga =0;riga< k ;riga++ ) {
                  for (int colonna = 0; colonna<k ;colonna++ ) {
                  	boolean prova = random.nextBoolean();
                  	if (riga == colonna){
                  		matrice[riga][colonna] = 0; //diagonale =0
                  	} else{
                  		if (prova==true) {
	                  		int a = 1;
	                  		matrice[riga][colonna] =a;
                  		} else{ //se false
	                  		int b =0;
	                  		matrice[riga][colonna] =b;
                  	}
                  	}
                  }
                }

                //stampa la matrice
                for (int i = 0; i < matrice.length; i++) {
				    for (int asd = 0; asd < matrice[i].length; asd++) {
				        System.out.print(matrice[i][asd] + " ");
				        //outputStream3.print(matrice[i][asd] + " ");
				    }
				    System.out.println();
				}

				Grafo grafo = new Grafo(matrice,k);

				//Scrive la descrizione del grado sul file Output1Es4.txt
				outputStream4.println("Numero di chiavi generate: "+ k);
				for (int i =0;i <k ;i++ ) {
                  double potenza = Math.pow(2.0, 21.0);
                  int totale = (int) potenza;
                  int a = totale;
                  int b = random.nextInt(totale);

                  //numero di elementi contenuti nella lista relativa alla chiave k
                  int o = 3;
                  int p = 7-o;
                  int l = random.nextInt(o)+p;//Valori compresi tra 20 e 40
                  outputStream4.print(b+", "+l+", ");
                  for (int we=0;we<l ;we++ ) {
                     int x = random.nextInt();
                     outputStream4.print(x+", ");
                     }
                  outputStream4.println();
                  }





                  //chiusura stream
                  try {
                     inputStream1.close();
                     outputStream3.close();
                     outputStream4.close();
                     outputStream5.close();
                  }catch (IOException ex) {
                        System.out.println("IOException nella Chiusura Streams.");
                     }


	}//main

}//fine classe