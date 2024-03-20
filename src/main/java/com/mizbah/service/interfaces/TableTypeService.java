package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.TableTypeDto;
import com.mizbah.dto.request.TableTypeRequest;

public interface TableTypeService {

	List<TableTypeDto> getAllTableTypes();

	TableTypeDto getTableTypeById(long id);

	TableTypeDto createTableType(TableTypeRequest tableType);

	TableTypeDto updateTableType(long id, TableTypeRequest tableType);

	void deleteTableType(long id);

}
