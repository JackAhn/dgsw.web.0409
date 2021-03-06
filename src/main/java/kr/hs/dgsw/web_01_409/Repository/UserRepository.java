package kr.hs.dgsw.web_01_409.Repository;


import kr.hs.dgsw.web_01_409.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    String findByStoragePath(String path);
}

