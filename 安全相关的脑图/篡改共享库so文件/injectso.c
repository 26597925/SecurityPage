//injectso.c
//gcc injectso.c -o injectso
#include <sys/ptrace.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/user.h>
#include <sys/file.h>
#include <stdio.h>
#include <string.h>

const int long_size = sizeof(long);

void getdata(pid_t child, long addr, char *str, int len)
{
    char *laddr;
    int i,j;
    union u{
        long val;
        char chars[long_size];
    }data;

    i = 0;
    j = len / long_size;
    laddr = str;
    while(i < j){
        data.val = ptrace(PTRACE_PEEKDATA, child, addr + i*4, NULL);
        if (data.val < 0) {
            printf("getdata1 Failed! \n");
            return;
        }

        memcpy(laddr, data.chars, long_size);
        ++i;
        laddr += long_size;
    }
    j = len % long_size;
    if(j != 0){
        data.val = ptrace(PTRACE_PEEKDATA, child, addr + i*4, NULL);
        if (data.val < 0) {
            printf("getdata2 Failed! \n");
            return;
        }
        memcpy(laddr, data.chars, j);
    }
    str[len] = ' ';
}

void putdata(pid_t child, long addr, char *str, int len)
{
    char *laddr;
    int i,j;
    union u{
        long val;
        char chars[long_size];
    }data;

    long rst; 

    i = 0;
    j = len / long_size;
    laddr = str;
    while(i < j){
        memcpy(data.chars, laddr, long_size);
        rst = ptrace(PTRACE_POKEDATA, child, addr + i*4, data.val);
        if (rst < 0) {
            printf("Putdata1 Failed! \n");
            return;
        }
        ++i;
        laddr += long_size;
    }
    j = len % long_size;
    if(j != 0){
        memcpy(data.chars, laddr, j);
        rst = ptrace(PTRACE_POKEDATA, child, addr + i*4, data.val);
        if (rst < 0) {
            printf("Putdata2 Failed! \n");
            return;
        }
    }
}

long getsobaseaddr(pid_t pid, char* soname)
{
    FILE *fp;
    char filename[30];
    char line[100];
    long addr;
    char str[100];
    sprintf(filename, "/proc/%d/maps", pid);
    fp = fopen(filename, "r");
    if(fp == NULL)
        return 1;
    while(fgets(line, 100, fp) != NULL) {
        sscanf(line, "%x-%*s %*s %*s %*s %*s %s", &addr, 
               str, str, str, str, str, str);
        if(strstr(str, soname) != NULL)
            break;
    }
    fclose(fp);
    return addr + 0x437; //offset
}

int main(int argc, char *argv[])
{
    pid_t traced_process;
    struct user_regs_struct regs;
    int len = 49;

    /* hello world */
    char code[] =
        "\xe9\x1a\x00\x00\x00\x59\xb8\x04"
        "\x00\x00\x00\xbb\x01\x00\x00\x00"
        "\xba\x0d\x00\x00\x00\xcd\x80\x83"
        "\xc4\x24\x5b\x5d\xc3\x90\x90\xe8"
        "\xe1\xff\xff\xff\x48\x65\x6c\x6c"
        "\x6f\x20\x77\x6f\x72\x6c\x64\x21"
        "\x0a";

    if(argc != 2) {
        printf("PID?\n");
        return 1;
    }

    traced_process = atoi(argv[1]);
    ptrace(PTRACE_ATTACH, traced_process, NULL, NULL);
    int pid = wait(NULL);
    printf("Attach Pid: %d\n",pid);
    ptrace(PTRACE_GETREGS, traced_process, NULL, &regs);

    long helloaddr = getsobaseaddr(traced_process, "libfso.so");
    printf("Inject So Addr: %p\n", helloaddr);
    putdata(traced_process, helloaddr, code, len);

    ptrace(PTRACE_SETREGS, traced_process, NULL, &regs);
    ptrace(PTRACE_DETACH, traced_process, NULL, NULL);
    return 0;
}