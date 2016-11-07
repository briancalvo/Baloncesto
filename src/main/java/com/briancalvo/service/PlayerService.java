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
 * Created by Brian on 10/10/2016.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public void testPlayers(){

        Player player1 = new Player(1L,"Lebron","James",LocalDate.of(1984,12,30),26649,6909,7106,"ala-pivot");
        Team team1 = teamRepository.findByNameLike("Cavaliers");
        player1.setTeam(team1);
        playerRepository.save(player1);

        Player player2 = new Player(2L,"Kevin","Durant",LocalDate.of(1988,9,29),17563,2371,4487,"alero");
        Team team2 = teamRepository.findByNameLike("Warriors");
        player2.setTeam(team2);
        playerRepository.save(player2);

        Player player3 = new Player(3L,"Anthony","Davis",LocalDate.of(1993,3,11),2266,170,1192,"ala-pivot");
        Team team3 = teamRepository.findByNameLike("Pelicans");
        player3.setTeam(team3);
        playerRepository.save(player3);

        Player player4 = new Player(4L,"Stephen","Curry",LocalDate.of(1988,3,14),11088,3415,2128,"base");
        player4.setTeam(team2);
        playerRepository.save(player4);

        Player player5 = new Player(5L,"James","Harden",LocalDate.of(1989,8,26),8904,1988,1943,"escolta");
        Team team4 = teamRepository.findByNameLike("Rockets");
        player5.setTeam(team4);
        playerRepository.save(player5);

        Player player6 = new Player(6L,"Kevin","Love",LocalDate.of(1988,9,7),9442,1238,5985,"ala-pivot");
        player6.setTeam(team1);
        playerRepository.save(player6);

        Player player7 = new Player(7L,"Kyrie","Irving",LocalDate.of(1992,3,23),6458,1699,1050,"base");
        player7.setTeam(team1);
        playerRepository.save(player7);

        System.out.println("");
        System.out.println("Player with name Lebron");
        System.out.println(playerRepository.findByName("Lebron"));

        System.out.println("");
        System.out.println("Players with greater or equal than 10.000 points");
        System.out.println(playerRepository.findByPointsGreaterThanEqual(10000));

        System.out.println("");
        System.out.println("Players with number of assists between 2000 and 5000");
        System.out.println(playerRepository.findByAssistsBetween(2000,5000));

        System.out.println("");
        System.out.println("Players that play base position");
        System.out.println(playerRepository.findByPosition("base"));

        System.out.println("");
        System.out.println("Players with born after 1990");
        System.out.println(playerRepository.findByDateGreaterThan(LocalDate.of(1990,1,1)));

        System.out.println("");
        System.out.println("Average of Points, Rebounds and Assists by Positions");
        List<Object[]> playerList = playerRepository.AvgPointsReboundsAssistsPerPosition();
        for (Object[] player : playerList){
            System.out.println("======================");
            System.out.println("Position: "+player[0]);
            System.out.println("Points: "+player[1]);
            System.out.println("Rebounds: "+player[2]);
            System.out.println("Assists: "+player[3]);
        }

        System.out.println("");
        System.out.println("Average, Maximun and Minimun of Points, Rebounds and Assists by Position");
        List<Object[]> playerList2 = playerRepository.AvgMaxMinPointsReboundsAssistsPerPosition();
        for (Object[] player : playerList2){
            System.out.println("=======================");
            System.out.println("Position: "+player[0]);
            System.out.println("AVG Points: "+player[1]);
            System.out.println("MAX Points: "+player[2]+" - "+playerRepository.findByPoints((int)player[2]));
            System.out.println("MIN Points: "+player[3]+" - "+playerRepository.findByPoints((int)player[3]));
            System.out.println("AVG Rebounds: "+player[4]);
            System.out.println("MAX Rebounds: "+player[5]+" - "+playerRepository.findByRebounds((int)player[5]));
            System.out.println("MIN Rebounds: "+player[6]+" - "+playerRepository.findByRebounds((int)player[6]));
            System.out.println("AVG Assists: "+player[7]);
            System.out.println("MAX Assists: "+player[8]+" - "+playerRepository.findByAssists((int)player[8]));
            System.out.println("MIN Assists: "+player[9]+" - "+playerRepository.findByAssists((int)player[9]));
        }

        System.out.println("");
        System.out.println("The player of Golden State Warriors are: " +playerRepository.findPlayerByTeam(team2));

        System.out.println("");
        System.out.println("The player of Cleveland Cavaliers on ala-pivot position are: "+playerRepository.findPlayerPositionByTeam(team1,"ala-pivot"));

        System.out.println("");
        System.out.println("The max points player of Cleveland Cavaliers is: "+playerRepository.findMaxPointsPlayerByTeam(team2));
    }

}
