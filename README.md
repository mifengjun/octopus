# octopus

[![GitHub license](https://img.shields.io/github/license/lvgocc/octopus)](https://github.com/lvgocc/octopus/blob/master/LICENSE)

octopus > pa!! pa!!


# 八爪鱼 砰砰

## 整体架构


### 数据提取

#### Octopus


通过基于Jsoup进行简单封装后集成多项自定义可插拔式配置项来进行数据连接, 其中包括
```$xslt
a. 数据请求方式 , 默认为 GET请求
b. 请求头, 按需设置, 不需要可不配置
c. 是否翻页抓取
d. 抓取页码, 抓几页
e. pageSize每页数据量
f. threadSize 线程数, 默认支持多线程
g. 请求超时时间 , 单位毫秒
h. 设置代理
```


### 数据解析


#### Extractor

数据解析为用户自定义内容, 需实现接口该支接口

```java
interface Extractor {
    /**
    * 分页信息
    * 
    * @param octopus 
    * @return 
    */
    OctopusPage getPageInfo(Octopus octopus);
    
    /**
    * 数据解析
    * 
    * @param octopus
    */
    void extract(Octopus octopus);
    
    /**
    * 实现自定义内容解析
    * 
    * @param octopus
    * @param element
    */
    void elementHandle(Octopus octopus, Element element);
    
    /**
    * 默认提供并发处理解析方法
    * 
    * @param octopus
    * @param elements
    */
    default void concurrentHandle(Octopus octopus, Elements elements);
}
```


### 数据处理
### 数据存储
### 数据展示


----


## 实现

### 第一步, 获取页面全部内容

提供一个目标站点
```
url = "https://www.lvgo.org";
```

使用``jsoup``拿到网页信息

> 这里有个问题. 网页中异步数据如何获取?

上有政策, 下有对策, 对返回的页面进行分析, 找出其中的请求地址, 在数据解析过程中, 模拟请求, 取得返回数据. 类似案例:豆瓣影评

网页信息其中包括

1. 更多信息网页链接
2. 网站资源(文字、图片、视频、音乐等等)

### 第二步, 提取所需内容

针对不同的网站内容, 要有不同的解析方案. 并做好对应信息的存储.

### 第三步, 数据可视化

对保存的数据进行数据处理、分析、可视化。

## 技术难点

### 访问

针对各种网站的访问限制机制进行分析, header/cookie等信息的预置.

### 反反爬虫

对网站的反爬虫进行处理. 目前通过IP代理来进行简单处理.

### 可用性

对于应用启动后, 如何实现一直再爬, 一直有数据可爬.

### 高效

数据量获取的效率如何保证

### 数据存储

保存数据格式选择的方式, 文本或数据库, 与其对应的具体格式. 数据库的选择.

### 扩展性*

程序需要具有一定的扩展能力, 升级拓展必须要有, 目前来看建议采用接口式编程

### 程序监控

对程序赋予日志&报警机制.

## 功能列表

- [X] 已实现
- [ ] 有想法,未实现