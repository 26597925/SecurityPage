#elf文件的格式
##elf head
存储so文件最为基本的信息，如so运行的平台。program header的数量 section header数量。重要性等同于dex header

##Elf Section header
存储so的链接信息。主要是用于给外部程序 详细地提供so的信息。比如第几行对应哪个函数，什么名字。对应源码位置等
ida就是读取该头信息进行so分析

360加固好像就是混淆填充垃圾数据到section table，然后就导致ida无法正常分析
解决办法：自定义linker


##ELF program header
存储so文件运行时需要的信息被linker读取然后加载so
对应Segemnt mapping

##ELF symbol table
导入的所有函数

##elf文件变形和保护
1.Section端处理
删除section table的所有值
修改offset
2.program段处理
增加tag混淆
解决办法：
只看最后一个tag


https://segmentfault.com/a/1190000007008889#articleHeader7

##跟踪初始化函数时，我们就知道可以将断点设在 do_dlopen 或是 CallConstructors。

SO: 即被保护的目标 SO。
loader: 自身也是一个 SO，系统加载时首先加载 loader，loader 首先还原出经过加密、压缩、变换的 SO，再将 SO 加载到内存，并完成链接过程，使 SO 可以正常被其他模块使用。
加壳工具: 将被保护的 SO 加密、压缩、变换，并将结果作为数据与 loader 整合为 packed SO。

时机：
SO 的 init 或 initarray
jni_onload