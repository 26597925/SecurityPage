##java反射
大前提：Class.forName(xxx.xx.xx) 返回的是一个类对象 

任何一个class都需要装载在虚拟机上才能运行。

用包名和类名去实列化对象：
A a=（A）class.forname("package.A").newinstance;

在执行class.forname()的过程中会由jvm查找并加载指定的类，也就是会执行类里面的静态函数。（病毒可以思考恶意代码写在静态代码里面，直接jvm反射调用加载）



##new与newInstance的区别：
new：


- 使用时，类可以没有被jvm加载。
- 强类型，能调用任何public构造函数。


而newinstance：
- 这个类已经加载了
- 这个类已经连接了
- 弱类型，低效率，只能构造调用无参构造函数。


#import关键字和classloader动态加载类的区别：
###import引用类的两个特点
1，必须存在于本地，当程序运行该类时，内部类装载器会自动装载该类。
2，编译时必须在场，否则无法引用文件而编译失败

###classloader特点与import相反，而且classloader更加灵活自由。
classloader的父类bootstrap classloader先找该类如果找不到，再让classloader去找。保证jvm的安全机制也叫双亲委托机制。任何一个自定义ClassLoader加载一个类之前，它都会先委托它的父亲ClassLoader进行加载，只有当父亲ClassLoader无法加载成功后，才会由自己加载。







- Class<?> loadClass(String name) 
- Class<?> loadClass(String name, boolean resolve) 
- 第二个参数是用于连接true/false
- 在JVM加载类的时候，需要经过三个步骤，装载、连接、初始化。
- 装载就是找到相应的class文件，读入JVM
- 连接:连接分三步，第一步是验证class是否符合规格，第二步是准备，就是为类变量分配内存同时设置默认初始值，第三步就是解释，而这步就是可选的，根据上面loadClass方法的第二个参数来判定是否需要解释，所谓的解释根据《深入JVM》这本书的定义就是根据类中的符号引用查找相应的实体，再把符号引用替换成一个直接引用的过程。
 

#试一试pathclassloader
出现的错误有：
login--》打开safephone的homeactivity失败







##正常dexclassloader加载的流程会有一个DexOpt产生odex过程
底层c语言也是能解析dex文件的




#找出广告是那个包
adb shell dumpsys statusbar | grep notification=Notification
这条命令可以找出状态栏通知的包名，进而找到是哪个应用。
adb shell dumpsys activity | grep mFocusedActivity
显示栈顶的activity



























#eclispe换成jdk1.7之后报错
[2016-09-10 12:53:13 - Login] ------------------------------
[2016-09-10 12:53:13 - Login] Android Launch!
[2016-09-10 12:53:13 - Login] The connection to adb is down, and a severe error has occured.
[2016-09-10 12:53:13 - Login] You must restart adb and Eclipse.
[2016-09-10 12:53:13 - Login] Please ensure that adb is correctly located at 'F:\sdk\platform-tools\adb.exe' and can be executed.


    然后更换sdk的location
    elipse->window->preferences->android->SDK Location->选择本地android的sdk路径