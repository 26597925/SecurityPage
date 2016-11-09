//fso.c
//gcc -c -Wall -Werror -fPIC fso.c
//gcc -shared -o libfso.so fso.o

#include <stdio.h>

void hello()
{
    int i = 0;
    int j = 0;
    printf("Hello Myboy!\n");
    for(i=0; i<10000000; i++)
    {
        j++;
    }
}
