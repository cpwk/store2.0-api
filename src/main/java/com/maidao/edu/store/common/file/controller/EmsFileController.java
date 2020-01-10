package com.maidao.edu.store.common.file.controller;


import com.maidao.edu.store.common.authority.AdminType;
import com.maidao.edu.store.common.authority.RequiredPermission;
import com.maidao.edu.store.common.controller.BaseController;
import com.maidao.edu.store.common.file.service.FileService;
import com.maidao.edu.store.common.file.service.ImgBase64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/ems/file")
public class EmsFileController extends BaseController {

    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload_token")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView upload_token(String fileName, int fileSize) throws Exception {
        return feedback(fileService.uploadToken("f", fileName, fileSize, true));
    }

    @RequestMapping(value = "/img_to_base64")
    @RequiredPermission(adminType = AdminType.ADMIN)
    public ModelAndView img_to_base64(String url) throws Exception {
        return feedback(ImgBase64Utils.base64FromURL(url));
    }

}
