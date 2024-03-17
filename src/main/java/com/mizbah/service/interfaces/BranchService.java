package com.mizbah.service.interfaces;

import java.util.List;

import com.mizbah.dto.CityDto;

public interface BranchService {

	List<CityDto> getBranchesByRestaurantId(long restaurantId);

	List<CityDto> createBranch(long restaurantId, long cityId);

	List<CityDto> deleteBranch(long restaurantId, long cityId);

}
