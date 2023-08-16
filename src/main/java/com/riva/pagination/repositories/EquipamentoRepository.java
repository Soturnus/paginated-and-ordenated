package com.riva.pagination.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.riva.pagination.entities.Equipamento;

public interface EquipamentoRepository extends PagingAndSortingRepository<Equipamento, Long>{

	Page<Equipamento> findByStatusOrderByDtMovimentoAsc(Integer status, Pageable pageable);

	List<Equipamento> findAll();
	
}
