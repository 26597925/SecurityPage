IDA使用操作：
地址是基址0x00400000 + 偏移地址8FEEC，假如我们使用WinHex打开查看，可以看到偏移地址8FEEC 处的数据

修改ida里面的汇编指令ALT+F2

显示opcaode
opinions-》general

Edit -> Patch program -> assemble 
如修改显示vip的那个GGTest的列子


##破解思路：GGtest.apk
###静态
1.修改smali代码
2.修改字符串名称
3.修改带码逻辑
###动态
hook某个函数代码