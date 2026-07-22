package com.proyecto.RecruitAI.skill.repository;

import com.proyecto.RecruitAI.skill.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    List<Skill> findByNameIn(List<Skill> names);

}