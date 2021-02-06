约拍街后端程序
-
搭建wikipedia：

 ![[company plan]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/wikiQiHua.png)

后端暂时仅使用SpringBoot作为单体应用，

项目目前分成两个app，一个为file负责上传下载文件，一个为web负责一般业务逻辑

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/ProStru.png)

其中file app用的是阿里云Oss作为图床；

目前应用配置了swagger，springSecurity，下划线格式参数自动转驼峰格式的拦截器

---

数据表设计：

gallery_album--用户相册表

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/gallery_album.png)

gallery_album_image_rel--相册、图片关联表

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/gallery_album_image_rel.png)

gallery_image--图片表

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/gallery_image.png)

users--用户信息表

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/users.png)

user_auth--登录认证表

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/user_auth.png)

---
前端用vue编写，目前已设计好布局

网址：[前端网址：www.yuepaijie.com](http://www.yuepaijie.com/)

 ![[pro-struct]](https://yuepaijie-public.oss-cn-hongkong.aliyuncs.com/humanpub/layout.png)