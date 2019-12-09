package com.jr7.cystudy.service;

import com.jr7.cystudy.model.Stats;
import com.jr7.cystudy.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

  @Autowired private StatsRepository statsRepo;
  @Autowired private UserService userService;
  @Autowired private ClassesService classService;
  @Autowired private UserClassService usrClassServ;

  /**
   * Add time to a user's stats for a specific class.
   *
   * @param className name of the class the user is in to add stats to.
   * @param usrName username
   * @param t time spent that will be added to the user's stats
   * @return message indicating errors, or that time was added successfully
   */
  public String addTime(String className, String usrName, float t) {

    if (!userService.checkUserExists(usrName)) {
      return "User with name " + usrName + " does not exist";
    }

    if (!classService.checkClassExists(className)) {
      return "Class with name " + className + " does not exist";
    }

    if (!usrClassServ.checkUserInClass(usrName, className)) {
      return "User " + usrName + " is not in class " + className;
    }

    Stats usrStats = statsRepo.getStatsByUsernameAndClassName(usrName, className);
    usrStats.addTime(t);
    statsRepo.save(usrStats);
    return "Time added successfully";
  }

  public void gameAdd(int correct, String username) {

    Stats s = statsRepo.getStatsByUsernameAndClassName(username, "COMS309");
    s.addTotal();
    s.addCorrect(correct);
    statsRepo.save(s);
  }

  public Stats getUsersStats(String username, String className) {
    return statsRepo.getStatsByUsernameAndClassName(username, className);
  }

  public void save(String username, String className) {

    Stats s = new Stats();
    s.setUsername(username);
    s.setClassName(className);
    statsRepo.save(s);
  }
}
