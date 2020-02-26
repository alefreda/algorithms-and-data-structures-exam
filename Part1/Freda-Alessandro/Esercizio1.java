import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;

public class Esercizio1 {

	private static String fileInput1;
	private static String fileInput2;
	private static String fileOutput;

	public static void main(String[] args) {

		final int size = 75;
		if (args.length < 2) {
			System.out.println("Scrivere file di input e output come argomenti.");
		System.exit(0);
		}

		MaxHeap maxheap = new MaxHeap(size); //inizializzo il MaxHeap


		//file input1
		fileInput1 = args[0];
		BufferedReader inputStream1 = null;
		FileReader reader1 = null;

		//file input2
		fileInput2 = args[1];
		BufferedReader inputStream2 = null;
		FileReader reader2 = null;

		//file output
		fileOutput = args[2];
		PrintWriter outputStream = null;
		FileOutputStream writer = null;

		try {
         reader1 = new FileReader(fileInput1);
         inputStream1 = new BufferedReader(reader1);

         reader2 = new FileReader(fileInput2);
         inputStream2 = new BufferedReader(reader2);

      	} catch (FileNotFoundException e) {
         System.out.println("FileNotFoundException nello Stream Input.");
      	}

      	try{

      	writer = new FileOutputStream(fileOutput, true);
        outputStream = new PrintWriter(writer);

      	} catch (FileNotFoundException e) {
        	System.out.println("FileNotFoundException nello Stream Output.");
      	}
      	//popola heap
      	try{

	      			String riga = inputStream1.readLine();
		      		//leggo tutte le righe
		      		while (riga != null) {
		      			String[] data = riga.split("      ");
		      			maxheap.maxHeapBuild(data[0].charAt(0), Integer.parseInt(data[1])); //popola l'abero secondo i criteri del max heap
		      			riga = inputStream1.readLine(); //legge una nuova riga
		      		}
		      		System.out.println("Heap popolato con successo!");

	      	}catch(IOException e){
	      		System.out.println("Errore lettura "+ fileInput1);
	      	}

	    try {

	      		String riga2 = inputStream2.readLine();

	      		while (riga2 != null) {
	      			String riga22 = riga2.replaceAll(" {2,}", " ");
	      			String[] data2 = riga22.split(" ");
	      			maxheap.azzeraContatoreSwap();
	      			switch (data2[0]) {
	      				case "I":
	      				outputStream.print("InsertHeap: <"+ data2[1]+ ", "+ data2[2]+ "> ");
	      				maxheap.insertHeap(data2[1].charAt(0), Integer.parseInt(data2[2]));
	      				outputStream.println("\tValori radice: <"+maxheap.valoreRadiceCategoria()+", "+
	      					maxheap.valoreRadiceStatus()+">" +" \tNumero di swap: " + maxheap.returnSwap());

	      				break;

	      				case "R":

	      				outputStream.print("RemoveHeap: <"+maxheap.valoreRadiceCategoria()+", "+
	      					maxheap.valoreRadiceStatus()+"> ");
	      				maxheap.removeMax();
	      				outputStream.println("\tValore radice: <"+maxheap.valoreRadiceCategoria()+", "+
	      					maxheap.valoreRadiceStatus()+">"+" \tNumero di swap: " + maxheap.returnSwap());

	      			}//fine switch
	      			riga2 = inputStream2.readLine(); //legge una nuova riga
	      			maxheap.azzeraContatoreSwap();
	      		}//fine while

      	} catch(IOException e){
      		System.out.println("Errore lettura "+ fileInput1);
      	}

      	try {
      		inputStream1.close();
      		inputStream2.close();
      		outputStream.close();

      	} catch (IOException ex){
      		System.out.println("Eccezione nella chiusura dello stream");
      	}





	}//fine main

}//fine classe