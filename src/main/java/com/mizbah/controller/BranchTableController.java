package com.mizbah.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.BranchTableDto;
import com.mizbah.service.interfaces.BranchTableService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
@RestController
public class BranchTableController {

	BranchTableService branchTableService;

	@GetMapping("/branches/{branch_id}/branch-tables")
	ResponseEntity<List<BranchTableDto>> getTablesByBranchId(@PathVariable("branch_id") long branchId) {
		return ResponseEntity.ok(branchTableService.getTablesByBranchId(branchId));
	}

	@PostMapping("/branches/{branch_id}/branch-tables/{table_id}")
	ResponseEntity<BranchTableDto> createTable(@PathVariable("branch_id") long branchId,
			@PathVariable("table_id") long tableId) {
		return ResponseEntity.ok(branchTableService.createTable(branchId, tableId));
	}

	// Update & delete TODO

}
