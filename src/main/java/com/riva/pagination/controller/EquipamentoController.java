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

    //EXAMPLE: Paginado
    /*
     * nessa rota é possivel filtrar 2 serviço por qualquer status especifico
     * a quantidade de itens desejado por pagina
     * e os itens de qual pagina deseja verificar
     * 
     * localhost:8080/equipamentos/2?pagina=0&tamanhoPagina=10
     */
    @GetMapping(value = "/{status}")
    public ResponseEntity<Page<Equipamento>> buscarEquipamentosPaginados(
    		@PathVariable Integer status,
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int tamanhoPagina) {
        Page<Equipamento> equipamentos = equipamentoService.buscarEquipamentosPaginadosOrdenados(status, pagina, tamanhoPagina);
        return ResponseEntity.ok(equipamentos);
    }
    
    //EXAMPLE: OrdenadoPeloStatus2
    /*
     * Aqui esta ordenando tudo pelo mais antigo,
     * depois é feito um filtro para puxar todos os que tem status 2
     * em seguida incluir os demais itens da lista (ordenados pelos mais antigos)
     * 
     * localhost:8080/equipamentos/status2
     */
    @GetMapping("/status2")
    public ResponseEntity<List<Equipamento>> buscarPorStatus2(){
    	
    	List<Equipamento> equipamentos = equipamentoService.buscarTodosStatus2();
		return ResponseEntity.ok(equipamentos);

    }
    
}
