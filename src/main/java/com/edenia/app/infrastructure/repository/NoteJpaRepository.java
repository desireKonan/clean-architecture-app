package com.edenia.app.infrastructure.repository;

import com.edenia.app.infrastructure.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteJpaRepository extends JpaRepository<NoteEntity, Long> {
}
