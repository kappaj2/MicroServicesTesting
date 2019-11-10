package za.co.ajk.in28min.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.ajk.in28min.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
