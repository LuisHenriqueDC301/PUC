//imports

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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
        ArvoreDeArvore arvore = new ArvoreDeArvore();
        String palavra = sc.nextLine();

        while(!palavra.equals("FIM")){
            int x = Integer.parseInt(palavra);
            arvore.inserir(lista_pokemon.get(x-1));
            palavra = sc.nextLine();
        }
        //arvore.caminharCentral();
        double inicio,fim;

        inicio = now();
        String nome = sc.nextLine();
        while(!nome.equals("FIM")){
            boolean resp = arvore.pesquisar(nome);
            System.out.println(resp ? "SIM" : "NAO");

            nome = sc.nextLine();
        }

        fim = now();
        sc.close(); // Fecha o Scanner ao final

        
        // Geração do arquivo de log
        String matricula = "863566";
        FileWriter logWriter = new FileWriter("863566_arvoreBinaria.txt");
        logWriter.write(matricula + "\t" + (fim - inicio) / 1000.0  + "\t" + arvore.comp);
        logWriter.close();
    }
    public static long now() {
        return new Date().getTime();
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

class No {

    public int mod;
    public No esq, dir;
    public No2 outro;

    public No(int mod){
        this.mod = mod;
        this.esq = this.dir = null;
        this.outro = null;
    }

    public No(int mod, No esq, No dir){
        this.mod = mod;
        this.esq = esq;
        this.dir = dir;
    }


    public int getMod(){
        return this.mod;
    }
}


 class No2 {

    public Pokemon pokemon;
    public No2 esq, dir;

    public No2(Pokemon pokemon){
        this.pokemon = pokemon;
        this.esq = this.dir = null;
    }

    public No2(Pokemon Pokemon, No2 esq, No2 dir){
        this.pokemon = pokemon;
        this.esq = esq;
        this.dir = dir;
    }

}


class ArvoreDeArvore{
    private No raiz;
    public int comp, mov;

    public ArvoreDeArvore(){
        raiz = null;
        try{
        inserir(7); 
        inserir(3);
        inserir(11);
        inserir(1);
        inserir(5);
        inserir(9);
        inserir(13);
        inserir(0);
        inserir(2);
        inserir(4);
        inserir(6);
        inserir(8);
        inserir(10);
        inserir(12);
        inserir(14); 
        }catch(Exception e){
            System.err.println("Erro ao configurar Arvore de Arvore: " + e.getMessage());
        }
    }

    public void inserir(int mod) throws Exception{
        raiz = inserir(mod, raiz);
    }

    private No inserir(int mod, No i) throws Exception{
        if( i == null){
            i = new No(mod);
        }else if(mod < i.mod){
            comp++;
            i.esq = inserir(mod, i.esq);
        }else if(mod > i.mod){
            comp++;
            i.dir = inserir(mod, i.dir);
        }else {
            throw new Exception("Erro ao inserir! elemento inexistente");
        }

        return i;
    }


    public void inserir(Pokemon pokemon) throws Exception{
        inserir(pokemon, raiz);
    }

    private No inserir(Pokemon pokemon, No i) throws Exception{
        if( i == null){
            throw new Exception("Erro ao inserir: mod invalido");
        }else if(pokemon.getCaptureRate() % 15 < i.mod){
            comp++;
            i.esq = inserir(pokemon, i.esq);

        }else if(pokemon.getCaptureRate() % 15 > i.mod){
            comp++;
            i.dir = inserir(pokemon, i.dir);
            
        }else {
            i.outro = inserir(pokemon, i.outro);
        }
        
        return i;
    }


    private No2 inserir(Pokemon pokemon, No2 i) throws Exception{
        if( i == null){
            i = new No2(pokemon);
        }else if(pokemon.getName().compareTo(i.pokemon.getName()) < 0){
            comp++;
            i.esq = inserir(pokemon, i.esq);
        }else if(pokemon.getName().compareTo(i.pokemon.getName()) > 0){
            comp++;
            i.dir = inserir(pokemon, i.dir);
        }else {
            throw new Exception("Erro ao inserir! Elemento inexistente");
        }

        return i;
    }
    


    public boolean pesquisar(String name){
        System.out.println("=> " + name);
        System.out.print("raiz ");
        return PesquisarcaminharPre(name, raiz);
    }

    private boolean PesquisarcaminharPre(String name, No i){
        boolean resp;
        if(i == null){
            resp = false;
        }else{
			resp = pesquisar(name, i.outro);
            
            if(resp == false){
                System.out.print("ESQ ");
                resp = PesquisarcaminharPre(name, i.esq);
            }

            if(resp == false){
                System.out.print("DIR ");
                resp = PesquisarcaminharPre(name, i.dir);
            }

        }

        return resp;
    }

    private boolean pesquisar(String name, No2 i){
        boolean resp;
        if( i == null){
            resp = false;
        } else if(name.equals(i.pokemon.getName())){
            comp++;
            resp = true;
        } else if(name.compareTo(i.pokemon.getName()) < 0){
            comp++;
            System.out.print("esq ");
            resp = pesquisar(name, i.esq);
        } else{
            System.out.print("dir ");
            resp = pesquisar(name, i.dir);
        }

        return resp;
    }




}

