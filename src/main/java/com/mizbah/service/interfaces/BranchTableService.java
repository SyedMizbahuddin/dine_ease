package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.BranchTableDto;
import com.mizbah.entity.User;

public interface BranchTableService {

	BranchTableDto createTable(long branchId, long tableId);

	List<BranchTableDto> getTablesByBranchId(long branchId);

	void deleteTable(long branchTableId);

	boolean isOwner(Long branchTableId, User authUser);

}
