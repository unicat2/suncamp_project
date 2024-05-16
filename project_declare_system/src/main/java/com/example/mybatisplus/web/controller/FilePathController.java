package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.FileUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.FilePathService;
import com.example.mybatisplus.model.domain.FilePath;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


@Controller
@RequestMapping("/file")
public class FilePathController {

    private final Logger logger = LoggerFactory.getLogger( FilePathController.class );

    @Autowired
    private FilePathService filePathService;



    /*
    单文件上传
    */
    @RequestMapping("/upload")
    @ResponseBody
    public JsonResponse upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id,@RequestParam("phoneNumber") String phoneNumber) {

        FilePath filePath = new FilePath().setFormId(id).setPhoneNumber(phoneNumber);
        filePath.setPath(FileUtil.upload(file,"resource/"));
        filePathService.saveOrUpdate(filePath);

        return JsonResponse.success("success");
    }




    /*
    多文件上传
    */
    @PostMapping("/uploadBatch")
    @ResponseBody
    public String uploadBatch(@RequestParam("files") MultipartFile[] files) {
        logger.info("文件名称："+ files );
        if(files!=null&&files.length>0){
            String filePath = "D:\\datafile\\";
            for (MultipartFile mf : files) {
                // 获取文件名称
                String fileName = mf.getOriginalFilename();
                // 获取文件后缀
                String suffixName = fileName.substring(fileName.lastIndexOf("."));
                // 重新生成文件名
                fileName = UUID.randomUUID()+suffixName;

                if (mf.isEmpty()) {
                    return "文件名称："+ fileName +"上传失败，文件为空!";
                }
                File dir = new File(filePath + fileName);
                try {
                    // 写入文件
                    mf.transferTo(dir);
                    logger.info("文件名称："+ fileName +"上传成功");
                } catch (IOException e) {
                    logger.error(e.toString(), e);
                    return "文件名称："+ fileName +"上传失败";
                }
            }
            return "多文件上传成功";
        }
        return "上传文件不能为空";
    }




    /*
    文件下载
    */
    @GetMapping("/download")
    public void download(@RequestParam("id") Integer id, HttpServletResponse response,@RequestParam("phoneNumber") String phoneNumber){

        FilePath filePath = new FilePath().setFormId(id).setPhoneNumber(phoneNumber);

        try {
            FileInputStream fileInputStream = new FileInputStream(new File(filePath.getPath()));
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            //关闭资源
            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

