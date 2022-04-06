package ua.com.alex.taskmanager.todo.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alex.taskmanager.entity.Task;
import ua.com.alex.taskmanager.todo.search.TaskSearchValues;
import ua.com.alex.taskmanager.todo.sevice.TaskService;


/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@RestController
@RequestMapping("/task")
public class TaskController {

  public static final String ID_COLUMN = "id";
  private final TaskService taskService;

  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @PostMapping("/all")
  public ResponseEntity<List<Task>> findAll(@RequestBody Long userId) {
    return ResponseEntity.ok(taskService.findAll(userId));
  }

  @PostMapping("/add")
  public ResponseEntity<Task> add(@RequestBody Task task) {

    if (task.getId() != null && task.getId() != 0) {
      return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
    }

    if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
      return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
    }

    return ResponseEntity.ok(taskService.add(task));

  }


  @PutMapping("/update")
  public ResponseEntity<Task> update(@RequestBody Task task) {

    if (task.getId() == null || task.getId() == 0) {
      return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
    }

    if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
      return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
    }

    taskService.update(task);

    return new ResponseEntity(HttpStatus.OK);

  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity delete(@PathVariable("id") Long id) {

    try {
      taskService.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping("/id")
  public ResponseEntity<Task> findById(@RequestBody Long id) {

    Task task = null;

    try {
      task = taskService.findById(id);
    } catch (NoSuchElementException e) {
      e.printStackTrace();
      return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
    }

    return ResponseEntity.ok(task);
  }

  @PostMapping("/search")
  public ResponseEntity<Page<Task>> search(@RequestBody TaskSearchValues taskSearchValues)
      throws ParseException {

    String title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;

    Boolean completed =
        taskSearchValues.getCompleted() != null && taskSearchValues.getCompleted() == 1;

    Long priorityId =
        taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
    Long categoryId =
        taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;

    String sortColumn =
        taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
    String sortDirection =
        taskSearchValues.getSortDirection() != null ? taskSearchValues.getSortDirection() : null;

        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : null;
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : null;

        Long userId = taskSearchValues.getUserId() != null ? taskSearchValues.getUserId() : null; // для показа задач только этого пользователя

        // проверка на обязательные параметры
        if (userId == null || userId == 0) {
            return new ResponseEntity("missed param: user id", HttpStatus.NOT_ACCEPTABLE);
        }


        // чтобы захватить в выборке все задачи по датам, независимо от времени - можно выставить время с 00:00 до 23:59

    Date dateFrom = null;
    Date dateTo = null;

    if (taskSearchValues.getDateFrom() != null) {
      Calendar calendarFrom = Calendar.getInstance();
      calendarFrom.setTime(taskSearchValues.getDateFrom());
      calendarFrom.set(Calendar.HOUR_OF_DAY, 0);
      calendarFrom.set(Calendar.MINUTE, 1);
      calendarFrom.set(Calendar.SECOND, 1);
      calendarFrom.set(Calendar.MILLISECOND, 1);

      dateFrom = calendarFrom.getTime();

    }

    if (taskSearchValues.getDateTo() != null) {

      Calendar calendarTo = Calendar.getInstance();
      calendarTo.setTime(taskSearchValues.getDateTo());
      calendarTo.set(Calendar.HOUR_OF_DAY, 23);
      calendarTo.set(Calendar.MINUTE, 59);
      calendarTo.set(Calendar.SECOND, 59);
      calendarTo.set(Calendar.MILLISECOND, 999);

      dateTo = calendarTo.getTime();

    }

    Sort.Direction direction =
        sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim()
            .equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

    Sort sort = Sort.by(direction, sortColumn, ID_COLUMN);

    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

    Page<Task> result = taskService.findByParams(title, completed, priorityId, categoryId, userId,
        dateFrom, dateTo, pageRequest);

    return ResponseEntity.ok(result);

  }

}
