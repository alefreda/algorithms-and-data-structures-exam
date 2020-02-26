/*
    Alessandro Freda - 772546 - alessandro.freda2@studio.unibo.it

    Per l'implementazione dell'algoritmo ho deciso di utilizzare per quest'esercizio la programmazione dinamica, inizialmente ho ricondotto
    il problema dell'esercizio al problema del resto visto a lezione, tuttavia dopo aver scambiato delle mail di chiarimenti con il prof Bedogni 
    ho strutturato l'algoritmo in modo che lo stesso contenitore potesse riempire più volte il serbatoio.
    Tale implementazione richiede per l'esecuzione dell'algoritmo un tempo O(n^2) più precisamente C * N dove C è la capacità massima del serbatoio e N 
    il numero di contenitori disponibili per il rimpimento del serbatoio, inoltre non ho utilizato una matrice per la programmazione dinamica ma un array.
  
 */


import java.io.*;
import java.util.Scanner;
import java.util.Locale;


public class Esercizio2 {

    int[] n; // array che contiene la capacità degli N contenitori
    int N;  // numero di contenitori disponibili per il rimpimento del serbatoio
    int C;  // capacità massima del serbatoio

    int[] array; //array per la programmazione dinamica

    public Esercizio2( String fileInput )
    {
        Locale.setDefault(Locale.US);
        try {
            Scanner scanner = new Scanner( new FileReader( fileInput ) );
            //inizializza le varibili con i dati ricevuti in input
            C = scanner.nextInt();
            N = scanner.nextInt();
            n = new int[N];
            for ( int i=0; i<N; i++ ) {
                n[i] = scanner.nextInt();
            }

        } catch ( IOException ex ) {
            System.err.println(ex);
            System.exit(1);
        }
    }



    private int riempiSerbatoio( )
    {
        int i, j;
        //crea array di C + 1 elementi
        array = new int[C+1];

        array[0] = 0;
        //popola inizialmente l'array del valore di C + 1 per i successivi confronti
        for (i = 1; i < C+1 ;i ++ ) {
            array[i] = C + 1;
            
        }
    
        for (i = 1; i <= C ; i++ ) {

            for (j = 0; j < N ;j++ ) {
                //se la capacità del contenitore n è minore della capacita attuale allora sostituisce il valore dell'array in i come 
                //il minimo fra il valore attuale e il valore dell'array in  array[i - n[j]]+1
                if (n[j] <= i) {
                    array[i] = Math.min(array[i], array[i - n[j]]+1);
                }
            }
        }

        //se l'ultimo elemento dell'array è maggiore della capacità del serbatoio allora non è possibile
        //riempire esattamente il serbatoio, quindi ritorna -1
        if (array[C] > C) {
            return -1;
        } else{
            return array[C];
        }
        
    }    

    public static void main( String[] args )
    {
        if ( args.length != 2 ) {
            System.err.println("Inserire il file di input e di output!");
            System.exit(1);
        } else {

            Esercizio2 problema = new Esercizio2(args[0]);
            Locale.setDefault(Locale.US);
        try {

            PrintWriter outputStream = new PrintWriter(new FileOutputStream(args[1]));

            outputStream.println(problema.riempiSerbatoio());;
            outputStream.close();
           
        } catch ( FileNotFoundException e ) {
            System.out.println("Errore nell'apertura del file: "+ args[1]);
            System.exit(0);


        }
    }
}//main

}