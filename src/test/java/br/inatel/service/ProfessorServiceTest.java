package br.inatel.service;

import br.inatel.model.Professor;
import br.inatel.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfessorServiceTest {

    private final ProfessorRepository professorRepository = new ProfessorRepositoryMock();
    private final ProfessorService professorService = new ProfessorService(professorRepository);

    @Test
    public void testeBuscarProfessorChris(){

        final Professor professor = professorService.buscarProfessor("Chris");

        final Professor professorEsperado = getProfessor("Chris", "17:30:00", "noturno");

        assertEquals(professorEsperado.getNome(), professor.getNome());
        assertEquals(professorEsperado.getHorario(), professor.getHorario());
        assertEquals(professorEsperado.getPeriodo(), professor.getPeriodo());
    }

    @Test
    public void testeBuscarProfessorMarceloEVerificarChamadoDoProfessorRepository(){

        final Professor professor = professorService.buscarProfessor("Marcelo");

        final Professor professorEsperado = getProfessor("Marcelo", "15:30:00", "diurno");

        assertEquals(professorEsperado.getNome(), professor.getNome());
        assertEquals(professorEsperado.getHorario(), professor.getHorario());
        assertEquals(professorEsperado.getPeriodo(), professor.getPeriodo());
    }

    @Test
    public void testeBuscarRenzoERenanEVerificarSeProfessorRepositoryFoiChamado2Vezes(){

        final Professor professorRenzo = professorService.buscarProfessor("Renzo");
        final Professor professorRenan = professorService.buscarProfessor("Renan");

        final Professor professorRenzoEsperado = getProfessor("Renzo", "21:30:00", "noturno");
        final Professor professorRenanEsperado = getProfessor("Renan", "10:00:00", "diurno");

        assertEquals(professorRenzo.getNome(), professorRenzoEsperado.getNome());
        assertEquals(professorRenzo.getHorario(), professorRenzoEsperado.getHorario());
        assertEquals(professorRenzo.getPeriodo(), professorRenzoEsperado.getPeriodo());
        assertEquals(professorRenan.getNome(), professorRenanEsperado.getNome());
        assertEquals(professorRenan.getHorario(), professorRenanEsperado.getHorario());
        assertEquals(professorRenan.getPeriodo(), professorRenanEsperado.getPeriodo());
    }

    @Test
    public void testeBuscarProfessorPhyllQueNaoExiste(){

        final Professor professor = professorService.buscarProfessor("Phyll");

        final Professor professorEsperado = getProfessor("Inexistente", "00:00:00", "inexistente");

        assertEquals(professorEsperado.getNome(), professor.getNome());
        assertEquals(professorEsperado.getHorario(), professor.getHorario());
        assertEquals(professorEsperado.getPeriodo(), professor.getPeriodo());
    }

    @Test
    public void testeBuscarProfessorPassandoNomeNull(){

        final Professor professor = professorService.buscarProfessor(null);

        final Professor professorEsperado = getProfessor("Inexistente", "00:00:00", "inexistente");

        assertEquals(professorEsperado.getNome(), professor.getNome());
        assertEquals(professorEsperado.getHorario(), professor.getHorario());
        assertEquals(professorEsperado.getPeriodo(), professor.getPeriodo());
    }

    @Test
    public void testeBuscarProfessorPassandoStringVazia(){

        final Professor professor = professorService.buscarProfessor("");

        final Professor professorEsperado = getProfessor("Inexistente", "00:00:00", "inexistente");

        assertEquals(professorEsperado.getNome(), professor.getNome());
        assertEquals(professorEsperado.getHorario(), professor.getHorario());
        assertEquals(professorEsperado.getPeriodo(), professor.getPeriodo());
    }

    private Professor getProfessor(String nome, String horario, String periodo){
        return new Professor(
            nome,
            LocalTime.parse(horario),
            periodo
        );
    }
}
