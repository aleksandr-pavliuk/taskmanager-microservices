package ua.com.alex.taskmanager.todo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.com.alex.taskmanager.entity.User;

/**
 * @author Alex
 * @link https://intvw.me
 */
@FeignClient(name = "taskmanager-users", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

  @PostMapping("/user/id")
  ResponseEntity<User> findUserById(@RequestBody Long id);
}

@Component
class UserFeignClientFallback implements UserFeignClient {


  @Override
  public ResponseEntity<User> findUserById(Long id) {
    return null;
  }
}
