package kr.hs.dgsw.web_01_409.Controller;

import kr.hs.dgsw.web_01_408.Domain.Comment;
import kr.hs.dgsw.web_01_408.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_01_408.Service.CommentService;
import kr.hs.dgsw.web_01_408.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<CommentUsernameProtocol> list(){
        return this.commentService.listAllCommments();
    }

    @GetMapping("/listdeletefind/{id}")
    public Comment findComments(@PathVariable Long id){
        return this.commentService.findComments(id);
    }

    @GetMapping("/listsearch/{userId}")
    public List<CommentUsernameProtocol> find(@PathVariable Long userId){
        return commentService.findUserComments(userId);
    }

    @PostMapping("/listadd")
    public CommentUsernameProtocol add(@RequestBody Comment comment){
        return commentService.addComments(comment);
    }

    @PutMapping("/listmodify")
    public Comment modify(@RequestBody Comment comment){
        return commentService.modifyComments(comment);
    }

    @DeleteMapping("/listdelete/{id}")
    public boolean delete(@PathVariable Long id){
        return commentService.deleteComments(id);
    }

}
