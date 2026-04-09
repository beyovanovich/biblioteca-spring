package com.example.QuickOrder.repository;

import com.example.QuickOrder.model.Pedido;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class PedidoRepository {

    private List<Pedido> lista = new ArrayList<>();
    private Long id = 1L;

    public Pedido guardar(Pedido p) {
        p.setId(id++);
        lista.add(p);
        return p;
    }

    public List<Pedido> listar() {
        return lista;
    }

    public Optional<Pedido> buscar(Long id) {
        return lista.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void eliminar(Long id) {
        lista.removeIf(p -> p.getId().equals(id));
    }
}
