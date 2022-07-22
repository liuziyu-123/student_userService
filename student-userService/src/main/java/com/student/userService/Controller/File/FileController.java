package com.student.userService.Controller.File;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.student.userService.Dao.ResourcesFile;
import com.student.userService.Dao.User;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.ErrorConstant;
import com.student.userService.Utils.LocalThread;
import com.student.userService.Utils.UUIDUtils;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 对文件的处理
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    /**
     * 文件上传接口
     * @param file
     * @return
     * @throws IOException
     * 对应与前端图片上传路径：http://localhost:8081/file/upload/img
     */
    @PostMapping("/upload/img")
    public ApiResult upload(@RequestParam MultipartFile file, Request request) throws IOException {

        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);       //后缀
        long size = file.getSize();                 //文件大小
        //先存储到磁盘
        File uploadParentFile = new File(fileUploadPath);
        //判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        if (!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        //文件路径与下载接口路径一样
        request.getContext();
        String url = "http://localhost:8081/file/" + fileUUID;
        //把获取到的文件存储到磁盘目录
        file.transferTo(uploadFile);

        ResourcesFile picFile=new ResourcesFile();
        picFile.setId(UUIDUtils.getGUID32());
        picFile.setLocalUrl(originalFilename);
        picFile.setMediaId(uuid);
        picFile.setSize(size);
        picFile.setType(type);



//
//        //存入数据库   根据自己项目的需求
//        Data_Resource data_resource = new Data_Resource();      //数据库实体类
//        data_resource.setR_name(originalFilename);
//        datasMapper.Insert_res(data_resource);

        return ApiResult.success(url);

    }

    /**
     * 通过流的形式下载
     * @param fileUUID
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        //根据文件的标识码获取文件
        File uploadFile = new File(fileUploadPath + fileUUID);
        //设置输出流格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Context-Disposition","attachment;filename="+
                URLEncoder.encode(fileUUID,"UTF-8"));
        response.setContentType("application/octet-stream");
        //读取字节流
        os.write(FileUtil.readBytes(uploadFile));
        os.flush();
        os.close();
    }
}
