package com.haiying.asset.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.haiying.asset.common.exception.PageTipException;
import com.haiying.asset.model.entity.SysUser;
import com.haiying.asset.model.vo.FileVO;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class UploadFileController {
    @Autowired
    HttpSession httpSession;

    @PostMapping("uploadFile")
    public FileVO uploadFile(MultipartFile file) throws IOException {
        SysUser user = (SysUser) httpSession.getAttribute("user");
        if (user == null) {
            throw new PageTipException("用户未登录");
        }
        String fileName = file.getOriginalFilename();
        //保存到本地硬盘
        String directory = "D:/appFile/assetFile/upload/" + user.getLoginName();
        File directoryFile = new File(directory);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        }
        File f = new File(directory + "/" + fileName);
        if (f.exists()) {
            fileName = FileUtil.getPrefix(fileName) + IdUtil.fastSimpleUUID() + "." + FileUtil.getSuffix(fileName);
        }
        IOUtils.copy(file.getInputStream(), new FileOutputStream(directory + "/" + fileName));
        //
        FileVO fileVO = new FileVO();
        fileVO.setName(fileName);
        fileVO.setStatus("done");
        fileVO.setUrl("/asset/upload/" + user.getLoginName() + "/" + fileName);
        return fileVO;
    }
}
