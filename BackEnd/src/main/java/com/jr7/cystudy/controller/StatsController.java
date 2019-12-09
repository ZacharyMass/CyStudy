package com.jr7.cystudy.controller;

import com.jr7.cystudy.model.Stats;
import com.jr7.cystudy.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatsController {

  @Autowired private StatsService statsServ;

  @GetMapping(path = "/get-users-stats")
  public Stats getUsersStats(@RequestParam String usrname, @RequestParam String className) {
    return statsServ.getUsersStats(usrname, className);
  }

  @PostMapping(path = "/add-time")
  public String addTime(@RequestBody String username, @RequestBody String className, @RequestBody float t){
    return statsServ.addTime(className, username, t);
  }
}
