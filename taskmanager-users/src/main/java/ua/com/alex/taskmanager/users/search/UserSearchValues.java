package ua.com.alex.taskmanager.users.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Alex
 * @link https://intvw.me
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchValues {

  private String email;
  private String username;

  private Integer pageNumber;
  private Integer pageSize;

  private String sortColumn;
  private String sortDirection;

}
