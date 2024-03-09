package com.mizbah.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mizbah.dto.TableTypeDto;
import com.mizbah.entity.TableType;
import com.mizbah.repository.TableTypeRepository;
import com.mizbah.service.interfaces.TableTypeService;
import com.mizbah.util.ConversionUtil;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class TableTypeServiceImpl implements TableTypeService {

	TableTypeRepository tableTypeRepository;

	@Override
	public List<TableType> getAllTableTypes() {
		return tableTypeRepository.findAll();
	}

	@Override
	public TableType getTableTypeById(long id) {
		Optional<TableType> tableType = tableTypeRepository.findById(id);
		if (tableType.isEmpty()) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}
		return tableType.get();
	}

	@Override
	public TableType createTableType(TableTypeDto tableTypeRequest) {
		TableType tableType = ConversionUtil.convert(tableTypeRequest, TableType.class);
		tableTypeRepository.save(tableType);
		return tableType;
	}

	@Override
	public TableType updateTableType(long id, TableTypeDto tableTypeRequest) {

		if (!tableTypeRepository.existsById(id)) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}

		TableType tableType = ConversionUtil.convert(tableTypeRequest, TableType.class);
		tableType.setId(id);
		tableTypeRepository.save(tableType);
		return tableType;

	}

	@Override
	public void deleteTableType(long id) {
		if (!tableTypeRepository.existsById(id)) {
			throw new EntityNotFoundException("TableType not found with ID: " + id);
		}
		// dependency check to be made, CASCADE
		tableTypeRepository.deleteById(id);

	}
}
