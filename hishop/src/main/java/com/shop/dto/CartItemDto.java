package com.shop.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemDto {
	
	@NotNull(message = "상품 아이디는 필수 입력 값입니다.")
	private Long ItemId;
	
	@Min(message = "최소 1개이상 담아주세요.", value = 1)
	private int count;
}
