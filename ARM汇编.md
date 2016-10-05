http://staff.ustc.edu.cn/~llxx/embedded/slides/llxx7.pdf
教程

##apk文件用二进制查看工具打开是
- 有个很明显的一个pk
- 然后将apk文件拖入ida里面
- 点击里面出现的elf（shared object）文件



点击查看ida里面的elf文件
    
    然后用ida进行动态调试
    1.push ida目录下的android_server 到手机的/data/local/tmp目录下
    2.启动服务端口
    chmod 777 android_server
    ./android_server
    3.端口转发
    adb forword tcp: pc_port tcp:mobile_port
    4.ida调式端口设置
    Debugger-》Process Option -》hostname 
    5.启动程序
    am startactivity -D -n **/.*
    6.连接jdb
    jdb connect com.sun.jdi。。。


看寄存器窗口：
R1~R12个
SP：栈顶指针
LR：返回位置的寄存器
PC：ip寄存器，执行位置的寄存器
PSR

堆栈：可以让我们存一些临时的信息

标志位：
N
Z
C
V
Q
F
T
MODE


基本指令：


    BL：目标地址
    子函数调用指令
    直接修改pc的值，并把下一跳指令的(返回地址)赋值LR寄存器中 
    相当于是x86中的call指令



    mov 目标寄存器，源寄存器
    目标寄存器=源寄存器
    是一个赋值操作

    LDR：




so文件的两款分析工具
readelf和so helper

elf格式：
elf header
section header
program  header
符号表
动态节


###so的加载顺序
So  load-Init_array -JNI_onLoad 其他的奇怪函数 -fini_array

#函数平衡原理
使用了多少堆栈空间，完成后就释放多少空间

###堆栈
用来存储临时变量和寄存器的值

###函数参数传递
ARM遵循ATPCS规则，前四个参数用R0~R3寄存器

##堆空间：

##栈空间：

堆，队列优先,先进先出（FIFO—first in first out）
栈，先进后出(FILO—First-In/Last-Out)

###重定位：000 ->偏移为120

加上当前pc+偏移地址

内存偏移

pc指针分三个流程来执行命令

                 取址--分析--运行 3
          取址---分析--运行       2
    取址---分析---运行            1
三个进程同时执行：


#堆栈保护
push {R7，LR}
sub  SP，SP 

mov sp，R7
po {R7，pc}


一个完整的arm汇编包括哪些？
    AREATigerJohn,CODE,READONLY；声明代码段

     CODE32   ；声明为32位ARM指令
     ENTRY ；声明程序入口
     START   MOV  R0 ,#0
     MOV R1 ,#1
     ADD R1, R1,R0
     B START
     END
    
    


###堆栈校验代码
stack_check_guard

###c++中取字符长度
strlen(s)

###数组初始化函数
j_j+meset(s,0,0x14u)
把数组里面都填0


##ARM和Thumb
    arm占4个字节码
    thumb每个指令占2个字节码
    
    
    BX和BLX等指令在跳转的时候实现切换
    
    ALT+G切换识别版本


###opcode 字节码


###指令偏移


##爆破修改so后  不需要重新打包 直接push到指定位置即可




##加壳后的elf文件
elf-header
programe-header
修改session