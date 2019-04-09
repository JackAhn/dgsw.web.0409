package kr.hs.dgsw.web_01_409.Controller;


import kr.hs.dgsw.web_01_409.Domain.User;
import kr.hs.dgsw.web_01_409.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/userlist")
    public List<User> list(){
        return userService.listAllUser();
    }

    @GetMapping("/userlogin/{id}/{pw}")
    public User chkLogin(@PathVariable String id, @PathVariable String pw){
        return this.userService.checkLogin(id, pw);
    }

    @GetMapping("/usersearch/{id}")
    public User find(@PathVariable Long id){
        return userService.findUser(id);
    }

    @GetMapping("/userPath/{id}")
    public String getFilePath(@PathVariable Long id){
        return this.userService.findfilePath(id);
    }

    @PostMapping("/useradd")
    public User add(@RequestBody User user){
        return this.userService.addUser(user);
    }

    @PutMapping("/usermodify/{id}")
    public User modify(@RequestBody User user, @PathVariable Long id){
        if(find(id)!=null)
            return this.userService.modifyUser(id, user);
        else
            return null;
    }
    @DeleteMapping("/userdelete/{id}")
    public boolean delete(@PathVariable Long id){
        return this.userService.deleteUser(id);
    }
}
