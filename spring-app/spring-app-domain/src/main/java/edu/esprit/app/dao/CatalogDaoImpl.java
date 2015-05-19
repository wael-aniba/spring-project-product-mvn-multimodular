package edu.esprit.app.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import edu.esprit.app.entities.Product;

public class CatalogDaoImpl implements ICatalogDao {
	
	Logger logger = Logger.getLogger(CatalogDaoImpl.class);
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public void addProduct(Product p) {

		em.merge(p);
	}

	@Override
	public List<Product> getAllProducts() {

		return em.createQuery("select p from Product p").getResultList();
	}

	@Override
	public List<Product> getAllProductByCriteria(String criteria) {

		return em
				.createQuery(
						"select p from Product p where p.designation=:param")
				.setParameter("param", criteria).getResultList();
	}

	@Override
	public Product getProductByReference(String ref) {

		return em.find(Product.class, ref);
	}

	@Override
	public void deleteProduct(String ref) {

		em.remove(getProductByReference(ref));
	}

	@Override
	public void updateProduct(Product p) {

		em.merge(p);
	}
	
	public void init(){
		
		logger.info("\n/////////////////////////\nInitiating catalog\n/////////////////////////\n");
		
		addProduct(new Product("ref1", "Sony", 1000.0, 12));
		addProduct(new Product("ref2", "Dell", 1500.0, 15));
		addProduct(new Product("ref3", "Lenovo", 2000.0, 12));
		
	}
	
}
