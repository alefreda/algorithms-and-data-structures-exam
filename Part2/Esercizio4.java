/*
    Alessandro Freda - 772546 - alessandro.freda2@studio.unibo.it

    Per l'ercizio4 l'idea iniziale era quella di calcolare tutti i possibili percorsi da un nodo all'altro e poi confrontare le tasse dei vari nodi dei path
    per trovare il percorso migliore, dopo aver constatato che tale algoritmo avrebbe avuto un costo esponenziale dunque molto poco conveniente ho deciso di 
    trovare una soluzione che utilizzasse la programmazione dinamica. l'idea è quella di memorizzare in matrice il numero minore di tassa in quell'ora precisa
    della matrice, in questo modo all'ora h si trova il valore minimo di tassa. Per la ricostruzione dei padri ho utilizzato una matrice che memorizza per ogni
    passo il padre del nodo. Il costo dell'algoritmo è O(n^2*h).

 */



import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

public class Esercizio4{

	//classe che definisce il nodo del grafo
	private static class Nodo {
		//lista di archi adiacenti
		ArrayList<Arco> adiacenti = new ArrayList<Arco>();
        int id; //id di interi
        int tassa;

        Nodo(int id, int tassa) {
            this.id = id;
            this.tassa = tassa;
        }
    } //end classe nodo

    private static class Arco {
        Nodo s; //nodo partenza
        Nodo e; //nodo destinazione
        int ore; 

        Arco(Nodo s, Nodo e, int ore) {
            this.s = s;
            this.e= e;
            this.ore = ore;
        }

        Arco(Nodo s, Nodo e) {
            this.s = s;
            this.e= e;
            this.ore = 0;
        }

    } //end classe arco
    


    /*
    Metodo che memorizza in matrice 
     */
    
    private static ArrayList<Integer> calcolaPath(int partenza, int destinazione, int numeroOre, Nodo[] nodi){
    	ArrayList<Integer> percorso = new ArrayList<Integer>();
    	int[][] matricePD = new int[nodi.length][numeroOre+1]; 
        int[][] padri = new int[nodi.length][numeroOre]; //matrice che memorizza i padri

        //matrice a infinito
        for (int i = 0; i<nodi.length;i++ ) {
            for (int j = 0;j <= numeroOre ;j++ ) {
                matricePD[i][j] = Integer.MAX_VALUE; 
            }
        }

    	//inizializzo la matrice all'ora 0 
    	for (int x = 0; x < nodi.length ;x++ )
            matricePD[x][0] = 0;

    	for (int i=1; i<= numeroOre ;i++ ) {

    		for (int j = 0;j < nodi.length ;j++ ) {
    			 
    			 	//verifico tutti i nodi adiacenti 
    			 	for(Arco arco : nodi[j].adiacenti){
                        
                        if (i == 1) {
                            matricePD[j][i] = arco.s.tassa;
                        }

                        if (i+arco.ore < numeroOre) {
                            matricePD[j][i]= Math.min(matricePD[arco.e.id][i+arco.ore]  ,
                            matricePD[j][i-1] + nodi[j].tassa );
                        }
    			 		
    			 	}//end for nodi adiacenti   			 
    		}//end for
    	} //end for principale

            for (int i = 0; i < nodi.length; i++) {
                for (int j = 0; j < numeroOre; j++) {
                    System.out.print(matricePD[i][j] + " ");
                }
            System.out.println();
            }

    	/*
    	ripercorro il path a partire da matricePD[nodi.length][numeroOre]
    	e stampo matricePD[nodi.length][numeroOre].tassaMinima
    	 */
        return null;
    }//end metodo


    public static void main(String[] args) {
        if(args.length < 2) {
            System.err.println("Inserire file input e file output!");
            System.exit(1);
        }

        Locale.setDefault(Locale.US);
        try {
            Scanner scanner = new Scanner( new FileReader( args[0] ) );
            int numeroMagazzini = scanner.nextInt();
            int numeroArchi = scanner.nextInt();
            int magazzinoPartenza = scanner.nextInt();
            int magazzinoArrivo = scanner.nextInt();
            int ore = scanner.nextInt();

            Nodo[] nodi = new Nodo[numeroMagazzini]; //array di nodi

               int tassa;
               for (int i = 0; i < numeroMagazzini ; i++ ) {
                 tassa = scanner.nextInt();
                   nodi[i] = new Nodo(i,tassa);
               }


            int nodoA, nodoB, oreArco;
            for (int j = 0;j < numeroArchi ;j++ ) {
                nodoA = scanner.nextInt();
                nodoB = scanner.nextInt();
                oreArco = scanner.nextInt();
                nodi[nodoA].adiacenti.add(new Arco(nodi[nodoA], nodi[nodoB], oreArco));

            }

        } catch ( IOException ex ) {
            System.err.println(ex);
            System.exit(1);
        }

       

       } //end main

    
}//fine classe principale