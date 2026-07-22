package com.proyecto.RecruitAI.skill.controller;

import com.proyecto.RecruitAI.skill.dto.DescriptionRequest;
import com.proyecto.RecruitAI.skill.dto.SkillResponse;
import com.proyecto.RecruitAI.skill.model.Skill;
import com.proyecto.RecruitAI.skill.service.SkillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/extract")
    public ResponseEntity<String> extractSkills(
            @RequestBody DescriptionRequest request
    ) {
        return ResponseEntity.ok(
                skillService.extractSkills(request.description())
        );
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(skillService.testOllama());
    }

}
