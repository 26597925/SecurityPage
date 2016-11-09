//main.c
//gcc -L /home/mycos/so -Wall -o main main.c -lfso
//export LD_LIBRARY_PATH=/home/mycos/so :$LD_LIBRARY_PATH

#include <stdio.h>

extern void hello(void);

int main()
{
    int i = 0;
    printf("This is Main!\n");
    while(1)
    {
        if(i%10 == 0) printf("\n");
        sleep(1);
        hello();
        i++;
    }   
    return 0;
}
