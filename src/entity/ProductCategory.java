package entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductCategory {
	private int productCategoryId;
	private String productCategoryName;
}
