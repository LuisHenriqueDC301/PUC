#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 10000
#define MAX_LINE_LENGTH 1024
#define MAX_NAME_LENGTH 100
#define MAX_DESC_LENGTH 255
#define MAX_TYPES 2
#define MAX_ABILITIES 6

// Estrutura Date para armazenar a data
typedef struct Date
{
    int dia;
    int mes;
    int ano;
} Date;

// Estrutura para armazenar informações de um Pokémon
typedef struct Pokemon
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
Pokemon *clone(Pokemon *pokemon1)
{
    Pokemon *pokemon = (Pokemon *)malloc(sizeof(Pokemon));
    pokemon1->id = pokemon->id;
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

// Estrutura de Lista
typedef struct Lista
{
    Pokemon *pks[801];
    int n;
} Lista;

// Função para inserir no início da lista
void inserirInicio(Pokemon *x, Lista *lista)
{
    if (lista->n >= 801)
    {
        printf("Erro ao inserir!\n");
        exit(1);
    }
    for (int i = lista->n; i > 0; i--)
    {
        lista->pks[i] = lista->pks[i - 1];
    }
    lista->pks[0] = x;
    lista->n++;
}

// Função para inserir no fim da lista
void inserirFim(Pokemon *x, Lista *lista)
{
    if (lista->n >= 801)
    {
        printf("Erro ao inserir!\n");
        exit(1);
    }
    lista->pks[lista->n++] = x;
}

// Função para inserir em uma posição específica da lista
void inserir(Pokemon *x, int pos, Lista *lista)
{
    if (lista->n >= 801 || pos < 0 || pos > lista->n)
    {
        printf("Erro ao inserir!\n");
        exit(1);
    }
    for (int i = lista->n; i > pos; i--)
    {
        lista->pks[i] = lista->pks[i - 1];
    }
    lista->pks[pos] = x;
    lista->n++;
}

// Função para remover do início da lista
Pokemon *removerInicio(Lista *lista)
{
    if (lista->n == 0)
    {
        printf("Erro ao remover!\n");
        exit(1);
    }
    Pokemon *resp = lista->pks[0];
    for (int i = 0; i < lista->n - 1; i++)
    {
        lista->pks[i] = lista->pks[i + 1];
    }
    lista->n--;
    return resp;
}

// Função para remover do fim da lista
Pokemon *removerFim(Lista *lista)
{
    if (lista->n == 0)
    {
        printf("Erro ao remover!\n");
        exit(1);
    }
    return lista->pks[--lista->n];
}

// Função para remover de uma posição específica da lista
Pokemon *remover(int pos, Lista *lista)
{
    if (lista->n == 0 || pos < 0 || pos >= lista->n)
    {
        printf("Erro ao remover!\n");
        exit(1);
    }
    Pokemon *resp = lista->pks[pos];
    for (int i = pos; i < lista->n - 1; i++)
    {
        lista->pks[i] = lista->pks[i + 1];
    }
    lista->n--;
    return resp;
}

// Função para mostrar os Pokémon na lista
void mostrar(Lista *lista)
{
    for (int i = 0; i < lista->n; i++)
    {
        printf("[%d] ", i);
        imprimir(lista->pks[i]);
    }
}

// Função para inicializar uma lista vazia
Lista *lista_vazia()
{
    Lista *lista = (Lista *)malloc(sizeof(Lista));
    lista->n = 0;
    return lista;
}

Pokemon **lerarquivo() {
    // Alocando dinamicamente uma lista de ponteiros de Pokémon para 801 pks
    Pokemon **pks = (Pokemon **)malloc(801 * sizeof(Pokemon *));
    if (pks == NULL) {
        printf("Erro de alocação de memória.\n");
        return NULL;
    }

    // Lidando com arquivo
    FILE *file;
    char linha[MAX];

    file = fopen("/tmp/pokemon.csv", "r");
    if (!file) {
        printf("Erro ao abrir o arquivo.\n");
        free(pks);  // Liberar memória antes de retornar
        return NULL;
    }

    fgets(linha, sizeof(linha), file); // Ignorando o cabeçalho

    for (int i = 0; i < 801; i++) {
        if (fgets(linha, sizeof(linha), file) == NULL) {
            printf("Erro ao ler a linha %d do arquivo.\n", i + 1);
            break;
        }
        pks[i] = pokemon_vazio(); // Criar um novo Pokémon vazio
        ler(linha, pks[i]);       // Preencher o Pokémon com os dados da linha lida
    }

    fclose(file);
    return pks;
}

int main()
{
    Pokemon ** pks = lerarquivo();
    // Lista
    Lista *lista = lista_vazia();

    // Entrada do usuário
    char entrada[500];
    scanf("%[^\r\n]%*c", entrada);

    // Loop até encontrar "FIM"
    while (strcmp(entrada, "FIM") != 0)
    {
        int numero = atoi(entrada); // Convertendo string para int (se necessário)
        inserirFim(pks[numero - 1], lista);
        // Ler próxima entrada
        scanf("%[^\r\n]%*c", entrada);
    }

   
    int n = 0;
    scanf("%d", &n);  

    for (int i = 0; i < n; i++) {
        char entrada[500];
        scanf(" %s", entrada);  
        if (strcmp(entrada, "II") == 0) {
            int number = 0;
            scanf("%d", &number);  
            inserirInicio(pks[number - 1], lista);
        }
        else if (strcmp(entrada, "IF") == 0) {
            int number = 0;
            scanf("%d", &number);
            inserirFim(pks[number - 1], lista);
        }
        else if (strcmp(entrada, "I*") == 0) {
            int posi = 0;
            scanf("%d", &posi);
            int number = 0;
            scanf("%d", &number);
            inserir(pks[number - 1], posi, lista);
        }
        else if (strcmp(entrada, "RI") == 0) {
            printf("(R) %s\n", removerInicio(lista)->name);  
        }
        else if (strcmp(entrada, "RF") == 0) {
            printf("(R) %s\n", removerFim(lista)->name);  
        }
        else if (strcmp(entrada, "R*") == 0) {
            int posi = 0;
            scanf("%d", &posi);
            printf("(R) %s\n", remover(posi, lista)->name);  
        }
    }

    // Imprime a lista completa
    mostrar(lista);

// Liberar memória dos pokémons
for (int i = 0; i < 801; i++)
{
    free(pks[i]);
}

return 0;
}