# Java课程设计 职工信息管理系统
## 课程设计要求
    1. 设计要求：登陆界面，用户注册
      试设计一职工信息管理系统，使之能提供以下功能：
        - 系统以菜单方式工作
        - 职工信息录入功能(职工信息用文件保存)－－输入职工信息浏览功能 －－输出
        - 查询和排序功能：(至少一种查询方式)－－算法
        - 按工资查询
        - 按学历查询等
        - 职工信息删除、修改功能(任选项)
    2. 设计提示：
        职工信息包括职工号、姓名、性别、年龄、学历、工资、住址、电话等（职工号不重复）。
    3. 在线交流平台

## 创建数据库

    1.创建数据库
      create database Staff_Manager; 
      
    2.创建表
      (1) 职工信息表（职工号、姓名、工资、性别、年龄、学历、住址、电话）
            CREATE TABLE Staff_info(
            Sid VARCHAR(20) NOT NULL,
            Sname VARCHAR(20) NOT NULL,
            Wages varchar(20) not null,
            Ssex VARCHAR(20) NOT NULL,
            Sage VARCHAR(20) NOT NULL,
            Sli VARCHAR(20) NOT NULL,
            Saddr VARCHAR(20) NOT NULL,
            Stel VARCHAR(20) NOT NULL,
            PRIMARY KEY ( Sid )
            )ENGINE=InnoDB DEFAULT CHARSET=utf8;
      
      (2) 用户表（账号，密码）
            CREATE TABLE Admin(
            Sid VARCHAR(20) NOT NULL,
            pwd varchar(20) not null,
            PRIMARY KEY ( Sid )
            )ENGINE=InnoDB DEFAULT CHARSET=utf8;
    3. 插入测试数据
        INSERT INTO Admin (Sid,pwd)VALUES('20182033','123456');
        INSERT INTO staff_info(Sid,Sname,Wages,Ssex ,Sage ,Sli,Saddr,Stel)
                        values('20182033','coco','12000','女','25','专科','江西','1********8');
        INSERT INTO staff_info(Sid,Sname,Wages,Ssex ,Sage ,Sli,Saddr,Stel)
                        values('20182034','Aravy','15000','男','29','本科','江苏','1********9');
        INSERT INTO staff_info(Sid,Sname,Wages,Ssex ,Sage ,Sli,Saddr,Stel)
                        values('20182035','Amy','13000','女','29','专科','湖南','1********2');

## 连接数据库

## 图形界面设计

   1. frame.setLayout(null);

    未设置Layout时，java默认为flowLayout布局的，设置为null即为清空布局管理器，
    之后添加组件，常常是设置组件左上角坐标相对于容器左上角（0，0）的x,y值来确定组件的位置，
    即使更改容器大小也不会改变位置。这种方式常常用于窗体大小固定的容器里。