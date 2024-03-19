package com.mizbah.adapter;

import org.springframework.stereotype.Service;

import com.mizbah.adapter.interfaces.AbstractAdapter;
import com.mizbah.dto.BranchTableDto;
import com.mizbah.entity.BranchTable;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BranchTableAdapter extends AbstractAdapter<BranchTable, BranchTableDto> {

	TableTypeAdapter tableTypeAdapter;

	@Override
	public BranchTable toEntity(BranchTableDto dto) {
		return BranchTable.builder().build();
	}

	@Override
	public BranchTableDto toDto(BranchTable entity) {
		return BranchTableDto.builder()
				.id(entity.getId())
				.tableType(tableTypeAdapter.toDto(entity.getTableType()))
				.build();

	}

}
