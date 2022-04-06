package ua.com.alex.taskmanager.todo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.alex.taskmanager.entity.Stat;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {

  Stat findByUserId(Long id);
}
