; 32-bit "Hello World!" in CentOS 6 i686
; nasm -felf32 hello.asm -o hello.o
; ld -s -o hello hello.o

global _start

_start:
    jmp string

code:

    pop     ecx
    mov     eax, 0x4
    mov     ebx, 0x1
    mov     edx, 0xD
    int     0x80

    nop
    nop
    nop
    nop
    nop
    nop
    nop
    nop

string:
    call    code
    db 'Hello world!',0x0a