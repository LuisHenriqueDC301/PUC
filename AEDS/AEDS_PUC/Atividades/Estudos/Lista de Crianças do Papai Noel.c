#include <stdio.h>
#include <string.h>
#include <stdlib.h>
void ordemAlpha(char*array[], int n){
    int ls = n - 1;
    int nls;
    for(int i= 0; i < n;i++){
        nls = 0;
        for(int j = 0; j < ls; j++){
            if(array[j][0] > array[j+1][0]){
                char* aux = array[j+1];
                array[j + 1] = array[j];
                array[j] = aux;
                nls = j;
            }
        }
    }
}
char* removeChar(char* palavra){
    char *s1 = (char*)malloc((strlen(palavra)+1 * sizeof(char)));
    int cont = 0;
    for(int i = 0; i < strlen(palavra); i++){
        if(!(palavra[i] == '+' || palavra[i] == '-' || palavra[i] == ' ')){
            s1[cont] = palavra[i];
            cont++;
        }
        
    }
    s1[cont] = '\0';

    return s1;
}

int main(){
    int n = 0;
    int contmais = 0;
    char palavra[100];
    
    scanf(" %d", &n);
    char*array[n];

    for(int i =0; i<n; i++){
        scanf(" %[^\n]", palavra);
        if(palavra[0] == '+'){
            contmais++;
        }
        array[i] = removeChar(palavra);
    }

    ordemAlpha(array, n);
    for(int i = 0; i < n; i++){
        printf("%s\n",array[i]);
    }
    printf("Se comportaram: %d | Nao se comportaram: %d", contmais, n-contmais);
    return 0;
}
