#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MAX 10000
#define MAX_LINE_LENGTH 1024
#define MAX_NAME_LENGTH 100
#define MAX_DESC_LENGTH 255
#define MAX_TYPES 2
#define MAX_ABILITIES 6

int comparacoes = 0;
int movimentao = 0;

// Estrutura Date para armazenar a data
typedef struct Date
{
    int dia;
    int mes;
    int ano;
} Date;

// Estrutura para armazenar informações de um Pokémon
typedef struct
{
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
Pokemon *pokemon_vazio()
{
    Pokemon *pokemon = (Pokemon *)malloc(sizeof(Pokemon));

    pokemon->id = -1;
    pokemon->generation = -1;

    // Strings e arrays já são inicializados com zeros, então não precisa de calloc
    strcpy(pokemon->name, "");
    strcpy(pokemon->description, "");

    for (int i = 0; i < MAX_TYPES; i++)
    {
        strcpy(pokemon->types[i], "");
    }

    for (int i = 0; i < MAX_ABILITIES; i++)
    {
        strcpy(pokemon->abilities[i], "");
    }

    pokemon->weight = 0.0;
    pokemon->height = 0.0;
    pokemon->captureRate = 0;
    pokemon->isLegendary = 0;

    pokemon->data.dia = 0;
    pokemon->data.mes = 0;
    pokemon->data.ano = 0;

    return pokemon;
}

// Função para criar um novo Pokémon
Pokemon *pokemon_new(int id,
                     int generation,
                     const char *name,
                     const char *description,
                     const char types[MAX_TYPES][MAX_NAME_LENGTH],
                     const char abilities[MAX_ABILITIES][MAX_NAME_LENGTH],
                     double weight,
                     double height,
                     int captureRate,
                     int isLegendary,
                     Date data)
{

    Pokemon *pokemon = (Pokemon *)malloc(sizeof(Pokemon));

    pokemon->id = id;
    pokemon->generation = generation;

    // Copiar strings e arrays
    strcpy(pokemon->name, name);
    strcpy(pokemon->description, description);

    for (int i = 0; i < MAX_TYPES; i++)
    {
        strcpy(pokemon->types[i], types[i]);
    }

    for (int i = 0; i < MAX_ABILITIES; i++)
    {
        strcpy(pokemon->abilities[i], abilities[i]);
    }

    pokemon->weight = weight;
    pokemon->height = height;
    pokemon->captureRate = captureRate;
    pokemon->isLegendary = isLegendary;
    pokemon->data = data;

    return pokemon;
}
void imprimirTipos(char tipos[MAX_TYPES][MAX_NAME_LENGTH], char *buffer, int n)
{
    strcpy(buffer, "[");
    int first = 1; // Flag para saber se é o primeiro elemento
    for (int i = 0; i < n; i++)
    {
        if (strcmp(tipos[i], "") != 0)
        { // Ignora tipos vazios
            if (!first)
            {
                strcat(buffer, ", "); // Adiciona vírgula somente se não for o primeiro
            }
            strcat(buffer, "'");      // Adiciona aspas simples
            strcat(buffer, tipos[i]); // Adiciona o tipo
            strcat(buffer, "'");      // Fecha aspas simples
            first = 0;
        }
    }
    strcat(buffer, "]");
}

void imprimirHabilidades(char habilidades[MAX_ABILITIES][MAX_NAME_LENGTH], char *buffer, int n)
{
    strcpy(buffer, "[");
    int first = 1; // Flag para saber se é o primeiro elemento
    for (int i = 0; i < n; i++)
    {
        if (strcmp(habilidades[i], "") != 0)
        { // Ignora habilidades vazias
            if (!first)
            {
                strcat(buffer, ", "); // Adiciona vírgula somente se não for o primeiro
            }
            strcat(buffer, "'");            // Adiciona aspas simples
            strcat(buffer, habilidades[i]); // Adiciona a habilidade
            strcat(buffer, "'");            // Fecha aspas simples
            first = 0;
        }
    }
    strcat(buffer, "]");
}

void imprimir(Pokemon *pokemon)
{
    char tiposStr[256];       // Buffer para armazenar os tipos
    char habilidadesStr[256]; // Buffer para armazenar as habilidades

    // Preencher os buffers com os tipos e habilidades formatados
    imprimirTipos(pokemon->types, tiposStr, MAX_TYPES);
    imprimirHabilidades(pokemon->abilities, habilidadesStr, MAX_ABILITIES);

    // Imprimir as informações formatadas do Pokémon
    printf("[#%d -> %s: %s - %s - %s - %.1fkg - %.1fm - %d%% - %s - %d gen] - %02d/%02d/%04d\n",
           pokemon->id, pokemon->name, pokemon->description, tiposStr, habilidadesStr,
           pokemon->weight, pokemon->height, pokemon->captureRate,
           (pokemon->isLegendary ? "true" : "false"), pokemon->generation,
           pokemon->data.dia, pokemon->data.mes, pokemon->data.ano);
}

char **strsplit(const char *src, const char *delim, int *count)
{
    char *pbuf = NULL;
    int srclen = strlen(src);
    int delimlen = strlen(delim);
    char **pparr = NULL;
    *count = 0;

    // Alocando o buffer temporário
    pbuf = (char *)malloc(srclen + 1);
    if (!pbuf)
        return NULL;

    strcpy(pbuf, src);

    char *start = pbuf;
    char *end = strstr(start, delim);

    while (end != NULL)
    {
        // Adiciona substring encontrada entre os delimitadores
        pparr = (char **)realloc(pparr, (*count + 1) * sizeof(char *));
        *end = '\0';                   // Substitui o delimitador por fim de string
        pparr[*count] = strdup(start); // Copia a parte antes do delimitador
        (*count)++;

        // Verifica se há delimitadores consecutivos e adiciona strings vazias
        start = end + delimlen;
        while (strncmp(start, delim, delimlen) == 0)
        {
            pparr = (char **)realloc(pparr, (*count + 1) * sizeof(char *));
            pparr[*count] = strdup(""); // Adiciona uma string vazia
            (*count)++;
            start += delimlen;
        }

        // Procura o próximo delimitador
        end = strstr(start, delim);
    }

    // Adiciona o último pedaço da string (depois do último delimitador)
    pparr = (char **)realloc(pparr, (*count + 1) * sizeof(char *));
    pparr[*count] = strdup(start); // Copia o último pedaço
    (*count)++;

    // Finaliza o array com NULL
    pparr = (char **)realloc(pparr, (*count + 1) * sizeof(char *));
    pparr[*count] = NULL;

    free(pbuf);
    return pparr;
}

void strsplitfree(char **strlist)
{
    int i = 0;

    while (strlist[i])
        free(strlist[i++]);

    free(strlist);
}

void trim(char *str)
{
    int start = 0;
    int end = strlen(str) - 1;

    // Remover espaços à esquerda
    while (str[start] == ' ' || str[start] == '\t' || str[start] == '\n')
    {
        start++;
    }

    // Remover espaços à direita
    while ((end >= start) && (str[end] == ' ' || str[end] == '\t' || str[end] == '\n'))
    {
        end--;
    }

    // Mover a parte "trimmed" para o início da string e adicionar o terminador nulo
    int i;
    for (i = start; i <= end; i++)
    {
        str[i - start] = str[i];
    }
    str[i - start] = '\0'; // Adiciona o terminador nulo no final da string
}

// Função para ler e popular as informações de um Pokémon a partir de uma linha
void ler(char *linha, Pokemon *pokemon)
{
    int c = 0;
    int count = 0;
    char **array = strsplit(linha, ",", &count); // Dividindo a linha em várias partes

    // Atribuindo os dados
    pokemon->id = atoi(array[c++]);
    pokemon->generation = atoi(array[c++]);
    strcpy(pokemon->name, array[c++]);
    strcpy(pokemon->description, array[c++]);

    // Lidando com os tipos

    strcpy(pokemon->types[0], array[c++]);
    if (strcmp(array[c], "") != 0)
    {
        strcpy(pokemon->types[1], array[c++]);
    }
    else
    {
        strcpy(pokemon->types[1], "");
        c++;
    }

    // Lidando com as habilidades
    int habilidadesIndex = 0;
    while (habilidadesIndex <= MAX_ABILITIES && c < count)
    {
        strcpy(pokemon->abilities[habilidadesIndex], array[c]);
        habilidadesIndex++;
        c++;
        if (strchr(array[c - 1], ']'))
        { // Se encontrou o fechamento de lista
            break;
        }
    }

    // Removendo os caracteres extras das habilidades
    for (int i = 0; i < habilidadesIndex; i++)
    {
        char *habilidade = pokemon->abilities[i];
        int len = strlen(habilidade);
        char habilidadeLimpa[MAX_NAME_LENGTH] = "";
        int idx = 0;

        // Percorrendo a string e copiando apenas os caracteres válidos
        for (int j = 0; j < len; j++)
        {
            if (habilidade[j] != '[' && habilidade[j] != ']' && habilidade[j] != '"' && habilidade[j] != '\'')
            {
                habilidadeLimpa[idx++] = habilidade[j];
            }
        }
        habilidadeLimpa[idx] = '\0';                    // Finaliza a string limpa
        trim(habilidadeLimpa);                          // Remover espaços à esquerda e direita
        strcpy(pokemon->abilities[i], habilidadeLimpa); // Copia a string limpa de volta
    }

    // Lidando com peso e altura

    if (strcmp(array[c], "") == 0)
    {
        pokemon->weight = 0.0; // Corrigido para atribuição
    }
    else
    {
        pokemon->weight = atof(array[c]); // Corrigido para atribuição
    }
    c++;

    if (strcmp(array[c], "") == 0)
    {
        pokemon->height = 0.0; // Corrigido para atribuição
    }
    else
    {
        pokemon->height = atof(array[c]); // Corrigido para atribuição
    }
    c++;

    // Taxa de captura e lendário
    pokemon->captureRate = atoi(array[c++]);
    pokemon->isLegendary = (strcmp(array[c++], "1") == 0);

    // Convertendo a data (dd/MM/yyyy)

    char *data = array[c++];
    sscanf(data, "%d/%d/%d", &pokemon->data.dia, &pokemon->data.mes, &pokemon->data.ano);

    // Liberar a memória alocada para a divisão de strings
    free(array);
}
Pokemon *clone(Pokemon *pokemon1)
{
    // Aloca memória para o novo Pokémon
    Pokemon *pokemon2 = (Pokemon *)malloc(sizeof(Pokemon));

    // Copiar os dados do Pokémon original (pokemon1) para o clone (pokemon2)
    pokemon2->id = pokemon1->id;
    pokemon2->generation = pokemon1->generation;

    // Copiar strings
    strcpy(pokemon2->name, pokemon1->name);
    strcpy(pokemon2->description, pokemon1->description);

    // Copiar tipos
    for (int i = 0; i < MAX_TYPES; i++)
    {
        strcpy(pokemon2->types[i], pokemon1->types[i]);
    }

    // Copiar habilidades
    for (int i = 0; i < MAX_ABILITIES; i++)
    {
        strcpy(pokemon2->abilities[i], pokemon1->abilities[i]);
    }

    // Copiar os outros atributos
    pokemon2->weight = pokemon1->weight;
    pokemon2->height = pokemon1->height;
    pokemon2->captureRate = pokemon1->captureRate;
    pokemon2->isLegendary = pokemon1->isLegendary;

    // Copiar a data
    pokemon2->data.dia = pokemon1->data.dia;
    pokemon2->data.mes = pokemon1->data.mes;
    pokemon2->data.ano = pokemon1->data.ano;

    return pokemon2;
}
int pesquisaBinaria(char *nome, Pokemon *array[], int n)
{
    int resp = 0;
    int dir = n - 1, esq = 0, meio;
    while (esq <= dir)
    {
        meio = (esq + dir) / 2;
        int comparacao = strcmp(nome, array[meio]->name);
        if (comparacao == 0)
        {
            resp = 1;
            esq = n;
        }
        else if (comparacao > 0)
        {
            esq = meio + 1;
        }
        else
        {
            dir = meio - 1;
        }
    }
    return resp;
}

void swap(Pokemon **a, Pokemon **b)
{
    Pokemon *temp = *a;
    *a = *b;
    *b = temp;
    movimentao= movimentao+2;
}
int minIndex(Pokemon *arr[], int i, int j)
{
    if (i == j)
        return i;

    int k = minIndex(arr, i + 1, j);

    // Retorna o índice do menor nome lexicograficamente
    return (strcmp(arr[i]->name, arr[k]->name) < 0) ? i : k;
    comparacoes++;
}

void recurSelectionSort(Pokemon *arr[], int n, int index)
{   
    comparacoes++;
    if (index == n)
        return;

    // Encontra o índice do menor nome a partir do índice atual
    int k = minIndex(arr, index, n - 1);

    // Se o menor índice for diferente do índice atual, faz a troca
    if (k != index)
        swap(&arr[k], &arr[index]);
        movimentao++;movimentao++;

    // Chamada recursiva para a próxima posição
    recurSelectionSort(arr, n, index + 1);
}
void quicksortRec(Pokemon **array, int esq, int dir) {
    int i = esq, j = dir;
    Pokemon* pivo = array[(esq + dir) / 2];

    while (i <= j) {
        while (array[i]->generation < pivo->generation || 
              (array[i]->generation == pivo->generation && strcmp(array[i]->name, pivo->name) < 0)) {
            i++;
        }
        while (array[j]->generation > pivo->generation || 
              (array[j]->generation == pivo->generation && strcmp(array[j]->name, pivo->name) > 0)) {
            j--;
        }
        if (i <= j) {
            swap(&array[i], &array[j]);
            i++;
            j--;
        }
    }
    if (esq < j) quicksortRec(array, esq, j);
    if (i < dir) quicksortRec(array, i, dir);
}

// Função que inicia o QuickSort
void quicksort(Pokemon **array, int n) {
    quicksortRec(array, 0, n - 1);
}

int main()
{
    char matricula[] = "863566";
    clock_t inicio, fim;
    // Criando uma lista de ponteiros de pokémons para 801 pks
    Pokemon *pks[801];

    // Lidando com arquivo
    FILE *file;
    char linha[MAX];

    file = fopen("/tmp/pokemon.csv", "r");
    if (!file)
    {
        printf("Erro ao abrir o arquivo.\n");
        return 1;
    }

    fgets(linha, sizeof(linha), file); // Ignorando o cabeçalho

    for (int i = 0; i < 801; i++)
    {
        fgets(linha, sizeof(linha), file);
        pks[i] = pokemon_vazio(); // Criar um novo Pokémon vazio
        ler(linha, pks[i]);       // Preencher o Pokémon com os dados da linha lida
    }

    fclose(file);

    
    // Criando um subarray de pokemons
    Pokemon *subpks[500];

    int i = 0; // contador de elementos no subarray

    // Entrada do usuário
    char entrada[500];
    scanf("%[^\r\n]%*c", entrada);


    // Loop até encontrar "FIM"
    while (strcmp(entrada, "FIM") != 0)
    {
        int numero = atoi(entrada); // Convertendo string para int (se necessário)
        subpks[i] = clone(pks[numero - 1]);
        i++;
        // Ler próxima entrada
        scanf("%[^\r\n]%*c", entrada);
    }

    inicio = clock();

    quicksort(subpks, i);

    fim = clock();
    for(int j = 0; j < i; j++){
        imprimir(subpks[j]);
    }


    
    FILE *logWriter = fopen("863566_selecaoRecursiva.txt", "w");
    if (logWriter == NULL)
    {
        printf("Error opening file!\n");
        return 1;
    }

    fprintf(logWriter, "%s\t%d\t%d\t%f\n", matricula, movimentao , comparacoes, (double)(fim - inicio) / CLOCKS_PER_SEC);
    fclose(logWriter);

    // Liberar memória dos pokémons
    for (int i = 0; i < 801; i++)
    {
        free(pks[i]);
    }

    return 0;
}