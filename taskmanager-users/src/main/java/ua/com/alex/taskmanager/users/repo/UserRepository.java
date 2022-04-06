package ua.com.alex.taskmanager.users.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.com.alex.taskmanager.entity.User;

/**
 * @author Alex
 * @link https://intvw.me
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findFirstByEmail(String email);

  void deleteByEmail(String email);

  @Query("SELECT u FROM User u where " +
      "(:email is null or :email='' or lower(u.email) like lower(concat('%', :email,'%'))) and" +
      " (:username is null or :username='' or lower(u.username) like lower(concat('%', :username,'%')))"
  )
  Page<User> findByParams(@Param("email") String email,
      @Param("username") String username,
      Pageable pageable
  );

}
