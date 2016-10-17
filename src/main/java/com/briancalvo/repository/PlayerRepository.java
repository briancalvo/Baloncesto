package com.briancalvo.repository;

import com.briancalvo.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Brian on 10/10/2016.
 */
public interface PlayerRepository extends JpaRepository<Player,Long>{

    //Spring Data Queries

    List<Player> findByName(String name);

    List<Player> findByPointsGreaterThanEqual(Integer points);

    List<Player> findByAssistsBetween(Integer min, Integer max);

    List<Player> findByPosition(String position);

    List<Player> findByDateLessThan(String date);

    // JPL Queries


}
