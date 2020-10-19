package com.yuepaijie.usercontroller;

import com.yuepaijie.ImgFileService;
import com.yuepaijie.obj.vo.RestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/imgFile")
public class ImgFileController {

    @Autowired
    ImgFileService imgFileService;

    /**
     * 接收上传的文件，并且将上传的文件存储在指定路径下
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/upload")
    public RestEntity upload(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request) {
        return imgFileService.saveImgFile(file, request);
    }
}
