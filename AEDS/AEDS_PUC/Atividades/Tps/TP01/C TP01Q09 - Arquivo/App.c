#include <stdio.h>

int main() {
    FILE *file;
    int n;
    double value;

    file = fopen("numbers.bin", "wb");
    scanf("%d", &n);


    for (int i = 0; i < n; i++) {
        scanf("%lf", &value);
        fwrite(&value, sizeof(double), 1, file);
    }

    
    fclose(file);


    file = fopen("numbers.bin", "rb");

    
    for (int i = n - 1; i >= 0; i--) {
        fseek(file, i * sizeof(double), SEEK_SET);
        fread(&value, sizeof(double), 1, file);
        printf("%g\n", value);                   
    }

    fclose(file);

    return 0;
}