package ua.com.alex.taskmanager.todo.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.alex.taskmanager.entity.Priority;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
public interface PriorityRepository extends JpaRepository<Priority, Long> {


  List<Priority> findByUserIdOrderByIdAsc(Long id);

  @Query("SELECT p FROM Priority p where "
      + "(:title is null or :title='' "
      + "or lower(p.title) like lower(concat('%',:title,'%'))) "
      + "and p.userId=:id "
      + "order by p.title asc")
  List<Priority> findByTitle(@Param("title") String title, @Param("id") Long id);

}
