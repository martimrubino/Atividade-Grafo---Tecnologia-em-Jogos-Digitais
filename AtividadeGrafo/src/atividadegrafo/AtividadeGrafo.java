package atividadegrafo;

import java.io.IOException;

public class AtividadeGrafo {
    public static void main(String[] args) throws IOException {
      
        Graph g = new Graph("entrada.txt");
        
        g.mostra();
        
        // para construir meu grafo sem um arquivo de texto
        /*
        g.insereA(0,1);
        g.insereA(0,2);
        g.insereA(3,1);
        g.insereA(1,4);
        g.insereA(3,5);
        g.insereA(2,4);
        g.insereA(4,5);
        
        g.mostra();
        
        g.indeg(3);
        g.outdeg(3);
        
        g.fonte(5);
        g.sorvedouro(5);
        */
    } 
}
