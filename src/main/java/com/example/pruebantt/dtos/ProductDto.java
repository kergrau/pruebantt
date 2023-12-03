package com.example.pruebantt.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 5124172863334956773L;
	String name;
	String category;
}
