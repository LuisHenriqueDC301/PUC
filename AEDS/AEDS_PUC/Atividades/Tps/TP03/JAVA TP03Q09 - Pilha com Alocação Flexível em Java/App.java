//imports

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

//Classe Principal


public class App {

    //main
    public static void main(String[] args) throws Exception {
        @SuppressWarnings("deprecation")
        Locale locale = new Locale("pt", "BR");
        Locale.setDefault(locale);
        
        // Scanner fora do método para evitar múltiplas criações
        Scanner sc = new Scanner(System.in);

        // Cria a instância de GerenciarPokemon que lê o arquivo
        GerenciarPokemon gerenciarPokemon = new GerenciarPokemon();
        gerenciarPokemon.lerArquivo();
        ArrayList<Pokemon> lista_pokemon = gerenciarPokemon.getPokemons();

        //Programa Principal
        Pilha pilha = new Pilha();
        String palavra = sc.nextLine();
        while(!palavra.equals("FIM")){
            int x = Integer.parseInt(palavra);
            pilha.push(lista_pokemon.get(x-1));
            palavra = sc.nextLine();
        }
        
        int n = sc.nextInt();
        for(int i = 0; i < n; i++){
            String operacao = sc.next();
            if(operacao.equals("I")){
                int number = sc.nextInt();
                pilha.push(lista_pokemon.get(number-1));
            }
            if(operacao.equals("R")){
                System.out.println("(R) "+pilha.pop().getName());
            }
        }
        pilha.imprimir();
        sc.close(); // Fecha o Scanner ao final
    }
}
class Celula{
    public Pokemon elemento;
    public Celula prox;

    Celula(){
        elemento = null;
        prox = null;
    }
    Celula(Pokemon elemento){
        this.elemento = elemento;
        prox = null;
    }
} 
class Pilha{
    public Celula inicio;
    public Celula ultimo;

    Pilha(){
        inicio = null;
    }

    void push(Pokemon ele){
        Celula tmp = new Celula(ele);
        if(inicio == null){
            inicio = tmp;
            tmp = null;
        }else{
            Celula i = inicio;
            for(;i.prox != null; i = i.prox);
            i.prox = tmp;
            tmp.prox = null;
        }
    }

    Pokemon pop(){
        if (inicio == null) {
            return null; // Pilha vazia
        }

        Celula atual = inicio;
        Celula anterior = null;

        // Percorre até o último nó
        while (atual.prox != null) {
            anterior = atual;
            atual = atual.prox;
        }

        // Guarda o elemento a ser retornado
        Pokemon elementoRemovido = atual.elemento;

        if (anterior == null) {
            // Caso especial: havia apenas um elemento na pilha
            inicio = null;
        } else {
            // Desconecta o último elemento
            anterior.prox = null;
        }

        return elementoRemovido;
    }

    void imprimir(){
        int j = 0;
        for(Celula i = inicio; i != null; i = i.prox, j++){
            System.out.print("["+j+"] ");
            i.elemento.imprimir();
        }

    }

}


class GerenciarPokemon {

    private ArrayList<Pokemon> lista_pokemon;

    // Construtor que já inicializa a leitura do arquivo
    public GerenciarPokemon() throws IOException, ParseException {
        this.lista_pokemon = lerArquivo();
    }

    // Método para ler o arquivo CSV e retornar a lista de pokemons
    public ArrayList<Pokemon> lerArquivo() throws IOException, ParseException {
        String path = "/tmp/pokemon.csv"; // Caminho do arquivo
        BufferedReader br = new BufferedReader(new FileReader(path));
        
        // Ignora a primeira linha (se for um cabeçalho)
        br.readLine();
        
        // Criando uma lista de todos os pokemons
        ArrayList<Pokemon> lista_pokemon = new ArrayList<>();
        String line;
        
        // Lê a primeira linha de dados
        line = br.readLine();
        if (line != null) {
            Pokemon pokemon = new Pokemon();
            pokemon.ler(line); // Assume-se que o método `ler` está implementado em `Pokemon`
            lista_pokemon.add(pokemon);
        }
        
        // Lê as linhas subsequentes
        while ((line = br.readLine()) != null) {
            Pokemon pokemon = new Pokemon();
            pokemon.ler(line); // Pode lançar ParseException
            lista_pokemon.add(pokemon);
        }
        
        br.close(); // Fecha o BufferedReader
        return lista_pokemon;
    }

    // Método para retornar a lista de pokemons
    public ArrayList<Pokemon> getPokemons() {
        return lista_pokemon;
    }
}
//Pokemon
class Pokemon {
    // Atributos
    int id;
    int generation;
    String name;
    String description;
    ArrayList<String> types;
    ArrayList<String> abilities;
    double weight;
    double height;
    int captureRate;
    boolean isLegendary;
    Date captureDate;

    // Construtores
    Pokemon() {
        id = 0;
        generation = 0;
        name = "";
        description = "";
        types = new ArrayList<>();
        abilities = new ArrayList<>();
        weight = 0.0;
        height = 0.0;
        captureRate = 0;
        isLegendary = false;
        captureDate = null;
    }

