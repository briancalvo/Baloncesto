package com.briancalvo.service;

import com.briancalvo.domain.Player;
import com.briancalvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Brian on 10/10/2016.
 */
@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public void testPlayers(){

        Player player1 = new Player(1,"Lebron","James","30-12-1984",26649,6909,7106,"ala-pivot");
        playerRepository.save(player1);

        Player player2 = new Player(2,"Kevin","Durant","29-09-1988",17563,2371,4487,"alero");
        playerRepository.save(player2);

        Player player3 = new Player(3,"Anthony","Davis","11-03-1993",2266,170,1192,"ala-pivot");
        playerRepository.save(player3);

        Player player4 = new Player(4,"Stephen","Curry","14-03-1988",11088,3415,2128,"base");
        playerRepository.save(player4);

        Player player5 = new Player(5,"James","Harden","26-08-1989",8904,1988,1943,"escolta");
        playerRepository.save(player5);

        System.out.println("Player with name Lebron");
        System.out.println(playerRepository.findByName("Lebron"));

        System.out.println("Players with greater or equal than 10.000 points");
        System.out.println(playerRepository.findByPointsGreaterThanEqual(10000));

        System.out.println("Players with number of assists between 2000 and 5000");
        System.out.println(playerRepository.findByAssistsBetween(2000,5000));

        System.out.println("Players that play base position");
        System.out.println(playerRepository.findByPosition("base"));

        System.out.println("Players with born before 1990");
        System.out.println(playerRepository.findByDateLessThan("01-01-1990"));



    }

}
