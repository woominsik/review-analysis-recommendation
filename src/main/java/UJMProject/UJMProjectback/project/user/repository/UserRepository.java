package UJMProject.UJMProjectback.project.user.repository;

import UJMProject.UJMProjectback.project.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
