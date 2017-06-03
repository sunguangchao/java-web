# Java Web Projects


1. shopping system00
---------------

此项目具有商品管理、前台收银、商品库存等功能，使用java作为开发语言，原生JDBC连接数据库存储数据，没有使用任何框架。来自[zhanglei-workspace](https://github.com/zhanglei-workspace/shopping-management-system#project0)

作者使用的是Oracle数据库，自己改成了MySQL数据库实现。



2. chapter2
------------
《精通Spring4.x企业应用开发实战》第二章，Java Web有关框架方面入门的一个例子。

用Maven进行依赖管理，Spring JDBC实现持久层，Spring实现业务层，Spring MVC负责展现层。



3. bbs_xiaochun
------------
实现了一个比较完整的论坛系统，同样用Maven依赖管理，Spring+Hibernate+SpringMVC。

已经实现的功能：

* 用户登录、注册
* 登录后获得积分，记录登录时间、IP
* 对用户区分权限，不同权限的用户得到的功能不一样
* 回复帖子、删除帖子、发表帖子

要添加的功能：

* 展示所用户回复的帖子
* 管理者对普通用户的锁定和解锁功能

在这个过程中遇到一个Bug：[PWC5988](http://2e1e9233.wiz03.com/share/s/0K7F8P2pbAmP2NYZEn3o7Uuf0ay7pv2eQQ2M2iI2ml3iskyA)

以下是实现的暂时结果：
![](http://o90jubpdi.bkt.clouddn.com/%E8%AE%BA%E5%9D%9B%E9%A6%96%E9%A1%B52.png)
用户登录后点击`帖子管理`这个功能：
![](http://o90jubpdi.bkt.clouddn.com/%E8%AE%BA%E5%9D%9B%E7%94%A8%E6%88%B74.png)
再看数据库里，数据是一致的
![](http://o90jubpdi.bkt.clouddn.com/id=1.png)
换一个用户登录，也是同样的表现，证明功能比较稳定
![](http://o90jubpdi.bkt.clouddn.com/%E8%AE%BA%E5%9D%9B%E7%94%A8%E6%88%B71.png)
![](http://o90jubpdi.bkt.clouddn.com/id=4.png)