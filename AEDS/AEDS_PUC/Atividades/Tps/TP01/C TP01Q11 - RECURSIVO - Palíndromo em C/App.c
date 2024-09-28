#include <stdio.h>
#include <stdbool.h>

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
bool Palindromo(const char *palavra, int i, int j) {
    return (i >= j) || (palavra[i] == palavra[j] && Palindromo(palavra, i + 1, j - 1));
}
int main(){
    char palavra[500];
    scanf("%[^\r\n]%*c", palavra);
    
    while(compare(palavra, "FIM") != 1){
        if(Palindromo(palavra,0,lenght(palavra)-1)){
            printf("SIM\n");
        }else{
            printf("NAO\n");
        }
        
        scanf("%[^\r\n]%*c", palavra);
    }
    
    
    return 0;
}