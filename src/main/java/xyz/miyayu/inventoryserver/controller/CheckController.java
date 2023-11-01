package xyz.miyayu.inventoryserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/check")
public class CheckController {

  @RequestMapping("/init")
  private String init() {
    return "OK";
  }
}
