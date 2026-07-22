package com.proyecto.RecruitAI.skill.service;

import com.proyecto.RecruitAI.skill.dto.SkillResponse;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final ChatClient chatClient;

    public SkillService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String extractSkills(String description) {

        return chatClient.prompt()
                .user("""
        Analiza la siguiente descripción y extrae TODAS las habilidades
        técnicas mencionadas explícita o directamente.

        Considera como habilidades técnicas:
        - Lenguajes de programación.
        - Frameworks y librerías.
        - Bases de datos.
        - Herramientas de software.
        - Programas informáticos.
        - IDEs y editores de código.
        - Sistemas operativos.
        - Plataformas y motores de desarrollo.
        - Tecnologías y metodologías técnicas.

        Reglas estrictas:
        - Extrae todas las habilidades técnicas mencionadas.
        - No inventes habilidades que no aparezcan en la descripción.
        - No omitas ninguna habilidad técnica mencionada.
        - Elimina únicamente los duplicados.
        - Usa nombres estandarizados cuando sea posible.
        - Devuelve únicamente el JSON.
        - No incluyas explicaciones.
        - No incluyas texto antes ni después del JSON.

        Devuelve exactamente este formato:

        [
          {
            "skill": "Java"
          },
          {
            "skill": "Spring Boot"
          }
        ]

        Descripción:
        %s
        """.formatted(description))
                .call()
                .content();
    }


    public String testOllama() {

        long inicio = System.currentTimeMillis();

        String response = chatClient.prompt()
                .user("Responde únicamente con la palabra Hola")
                .call()
                .content();

        long fin = System.currentTimeMillis();

        System.out.println("Tiempo Ollama + Spring AI: " + (fin - inicio) + " ms");

        return response;
    }

}