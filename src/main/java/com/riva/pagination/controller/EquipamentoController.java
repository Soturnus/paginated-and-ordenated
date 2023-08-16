package com.riva.pagination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riva.pagination.entities.Equipamento;
import com.riva.pagination.services.EquipamentoService;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {
    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping(value = "/{status}")
    public ResponseEntity<Page<Equipamento>> buscarEquipamentosPaginados(
    		@PathVariable Integer status,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina) {
        Page<Equipamento> equipamentos = equipamentoService.buscarEquipamentosPaginadosOrdenados(status, pagina, tamanhoPagina);
        return ResponseEntity.ok(equipamentos);
    }
    
    @GetMapping("/status2")
    public ResponseEntity<List<Equipamento>> buscarPorStatus2(){
    	
    	List<Equipamento> equipamentos = equipamentoService.buscarTodosStatus2();
		return ResponseEntity.ok(equipamentos);

    }
    
}
