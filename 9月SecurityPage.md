---------------9/8日
#AccessilityService

- http://www.jianshu.com/p/65afab3d1e2a
- http://blogs.360.cn/360mobile/2016/09/07/research_of_accessibility/


#解决genymotion 无法使用adb的问题
- Genymotion设置->Setting->ADB->use custom Android SDK tools
- 换成自己的sdk即可解决

#drozer进行渗透测试
http://bobao.360.cn/learning/detail/158.html
http://download.csdn.net/download/behappy1987/7122875


#dexclassloader
    不是普通类怎么办？
    
    系统组件如何接收回调？
    
    如何加载资源？

    PackageInfo处理？

    Resources的处理？

    Assets的处理？


#dex分包
原因一个dex里面方法数目不能超过65536个

或者一个dex文件不能超过65k
所以需要分包/或者动态加载



##xposed学习

    

- 替换系统的app_process（当然，这个操作需要Root权限）
app_process是什么看init.rc？
    service zygote /system/bin/app_process -Xzygote /system/bin –zygote –start-system-server
    socket zygote stream 666 
    onrestart write /sys/android_power/request_state wake
    onrestart write /sys/power/state on
    onrestart restart media
    onrestart restart netd
    
app_process是andriod app的启动程序（具体形式是zygote fork()调用一个 app_process作为Android app的载体）




- 将xposed的api文件,XposedBridge.jar文件放置到私有目录中并且buildpath
- 注意配置模块meta-data
- 设置一个main类来实现xposed的接口
- 然后新建一个xposed_init文件，声明主入口类



###然后：Android Xposed框架出现java.lang.IllegalAccessError: Class ref in pre-verified class resolved to unexpected implementation问题



- 然后xposed的jar包不能放在libs目录下面。放在自己新建一个lib目录然后bulidpath

findAndHookMethod找到要hook的包名函数名等beforehook和afterhook改变原来代码的执行逻辑



- 但是每次重启太麻烦，而且还需要root权限
- 进阶的可以用缩水版的dexposed或者Andfix或者360的DroidPlugin


我理解的：双开其实就把原来的apk文件再执行一遍：应用程序本身是运行在虚拟机上的，再虚拟一次。
一般apk安装是移动到
/data/app目录下，
再建一个/data/data/packagename的目录
还有/davlik/cache存放优化的odex
双开就是不安装，但同时完成上面的过程
欺骗虚拟机，并虚拟出对应运行环境。在 /sdard/parallel/ 下有对应的目录虚拟内置存储的目录。但是有时候双开打开文件存储的应用会失败。是因为存储空间是虚拟出来模拟系统存储的。比如小米的双开应用两个存储网络图片的位置就不同。

那么应用分身又与双开是什么关系呢?
分身不是创建了图标吗?有没有app2?


