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
SP
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



###堆栈
用来存储临时变量和寄存器的值

###函数参数传递
ARM遵循ATPCS规则，

