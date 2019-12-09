package com.jr7.cystudy.controller;

import com.jr7.cystudy.model.Stats;
import com.jr7.cystudy.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StatsController {

  @Autowired private StatsService statsServ;

  @GetMapping(path = "/get-users-stats")
  public Stats getUsersStats(@RequestParam String username, @RequestParam String className) {
    return statsServ.getUsersStats(username, className);
  }

  @PostMapping(path = "/add-time", produces = "application/json", consumes = "application/json")
  public @ResponseBody String addTime(
      @RequestBody String username, @RequestBody String className, @RequestBody float t) {
    return statsServ.addTime(className, username, t);
  }
}
