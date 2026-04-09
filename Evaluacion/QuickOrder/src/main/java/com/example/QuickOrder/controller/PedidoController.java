package com.example.QuickOrder.controller;

import com.example.QuickOrder.model.*;
import com.example.QuickOrder.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear (@RequestBody Pedido p) {
        return ResponseEntity.status(201).body(service.crear(p));
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pedido p) {
        return ResponseEntity.ok(service.actualizar(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estado/{estado}")
    public List<Pedido> filtrar(@PathVariable Estado estado) {
        return service.filtrarPorEstado(estado);
    }


}
