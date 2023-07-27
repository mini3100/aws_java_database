package service;

import java.util.ArrayList;
import java.util.List;

import entity.ProductColor;
import repository.ProductColorRepository;

public class ProductColorService {
	
	private ProductColorRepository productColorRepository;
	private static ProductColorService instance;
	
	private ProductColorService() {
		productColorRepository = ProductColorRepository.getInstance();
	}
	
	public static ProductColorService getInstance() {
		if(instance == null) {
			instance = new ProductColorService();
		}
		return instance;
	}
	
	public List<String> getProductColorNameList(){
		List<String> productColorNameList = new ArrayList<>();
		
		productColorRepository.getProductColorListAll().forEach(productColor -> {
			productColorNameList.add(productColor.getProductColorName());
		});
		
		return productColorNameList;
	}
	
	public boolean isProductColorNameDuplicated(String productColorName) {
		boolean result = false;
		result = productColorRepository.findProductColorByProductColorName(productColorName) != null;
		//true : null이라면 중복된 결과(sql에서 unique 오류가 난 것)
		return result;
	}
	
	public boolean registerProductColor(ProductColor productColor) {
		boolean result = false;
		result = productColorRepository.saveProductColor(productColor) > 0;
		//true : 0보다 크면 성공적으로 insert 된 것
		return result;
	}
}
