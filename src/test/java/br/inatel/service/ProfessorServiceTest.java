package br.inatel.service;

import br.inatel.model.Professor;
import br.inatel.repository.ProfessorRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ProfessorServiceTest {

    private final ProfessorRepository professorRepository = mock(ProfessorRepository.class);

    private final ProfessorService professorService = new ProfessorService(professorRepository);

    @Test
    public void testeBuscarProfessorChris(){
        when(professorRepository.encontrarProfessor(anyString())).thenReturn(getProfessorString("Chris", "17:30:00", "noturno"));

        final Professor professor = professorService.buscarProfessor("Chris");

        final Professor professorEsperado = getProfessor("Chris", "17:30:00", "noturno");

        assertEquals(professorEsperado.getNome(), professor.getNome());
        assertEquals(professorEsperado.getHorario(), professor.getHorario());
        assertEquals(professorEsperado.getPeriodo(), professor.getPeriodo());
    }

    @Test
    public void testeBuscarProfessorMarceloEVerificarChamadoDoProfessorRepository(){
        when(professorRepository.encontrarProfessor(anyString())).thenReturn(getProfessorString("Marcelo", "15:30:00", "diurno"));

        final Professor professor = professorService.buscarProfessor("Marcelo");

        final Professor professorEsperado = getProfessor("Marcelo", "15:30:00", "diurno");

        assertEquals(professorEsperado.getNome(), professor.getNome());
        assertEquals(professorEsperado.getHorario(), professor.getHorario());
        assertEquals(professorEsperado.getPeriodo(), professor.getPeriodo());
        verify(professorRepository, times(1)).encontrarProfessor(anyString());
    }

    @Test
    public void testeBuscarRenzoERenanEVerificarSeProfessorRepositoryFoiChamado2Vezes(){
        when(professorRepository.encontrarProfessor(anyString())).thenReturn(getProfessorString("Renzo", "21:30:00", "noturno"));
        when(professorRepository.encontrarProfessor(anyString())).thenReturn(getProfessorString("Renan", "10:00:00", "diurno"));

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
        verify(professorRepository, times(2)).encontrarProfessor(anyString());
    }

    @Test
    public void testeBuscarProfessorPhyllQueNaoExiste(){
        when(professorRepository.encontrarProfessor("Phyll")).thenReturn(null);

        assertThrows(
                NullPointerException.class,
                () -> professorService.buscarProfessor("Phyll")
        );

        verify(professorRepository, times(1)).encontrarProfessor("Phyll");
    }

    @Test
    public void testeBuscarProfessorPassandoNomeNull(){
        when(professorRepository.encontrarProfessor(null)).thenReturn(null);

        assertThrows(
                NullPointerException.class,
                () -> professorService.buscarProfessor(null)
        );

        verify(professorRepository, times(1)).encontrarProfessor(null);
    }

    @Test
    public void testeBuscarProfessorPassandoStringVazia(){
        when(professorRepository.encontrarProfessor("")).thenReturn(null);

        assertThrows(
                NullPointerException.class,
                () -> professorService.buscarProfessor("")
        );

        verify(professorRepository, times(1)).encontrarProfessor("");
    }

    private String getProfessorString(String nome, String horario, String periodo){
        return  "{ \"nomeDoProfessor\": \""  + nome + "\", \"horarioDeAtendimento\": \"" + horario + "\", \"periodo\": \"" + periodo + "\"}";
    }

    private Professor getProfessor(String nome, String horario, String periodo){
        return new Professor(
            nome,
            LocalTime.parse(horario),
            periodo
        );
    }
}
