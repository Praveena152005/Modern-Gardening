package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.DIYTutorial;
import com.examly.springapp.model.Gardener;

@Repository
public interface DIYTutorialRepository extends JpaRepository<DIYTutorial, Long>{

    @Query("select d from DIYTutorial d where d.title=?1")
    List<Gardener> findByTitle(String title);
    @Modifying
    @Query(value = "INSERT INTO DIYTutorial (tutorialid,title,description,vediourl) VALUES(?,?,?,?)", nativeQuery = true)
    void addDIYTutorial(Long tutorialid,String title,String description,String vediourl);
    Optional<DIYTutorial> findById(Long id);
}
