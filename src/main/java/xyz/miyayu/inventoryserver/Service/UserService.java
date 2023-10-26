package xyz.miyayu.inventoryserver.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  private final PasswordService passwordService;
  //追加
  private final String specialUsername;

  //変更　→　コンストラクタで＠Valueアノテーションでapplication.propertiesの値で初期化
  public UserService(PasswordService passwordService,
                     @Value("${special.username}") String specialUsername) {
    this.passwordService = passwordService;
    this.specialUsername = specialUsername;
  }

  //追加　→　new User(specialUsername)
  public List<User> getUsers() {
    return List.of(new User("ryuki"), new User(specialUsername));
  }
}
