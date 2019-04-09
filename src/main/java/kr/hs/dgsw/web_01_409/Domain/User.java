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
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
    private String storagePath;
    private String originalName;

    @CreationTimestamp
    private LocalDateTime joined;
    @UpdateTimestamp
    private LocalDateTime modified;

    public User(){

    }

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.storagePath = user.getStoragePath();
        this.originalName = user.getOriginalName();
        this.joined = user.getJoined();
        this.modified = user.getModified();
    }
}
