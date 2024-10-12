//imports

//ERRRO UM ELEMENTO DA LISTA SIMPLISMENTE SOME

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
    public static int comparacoes = 0; // Contador de comparações
    public static int movimentacoes = 0; // Contador de movimentações
    // main
    public static void main(String[] args) throws ParseException, IOException {
        double inicio, fim;

        String path = "/tmp/pokemon.csv"; // Caminho do arquivo
        BufferedReader br = new BufferedReader(new FileReader(path));
        Scanner sc = new Scanner(System.in);

        // Ignora a primeira linha (se for um cabeçalho)
        br.readLine();

        // Criando uma lista de todos os pokemons
        ArrayList<Pokemon> lista_pokemon = new ArrayList<>();
        String line;
        line = br.readLine();
        Pokemon pokemon = new Pokemon();
        pokemon.ler(line);
        lista_pokemon.add(pokemon);
        while ((line = br.readLine()) != null) {
            pokemon = new Pokemon();
            pokemon.ler(line);
            lista_pokemon.add(pokemon);
        }

        // Criando um subarray de pokemons
        ArrayList<Pokemon> subarray = new ArrayList<>();

        
        String n = sc.nextLine();

        while (!n.equals("FIM")) {
            int x = Integer.parseInt(n);
            subarray.add(lista_pokemon.get(x - 1));
            n = sc.nextLine();
        }

        // Variáveis para medir tempo de execução e comparações
        inicio = now();

        countingsort(subarray);

        fim = now();

        for (int i = 0; i < subarray.size(); i++) {
            subarray.get(i).imprimir();
        }
        // Geração do arquivo de log
        String matricula = "863566";
        FileWriter logWriter = new FileWriter("863566_heapsort.txt");
        logWriter.write(matricula + "\t" + movimentacoes + "\t" + comparacoes + "\t" + (fim - inicio) / 1000.0);
        logWriter.close();
    }

    public static long now() {
        return new Date().getTime();
    }

    

    public static void countingsort(ArrayList<Pokemon> array) {
        int n = array.size();
        if (n == 0) return; // Verifica se o array está vazio
        
        int maior = getMaior(array);
        int[] count = new int[maior + 1];
        ArrayList<ArrayList<Pokemon>> ordenadoList = new ArrayList<>(maior + 1);
        
        // Inicializa as listas para cada possível valor de captura
        for (int i = 0; i <= maior; i++) {
            ordenadoList.add(new ArrayList<>());
            count[i] = 0;
        }
    
        // Contagem das ocorrências
        for (Pokemon pokemon : array) {
            int captureRate = pokemon.getCaptureRate();
            ordenadoList.get(captureRate).add(pokemon);
            count[captureRate]++;
            movimentacoes++;
        }
        
        // Ordena lexicograficamente os Pokémon com o mesmo captureRate
        for (ArrayList<Pokemon> pokemonsComMesmoRate : ordenadoList) {
            pokemonsComMesmoRate.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
        }
    
        // Atualiza o array de contagem
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
            movimentacoes++;
        }
    
        // Monta o array ordenado
        for (int i = maior; i >= 0; i--) {
            ArrayList<Pokemon> listaCaptura = ordenadoList.get(i);
            for (int j = listaCaptura.size() - 1; j >= 0; j--) {
                int pos = count[i] - 1;
                array.set(pos, listaCaptura.get(j));
                count[i]--;
                movimentacoes++;
            }
        }
    }
    
    // Método para obter o maior elemento do array
    public static int getMaior(ArrayList<Pokemon> array) {
        int maior = array.get(0).getCaptureRate();
    
        for (int i = 1; i < array.size(); i++) {
            if (maior < array.get(i).getCaptureRate()) {
                maior = array.get(i).getCaptureRate();
                comparacoes++;
            }
        }
        return maior;
    }
    
    
    
    public static void heapsort(ArrayList<Pokemon> array) {
        int n = array.size();
    
        // Construção do heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(array, tamHeap);
        }
    
        // Ordenação propriamente dita
        int tamHeap = n;
        while (tamHeap > 1) {
            swap(array, 1, tamHeap--);
            reconstruir(array, tamHeap);
        }
    }
    
    // Construir o heap com base no campo 'height'
    public static void construir(ArrayList<Pokemon> array, int tamHeap) {
        for (int i = tamHeap; i > 1 && compare(array.get(i - 1), array.get((i / 2) - 1)) > 0; i /= 2) {
            swap(array, i, i / 2);
        }
    }
    
    // Reconstruir o heap considerando o 'height'
    public static void reconstruir(ArrayList<Pokemon> array, int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(array, i, tamHeap);
            if (compare(array.get(i - 1), array.get(filho - 1)) < 0) {
                comparacoes++;
                swap(array, i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }
    
    // Obter o maior filho baseado no 'height'
    public static int getMaiorFilho(ArrayList<Pokemon> array, int i, int tamHeap) {
        int filho;
        if (2 * i == tamHeap || compare(array.get((2 * i) - 1), array.get((2 * i + 1) - 1)) > 0) {
            comparacoes++;comparacoes++;
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }
    
    // Função de troca
    public static void swap(ArrayList<Pokemon> array, int i, int j) {
        Pokemon temp = array.get(i - 1);
        array.set(i - 1, array.get(j - 1));
        array.set(j - 1, temp);
        movimentacoes++;movimentacoes++;
    }
    public static int compare(Pokemon a, Pokemon b) {
        // Comparar por 'height'
        if (a.getHeight() != b.getHeight()) {
            return Double.compare(a.getHeight(), b.getHeight());
        }
        // Desempatar pelo 'name'
        return a.getName().compareTo(b.getName());
    }
}

// Pokemon
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

    Pokemon(int id, int generation, String name, String description, ArrayList<String> types,
            ArrayList<String> abilities, double weight, double height, int captureRate, boolean isLegendary,
            Date captureDate) {
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

    // Clone
    public Pokemon clone() {
        Pokemon clone = new Pokemon(
                this.id,
                this.generation,
                this.name,
                this.description,
                new ArrayList<>(this.types), // Cópia profunda
                new ArrayList<>(this.abilities), // Cópia profunda
                this.weight,
                this.height,
                this.captureRate,
                this.isLegendary,
                (Date) this.captureDate.clone() // Cópia profunda
        );
        return clone;
    }

    // Gets
    public int getId() {
        return id;
    }

    public int getGeneration() {
        return generation;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTypes() {
        String s3 = "[";
        for (int i = 0; i < this.types.size(); i++) {
            s3 += "'" + this.types.get(i) + "'";
            if (i != types.size() - 1) {
                s3 += ", ";
            }
        }
        s3 += "]";
        return s3;
    }

    public String getAbilities() {
        String s3 = "[";
        for (int i = 0; i < this.abilities.size(); i++) {
            s3 += "'" + this.abilities.get(i) + "'";
            if (i != abilities.size() - 1) {
                s3 += ", ";
            }
        }
        s3 += "]";
        return s3;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getCaptureRate() {
        return captureRate;
    }

    public boolean getLegendary() {
        return isLegendary;
    }

    public Date getCaptureDate() {
        return captureDate;
    }

    // Sets
    public void setId(int id) {
        this.id = id;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypes(ArrayList<String> types) {
        this.types = types;
    }

    public void setAbilities(ArrayList<String> abillities) {
        this.abilities = abillities;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setCaptureRate(int captureDate) {
        this.captureRate = captureDate;
    }

    public void setLegendary(boolean isLegendary) {
        this.isLegendary = isLegendary;
    }

    public void setCaptureDate(Date captureDate) {
        this.captureDate = captureDate;
    }

    // Print
    public void imprimir() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String data = formato.format(getCaptureDate());
        System.out.println("[#"
                + this.getId() + " -> "
                + this.getName() + ": "
                + this.getDescription() + " - "
                + this.getTypes() + " - "
                + this.getAbilities() + " - "
                + this.getWeight() + "kg - "
                + this.getHeight() + "m - "
                + this.getCaptureRate() + "% - "
                + (this.getLegendary() ? "true" : "false") + " - "
                + this.getGeneration() + " gen] - "
                + data);
    }

    // ler
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
            this.setWeight(0.0); // Se for nulo ou vazio, define 0.0
            c++;
        } else {
            this.setWeight(Double.parseDouble(array[c++])); // Caso contrário, faz o parsing
        }
        if (array[c] == null || array[c].isEmpty()) {
            this.setHeight(0.0); // Se for nulo ou vazio, define 0.0
            c++;
        } else {
            this.setHeight(Double.parseDouble(array[c++])); // Caso contrário, faz o parsing
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