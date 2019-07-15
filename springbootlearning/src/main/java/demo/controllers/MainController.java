package demo.controllers;


import demo.models.Car;
import javax.validation.Valid;

import demo.service.Processor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

  private Processor processor;
  public MainController(Processor processor) {
    this.processor = processor;
  }

  @GetMapping("/message")
  public String getMessage() {
    return "Hellko";
  }

  @PostMapping("/message")
  public String sendMessage(@RequestBody @Valid Car request) throws InterruptedException {

    processor.send();
    return "Success";
  }
}
