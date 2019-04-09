package kr.hs.dgsw.web_01_409.Controller;

import kr.hs.dgsw.web_01_408.Domain.Comment;
import kr.hs.dgsw.web_01_408.Domain.User;
import kr.hs.dgsw.web_01_408.Protocol.AttachmentProtocol;
import kr.hs.dgsw.web_01_408.Service.CommentService;
import kr.hs.dgsw.web_01_408.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
public class AttachmentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/attachment")
    public AttachmentProtocol upload(@RequestPart MultipartFile srcFile) {
        String destFilename = "D:\\Spring_2\\web_01_408\\upload\\"
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd\\"))
                + UUID.randomUUID().toString() + "_"
                + srcFile.getOriginalFilename();
        try {
            File destFile = new File(destFilename);
            destFile.getParentFile().mkdirs();
            srcFile.transferTo(destFile);
            return new AttachmentProtocol(destFilename, srcFile.getOriginalFilename());
        } catch (Exception e) {
            System.out.println("에러" + e);
            return null;
        }
    }

    @GetMapping("/attachname/{id}")
    public String findfilePath(@PathVariable Long id){
        return this.userService.findfilePath(id);
    }

    private String findfileName(String path){
        return this.userService.findfileName(path);
    }

    @GetMapping("/attachment/{type}/{id}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable Long id){
//        String filepath = "D:\\Spring_2\\web_01_408\\upload\\2019_04_08\\56c2b1f7-05fd-49ff-8c14-cf048ec15c19_test.png";
        String filepath = null;
        String filename = null;
        User u = null;
        Comment c = null;
        if(type.equals("user")){
            u = this.userService.findUser(id);
            filepath = u.getStoragePath();
            filename = u.getOriginalName();
        }
        else if(type.equals("comment")){
            c = this.commentService.findComments(id);
            filepath = c.getStoragePath();
            filename = c.getOriginalName();
        }

        if(filepath == null || filename == null)
            return;
        File file = new File(filepath);
        if(file.exists()==false)
            return;

        String mimeType = URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null)
            mimeType = "application/octet-stream";

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
        response.setContentLength((int)file.length());

        try {
            InputStream is = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}