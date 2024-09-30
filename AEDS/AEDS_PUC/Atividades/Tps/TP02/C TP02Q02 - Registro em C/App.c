#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 10000
#define MAX_LINE_LENGTH 1024
#define MAX_NAME_LENGTH 100
#define MAX_DESC_LENGTH 255
#define MAX_TYPES 2
#define MAX_ABILITIES 5

// Estrutura Date para armazenar a data
typedef struct Date {
    int dia;
    int mes;
    int ano;
} Date;

// Estrutura para armazenar informações de um Pokémon
typedef struct {
    int id;
    int generation;
    char name[MAX_NAME_LENGTH];
    char description[MAX_DESC_LENGTH];
    char types[MAX_TYPES][MAX_NAME_LENGTH];
    char abilities[MAX_ABILITIES][MAX_NAME_LENGTH];
    double weight;
    double height;
    int captureRate;
    int isLegendary;
    Date data;
} Pokemon;

// Função para criar um Pokémon vazio
Pokemon pokemon_vazio() {
    Pokemon pokemon;

    pokemon.id = -1;
    pokemon.generation = -1;

    // Strings e arrays já são inicializados com zeros, então não precisa de calloc
    strcpy(pokemon.name, "");
    strcpy(pokemon.description, "");

    for (int i = 0; i < MAX_TYPES; i++) {
        strcpy(pokemon.types[i], "");
    }

    for (int i = 0; i < MAX_ABILITIES; i++) {
        strcpy(pokemon.abilities[i], "");
    }

    pokemon.weight = 0.0;
    pokemon.height = 0.0;
    pokemon.captureRate = 0;
    pokemon.isLegendary = 0;

    pokemon.data.dia = 0;
    pokemon.data.mes = 0;
    pokemon.data.ano = 0;

    return pokemon;
}

// Função para criar um novo Pokémon
Pokemon pokemon_new(int id, 
    int generation,
    const char* name,
    const char *description,
    const char types[MAX_TYPES][MAX_NAME_LENGTH],
    const char abilities[MAX_ABILITIES][MAX_NAME_LENGTH],
    double weight,
    double height,
    int captureRate,
    int isLegendary,
    Date data) {

    Pokemon pokemon;

    pokemon.id = id;
    pokemon.generation = generation;

    // Copiar strings e arrays
    strcpy(pokemon.name, name);
    strcpy(pokemon.description, description);

    for (int i = 0; i < MAX_TYPES; i++) {
        strcpy(pokemon.types[i], types[i]);
    }

    for (int i = 0; i < MAX_ABILITIES; i++) {
        strcpy(pokemon.abilities[i], abilities[i]);
    }

    pokemon.weight = weight;
    pokemon.height = height;
    pokemon.captureRate = captureRate;
    pokemon.isLegendary = isLegendary;
    pokemon.data = data;

    return pokemon;
}
void imprimirTipos(char tipos[MAX_TYPES][MAX_NAME_LENGTH], char* buffer, int n) {
    strcpy(buffer, "[");
    for (int i = 0; i < n; i++) {
        strcat(buffer, tipos[i]);
        if (i < n - 1) {
            strcat(buffer, ", "); // Adiciona uma vírgula entre os tipos
        }
    }
    strcat(buffer, "]");
}

// Função para imprimir as habilidades de um Pokémon
void imprimirHabilidades(char habilidades[MAX_ABILITIES][MAX_NAME_LENGTH], char* buffer, int n) {
    strcpy(buffer, "[");
    for (int i = 0; i < n; i++) {
        strcat(buffer, habilidades[i]);
        if (i < n - 1) {
            strcat(buffer, ", "); // Adiciona uma vírgula entre as habilidades
        }
    }
    strcat(buffer, "]");
}

//Funcao para ler a linha e adicionar no pokemon
void ler( char* linha, Pokemon pokemon){

}

// Função para imprimir todas as informações de um Pokémon
void imprimir(Pokemon pokemon) {
    char tiposStr[256]; // Buffer para armazenar os tipos
    char habilidadesStr[256]; // Buffer para armazenar as habilidades

    // Preencher os buffers com os tipos e habilidades formatados
    imprimirTipos(pokemon.types, tiposStr, MAX_TYPES);
    imprimirHabilidades(pokemon.abilities, habilidadesStr, MAX_ABILITIES);

    // Imprimir as informações formatadas do Pokémon
    printf("[#%d -> %s: %s - %s - %s - %.1fkg - %.1fm - %d%% - %s - %d] %02d/%02d/%04d\n",
           pokemon.id, pokemon.name, pokemon.description, tiposStr, habilidadesStr, 
           pokemon.weight, pokemon.height, pokemon.captureRate, 
           (pokemon.isLegendary ? "true" : "false"), pokemon.generation, 
           pokemon.data.dia, pokemon.data.mes, pokemon.data.ano);
}

int main() {
    // Criar um Pokémon vazio
    Pokemon p1 = pokemon_vazio();

    // Exemplo de tipos e habilidades
    char types[MAX_TYPES][MAX_NAME_LENGTH] = {"Water", "Flying"};
    char abilities[MAX_ABILITIES][MAX_NAME_LENGTH] = {"Torrent", "Rain Dish"};

    // Data de captura
    Date captureDate = {25, 12, 2023};

    // Criar um novo Pokémon com dados preenchidos
    Pokemon p2 = pokemon_new(1, 1, "Squirtle", "Tiny Turtle Pokémon", types, abilities, 9.0, 0.5, 45, 0, captureDate);

    imprimir(p2);


    //Lidando com arquivo

    FILE file;
    char *linha[MAX];
    
    file = fopen("./pokemon.csv", "r");
    fgets(linha, sizeof(linha), file);
    return 0;
}
