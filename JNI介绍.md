http://www.cnblogs.com/devinzhang/archive/2012/02/29/2373729.html
关于NDK的介绍如上：
#JNI
###what 什么是JNI？
*java native interface native 本地语言  系统是由什么开发的 linux是由c开发的 那么这种语言对于这个系统来说就是本地语言

*native 本地语言 本地代码

*作用是java 和c/c++相互调用

jni 实质是一个接口 java的方法调用c的函数
jni 相当于是 把c/c++函数翻译成java
也可以把java翻译成c/c++代码

###why 为什么用jni？
*java的优势 一处编译 到处运行 java虚拟机跨平台

  *java想直接访问系统底层的驱动 

  *android里的java代码都是跑在虚拟机里面的  

  *不能直接控制驱动  jni扩展java代码的能力   驱动大部分是由c编写的

  *特斯拉车载智能系统就是用的linux --按下按钮登就开了。

  *c编译生成的就是机器码  java需要虚拟机翻译（解释型语言--效
率低） 大型的3d游戏或者是音视频解码就不能用java

  *android系统是16ms刷新一次屏幕 通过JNI 放到C或者C++来实现

  *抗反编译的能力加强  加密的登录逻辑一定要放在c里面如密文和算法   eg拿到上传包+源码加密逻辑  即可破解 
  
开发者平时编写android时。framwork层连接函数库层也是用的JNI（Native Developer Kit） 
只是google已经提开发者封装好了，提供给开发者一个API。所以我们也用JNI，只是没有感觉到而已。

##how 怎么用JNI
会用 java
c/c++ 能看懂,会调用。
interface JNI的开发流程

#交叉编译
---在一个平台上编译出另一个平台可以运行的本地代码
##cpu平台
 arm架构 x86（Atom） mips
不同架构支持的指令集是有差别的
精简指令和复杂指令集
##操作系统平台
windows linux Mac os unix
模拟另一个平台的特点进行编译

##jni开发工具
NDk native develop kit 本地开发工具
NDK的目录结构
docs 帮助文档
platforms 根据不同的android版本分了不同的文件夹
--include  jni开发常用的头文件
--lib  google官方提供 jni开发中可能用到的库
build\tools   .sh文件linux下批处理文件 系统自动调用 开始交叉编译的过程
samples  样例
sources  ndk相关源码
ndk-build.cmd 开始交叉编译的命令

CDT 高亮显示c关键字 c代码提示

#jni   helloworld
新建一个android工程JNI_01
先写JAVA代码 用native关键字 声明本地方法 本地方法不用实现 在c中实现



看源码

JNIEnv  是一个别名  上面是结构体的指针
#else
typedef const struct JNINativeInterface* JNIEnv;
typedef const struct JNIInvokeInterface* JavaVM;
#endif

    jint        (*GetVersion)(JNIEnv *);
这个是函数体的指针
通过JNIEnv调用

再写玩c之后用ndk-build构建头文件
cd /d 项目所在的目录即可构建头文件
编辑后报错说缺少android.mk文件
补充上mk文件makefile文件
#找到jni目录的路径
LOCAL_PATH := $(call my-dir)
#清除上一次编译的local_Path不会被清除
     include $(CLEAR_VARS)
#编译生成的文件的名字
     LOCAL_MODULE    := hello
#本地资源文件，指定.c的文件   hello.c hello2.c
     LOCAL_SRC_FILES := hello.c 
#指定编译动态链接库.so(linux)  .dll(windows)
     include $(BUILD_SHARED_LIBRARY)
在cd到项目目录下 用ndk-build再编译


##jni开发常见错误
natvie method not found
1.本地函数名字 不符合命名规则
方法名或者包名有下滑线的需要进行+1操作
hello____1111world11___JNi();
用javah生成头文件
如果是jdk1.7以上的就到项目的src目录下看
如果是jdk1.6以下的就到bin/classes在哪运行头文件就到哪去找
2. System.loadlibrary("hello");忘记写了

loader ....findlibrary returned null
###只截取lib之后的modle     libhello
1.System.loadlibrary("libhello")
2.cpu平台不支持（比如  把你个只支持arm处理器的.so文件部署到x86架构下）
这时候就需要解决需要生成x86的so文件---途径如下：
建立一个Application.mk 导入APP_ABI  :x86 armeabi 
也可解决mindsdkversion的问题
APP_PLATFORM :=android-14   #解决警告
再用ndk-build重新编译一次

##jni开发简便流程
先写java代码 用native关键字 声明本地方法 本地方法不需要实现

添加本地支持  选择 adroid toos -》add native support
有的需要 点击window -》preferencs -》android 》ndk

会自动生成jni目录

再用javah生成头文件
用javap获取到方法的签名 javap -s命令（因为java方法有重载所以必须要有签名）
运行 javap 要到项目的bin/classes目录下 javap -s要回调的java方法所在类的全类名

#如何使用ndk
在上节我们写过helloworld之后 我们来些c和java之间的相互调用

1.导入后报错？
2.c代码向logcat输出日志
3.#define c的宏定义 起别名
- #define LOG_CAT "system.out"



再看c如何调java这边的函数
找到项目支持的最小的ndk版本的include


linux下的一秒就是1
sleep（1）；
先利用fork（）函数然后再保活进程
ps 看进程然后看到父进程变为1
进程保活的思路。
思路一： 可以做检测看是否一直为保活，如果被卸载就弹出浏览器和网址
思路二：守护进程
ondestory（）---》里面写startservice（）监听一些系统的广播，开机广播等---rom 去广播权限即不行
进程互相守护：---同时杀死 就不行
