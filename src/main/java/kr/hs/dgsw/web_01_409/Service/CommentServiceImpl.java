package kr.hs.dgsw.web_01_409.Service;

import kr.hs.dgsw.web_01_408.Domain.Comment;
import kr.hs.dgsw.web_01_408.Domain.User;
import kr.hs.dgsw.web_01_408.Protocol.CommentUsernameProtocol;
import kr.hs.dgsw.web_01_408.Repository.CommentRepository;
import kr.hs.dgsw.web_01_408.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void init(){
        User u = this.userRepository.save(new User("abc", "1234", "abc@dgsw"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 111"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 222"));
        this.commentRepository.save(new Comment(u.getId(), "hi there 333"));

    }

    @Override
    public List<CommentUsernameProtocol> listAllCommments() {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(comment.getUserId());
            String username = (found.isPresent()) ? found.get().getUsername() : null;
            cupList.add(new CommentUsernameProtocol(comment, username));
        });
        return cupList;
    }

    @Override
    public List<CommentUsernameProtocol> findUserComments(Long userId) {
        List<Comment> commentList = this.commentRepository.findAll();
        List<CommentUsernameProtocol> cupList = new ArrayList<>();
        commentList.forEach(comment -> {
            Optional<User> found = this.userRepository.findById(userId);
            if(found.isPresent()){
                String username = found.get().getUsername();
                cupList.add(new CommentUsernameProtocol(comment, username));
            }
        });
        return cupList;
    }

    @Override
    public Comment findComments(Long id) {
        return this.commentRepository.findById(id).get();
    }

    @Override
    public CommentUsernameProtocol addComments(Comment comment) {
        Optional<User> found = this.userRepository.findById(comment.getUserId());
        if(found.isPresent()){
            commentRepository.save(comment);
            return new CommentUsernameProtocol(comment, found.get().getUsername());
        }
        else{
            return null;
        }
    }

    @Override
    public CommentUsernameProtocol modifyComments(Comment comment) {
        Comment modify = this.commentRepository.findById(comment.getId())
                .map(found-> {
                    found.setContent(Optional.ofNullable(comment.getContent()).orElse(found.getContent()));
                    return this.commentRepository.save(found);
                })
                .orElse(null);
        return new CommentUsernameProtocol(modify, this.userRepository.findById(comment.getUserId()).get().getUsername());
    }

    @Override
    public boolean deleteComments(Long id) {
        try {
            this.commentRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}

