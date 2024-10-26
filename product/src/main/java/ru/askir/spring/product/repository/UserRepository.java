package ru.askir.spring.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.askir.spring.product.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
