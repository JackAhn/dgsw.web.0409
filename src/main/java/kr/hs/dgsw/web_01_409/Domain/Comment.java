package kr.hs.dgsw.web_01_409.Domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private String content;
    private String storagePath;
    private String originalName;

    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime modified;

    public Comment(){

    }

    public Comment(Long userId, String content){
        this.userId = userId;
        this.content = content;
    }

    public Comment(Comment comment){
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.content = comment.getContent();
        this.storagePath = comment.getStoragePath();
        this.originalName = comment.getOriginalName();
        this.created = comment.getCreated();
        this.modified = comment.getModified();
    }


}
