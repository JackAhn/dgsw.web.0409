package kr.hs.dgsw.web_01_409.Protocol;

import kr.hs.dgsw.web_01_408.Domain.Comment;
import lombok.Data;

@Data
public class CommentUsernameProtocol extends Comment {
    private String username;



    public CommentUsernameProtocol(Comment comment, String username) {
        super(comment);
        this.username = username;
    }
}
