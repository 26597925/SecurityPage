#android学习记录



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

然后xposed的jar包不能放在libs目录下面。放在自己新建一个lib目录然后bulidpath