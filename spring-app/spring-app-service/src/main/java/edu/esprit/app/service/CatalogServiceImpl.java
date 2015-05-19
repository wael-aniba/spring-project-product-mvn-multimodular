package edu.esprit.app.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import edu.esprit.app.dao.ICatalogDao;
import edu.esprit.app.entities.Product;

@Transactional
public class CatalogServiceImpl implements ICatalogService {
	
	
	private ICatalogDao dao;

	//Setter for dap required for dependency injection
	public void setDao(ICatalogDao dao) {
		this.dao = dao;
	}
	@Override
	public void addProduct(Product p) {

		dao.addProduct(p);
	}

	@Override
	public List<Product> getAllProducts() {
		
		return dao.getAllProducts();
	}

	@Override
	public List<Product> getAllProductByCriteria(String criteria) {

		return dao.getAllProductByCriteria(criteria);
	}

	@Override
	public Product getProductByReference(String ref) {
		
		return dao.getProductByReference(ref);
	}

	@Override
	public void deleteProduct(String ref) {
		
		dao.deleteProduct(ref);
	}

	@Override
	public void updateProduct(Product p) {

		dao.updateProduct(p);
	}
	
}
