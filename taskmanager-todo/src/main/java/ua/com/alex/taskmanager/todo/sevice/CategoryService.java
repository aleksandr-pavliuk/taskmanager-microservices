package ua.com.alex.taskmanager.todo.sevice;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.com.alex.taskmanager.todo.repo.CategoryRepository;
import ua.com.alex.taskmanager.entity.Category;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Service
@Transactional
public class CategoryService {

  private final CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public List<Category> findAll(Long userId) {
    return repository.findByUserIdOrderByTitleAsc(userId);
  }

  public Category add(Category category) {
    return repository.save(category);
  }

  public Category update(Category category) {
    return repository.save(category);
  }

  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public List<Category> findByTitle(String title, Long userId){
    return repository.findByTitle(title, userId);
  }

  public Category findById(Long id){
    return repository.findById(id).get();
  }
}
