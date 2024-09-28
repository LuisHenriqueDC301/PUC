#include <stdio.h>
#include <string.h>
int contMaisc(char *palavra){
    int i = 0;
    int n = 0;
    while (palavra[i] != '\0')
    {
        if(palavra[i] >= 'A' && palavra[i] <= 'Z'){
            n++;
        }
        i++;
    }
    printf("%d\n", n);
}
int main(){
    char palavra[500];
    scanf("%[^\r\n]%*c", palavra);
    
    while(strcmp(palavra, "FIM") != 0){
        contMaisc(palavra);
        scanf(" %[^\r\n]%*c", palavra);
    }
    
    
    return 0;
}

