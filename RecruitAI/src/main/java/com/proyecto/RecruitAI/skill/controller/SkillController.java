package com.proyecto.RecruitAI.skill.controller;

import com.proyecto.RecruitAI.skill.dto.DescriptionRequest;
import com.proyecto.RecruitAI.skill.dto.SkillDTO;
import com.proyecto.RecruitAI.skill.dto.SkillResponse;
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

    @GetMapping("/description")
    public ResponseEntity<List<SkillDTO>> extractSkills(
            @RequestBody DescriptionRequest request
    ) {
        return ResponseEntity.ok(
                skillService.getSkillsForDescription(request.description())
        );
    }


    @PostMapping("/description")
    public ResponseEntity<List<SkillResponse>> extractAndSaveSkills(@RequestBody DescriptionRequest request) {

       List<SkillResponse> skillResponses =skillService.saveNewSkills(request.description());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok(skillService.testOllama());
    }



}
