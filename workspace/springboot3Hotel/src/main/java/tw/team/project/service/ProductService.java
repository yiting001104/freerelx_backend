package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Product;
import tw.team.project.model.ProductRepository;
import tw.team.project.model.Supplier;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product saveproduct(Product product) {
		return productRepository.save(product);
	}

	public Product findByProductName(String name) {
		return productRepository.findByProductName(name);
	}

	public Product create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String productName = obj.isNull("productName") ? null : obj.getString("productName");
			Integer productPrice = obj.isNull("productPrice") ? null : obj.getInt("productPrice");
			Integer productStock = obj.isNull("productStock") ? null : obj.getInt("productStock");
			Integer productSupplierId = obj.isNull("productSupplierId") ? null : obj.getInt("productSupplierId");
			String productDescription = obj.isNull("productDescription") ? null : obj.getString("productDescription");
			Integer productArrivalDay = obj.isNull("productArrivalDay") ? null : obj.getInt("productArrivalDay");
			Product insert = new Product();
			Supplier supplier = new Supplier();
			insert.setProductName(productName);
			insert.setProductPrice(productPrice);
			insert.setProductStock(productStock);
			supplier.setId(productSupplierId);
			insert.setProductSupplierId(supplier);
			insert.setProductDescription(productDescription);
			insert.setProductArrivalDay(productArrivalDay);
			return productRepository.save(insert);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean existById(Integer id) {
		if (id != null) {
			return productRepository.existsById(id);
		}
		return false;
	}

	public boolean delete(Integer id) {
		if (id != null) {
			Optional<Product> optional = productRepository.findById(id);
			if (optional.isPresent()) {
				productRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

	public Product modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String productName = obj.isNull("productName") ? null : obj.getString("productName");
			Integer productPrice = obj.isNull("productPrice") ? null : obj.getInt("productPrice");
			Integer productStock = obj.isNull("productStock") ? null : obj.getInt("productStock");
			Integer productSupplierId = obj.isNull("productSupplierId") ? null : obj.getInt("productSupplierId");
			String productDescription = obj.isNull("productDescription") ? null : obj.getString("productDescription");
			Integer productArrivalDay = obj.isNull("productArrivalDay") ? null : obj.getInt("productArrivalDay");
			if (id != null) {
				Optional<Product> optional = productRepository.findById(id);
				if (optional.isPresent()) {
					Product insert = optional.get();
					Supplier supplier = new Supplier();
					insert.setProductName(productName);
					insert.setProductPrice(productPrice);
					insert.setProductStock(productStock);
					supplier.setId(productSupplierId);
					insert.setProductSupplierId(supplier);
					insert.setProductDescription(productDescription);
					insert.setProductArrivalDay(productArrivalDay);
					return productRepository.save(insert);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Product findById(Integer id) {
		if (id != null) {
			Optional<Product> optional = productRepository.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByowner(Supplier productSupplierId) {
		return (List<Product>) productRepository.findByowner(productSupplierId);
	}

	public long countByOwner(Supplier productSupplierId) {
		return productRepository.countByOwner(productSupplierId);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return productRepository.count(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public List<Product> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return productRepository.find(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
