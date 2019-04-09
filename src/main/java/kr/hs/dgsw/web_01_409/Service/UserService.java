package kr.hs.dgsw.web_01_409.Service;

import kr.hs.dgsw.web_01_409.Domain.User;

import java.util.List;

public interface UserService {
    List<User> listAllUser();
    User checkLogin(String username, String password);
    User findUser(Long id);
    User addUser(User user);
    User modifyUser(Long id, User user);
    String findfilePath(Long id);
    String findfileName(String path);
    boolean deleteUser(Long id);
}
