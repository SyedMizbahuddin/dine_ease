package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mizbah.adapter.TableTypeAdapter;
import com.mizbah.dto.TableTypeDto;
import com.mizbah.entity.TableType;
import com.mizbah.repository.TableTypeRepository;
import com.mizbah.service.interfaces.TableTypeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class TableTypeServiceImpl implements TableTypeService {

	TableTypeRepository tableTypeRepository;
	TableTypeAdapter tableTypeAdapter;

	@Override
	public List<TableTypeDto> getAllTableTypes() {
		return tableTypeAdapter.toDto(tableTypeRepository.findAll());
	}

	@Override
	public TableTypeDto getTableTypeById(long id) {
		Optional<TableType> tableType = tableTypeRepository.findById(id);
		if (tableType.isEmpty()) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}
		return tableTypeAdapter.toDto(tableType.get());
	}

	@Override
	public TableTypeDto createTableType(TableTypeDto tableTypeRequest) {
		TableType tableType = tableTypeAdapter.toEntity(tableTypeRequest);
		tableTypeRepository.save(tableType);
		return tableTypeAdapter.toDto(tableType);
	}

	@Transactional
	@Override
	public TableTypeDto updateTableType(long id, TableTypeDto tableTypeRequest) {

		if (!tableTypeRepository.existsById(id)) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}

		TableType tableType = tableTypeAdapter.toEntity(tableTypeRequest);
		tableType.setId(id);
		tableTypeRepository.save(tableType);
		return tableTypeAdapter.toDto(tableType);

	}

	@Transactional
	@Override
	public void deleteTableType(long id) {
		if (!tableTypeRepository.existsById(id)) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}
		
		tableTypeRepository.deleteById(id);

	}
}
