package ua.com.alex.taskmanager.todo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.alex.taskmanager.entity.User;
import ua.com.alex.taskmanager.todo.feign.UserFeignClient;
import ua.com.alex.taskmanager.todo.search.CategorySearchValues;
import ua.com.alex.taskmanager.todo.sevice.CategoryService;
import ua.com.alex.taskmanager.entity.Category;
import ua.com.alex.taskmanager.utils.rest.resttemplate.UserRestBuilder;

/**
 * @author Alex
 * @link http://healthfood.net.ua
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

  private CategoryService categoryService;

  private UserRestBuilder userRestBuilder;

  private UserFeignClient userFeignClient;

  public CategoryController(CategoryService categoryService, UserRestBuilder userRestBuilder,
      UserFeignClient userFeignClient) {
    this.categoryService = categoryService;
    this.userRestBuilder = userRestBuilder;
    this.userFeignClient = userFeignClient;
  }

  @PostMapping("/all")
  public List<Category> findAll(@RequestBody Long userId) {
    return categoryService.findAll(userId);
  }

  @PostMapping("/add")
  public ResponseEntity<Category> add(@RequestBody Category category) {

    if (category.getId() != null && category.getId() != 0) {
      return new ResponseEntity("redundant param: id MUST be null", HttpStatus.NOT_ACCEPTABLE);
    }

    if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
      return new ResponseEntity("missed param: title MUST be not null", HttpStatus.NOT_ACCEPTABLE);
    }

//        if (userRestBuilder.userExists(category.getUserId())) {
//            return ResponseEntity.ok(categoryService.add(category));
//        }

    ResponseEntity<User> result = userFeignClient.findUserById(category.getUserId());

    if (result == null){
      return new ResponseEntity("система пользователей недоступна", HttpStatus.NOT_FOUND);
    }

    if (result != null) {
      return ResponseEntity.ok(categoryService.add(category));
    }

    return new ResponseEntity("user id=" + category.getUserId() + " not found",
        HttpStatus.NOT_ACCEPTABLE);
  }


  @PutMapping("/update")
  public ResponseEntity<Category> update(@RequestBody Category category) {

    if (category.getId() == null && category.getId() == 0) {
      return new ResponseEntity("missed param: id", HttpStatus.NOT_ACCEPTABLE);
    }

    if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
      return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
    }

    categoryService.update(category);

    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Category> delete(@PathVariable("id") Long id) {

    try {
      categoryService.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      e.printStackTrace();
      return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping("/search")
  public ResponseEntity<List<Category>> search(
      @RequestBody CategorySearchValues categorySearchValues) {

    if (categorySearchValues.getUserId() == null
        && categorySearchValues.getUserId() == 0) {
      return new ResponseEntity("missed param: user id", HttpStatus.NOT_ACCEPTABLE);
    }

    List<Category> list = categoryService.findByTitle(categorySearchValues.getTitle(),
        categorySearchValues.getUserId());

    return ResponseEntity.ok(list);
  }

  @PostMapping("/id")
  public ResponseEntity<Category> findById(@RequestBody Long id) {

    Category category = null;

    try {
      category = categoryService.findById(id);
    } catch (NoSuchElementException e) {
      e.printStackTrace();
      return new ResponseEntity("id=" + id + " not found", HttpStatus.NOT_ACCEPTABLE);
    }

    return ResponseEntity.ok(category);
  }

}
