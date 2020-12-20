import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.util.Random;
import java.lang.Math;
import java.util.TreeMap;

public class Esercizio3 {

   private static String fileInput1;//lettura in Input1Es2.txt
   private static String fileInput2;//lettura in Input2Es2.txt
   private static String fileOutput3;//scrittura in Input1Es2.txt
   private static String fileOutput4;//scrittura in Output1Es2.txt
   private static String fileOutput5;//scrittura in Output2Es2.txt

   public static void main(String[] args) {

      if (args.length < 4) {
         System.out.println("Scrivere file di input e output come argomenti.");
      System.exit(0);
      }



      TreeMap<Integer,NodoAlbero> albero = new TreeMap<Integer,NodoAlbero>();



      //file input
      fileInput1 = args[0];
      BufferedReader inputStream1 = null;
      FileReader reader1 = null;

      fileInput2 = args[1];
      BufferedReader inputStream2 = null;
      FileReader reader2 = null;

      // file output
      fileOutput3 = args[0];
      PrintWriter outputStream3 = null;
      FileOutputStream writer3 = null;

      fileOutput4 = args[2];
      PrintWriter outputStream4 = null;
      FileOutputStream writer4 = null;

      fileOutput5 = args[3];
      PrintWriter outputStream5 = null;
      FileOutputStream writer5 = null;

         try {
         reader1 = new FileReader(fileInput1);
         inputStream1 = new BufferedReader(reader1);

         reader2 = new FileReader(fileInput2);
         inputStream2 = new BufferedReader(reader2);
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
 * Dati da inserire nel file Input1Es2.txt, il quale verrà utilizzato per inizializzare il dizionario 
 */
         
               int seme = 772546; 
               Random random = new Random(seme);
               //numero di chiavi da inserire nell'albero
               int j = 20;
               int n = 40-j;
               int k = random.nextInt(n)+j;//Valori compresi tra 20 e 40
               

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



/*
 * Popola il dizionario a partire dai dati presenti nel file Input1Es2.txt precedentemente scritti
 */


         try{

            String riga1 = inputStream1.readLine();
            outputStream4.println("Numero chiavi: " + k + "\tSeme dei generatori matricola: " + seme);

            while(riga1 != null){
               String[] data = riga1.split(", ");

               albero.put(Integer.parseInt(data[0]),new NodoAlbero(Integer.parseInt(data[0])) );

               outputStream4.print(Integer.parseInt(data[0])+", ");// stampa la chiave
               System.out.println("Chiave inserita");

               int nodiLista = Integer.parseInt(data[1]) + 1;

               outputStream4.print(Integer.parseInt(data[1])+", "); //il numero degli elementi nella lista

               for (int i=2;i<=nodiLista;i++ ) {//legge tutti gli elementi della lista e li inserisce nella lista del nodo corrispondente
                  outputStream4.print(Integer.parseInt(data[i])+", ");
                  albero.get(Integer.parseInt(data[0])).aggiungiCodice(Integer.parseInt(data[i]) );
               }
               outputStream4.println();
               riga1 = inputStream1.readLine(); //legge una nuova riga

            }//fine ciclo while


         } catch(IOException e){
               System.out.println("Errore lettura "+ fileInput1);
            }



/*
 Operazioni da eserguire sull'albero, le operazioni vengono lette dal file Input2Es2.txt,
 nel file Output2Es2.txt in ogni riga è contenuto il risultato dell'operazione indicata nella
 stessa riga del file Input2Es2.txt
 */


             try{

               String riga2 = inputStream2.readLine();

               while(riga2 != null){
                  String[] data2 = riga2.split(", ");

                  switch(data2[0]){
                     case "I": 
                        albero.put(Integer.parseInt(data2[1]), new NodoAlbero(Integer.parseInt(data2[1])) );

                        System.out.println("chiave inserita");
                        outputStream5.println("Inserimento riuscito");

                        int nodiLista = Integer.parseInt(data2[2]) + 2;
                        for (int i=3;i<=nodiLista;i++ ) {//legge tutti gli elementi della lista e li inserisce nella lista del nodo corrispondente
                           albero.get(Integer.parseInt(data2[1])).aggiungiCodice(Integer.parseInt(data2[i]) );

                        }
                        break;


                     case "R":
                        if ( albero.get(Integer.parseInt(data2[1])) != null) {
                           outputStream5.print("Chiave: "+Integer.parseInt(data2[1]));
                           outputStream5.println("\tCodici lista: "+albero.get(Integer.parseInt(data2[1])).getCodici());
                        } else{
                           outputStream5.println("Chiave non trovata");
                        }
                        break;

   
                     case "E":
                        albero.remove(Integer.parseInt(data2[1]));
                        System.out.println("nodo eliminato");
                        outputStream5.println("Cancellazione riuscita");
                        break;

                     case "RR":
                           if ( albero.get(Integer.parseInt(data2[1]) ) != null) {
                              boolean a = albero.get(Integer.parseInt(data2[1])).searchCodice(Integer.parseInt(data2[2]));
                              if(a == true){
                                 outputStream5.println("Elemento trovato");
                              } else{
                                 outputStream5.println("Elemento non trovato");
                              }
                        } else{
                           outputStream5.println("Chiave non trovata");
                        }
                        break;

                  }//fine switch

                     riga2 = inputStream2.readLine(); //legge una nuova riga

               }//fine ciclo while


            } catch(IOException e){
                  System.out.println("Errore lettura. ");
               }





                  //chiusura stream
                  try {
                     inputStream1.close();
                     inputStream2.close();
                     outputStream3.close();
                     outputStream4.close();
                     outputStream5.close();
                  }catch (IOException ex) {
                        System.out.println("IOException nella Chiusura Streams.");
                     }



         }//main
   }
   //classe esercizio2