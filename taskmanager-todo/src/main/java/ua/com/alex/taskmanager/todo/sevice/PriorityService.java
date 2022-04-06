package ua.com.alex.taskmanager.todo.sevice;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.com.alex.taskmanager.todo.repo.PriorityRepository;
import ua.com.alex.taskmanager.entity.Priority;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Service
@Transactional
public class PriorityService {

  private final PriorityRepository repository;

  public PriorityService(PriorityRepository repository) {
    this.repository = repository;
  }

  public List<Priority> findAll(Long userId) {
    return repository.findByUserIdOrderByIdAsc(userId);
  }

  public Priority add(Priority priority) {
    return repository.save(priority);
  }

  public Priority update(Priority priority) {
    return repository.save(priority);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public List<Priority> findByTitle(String title, Long userId) {
    return repository.findByTitle(title, userId);
  }

  public Priority findById(Long id) {
    return repository.findById(id).get();
  }
}
