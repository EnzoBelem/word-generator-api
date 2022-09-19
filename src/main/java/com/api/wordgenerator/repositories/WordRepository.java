package com.api.wordgenerator.repositories;

import com.api.wordgenerator.models.WordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface WordRepository extends JpaRepository<WordModel, UUID> {

    boolean existsByWord(String word);
    Optional<WordModel> findByWordIgnoreCase(String word);

    @Query(nativeQuery=true, value="SELECT * FROM TB_WORD ORDER BY random() LIMIT :limit")
    List<WordModel> getRandomWord(@Param("limit")int limit);

    @Query(nativeQuery=true, value="SELECT * FROM TB_WORD WHERE UPPER(word_type) = UPPER(:type) ORDER BY random() LIMIT :limit")
    List<WordModel> getRandomWordByType(int limit, String type);

    @Query(nativeQuery=true, value="SELECT * FROM TB_WORD WHERE UPPER(word_type) = UPPER(:type)")
    List<WordModel> getAllByType(String type);
}
