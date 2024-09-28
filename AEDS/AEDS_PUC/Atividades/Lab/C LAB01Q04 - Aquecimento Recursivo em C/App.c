#include <string.h>
#include <stdio.h>

int contarLetrasMaisRec(const char* palavra, int i, int n) {
    int resultado;


    if (palavra[i] == '\0') {
        resultado = n;
    } else {

        if ((palavra[i] >= 'A' && palavra[i] <= 'Z')) {
            n++;
        }
 
        resultado = contarLetrasMaisRec(palavra, i + 1, n);
    }

    return resultado;
}


int contarLetrasMais(const char* palavra) {
    return contarLetrasMaisRec(palavra, 0, 0);
}

int lenght(char *palavra){
    int i = 0;
    while( palavra[i] != '\0'){
        i++;
    }
    return i;
}

int compare(char *palavra1, char *palavra2){
    int result = 1;
    int n =  lenght(palavra1);
    if( n != lenght(palavra2)){
        result = 0;
    }else{
        for(int i =0; i < n;i++){
            if(palavra1[i] != palavra2[i]){
                result = 0;
                i = n;
            }
        }
    }
    return result;
}
int main(){
    char palavra[500];
    scanf("%[^\r\n]%*c", palavra);
    
    while(compare(palavra, "FIM") != 1){
        printf("%d\n",contarLetrasMaisRec(palavra,0,0));
        scanf("%[^\r\n]%*c", palavra);
    }
    
    
    return 0;
}