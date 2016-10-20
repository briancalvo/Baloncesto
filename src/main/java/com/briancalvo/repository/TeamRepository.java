package com.briancalvo.repository;

import com.briancalvo.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
/**
 * Created by Brian on 17/10/2016.
 */
public interface TeamRepository extends JpaRepository<Team,Long>{

        Team findByNameLike(String name);

}
