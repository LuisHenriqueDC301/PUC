#include <stdio.h>


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
void palindromo(char *palavra){

    int res = 1;
    int n = lenght(palavra);
    for(int i = 0; i < n / 2;i++){
        if(palavra[i] != palavra[n - i - 1]){
          res = 0;
          i = n;
        }
    }
    if(res){
        printf("SIM\n");
    }else{
        printf("NAO\n");
    }
}
int main(){
    char palavra[500];
    scanf("%[^\r\n]%*c", palavra);
    
    while(compare(palavra, "FIM") != 1){
        palindromo(palavra);
        scanf("%[^\r\n]%*c", palavra);
    }
    
    
    return 0;
}