package com.briancalvo.service;

import com.briancalvo.domain.Player;
import com.briancalvo.domain.Team;
import com.briancalvo.repository.PlayerRepository;
import com.briancalvo.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Brian on 17/10/2016.
 */
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public void testTeams(){
        Team team1 = new Team(1,"Cavaliers","Cleveland", LocalDate.of(1970,10,12));
        teamRepository.save(team1);

        Team team2 = new Team(2,"Cavaliers","Cleveland", LocalDate.of(1970,10,12));
        teamRepository.save(team2);

        Team team3 = new Team(3,"Cavaliers","Cleveland", LocalDate.of(1970,10,12));
        teamRepository.save(team3);

        Team team4 = new Team(4,"Cavaliers","Cleveland", LocalDate.of(1970,10,12));
        teamRepository.save(team4);

        Team team5 = new Team(5,"Cavaliers","Cleveland", LocalDate.of(1970,10,12));
        teamRepository.save(team5);

        System.out.println(teamRepository.findOne(1L));
        System.out.println(teamRepository.findByNameLike("Cleveland"));
    }

}
