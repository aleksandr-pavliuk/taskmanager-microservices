package ua.com.alex.taskmanager.todo.sevice;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.com.alex.taskmanager.entity.Stat;
import ua.com.alex.taskmanager.todo.repo.StatRepository;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Service
@Transactional
public class StatService {

  private final StatRepository repository;

  public StatService(StatRepository repository) {
    this.repository = repository;
  }

  public Stat findStat(Long userId) {
    return repository.findByUserId(userId);
  }

}
