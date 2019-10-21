package org.zerock.dto;

import lombok.Data;

@Data
public class PositionSearchDTO {
	private double currLat;
	private double currLng;
	private Integer page;
	
	public PositionSearchDTO() {
		this.page = 1;
	}
}
