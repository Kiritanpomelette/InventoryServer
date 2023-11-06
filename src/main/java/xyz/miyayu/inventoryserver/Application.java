package xyz.miyayu.inventoryserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext.xml") //追加
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

//  public void init() {
//    TimeZone.setDefault((TimeZone.getTimeZone("JST")));
//  }

}
