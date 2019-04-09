package kr.hs.dgsw.web_01_409.Protocol;


import kr.hs.dgsw.web_01_409.Domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttachmentProtocol extends User {

    private String storagePath;
    private String originalName;

    public AttachmentProtocol(String storagePath, String originalName) {
        this.storagePath = storagePath;
        this.originalName = originalName;
    }

}