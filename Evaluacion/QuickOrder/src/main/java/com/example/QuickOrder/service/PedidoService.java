package com.example.QuickOrder.service;

import com.example.QuickOrder.model.*;
import com.example.QuickOrder.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository repo;

    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    public Pedido crear(Pedido p) {
        if (p.getNombreCliente() == null || p.getNombreCliente().isEmpty()) {
            throw new RuntimeException("Nombre obligatorio");
        }
        if (p.getMontoTotal() <= 0) {
            throw new RuntimeException("Monto no valido");
        }

        p.setFechaPedido(LocalDate.now());
        return repo.guardar(p);
    }

    public List<Pedido> listar() {
        return repo.listar();
    }

    public Pedido obtener(Long id) {
        return repo.buscar(id)
                .orElseThrow(() -> new RuntimeException("No existe"));
    }

    public Pedido actualizar(Long id, Pedido nuevo) {
        Pedido p = obtener(id);

        p.setNombreCliente(nuevo.getNombreCliente());
        p.setDescripcion(nuevo.getDescripcion());
        p.setEstado(nuevo.getEstado());
        p.setTipoPedido(nuevo.getTipoPedido());
        p.setMontoTotal(nuevo.getMontoTotal());

        return p;
    }

    public void eliminar(Long id) {
        obtener(id);
        repo.eliminar(id);
    }

    public List<Pedido> filtrarPorEstado(Estado estado) {
        return repo.listar()
                .stream()
                .filter(p -> p.getEstado() == estado)
                .collect(Collectors.toList());
    }
}
