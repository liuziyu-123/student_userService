package com.student.userService.Service.Impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.student.userService.Domain.Entry.ResourcesFileEntry;
import com.student.userService.Mapper.FileMapper;
import com.student.userService.Service.FileService;
import com.student.userService.Utils.ApiResult;
import com.student.userService.Utils.MyException;
import com.student.userService.Utils.ResourceUtils;
import com.student.userService.Utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 1.@Description:
 * 2.@author:liuziyu
 * 3.@Time:2023/1/10
 **/
@Service
public class FileServiceImpl implements FileService {

    @Value("${files.upload.path}")
    private String fileUploadPath;


    @Value("${files.download.path}")
    private String fileDownloadPath;

    private FileMapper fileMapper;

    @Override
    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        //后缀
        String type = FileUtil.extName(originalFilename);
        //文件大小
        long size = file.getSize();
        //先生成文件存储路径
        File uploadParentFile = new File(fileUploadPath);
        //判断配置的文件目录是否存在，若不存在则创建一个新的文件目录
        if (!uploadParentFile.exists()) {
            uploadParentFile.mkdirs();
        }
        //定义一个文件唯一的标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUUID);
        //文件路径与下载接口路径一样
        //request.getServletContext();
        String url = fileDownloadPath + fileUUID;
        //把获取到的文件存储到磁盘目录
        file.transferTo(uploadFile);

        ResourcesFileEntry resource = new ResourcesFileEntry();
        resource.setId(UUIDUtils.getGUID32());
        resource.setLocalUrl(originalFilename);
        resource.setMediaId(uuid);
        resource.setSize(size);
        resource.setType(ResourceUtils.fileTypeToNum(type));
        fileMapper.insert(resource);

        return url;
    }

    @Override
    public List<ResourcesFileEntry> getList(int type) {


        return  fileMapper.getList(type);
    }


}
