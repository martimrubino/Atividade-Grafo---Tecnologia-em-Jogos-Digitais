package atividadegrafo;

//ATIVIDADE 2 - GRAFO

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//MARTIM RUBINO, EDUARDO SALVATORE

public class Graph {
    private int V; // quantidade vértices do grafo 
    private int A; // quantidade de arestas do grafo
    private int adj[][]; // matriz de adjacencia
    private int visit []; // vetor para verificacao de visitados durante a checagem de caminhos

    // inicializa os atributos da classe e cria a 
    // matriz de adjacência para V vértices
    public Graph( int V ){
        this.V = V;
        this.A = 0;
        this.adj = new int[this.V][this.V];
        // definindo o tamanho do meu vetor para a quantidade de vertices
        this.visit = new int [this.V];
    }
    
    // construtor para um arquivo de texto importado
    // o construtor recebe como parametro um arquivo de texto com as informacoes do grafo
    public Graph(String nomeArquivo) throws FileNotFoundException, IOException{
        // criando uma variavel que le o arquivo de texto
        BufferedReader entrada = new BufferedReader( new FileReader(nomeArquivo));
        String linha;
        linha = entrada.readLine();
        // convertendo minha primeira linha do arquivo em int,
        // assim utilizando a mesma para definir o numero de vertices contidos no grafo
        this.V = Integer.parseInt(linha);
        // definindo o numero inicial de arestas para zero
        this.A = 0;
        // definindo o tamanho da matriz que ira representar meu grafo
        this.adj = new int[this.V][this.V];
        
        // definindo o tamanho do meu vetor para a quantidade de vertices
        this.visit = new int [this.V];
        
        while((linha = entrada.readLine())!=null){
            // criando um vetor de string para armazenar a separacao da minha linha lida
            String vetor[] = linha.split(" ");
            // convertendo as separacoes da minha linha lida em variaveis inteiras
            int a = Integer.parseInt(vetor[0]);
            int b = Integer.parseInt(vetor[1]);
            // chamando a funcao que cria um novo vertice
            this.insereA(a, b);
        }
        
        // fecha arquivo de entrada
        entrada.close(); 
    }
    
    //A funcao insere um novo vertice ao grafo.
    //A mesma recebe como parametro dois valores, os quais sao usados para definir a posicao do vertice desejado,
    //inserindo 1 na posicao
    public void insereA(int v, int w){
        if(adj[v][w]==0){
            this.adj[v][w]=1;
            this.A++;
        }
    }
    
    //A funcao remove um vertice existente no grafo.
    //A mesma recebe como parametro dois valores, os quais sao usados para definir a posicao do vertice a ser removido,
    //inserindo 0 na posicao
    public void removeA(int v, int w){
        if(adj[v][w]== 1){
            this.adj[v][w]=0;
            this.A--;
        }
    }
    
    //A funcao mostra todos os vertices existentes e seus respectivos vertices adjascentes.
    public void mostra(){
        //Inserindo uma divisao no console, evitar a confusao com outras funcoes
        System.out.println("****************************************************************");
        for(int v=0;v<this.V;v++){
            System.out.print("v"+v+":");
            for(int w=0;w<this.V;w++){
                if(this.adj[v][w]==1)
                    System.out.print(w+" ");    
            }
            System.out.println();
        }   
    }
    
    //FUNCAO QUE CALCULA O GRAU DE ENTRADA DE UM VERTICE
    //Minha funcao vai receber um vertice "X" em especifico.
    //Desse modo a mesma ira posicionar esse vertice em relacao a altura da matriz,
    //assim verificando quantos vertices ocupados na horizontal possuo em relacao a este mesmo vertice "X"
    public void indeg(int X){
            //O "N" sera usado para calcular o numero de entradas.
            //Para cada casa na horizontal ocupada, +1 eh somado a variavel 
            int N = 0;
            //Neste caso, o "W" sera usado para verificar a horizontal da matriz
            for(int i=0; i<this.V; i++){
                if(this.adj[i][X]==1){
                    N++;
            }
        }
        //Assim, imprimo o resultado final de "N", ou seja, o grau de entrada.
        System.out.println("****************************************************************");
        System.out.println("O grau de entrada em V:"+X+"= "+N);
    }
    
    //FUNCAO QUE CALCULA O GRAU DE SAIDA DE UM VERTICE
    //Minha funcao vai receber como parametro um vertice "X".
    //A mesma ira posicionar esse vertice em relacao a horizontal da matriz,
    //desse modo, a mesma verifica os vertices ocupados na altura da matriz em relacao a "X"
    public void outdeg(int X){
        //O "N" sera usado para calcular o numero de saidas.
        //Para cada casa na altura ocupada, +1 eh somado a variavel 
        int N = 0;
        //Neste caso, o "W" sera usado para verificar a altura da matriz
        for(int i=0; i<this.V; i++){
            if(this.adj[X][i]==1){
                N++;
            }
        }
        //Assim, imprimo o resultado final de "N", ou seja, o grau de entrada.
        System.out.println("****************************************************************");
        System.out.println("O grau de saida em V:"+X+"= "+N);
    }
    
    //Esta funcao vai verificar se um vertice especificado "X" eh Fonte ou nao
    public boolean fonte(int X){
        //A funcao ira verificar a vertical da matriz em relacao ao vertice especificado
        for(int i=0; i<this.V; i++){
            if(this.adj[i][X]==1){
                System.out.println("Nao eh fonte");
                return false;
            }
        }
        System.out.println("Eh fonte");
        return true;
    }
    
    //Esta funcao vai verificar se um vertice especificado "X" eh Sorvedouro ou nao
    public boolean sorvedouro(int X){
        //A funcao ira verificar a horizontal da matriz em relacao ao vertice especificado
        for(int i=0; i<this.V; i++){
            if(this.adj[X][i]==1){
                System.out.println("Nao eh sorvedouro");
                return false;
            }
        }
        System.out.println("Eh sorvedouro");
        return true;
    }
    
    // minha funcao recebe dois parametros: o vertice desejado como ponto de partida v
    public void verCaminho(int v){
        // marcando o vertice como visitado:
        this.visit[v] = 1;
        System.out.println(v);
        for(int i=0; i<this.V; i++){
            if(visit[v] == 0 && this.adj[v][i] == 1){
                verCaminho(i);
            }
        }
    }
    
    // a funcao vai checar o meu vetor de visitados, apos a mesma fazer a checagem de caminhos,
    // para ver se ha um caminho entre dois vertices especificados
    public void visitados(int v, int t){
        // chamando a checagem de caminhos
        verCaminho(v);
        if(visit[t]==0){
            System.out.println("Nao ha caminho");
        }
    }
    
}
