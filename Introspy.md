主要是学习Instrospy进行黑盒测试
#先安装cydia substrate框架
然后分别再装 config和Core这两个apk
![](http://i.imgur.com/sut80ss.png)
原理如上




linux:
adb shell dumpsys activity | grep "mFocusedActivity"

windows:
adb shell dumpsys activity | findstr "mFocusedActivity"