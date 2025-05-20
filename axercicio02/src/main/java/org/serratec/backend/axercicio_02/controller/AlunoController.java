package org.serratec.backend.axercicio_02.controller;

import org.serratec.backend.axercicio_02.domain.Aluno;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private static List<Aluno> lista = new ArrayList<>();
    static {
        lista.add(new Aluno(1L, "Fulano", "1111-1111"));
        lista.add(new Aluno(2L, "Beltrano", "2222-2222"));
        lista.add(new Aluno(3L, "Ciclano", "3333-3333"));
    }

    @GetMapping
    public List<Aluno> listar() {
        return lista;
    }

    @GetMapping("/{matricula}") //variavel do endereco
    public Aluno buscar(@PathVariable Long matricula) { //PathVariable pega a variavel no endereco e usa como variavel
//        for (int i = 0; i < lista.size(); i++) {
//            if(lista.get(i).getMatricula().equals(matricula)) {
//                return lista.get(i);
//            }
//        }
//        return null;
        return lista.stream()
            .filter(a -> a.getMatricula().equals(matricula)).findFirst().orElse(null);
    }

    @PostMapping
    public Aluno inserir(@RequestBody Aluno aluno) {
        Random random = new Random();
        long matriculaAleatoria = random.nextLong();
        aluno.setMatricula(matriculaAleatoria);
        lista.add(aluno);
        return aluno;
    }

    @PutMapping("/{matricula}")
    public Aluno atualizar(@RequestBody Aluno aluno, @PathVariable Long matricula) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getMatricula().equals(matricula)) {
                Aluno a = new Aluno(matricula, aluno.getNome(), aluno.getTelefone());
                lista.set(i, a);
                return a;
            }
        }
        return null;
    }

    @DeleteMapping("/{matricula}")
    public void delete(@PathVariable Long matricula) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getMatricula().equals(matricula)) {
                lista.remove(i);
                break;
            }
        }

    }
}
