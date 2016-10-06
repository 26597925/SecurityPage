#elf head
存储so文件最为基本的信息，如so运行的平台。program header的数量 section header数量。重要性等同于dex header

#Elf Section header
存储so的链接信息。主要是用于给外部程序 详细地提供so的信息。比如第几行对应哪个函数，什么名字。对应源码位置等
ida就是读取该头信息进行so分析

#ELF program header
存储so文件运行时需要的信息被linker读取然后加载so


https://segmentfault.com/a/1190000007008889#articleHeader7




