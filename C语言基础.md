因为要学习JNI 所以把c的基础又看了一遍。
面试的时候也可能需要用到吧！鹅厂等我~
#C语法基础
1. Helloworld
2. C的基本数据结构
3. 输出函数
4. 输入函数
5. 内存地址的概念
6. 指针 
1. 指针的联系
2. 多级指针
3. 指针和数组的关系
4. 结构体
5. 联合体  枚举 自定义类型

---------------------

##输出 helloworld
1.Target：pritnf--》 helloworld
2.open the Calculator
#include<stdio.h> //import include 包含.h standard 标准输入输出流
#include<stdlib.h>//stdlib 标准函数库 java.lang 
main(){ //public static void main(String args[]){}
printf("hello world!\n");
system("calc");
system("pause");//调用系统的命令 在这是调用windows的命令
}

##C的基本数据类型
java的数据类型：四类八种    c的基本类型
boolean 1                    
byte    1 
char    2                 char    1*
short   2                 short    2
int     4                 int      4
long    8                 long      4*
float   4                 float     4
double  8                 double     8
###c没用boolean 而是用0和非零表示 false 和 true
java一个byte是8位二进制数
第一位是符号位 00010001
符号位为0  表示正数
符号位为1  表示负数
##输出函数
###c char
unsigned 只能用来修饰整形的变量 char short int long
11111111 0~255
singed  最高位是符号位 可以表示负数，但是表示的最大值相对要小
//关键字:sizeof(类型名字) sizeof 变量名
//printf 需要一个占位符
main(){ //public static void main(String args[]){}
printf("int4类型占%d个字节\n",sizeof(int));
printf("char2%d个字节\n",sizeof(char));
printf("float4%d个字节\n",sizeof(float));
printf("double8%d个字节\n",sizeof(double));
printf("lon8g%d个字节\n",sizeof(long));
printf("short4%d个字节\n",sizeof(short));
unsigned char c1=255;
char c2=255;

printf("c1=%d\n",c1);
printf("c2 = %d\n",c2);
system("pause");//调用系统的命令 在这是调用windows的命令
}
##占位符的选用
占位符要与输出的数据类型相匹配 不然会出现精度问题
/*占位符 
%d -  int 
%ld - long 
%lld - long long 
%hd - 短整型
%c --char 
%f -float
%lf -double 
%u -无符号位
%x -十六进制输出 int 或者long int 或者short int
%s  -字符串
*/

//printf 需要一个占位符
main(){ //public static void main(String args[]){}
int  a =10;
char b= 'Z';
long  c=92890;
float f=1.34;
printf("%d\n",a);
printf("%c\n",b);
printf("%ld\n",c);
printf("%.1f\n",f);//控制输出小数的有效位数%.位数f/d
system("pause");//调用系统的命令 在这是调用windows的命令

}
###数组的表示方法
main(){ 
char array[]={'a','b','c','\0'};//array位数数组需要手动添加结束符'\0'结束符也会占用一个字节
//array里面不能直接打印中文
char  arraya[]="abc啊啊啊";
printf("array为%s\n",array);
printf("arraya为%s\n",arraya);
system("pause");//调用系统的命令 在这是调用windows的命令
}

##输入函数
接受用户在控制台输入

java 里面的system.in

c 里面获取用户输入的函数为scanf("%d",内存地址) &为取地址符  c里面取地址符不能越界 使用的时候请注意

//printf 需要一个占位符
main(){ 
printf("请输入班级的人数\n");
int count;
scanf("%d",&count);//&取地址符，通过&把count对应的内存地址取出来
printf("班级的人数为:%d\n",count);
char name[4];
printf("count的内存的值为%d\n",&count);
printf("name的内存的值为%d\n",&name);
printf("请输入班的名字:\n");
scanf("%s",&name);
printf("班级的人数%d,和班级的名字%s\n",count,name);
system("pause");//调用系统的命令 在这是调用windows的命令
}

##内存地址的概念
计算机中一般习惯用16进制的整数表示内存地址
当生命一个变量  就会为这个变量在内存中申请一块内存空间
这个内存一定对应着一个编号，也就是内存地址的内存是没办法使用的
内存与cpu的关系？
答：


