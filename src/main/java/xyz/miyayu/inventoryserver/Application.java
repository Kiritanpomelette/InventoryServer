package xyz.miyayu.inventoryserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import xyz.miyayu.inventoryserver.Service.PasswordService;

@SpringBootApplication
@ConfigurationProperties(prefix = "special")
@ImportResource("classpath:applicationContext.xml") //追加
public class Application {

  @Bean
  public PasswordService passwordService() {
    return new PasswordService();
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
