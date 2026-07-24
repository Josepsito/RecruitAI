package com.proyecto.RecruitAI.skill.service;

import com.proyecto.RecruitAI.skill.dto.SkillDTO;
import com.proyecto.RecruitAI.skill.dto.SkillResponse;
import com.proyecto.RecruitAI.skill.model.Skill;
import com.proyecto.RecruitAI.skill.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class SkillService {

    private final ChatClient chatClient;
    private final SkillRepository skillRepository;
    private final Logger logger = LoggerFactory.getLogger(SkillService.class);

    public SkillService(ChatClient chatClient, SkillRepository skillRepository) {
        this.chatClient = chatClient;
        this.skillRepository = skillRepository;
    }

    private List<SkillDTO> extractSkills(String description) {

        return chatClient.prompt()
                .user("""
            Eres un experto en reclutamiento y extracción de habilidades para currículums y ofertas laborales.
            
            Tu tarea es identificar TODAS las habilidades mencionadas en el texto.
            
            Incluye:
            
            - Lenguajes de programación.
            - Frameworks.
            - Librerías.
            - Bases de datos.
            - Motores de bases de datos.
            - IDEs.
            - Editores de código.
            - Sistemas operativos.
            - Herramientas de desarrollo.
            - Plataformas cloud.
            - DevOps.
            - Control de versiones.
            - Metodologías técnicas.
            - Software profesional.
            - Herramientas de oficina.
            - Herramientas de diseño.
            - Tecnologías web.
            - Habilidades técnicas generales cuando sean explícitas (por ejemplo: desarrollo web, desarrollo móvil, diseño gráfico).
            
            Reglas:
            
            - Extrae únicamente habilidades que aparezcan explícitamente.
            - No inventes habilidades.
            - No infieras tecnologías.
            - No omitas ninguna habilidad.
            - Elimina duplicados.
            - Conserva el nombre más común de cada habilidad.
            - Si una habilidad tiene un nombre oficial, úsalo.
            - Devuelve únicamente un JSON válido.
            - No escribas markdown.
            - No escribas explicaciones.
            - No escribas texto adicional.
            
            Ejemplo:
            
            Entrada:
            Tengo experiencia en Java, Spring Boot, MySQL, Docker, Git, desarrollo web, Microsoft Word y Photoshop.
            
            Salida:
            
            [
              { "skill": "Java" },
              { "skill": "Spring Boot" },
              { "skill": "MySQL" },
              { "skill": "Docker" },
              { "skill": "Git" },
              { "skill": "Desarrollo Web" },
              { "skill": "Microsoft Word" },
              { "skill": "Adobe Photoshop" }
            ]
            
            Texto:
            
            %s
            """.formatted(description))
                .call()
                .entity(new ParameterizedTypeReference<>() {
                });
    }

    public List<SkillDTO> getSkillsForDescription(String description){

        return extractSkills(description);
    }

    public List<SkillResponse> saveNewSkills(String description) {
        List<Skill> extractedSkills = extractSkills(description).stream()
                .map(dto -> {
                    Skill skill = new Skill();
                    skill.setName(dto.skill());
                    return skill;
                })
                .toList();

        Set<String> skillNames = extractedSkills.stream()
                .map(Skill::getName)
                .collect(Collectors.toSet());

        Set<String> existingNames = skillRepository.findByNameIn(new ArrayList<>(skillNames))
                .stream()
                .map(Skill::getName)
                .collect(Collectors.toSet());

        List<Skill> newSkills = extractedSkills.stream()
                .filter(skill -> !existingNames.contains(skill.getName()))
                .toList();

        if (!newSkills.isEmpty()) {
            skillRepository.saveAll(newSkills);
        }

        return extractedSkills.stream()
                .map(skill -> new SkillResponse(skill.getId(),skill.getName()))
                .toList();
    }



    public String testOllama() {

        long inicio = System.currentTimeMillis();

        String response = chatClient.prompt()
                .user("Responde únicamente con la palabra Hola")
                .call()
                .content();

        long fin = System.currentTimeMillis();

        logger.info("Tiempo Ollama + Spring AI: {} ms", fin - inicio);

        return response;
    }

}