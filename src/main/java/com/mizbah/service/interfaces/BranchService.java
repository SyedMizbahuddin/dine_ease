package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.BranchDto;
import com.mizbah.entity.User;

public interface BranchService {

	List<BranchDto> getBranchesByRestaurantId(long restaurantId);

	BranchDto createBranch(long restaurantId, long cityId);

	void deleteBranch(long restaurantId);

	List<BranchDto> getRestaurantsByCityId(long cityId);

	boolean isOwner(Long branchId, User authUser);

}
