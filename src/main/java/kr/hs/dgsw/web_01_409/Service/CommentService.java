package kr.hs.dgsw.web_01_409.Service;


import kr.hs.dgsw.web_01_409.Domain.Comment;
import kr.hs.dgsw.web_01_409.Protocol.CommentUsernameProtocol;

import java.util.List;

public interface CommentService {
    List<CommentUsernameProtocol> listAllCommments();
    List<CommentUsernameProtocol> findUserComments(Long userId);
    Comment findComments(Long id);
    CommentUsernameProtocol addComments(Comment comment);
    Comment modifyComments(Comment comment);
    boolean deleteComments(Long id);
}
