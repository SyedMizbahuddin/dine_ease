package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.TableTypeDto;
import com.mizbah.entity.TableType;

public interface TableTypeService {

	List<TableType> getAllTableTypes();

	TableType getTableTypeById(long id);

	TableType createTableType(TableTypeDto tableType);

	TableType updateTableType(long id, TableTypeDto tableType);

	void deleteTableType(long id);

}
