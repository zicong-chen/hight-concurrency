package top.chenzicong.highconcurrency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.chenzicong.highconcurrency.common.Response;
import top.chenzicong.highconcurrency.common.StatusCode;
import top.chenzicong.highconcurrency.exception.MyException;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @RequestMapping("/uploadPage")
    public String uploadPage(){
        return "upload";
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public Response upload(MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            throw new MyException(StatusCode.FILE_EMPTY);
        }
        Path path = Paths.get("D:\\upload\\" + file.getOriginalFilename());

        try {
            file.transferTo(path);
        } catch (IOException e) {
            throw new MyException(StatusCode.UPLOAD_FAIL);
        }
        return Response.SUCCESS;
    }
}
