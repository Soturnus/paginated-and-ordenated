package com.riva.pagination.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riva.pagination.entities.Equipamento;
import com.riva.pagination.repositories.EquipamentoRepository;

@Service
public class EquipamentoService {
    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public Page<Equipamento> buscarEquipamentosPaginadosOrdenados(Integer status, int pagina, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(pagina, tamanhoPagina, Sort.by("dtMovimento").ascending());
        return equipamentoRepository.findByStatusOrderByDtMovimentoAsc(status, pageable);
    }
    
    public List<Equipamento> buscarTodosStatus2(){
    	List<Equipamento> equipamentos = equipamentoRepository.findAll();
    	
    	ordenarPorDataMaisAntiga(equipamentos);
    	
    	Collections.sort(equipamentos, new Comparator<Equipamento>() {
            @Override
            public int compare(Equipamento item1, Equipamento item2) {
                if (item1.getStatus() == 2 && item2.getStatus() != 2) {
                    return -1;
                } else if (item1.getStatus() != 2 && item2.getStatus() == 2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        
        List<Equipamento> equipamentosOrdenados = new ArrayList<>();
        
        for (Equipamento equipamento : equipamentos) {
        	equipamentosOrdenados.add(equipamento);
        }         
        
		return equipamentosOrdenados;
    }
    
    private static List<Equipamento> ordenarPorDataMaisAntiga(List<Equipamento> equipamento) {
        Collections.sort(equipamento, (equipamento1, equipamento2) -> 
        equipamento1.getDtMovimento().compareTo(equipamento2.getDtMovimento()));
        return equipamento;
    }
    
}
