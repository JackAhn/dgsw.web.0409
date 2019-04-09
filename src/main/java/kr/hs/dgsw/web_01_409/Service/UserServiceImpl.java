package kr.hs.dgsw.web_01_409.Service;

import kr.hs.dgsw.web_01_409.Domain.User;
import kr.hs.dgsw.web_01_409.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> listAllUser() {
        return this.userRepository.findAll();
    }

    @Override
    public User checkLogin(String username, String password) {
        Optional<User> found = this.userRepository.findByUsername(username);
        if(found.isPresent())
            return found.get();
        else
            return null;
    }

    @Override
    public User findUser(Long id) {
        Optional<User> found = this.userRepository.findById(id);
        if(found.isPresent())
            return found.get();
        else
            return null;
    }


    @Override
    public User addUser(User user) {
        Optional<User> found = this.userRepository.findByEmail(user.getEmail());
        User u = null;
        if(!found.isPresent()){
            u = new User(user.getUsername(), user.getPassword(), user.getEmail());
            this.userRepository.save(u);
        }
        return u;
    }

    @Override
    public User modifyUser(Long id, User user) {
        return this.userRepository.findById(id)
                .map(found-> {
                    found.setEmail(Optional.ofNullable(user.getEmail()).orElse(found.getEmail()));
                    found.setUsername(Optional.ofNullable(user.getUsername()).orElse(found.getUsername()));
                    found.setStoragePath(Optional.ofNullable(user.getStoragePath()).orElse(found.getStoragePath()));
                    found.setOriginalName(Optional.ofNullable(user.getOriginalName()).orElse(found.getOriginalName()));
                    return this.userRepository.save(found);
                })
                .orElse(null);
    }

    @Override
    public String findfilePath(Long id) {
        return this.userRepository.findById(id).get().getStoragePath();
    }

    @Override
    public String findfileName(String path) {
        return this.userRepository.findByStoragePath(path);
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            User u = this.userRepository.findById(id).get();
            this.userRepository.delete(u);
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
