package com.yuepaijie.service;

import java.io.*;

import com.yuepaijie.common.obj.vo.RestEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Service
@PropertySource(value = {"classpath:config/globalValue.properties"})
public class ImgFileService {

    @Value("${file.img.path}")
    String filePath;

    public RestEntity saveImgFile(MultipartFile file, HttpServletRequest request){
        //文件上传
        if (!file.isEmpty()) {
            try {
                //图片命名
                String newCompanyImageName = "newPIC";
                String newCompanyImagepath = filePath+newCompanyImageName;
                File newFile = new File(newCompanyImagepath);
                if (!newFile.exists()) {
                    newFile.createNewFile();
                }
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new RestEntity(400,"图片上传失败");
            } catch (IOException e) {
                e.printStackTrace();
                return new RestEntity(400,"图片上传失败");
            }
        }
        return new RestEntity(400,"图片上传失败");

    }
}