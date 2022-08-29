package br.inatel.service;

import br.inatel.model.Professor;
import br.inatel.repository.ProfessorRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor buscarProfessor(String nome){

        String professorString = professorRepository.encontrarProfessor(nome);

        JsonObject jsonObject = JsonParser.parseString(professorString).getAsJsonObject();

        return new Professor(jsonObject.get("nomeDoProfessor").getAsString(),
                jsonObject.get("horarioDeAtendimento").getAsString(),
                jsonObject.get("periodo").getAsString());
    }
}
