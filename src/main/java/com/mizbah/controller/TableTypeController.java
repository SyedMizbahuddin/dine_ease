package com.mizbah.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mizbah.dto.TableTypeDto;
import com.mizbah.entity.TableType;
import com.mizbah.service.interfaces.TableTypeService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/table-types")
@AllArgsConstructor
@RestController
public class TableTypeController {

	TableTypeService tableTypeService;

	@GetMapping
	ResponseEntity<List<TableType>> getAllTableTypes() {
		return ResponseEntity.ok(tableTypeService.getAllTableTypes());
	}

	@GetMapping("/{id}")
	ResponseEntity<TableType> getTableTypeById(@PathVariable("id") long id) {
		return ResponseEntity.ok(tableTypeService.getTableTypeById(id));
	}

	@PostMapping
	ResponseEntity<TableType> createTableType(@Validated @RequestBody TableTypeDto tableType) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tableTypeService.createTableType(tableType));
	}

	@PutMapping("/{id}")
	ResponseEntity<TableType> updateTableType(@PathVariable("id") long id,
			@Validated @RequestBody TableTypeDto tableType) {
		return ResponseEntity.ok(tableTypeService.updateTableType(id, tableType));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteTableType(@PathVariable("id") long id) {
		tableTypeService.deleteTableType(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