32位操作系统  地址总线32位  2^32次方 最大支持4个g
*为什么买来的内存是16g，而实际却没有16g？
显卡等其他硬件会占用系统的内存编号资源，而且还有就是按1000计算

*内存地址有什么用？
玩RPG游戏时如仙剑三，用修改器修改地址
Cheat Engine显示正在运行的内存，
还有金山游侠等修改器。 

##指针

main(){ 
int i=123;
printf("i内存地址%#x \n",&i);
//int*就是定义了类型的指针变量 用来保存int类型的变量的内存地址
//通过指针变量 可以和直接对内存地址保存的值 进行读写操作
//*pointer * pointer在等号右边 就是要读出它指向内存的值  *pointer在等号左边 就是要对它指向的内存的进行赋值的操作
int* pointer =&i;
printf("pointer =%#x \n",pointer);
//*pointer
printf("*pointer==%d\n",*pointer);
printf("pointer =%#x \n",&pointer);
system("pause");//调用系统的命令 在这是调用windows的命令
}


###指针使用常见的错误
1.野指针，未初始化就直接通过指针进行赋值操作
初始化 就是把自己程序中的变量的内存地址 赋值给指针
2.指针类型也要匹配
int类型的指针变量只能用来保存 int类型变量的内存地址

#c语言交换两个值的实现
void swap(int *p1, int *p2){
int tmp=*p1;
*p1=*p2;
*p2=tmp;
}
void main(){
int a=5;
int b=10;
//swap(i,j);引用传递
//值传递 传递的也是值，只不是传递的是变量的内存地址 
swap(&a,&b);
printf("%d\n",a);
printf("%d\n",b);

}
C的优势是有指针 可以直接操作内存
在数据交换中有很大的优势
C中定义int 数组【】一定要在变量名之后

#c语言看数组是否是连续的
//根据输出结果可得char的地址是连续的
static char(){
	char array[]={'a','b','c','d','\0'};
printf("array的地址%d\n",&array[0]);
printf("array的地址%d\n",&array[1]);
printf("array的地址%d\n",&array[2]);
printf("array的地址%d\n",&array[3]);
}
//int类型的数组地址不是连续的
void main(){
int a[]={1,2,23,4,4};
printf("array的地址%x\n",&a[0]);
printf("array的地址%x\n",&a[1]);
printf("array的地址%x\n",&a[2]);
printf("array的地址%x\n",&a[3]);
printf("array的地址%x\n",&a);
}
指针类型要匹配数据类型

#c中字符串和数组类似
char *str="abcd"
char array[]={'a','b','c','d','\0'};
char *str=&array[];


#多级指针
int i=23;
//一级指针
int*p=&i;
//二级指针
int**p1=&p;
//不能这样定义二级指针
int*q=&p;
error:cannot convert int** to int *;


#Main函数获取子函数的临时变量的地址
//这段程序的实质是把int变量i的地址给p1;
void function(int**  p){
int i=200;
*p=&i;
printf("%d\n",&i);
//打印变量i的地址
printf("&i=%d\n",p);
}
//多级指针
void main(){
	int *p1;
function(&p1);
//打印p1指针的地址
printf("p1=%d",p1);
}
在java里面可以retrun出来，但是这样效率会低一些

#指针类型变量的长度
指针类型的变量长度都是一致的
void main(){
printf("int*占%d个字节\n",sizeof(int*));
printf("double*占%d个字节\n",sizeof(double*));
}
结果如下：
int*占4个字节
double*占4个字节
64位编译器为8个字节

#静态内存分配和动态内存分配
##静态内存分配： 
系统统一分配 统一回收 占内存大小固定
char *  function
(){
	char array[]={
	'a','b','c','d'};
//返回首地址
	printf("array的首地址%#x\n",&array);
	return &array[0];
}
char *function2(){
	char array[]={'d','b','c','a'};
printf("%#x\n",&array);
return &array[0];
}

void main(){
char*p=function();
//有返回值的函数不一定要写返回值；
function2();
printf("%d,%d,%d\n",*p,*(p+1),*(p+2));
printf("%d,%d,%d\n",*p,*(p+1),*(p+2));
}

