package ua.com.alex.taskmanager.todo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alex.taskmanager.entity.Stat;
import ua.com.alex.taskmanager.todo.sevice.StatService;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@RestController
public class StatController {

  private final StatService statService;

  public StatController(StatService statService) {
    this.statService = statService;
  }

  @PostMapping("/stat")
  public ResponseEntity<Stat> findByEmail(@RequestBody Long userId) {

    return ResponseEntity.ok(statService.findStat(userId));
  }


}
