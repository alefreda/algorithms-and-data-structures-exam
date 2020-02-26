import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.*;

public class Grafo{


    private int numeroVertici; // numero di vertici presenti nel grafo
    public int[][] matriceAdiacenze;


    public Grafo(int[][] matrice, int numeroVerticiInput){

      matrice = new int[numeroVerticiInput][numeroVerticiInput];

    }


    //Aggiunge e crea un nuovo vertice nel grafo
    public Vertice addVertice(int chiave){

      Vertice vertice = new Vertice(chiave);

      return vertice;
    }



    public void DFS(int start) {
      Stack<Integer> q = new Stack<Integer>();
      boolean [] visited = new boolean[matriceAdiacenze.length];

      q.add(start);
      System.out.print(start + " ");
      visited[start] = true;

      while (!q.empty())
      {
        int node = q.pop();
        //System.out.print(node + " ");

        for (int i = 0; i < matriceAdiacenze.length; i++)
          if ((!visited[i])) {
            visited[i] = true;
            q.push(i);
            System.out.print(i + " ");
          }
      }
  }


	/*
	 * Classe che rappresenta un vertice
	 */


	public class Vertice{
		int chiave;
		LinkedList<Integer> lista; //lista di codici

		Vertice(int chiave){
			this.chiave = chiave;
			lista = new LinkedList<>();
		}

		public void aggiungiCodice(int codice) {
        lista.add(codice);
    	}

    	public LinkedList<Integer> getCodici() {
            return lista;
   		}

	}//fine classe Vertice



  /*
    Classe che rappresenta l'arco che collega due vertici
   */
  private class Arco{
    Vertice Vertice1;
    Vertice Vertice2;

    Arco(Vertice Vertice1, Vertice Vertice2){
      this.Vertice1 = Vertice1;
      this.Vertice2 = Vertice2;
    }
    @Override
    public String toString(){
      return "Arco tra: "+ Vertice1+ " e "+ Vertice2;
    }

  }//fine classe Arco

}//fine classe grafo

