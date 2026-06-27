import java.time.LocalDate;
import java.util.Optional;

public class UserRepository {
  public Optional<User> findById(int id) {
    if (id == 1) {
      User user = new User(1, "上田孝", LocalDate.of(1994, 8, 6));
      Optional<User> userOptional = Optional.of(user);
      return userOptional;
    }
    return Optional.empty();
  }
}
