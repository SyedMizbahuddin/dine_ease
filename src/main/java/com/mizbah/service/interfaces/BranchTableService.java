package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.BranchTableDto;

public interface BranchTableService {

	BranchTableDto createTable(long branchId, long tableId, BranchTableDto branchTableRequest);

	List<BranchTableDto> getTablesByBranchId(long branchId);

}
