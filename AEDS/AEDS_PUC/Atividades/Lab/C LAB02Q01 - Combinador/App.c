#include <stdio.h>
#include <string.h>
void combinador(char *palavra1, char *palavra2){
    int p1 = strlen(palavra1), p2 = strlen(palavra2);
    int n = 0;
    char palavra3[p1+p2];
    
    if(p1 > p2){
        n = p1;
    }else{
        n = p2;
    }
    for(int i = 0; i < n; i++){
        if(i < p1){
            printf("%c", palavra1[i]);
        }
        if(i < p2){
            printf("%c", palavra2[i]);
        }
        
    }
}
int main(){
    int x;
    char palavra1[500];
    char palavra2[500];
    
    while(scanf("%s %s", palavra1, palavra2)== 2){
        combinador(palavra1, palavra2);
        printf("\n");
    }
    
    return 0;
}