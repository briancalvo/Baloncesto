package com.briancalvo.service;

import com.briancalvo.domain.Player;
import com.briancalvo.repository.PlayerRepository;
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

    public void testPlayers(){

        Player player1 = new Player(1,"Lebron","James",LocalDate.of(1984,12,30),26649,6909,7106,"ala-pivot");
        playerRepository.save(player1);

        Player player2 = new Player(2,"Kevin","Durant",LocalDate.of(1988,9,29),17563,2371,4487,"alero");
        playerRepository.save(player2);

        Player player3 = new Player(3,"Anthony","Davis",LocalDate.of(1993,3,11),2266,170,1192,"ala-pivot");
        playerRepository.save(player3);

        Player player4 = new Player(4,"Stephen","Curry",LocalDate.of(1988,3,14),11088,3415,2128,"base");
        playerRepository.save(player4);

        Player player5 = new Player(5,"James","Harden",LocalDate.of(1989,8,26),8904,1988,1943,"escolta");
        playerRepository.save(player5);

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





    }

}
