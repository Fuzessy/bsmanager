package hu.fuz.bs.shopping.controller;

import hu.fuz.bs.shopping.service.event.ShoppingEventMonitor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;

@RestController
@RequestMapping("/shopping/event")
public class ShoppingSseController {

  @Resource
  private ShoppingEventMonitor shoppingEventMonitor;

  @GetMapping("/stream")
  public SseEmitter readEvents(){
    return shoppingEventMonitor.addEmitter();
  }

}