    Pokemon(int id, int generation, String name, String description, ArrayList<String> types, ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary, Date captureDate) {
        this.id = id;
        this.generation = generation;
        this.name = name;
        this.description = description;
        this.types = types != null ? types : new ArrayList<>();
        this.abilities = abilities != null ? abilities : new ArrayList<>();
        this.weight = weight;
        this.height = height;
        this.captureRate = captureRate;
        this.isLegendary = isLegendary;
        this.captureDate = captureDate;
    }

    // Métodos

    //Clone
    @Override
    public Pokemon clone() {
        Pokemon clone = new Pokemon(
            this.id,
            this.generation,
            this.name,
            this.description,
            new ArrayList<>(this.types),  // Cópia profunda
            new ArrayList<>(this.abilities),  // Cópia profunda
            this.weight,
            this.height,
            this.captureRate,
            this.isLegendary,
            (Date) this.captureDate.clone()  // Cópia profunda
        );
        return clone;
    }
    

    // Gets
    public int getId() {return id;}
    public int getGeneration() {return generation;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getTypes() {
        String s3 = "[";
        for(int i = 0; i < this.types.size(); i++){
            s3+="'"+this.types.get(i)+"'";
            if(i != types.size() - 1){
                s3+=", ";
            }
        }
        s3+="]";
        return s3;
    }
    public String getAbilities() {
        String s3 = "[";
        for(int i = 0; i < this.abilities.size(); i++){
            s3+="'"+this.abilities.get(i)+"'";
            if(i != abilities.size() - 1){
                s3+=", ";
            }
        }
        s3+="]";
        return s3;
    }
    public double getWeight() {return weight;}
    public double getHeight() {return height;}
    public int getCaptureRate() {return captureRate;}
    public boolean getLegendary() {return isLegendary;}
    public Date getCaptureDate() {return captureDate;}

    //Sets
    public void setId(int id) {this.id = id;}
    public void setGeneration(int generation) {this.generation = generation;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setTypes(ArrayList<String> types) {this.types = types;}
    public void setAbilities(ArrayList<String> abillities) { this.abilities = abillities;}
    public void setWeight(double weight) {this.weight = weight;}
    public void setHeight(double height) {this.height = height;}
    public void setCaptureRate(int captureDate) {this.captureRate =  captureDate;}
    public void setLegendary(boolean isLegendary) {this.isLegendary = isLegendary;}
    public void setCaptureDate(Date captureDate) {this.captureDate = captureDate;}
    
    //Print 
    public void imprimir(){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String data = formato.format(getCaptureDate());
        System.out.println("[#"
        +this.getId()+" -> "
        +this.getName()+": "
        +this.getDescription()+" - "
        +this.getTypes()+" - " 
        +this.getAbilities() + " - "
        +this.getWeight()+ "kg - "
        +this.getHeight()+ "m - "
        +this.getCaptureRate() + "% - "
        +(this.getLegendary() ? "true" : "false") + " - "
        +this.getGeneration() + " gen] - "
        +data
        );
    }

    //ler
    public void ler(String linha) throws ParseException {
        int c = 0;
        String[] array = linha.split(","); // Criando um Array de varias partes

        // Adicionando Dados
        this.setId(Integer.parseInt(array[c++]));
        this.setGeneration((Integer.parseInt(array[c++])));
        this.setName(array[c++]);
        this.setDescription(array[c++]);

        // Lidando com Array de tipos
        ArrayList<String> tipoList = new ArrayList<String>();
        tipoList.add(array[c++]);
        if (!array[c].isEmpty()) {
            tipoList.add(array[c]);
        }
        c++;
        this.setTypes(tipoList);

        // Lidando com o Array de Habilidades
        ArrayList<String> habilidadesList = new ArrayList<String>();
        if (array[c].charAt(array[c].length() - 2) == ']') {
            habilidadesList.add(array[c++]);
        } else {
            while (array[c].charAt(array[c].length() - 2) != ']') {
                habilidadesList.add(array[c]);
                c++;
            }
            habilidadesList.add(array[c++]);
        }
        for (int j = 0; j < habilidadesList.size(); j++) {
            habilidadesList.set(j, habilidadesList.get(j)
                .replace("[", "")
                .replace("\"", "")
                .replace("]", "")
                .replace("'", "")
                .trim());
        }
        this.setAbilities(habilidadesList);

        // Peso, Altura, Captura, Lendario
        if (array[c] == null || array[c].isEmpty()) {
            this.setWeight(0.0);  // Se for nulo ou vazio, define 0.0
            c++;
        } else {
            this.setWeight(Double.parseDouble(array[c++]));  // Caso contrário, faz o parsing
        }
        if (array[c] == null || array[c].isEmpty()) {
            this.setHeight(0.0);  // Se for nulo ou vazio, define 0.0
            c++;
        } else {
            this.setHeight(Double.parseDouble(array[c++]));  // Caso contrário, faz o parsing
        }

        this.setCaptureRate(Integer.parseInt(array[c++]));

        // Corrigir a conversão de lendário (0 ou 1)
        this.setLegendary(array[c++].equals("1"));

        // Convertendo data
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date captureDate = formatter.parse(array[c++]);
        this.setCaptureDate(captureDate);
    }
}
