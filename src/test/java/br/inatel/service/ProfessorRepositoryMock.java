package br.inatel.service;

import br.inatel.repository.ProfessorRepository;

public class ProfessorRepositoryMock implements ProfessorRepository {
    @Override
    public String encontrarProfessor(String professorName) {

        if(professorName == null){
            return ProfessorConst.INEXISTENTE;
        }

        switch (professorName){
            case "Chris":
                return ProfessorConst.CHRIS;
            case "Marcelo":
                return ProfessorConst.MARCELO;
            case "Renzo":
                return ProfessorConst.RENZO;
            case "Renan":
                return ProfessorConst.RENAN;
            default:
                return ProfessorConst.INEXISTENTE;
        }
    }
}
