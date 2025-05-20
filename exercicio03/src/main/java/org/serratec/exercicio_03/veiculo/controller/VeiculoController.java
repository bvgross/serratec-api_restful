package org.serratec.exercicio_03.veiculo.controller;

import org.serratec.exercicio_03.veiculo.domain.Veiculo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private static List<Veiculo> lista = new ArrayList<>();

    static {
        lista.add(new Veiculo(1, "Fiat", "Uno"));
        lista.add(new Veiculo(2, "Fiat", "Palio"));
        lista.add(new Veiculo(3, "Volkswagen", "Gol"));
    }

    @GetMapping
    public List<Veiculo> listar() {
        return lista;
    }

    @GetMapping("/{id}")
    public Veiculo buscar(@PathVariable int id) {
        return lista.stream()
            .filter(v -> v.getId() == id).findAny().orElse(null);
    }

    @PostMapping
    public Veiculo inserir(@RequestBody Veiculo veiculo) {
        Random random = new Random();
        int id = random.nextInt();
        veiculo.setId(id);
        lista.add(veiculo);
        return veiculo;
    }

    @PutMapping("/{id}")
    public Veiculo atualizar(@RequestBody Veiculo veiculo, @PathVariable int id) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                Veiculo v = new Veiculo(id, veiculo.getMarca(), veiculo.getModelo());
                lista.set(i, v);
                return v;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        for(int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getId() == id) {
                lista.remove(i);
                break;
            }
        }

    }
}
