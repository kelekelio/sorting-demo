package com.grzegorznowakowski.sortingdemo.translation.repository;

import com.grzegorznowakowski.sortingdemo.translation.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
}
