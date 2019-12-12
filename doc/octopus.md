
# octopus 大八爪鱼 砰!砰!

**2016-07 萌芽出现**

知道爬虫这个东西, 是因为一个单词的出现 python 蟒蛇

---

心心念念好长时间 , 没继续了解 , 也不清楚到底什么是爬虫

---

**2018-03 初尝禁果**

在网上照着抄了一个爬取图片的程序 https://gitee.com/lvgo/data-down 很显然, 这使我很开心

---

之后不知道过了多久, 我接连fork了几个项目

https://github.com/code4craft/webmagic  (Scala) 通过xpath解析

再有

https://github.com/lvgocc/ispider 这个项目是受作者的博客 (http://blog.51cto.com/xpleaf/2093952) 影响找到的

还有其他很多博客和项目也看了一些, 主要这两个看了一些源码, 对爬虫才算真正有了一点概念. 知道了一些关于爬虫的事情, 比如 反爬虫,反反爬虫,反反反爬虫,反反反反爬虫,反反反反反爬虫.....

---

**2018-07-04 混沌之梦**

写了许多后台接口 , 发现无处施展, 因为Java底子还不错, 搭了环境开发了个自己的APP, 没错, android app , 当时天真的想着把我的爬虫大梦控制台放在我的android app 上. 结果 android 坑填不上了, 在android的路上越走越远 , 好在后面工作忙, 爬虫项目没搞出来, app 的坑到挖了很深, app 基本页面开发了一些.... 还自己写后台接口 , 集成各种 api , 支付.. 第三方登录.. 定位...

---

**2019-07-21 梦醒时分**

![](../media/0721.jpg) 


终于 ... 我象征性的建了一个仓库 
![](../media/getFromNet.png) 
之后就不了了知了.......

后来

当然, 这段时间没闲着, 除了忙碌的工作以外, 真的很忙.....

我开发了一个多线程任务处理组件, 欢迎大家品尝, 后面的 octopus 里面也有用到, 组件已经发布到中央仓库

https://gitee.com/lvgo/silent

https://github.com/lvgocc/silent

---

**2019-10-22 杨帆!起航**

经历了之前的种种种种, 立下了无数的flag , 这一次, 我不想在逃了

**2019-10-22**

第一个commit, octopus诞生

**2019-11-04**

提交 readme 梳理 octopus 结构, 罗列问题 , 逐个解决补充

**2019-12-10**

octopus 逐渐雏形, 提交第一个demo , 抓取新浪博客数据

**2019-12-11**

完善插拔式配置, 尝试抓取豆瓣影评, 问题随之出现, 在抓取560条记录后, 出现ip问题


![](../media/doubanComment.png) 
![](../media/doubanIPwenti.jpg) 

**2019-12-12**

- [X] 集成 [silent](https://github.com/lvgocc/silent) 组件
- [ ] 代理IP问题

在集成 silent 过程中, 持续增加 octopus 插拔式配置 , 今日增加
1. 超时 timeOut 参数
2. 线程数设置 threadSize
3. 分页大小, 以及固定抓取几页

代理问题可能需要购买代理ip来解决, 暂时放一放, 口子留好, 继续往下走