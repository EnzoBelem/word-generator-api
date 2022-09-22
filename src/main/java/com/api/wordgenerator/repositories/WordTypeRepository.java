package com.api.wordgenerator.repositories;

import com.api.wordgenerator.models.WordTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordTypeRepository extends JpaRepository<WordTypeModel, String> {

    boolean existsByType(String type);

    Optional<WordTypeModel> findByTypeIgnoreCase(String type);

    @Query(nativeQuery = true, value = "SELECT word_type FROM tb_word_type")
    List<String> getAllTypes();
}