定义一个方法会在栈内分配内存，使用完后会被回收。

##动态内存分配：
#include<stdio.h>
#include <stdlib.h>  
/*
realloc  re alloc 

realloc(malloc的返回值, 重新申请的堆内存的总大小)
如果malloc 申请到的堆内存后有足够大的内存空间 realloc就会在malloc申请到的内存后继续申请足够大的内存
如果  malloc 申请到的堆内存后没有足够大的内存空间  realloc就会找到一块新的足够大的内存 然后把malloc对应的内存中的数据复制过来 然后
把malloc申请到的堆内存释放掉 


*/
main()
{        
    printf("请输入班级人数:");
    int number;
    scanf("%d",&number);
    printf("班级的人数为%d\n",number);
    //申请刚好够大的堆内存 保存number个学生的学号 
    int* p = malloc(sizeof(int)*number);
//score = (int *)malloc(n * sizeof(int));
//malloc的返回值是 void 用强制转换
    int i;
    for(i = 0;i<number;i++){
          printf("请输入第%d个学生的学号",i+1);
          scanf("%d",p+i); 
          }
    for(i = 0;i<number;i++){
          printf("第%d个学生的学号是%d\n",i+1,*(p+i));
          }
    printf("请输入插班生的人数");
    int increment;
    scanf("%d",&increment);
     p = realloc(p,sizeof(int)*(number+increment));
     for(i = number;i<number+increment;i++){
             printf("请输入第%d个学生的学号",i+1);
          scanf("%d",p+i); 
           }
     for(i = 0;i<number+increment;i++){
          printf("第%d个学生的学号是%d\n",i+1,*(p+i));
          }
        system("pause");            
       }


##结构体
大小：大于或者等于每一个元素大小的和   而且是最大的元素所占节数的整数倍
//c中结构体不能直接定义函数
//要使用函数就用函数指针
函数指针 定义方式: 返回值(*函数指针名字)(形参){
函数指针指向的函数 要跟函数指针定义的返回值和形参类型保持一致
}


###结构体的指针

/*
struct 结构体
在java中相当于是class
*/

void study(){
printf("好好学习  天天向上");
}

struct Student{
float score;//4
short age;//2
char sex;//1
//c中结构体不能直接定义函数
//要使用函数就用函数指针
//函数指针的定义方式：返回值()(形参)
void (*studyP)();
}

main(){
	struct Student stu = {2.2f,22,'f'};
stu.age=20;
//调用函数时编译器报错
//stu.study();
printf("%d\n",stu.age);
//看结构体的大小
printf("%d\n",sizeof stu);

//采用结构体指针来调用结构体里面的方法和函数
/*通过结构体类型的定义
struct Student* A=结构体的地址
直接引用和间接引用
*/
//直接引用
  struct Student * stup =&stu;
(*stup).age=110;
printf("%d\n",(*stup).age);
//间接引用
stup->age=220;
printf("%d",(*stup).age);
//方法这里一直报错
stup->studyP;
}


#联合体
include<stdio.h>
include<stdlib.h>
/*
联合体  公用体
联合体所有元素公用同一块内存 它的大小  跟最大的变量所占的大小相同
联合体 对不同的元素多次赋值只有最后一次有效

  在嵌入式开发中 片上内存很小 所以经常使用联合体
*/

union un{
	int i;
	short s;
};

main(){
union un u;
u.i=23;
u.s=20;
printf("u.s=%d\n",u.s);
}


#枚举
//声明枚举类  enum 定义枚举类 类型的取值范围只能从枚举值中取
//typedef type define
enum WeekDay{
//设置a为1 则从一开始递增
	a=1,b,c,d,e,f
};

main(){
//调用枚举类
enum WeekDay day1=a;
enum WeekDay day4=d;
printf("%d\n",day1);
printf("%d\n",day4);
}
(注意枚举类的声明形式)


#自定义类型
在jni中 typedef void* jobject;
给任意类型的指针void* 起别名为jobject
typedef jobject jclass;
typedef jobject jarray;
typedef jarray jobjectArray;
其实都是表示void*  实质上是为了增强代码的可读性表示在java那边处理的是那种的数据类型

