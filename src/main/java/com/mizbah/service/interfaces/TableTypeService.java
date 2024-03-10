package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.TableTypeDto;

public interface TableTypeService {

	List<TableTypeDto> getAllTableTypes();

	TableTypeDto getTableTypeById(long id);

	TableTypeDto createTableType(TableTypeDto tableType);

	TableTypeDto updateTableType(long id, TableTypeDto tableType);

	void deleteTableType(long id);

}
