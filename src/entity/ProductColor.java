package entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductColor {
	private int productColorId;
	private String productColorName;
}
