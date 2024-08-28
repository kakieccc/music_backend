# music_backend

基于Springboot3的仿QQ音乐在线音乐平台后端实现

##  项目说明

本音乐网站的客户端和管理端使用 **Vue3** 框架来实现，服务端使用 **Spring Boot + MyBatisPlus** 来实现，数据库使用了 **MySQL + Redis**。

## 项目功能

- 音乐播放
- 用户登录注册
- 用户信息编辑、头像修改
- 歌曲、歌单、歌手搜索
- 歌单打分
- 歌单、歌曲评论
- 歌单列表、歌手列表分页显示
- 歌词同步显示
- 音乐收藏、下载、拖动控制、音量控制
- 后台对用户、歌曲、歌手、歌单信息的管理
- 首页轮播图
- 允许用户自建歌单

## 技术栈

### 前端

**Vue3.0 + TypeScript + Vue-Router + Vuex + Axios + ElementPlus + Echarts**

### 后端

**SpringBoot3 + MyBatisPlus + Redis+ minio+swagger3+knife4j**

## 开发环境

JDK： java17

mysql：mysql8.0.33或更高版本

redis：7.0.14

node：16.20.2

IDE：IntelliJ IDEA 2023、VSCode

minio: 下载本地最新即可

## 下载运行

### 前端

1. 将项目下载到本地

    ```
    https://github.com/kakieccc/music_frontend.git
    ```

2. 前端分为客户端和管理端，两个项目中均有Readme进行指导，如果`npm install`时出现报错请先调整node版本一致后重试

### 后端

1. 将项目下载到本地

```
https://github.com/kakieccc/music_backend.git
```

2. 找到项目目录下的`sql`文件夹，里面存放了数据库建表语句
3. 更新pom.xml
4. 修改配置文件`application-dev.properties`。需要修改的地方有：
    - 数据库用户名密码 `spring.datasource.username` 和 `spring.datasource.password`
    - minio用户名、密码`minio.access-key`和`minio.secret-key`

## 注意事项

1. 项目的文件采用minio进行管理。项目中所用的bucketname为user01

2. 对于图片和歌曲的存放目录，其目录结构如下：

    ```
    |-- user01（bucket)
    	|-- img
    		|-- avatorImages（用户头像）
    		|-- singerPic（歌手图片）
    		|-- songListPic（歌单封面）
    		|-- songPic（歌曲封面）
    		|-- swiper（轮播图）
    	|-- song
    ```

    如果需要像数据库中插入数据，图片和歌曲路径需要如下形式：

    ```
    banner: 
    	/img/swiper/1.jpg
    consumer: 
    	/img/avatorImages/user.jpg
    singer: 
    	/img/singerPic/zhangjie.jpg
    song: 
    	/img/songPic/yanhuayileng.jpg 
    	/song/周杰伦-听妈妈的话.mp3
    song_list: 
    	/img/songListPic/pexels-tomtookit-3538721.jpg
    ```

3. 项目采用了Redis进行缓存，请确保运行前开启了Redis和minio服务器

## 待实现功能

- [ ] 首页轮播图
- [ ] 用户自建歌单、上传
- [ ] 歌词上传、修改

