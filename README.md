约拍街后端程序
-
搭建wikipedia：

 ![[company plan]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/plan.png)

后端暂时仅使用SpringBoot作单体应用，

项目目前分成两个app，一个为file负责上传下载文件，一个为web负责一般业务逻辑

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/ProStru.png)

其中file app用的是阿里云Oss作为图床；

目前项目配置了swagger，springSecurity，redis，mysql，下划线格式参数自动转驼峰格式的拦截器，有待配置Apollo

**项目目前实现功能：**

1、注册用户，分布式登录、主动登出（或一段时间没有操作自动登出）；

2、用户新建相册，上传照片并加密，删除相册并关联相册中照片以及图床数据；

有待实现：

1、架构有待改成微服务，登录验证逻辑写到Zuul网关上

2、实现其余功能

---

目前数据表设计：

gallery_album--用户相册表

 ![[gallery_album]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/gallery_album.png)

gallery_album_image_rel--相册、图片关联表

 ![[gallery_album_image_rel]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/gallery_album_image_rel.png)

gallery_image--图片表

 ![[gallery_image]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/gallery_image.png)

users--用户信息表

 ![[users]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/users.png)

user_auth--登录认证表，支持多种登录方式

 ![[user_auth]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/user_auth.png)

---
前端用vue编写，目前已设计好布局

网址：[前端域名：www.yuepaijie.com](http://www.yuepaijie.com/)

 ![[layout]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/layout.png)