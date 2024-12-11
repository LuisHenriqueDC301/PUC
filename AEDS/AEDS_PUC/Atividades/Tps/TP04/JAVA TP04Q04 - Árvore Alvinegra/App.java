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
        ArvoreAlvinegra arvore = new ArvoreAlvinegra();
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
        FileWriter logWriter = new FileWriter("863566_avinegra.txt");
        logWriter.write(matricula + "\t" + (fim - inicio) / 1000.0  + "\t" + arvore.comparacoes + "\t" + arvore.movimentacoes);
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
                s3+=",";
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
class NoAN {

    public boolean cor;
    public Pokemon pokemon;
    public NoAN esq, dir;

    public NoAN(Pokemon pokemon){
        this(pokemon, false, null, null);
    }

    public NoAN(Pokemon pokemon, boolean cor){
        this(pokemon, cor, null, null);
    }

    public NoAN(Pokemon pokemon, boolean cor, NoAN esq, NoAN dir){
        this.cor = cor;
        this.pokemon = pokemon;
        this.esq = esq;
        this.dir = dir;
    }
    
}


class ArvoreAlvinegra{
    private NoAN raiz;
    public int comparacoes;
    public int movimentacoes;

    public ArvoreAlvinegra(){
        raiz = null;
    }

    public boolean pesquisar(String name){
        System.out.println(name);
        System.out.print("raiz ");
        return pesquisar(name, raiz);
    }

    private boolean pesquisar(String name, NoAN i){
        boolean resp;
        if( i == null){
            resp = false;
        } else if(name.equals(i.pokemon.getName())){
            comparacoes++;
            resp = true;
        } else if(name.compareTo(i.pokemon.getName()) < 0){
            comparacoes++;
            System.out.print("esq ");
            resp = pesquisar(name, i.esq);
        } else{
            System.out.print("dir ");
            resp = pesquisar(name, i.dir);
        }

        return resp;
    }


    public void caminharPre() {
		caminharPre(raiz);
	}

    private void caminharPre(NoAN i){
        if (i != null) {
			System.out.println(i.pokemon.getName());
			caminharPre(i.esq);
			caminharPre(i.dir);
            
		}
    }


    public void inserir(Pokemon pokemon) throws Exception{
        if( raiz == null){
            raiz = new NoAN(pokemon);
        }else if(raiz.esq == null && raiz.dir == null){
            if(pokemon.getName().compareTo(raiz.pokemon.getName()) < 0){
                comparacoes++;
                raiz.esq = new NoAN(pokemon);
            }else{
                comparacoes+=2;
                raiz.dir = new NoAN(pokemon);
            }
        }else if(raiz.esq == null){
            if(pokemon.getName().compareTo(raiz.pokemon.getName()) < 0){
                comparacoes++;
                raiz.esq = new NoAN(pokemon);
            }else if(pokemon.getName().compareTo(raiz.dir.pokemon.getName()) < 0){
                comparacoes++;
                raiz.esq = new NoAN(raiz.pokemon);
                raiz.pokemon = pokemon;
                movimentacoes++;
            }else{
                comparacoes+=2;
                raiz.esq = new NoAN(raiz.pokemon);
                raiz.pokemon = raiz.dir.pokemon;
                raiz.dir.pokemon = pokemon;
                movimentacoes+=2;
            }
            raiz.esq.cor = raiz.dir.cor = false;
        }else if(raiz.dir == null){
            if(pokemon.getName().compareTo(raiz.pokemon.getName()) > 0){
                comparacoes++;
                raiz.dir = new NoAN(pokemon);
            }else if(pokemon.getName().compareTo(raiz.esq.pokemon.getName()) > 0){
                comparacoes++;
                raiz.dir = new NoAN(raiz.pokemon);
                raiz.pokemon = pokemon;
                movimentacoes++;
            }else{
                comparacoes+=2;
                raiz.dir = new NoAN(raiz.pokemon);
                raiz.pokemon = raiz.esq.pokemon;
                raiz.esq.pokemon = pokemon;
                movimentacoes+=2;
            }
            raiz.esq.cor = raiz.dir.cor = false;
    
        // Senao, a arvore tem tres ou mais elementos
        }else{
            inserir(pokemon, null, null, null, raiz);
        }
        raiz.cor = false;
        }



        private void inserir(Pokemon pokemon, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
            if (i == null) {
               if (pokemon.getName().compareTo(pai.pokemon.getName()) < 0) {
                  i = pai.esq = new NoAN(pokemon, true);
               } else {
                  i = pai.dir = new NoAN(pokemon, true);
               }
               if (pai.cor == true) {
                  balancear(bisavo, avo, pai, i);
               }
            } else {
               // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
               if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                  i.cor = true;
                  i.esq.cor = i.dir.cor = false;
                  if (i == raiz) {
                     i.cor = false;
                  } else if (pai.cor == true) {
                     balancear(bisavo, avo, pai, i);
                  }
               }
               if (pokemon.getName().compareTo(i.pokemon.getName()) < 0) {
                  inserir(pokemon, avo, pai, i, i.esq);
               } else if (pokemon.getName().compareTo(i.pokemon.getName()) > 0) {
                  inserir(pokemon, avo, pai, i, i.dir);
               } else {
                  throw new Exception("Erro inserir (Pokemon repetido)!");
               }
            }
         }




        private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
            // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
            if (pai.cor == true) {
               // 4 tipos de reequilibrios e acoplamento
               if (pai.pokemon.getName().compareTo(avo.pokemon.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
                  if (i.pokemon.getName().compareTo(pai.pokemon.getName()) > 0) {
                     avo = rotacaoEsq(avo);
                  } else {
                     avo = rotacaoDirEsq(avo);
                  }
               } else { // rotacao a direita ou esquerda-direita
                  if (i.pokemon.getName().compareTo(pai.pokemon.getName()) < 0) {
                     avo = rotacaoDir(avo);
                  } else {
                     avo = rotacaoEsqDir(avo);
                  }
               }
               if (bisavo == null) {
                  raiz = avo;
               } else if (avo.pokemon.getName().compareTo(bisavo.pokemon.getName()) < 0) {
                  bisavo.esq = avo;
               } else {
                  bisavo.dir = avo;
               }
               // reestabelecer as cores apos a rotacao
               avo.cor = false;
               avo.esq.cor = avo.dir.cor = true;

            } // if(pai.cor == true)
         }


         private NoAN rotacaoDir(NoAN no) {
            NoAN noEsq = no.esq;
            NoAN noEsqDir = noEsq.dir;
      
            noEsq.dir = no;
            no.esq = noEsqDir;

            movimentacoes+=4;
            return noEsq;
         }
      
         private NoAN rotacaoEsq(NoAN no) {
            NoAN noDir = no.dir;
            NoAN noDirEsq = noDir.esq;
      
            noDir.esq = no;
            no.dir = noDirEsq;

            movimentacoes+=4;
            return noDir;
         }
      
         private NoAN rotacaoDirEsq(NoAN no) {
            no.dir = rotacaoDir(no.dir);
            return rotacaoEsq(no);
         }
      
         private NoAN rotacaoEsqDir(NoAN no) {
            no.esq = rotacaoEsq(no.esq);
            return rotacaoDir(no);
         }

}


