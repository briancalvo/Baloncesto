package com.briancalvo.controller;

import com.briancalvo.controller.DTO.PositionStatistics;
import com.briancalvo.controller.util.HeaderUtil;
import com.briancalvo.domain.Player;
import com.briancalvo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by Brian on 24/10/2016.
 */
@RestController
@RequestMapping ("/players")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) throws URISyntaxException {
        if (player.getId() != null) {
            return ResponseEntity.
                    badRequest().
                    headers(
                            HeaderUtil.
                                    createFailureAlert("player", "idexists", "A new player cannot already have an ID")).body(null);
        }
        Player result = playerRepository.save(player);
        return ResponseEntity.created(new URI("/players/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("player", result.getId().toString()))
                .body(result);
    }

//    @GetMapping
//    public List<Player> findAll() {
//        return playerRepository.findAll();
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id){
        Player player = playerRepository.findOne(id);
        if (player!=null){
            return new ResponseEntity<Player>(player,HttpStatus.OK);
        }else{
            return new ResponseEntity<Player>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/orderByPoints")
    public List<Player> findByPointsOrderByPointsDesc(){
        return playerRepository.findAllByOrderByPointsDesc();
    }

    @GetMapping("/byPoints/{num}")
    public List<Player> findByPointsGreaterThanEqual(@PathVariable Integer num){
        return playerRepository.findByPointsGreaterThanEqual(num);
    }

    @GetMapping("/byPointsBetween/{min},{max}")
    public List<Player> findByPointsBetween(@PathVariable Integer min, @PathVariable Integer max){
        return playerRepository.findByPointsBetween(min,max);
    }

    @GetMapping("/byPositionAndAverage/")
    public Map<String, PositionStatistics> findByPositionAndAverage() {

        List<Object[]> positionsStatistics = playerRepository.findByPositionAndAverage();

        Map<String, PositionStatistics> positionStatisticsHashMap = new HashMap<>();

        positionsStatistics.forEach(positionStatistics -> {

                    PositionStatistics aux = new PositionStatistics();
                    aux.setPosition((String) positionStatistics[0]);
                    aux.setMinPoints((Integer) positionStatistics[1]);
                    aux.setMaxPoints((Integer) positionStatistics[2]);
                    aux.setAvgPoints((Double) positionStatistics[3]);
                    positionStatisticsHashMap.put(aux.getPosition(), aux);

                });

        return positionStatisticsHashMap;
    }

    @GetMapping("/byAssists/{num}")
    public List<Player> findByAssistsGreater(@PathVariable Integer num){
        return playerRepository.findByAssistsGreaterThan(num);
    }

    // T0D0 Gestión de errores cuando el parametro de ordenación es invalido.
    @GetMapping
    public List<Player> findAllOrderBy(
            @RequestParam(
                name = "orderBy", required = false) String orderBy,
            @RequestParam(
                name = "direction",defaultValue = "ASC") String direction
            ){
        if(orderBy != null) {
            Sort sort;
            if (direction.equals("ASC")){
                sort = new Sort(Sort.Direction.ASC, orderBy);
            }
            else{
                sort = new Sort(Sort.Direction.DESC, orderBy);
            }
            return playerRepository.findAll(sort);
        }
        return playerRepository.findAll();
    }

}
