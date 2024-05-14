package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Product;
import tw.team.project.model.Productphoto;
import tw.team.project.model.ProductphotoRepository;

@Service
public class ProductphotoService {

	@Autowired
	private ProductphotoRepository productphotoRepository;

	public Productphoto savephoto(Productphoto pp) {
		return productphotoRepository.save(pp);
	}

	public Productphoto findById(Product productid) {
		if (productid != null) {
			List<Productphoto> optional = productphotoRepository.findByproductid(productid);
			if (!optional.isEmpty()) {
				return optional.get(0);
			}
		}
		return null;
	}

	public List<Productphoto> findByIdall(Product productid) {
		if (productid != null) {
			List<Productphoto> optional = productphotoRepository.findByproductid(productid);
			return optional;
		}
		return null;
	}

	public Productphoto findByentityId(Integer id) {
		if (id != null) {
			Optional<Productphoto> optional = productphotoRepository.findById(id);
			if (!optional.isEmpty()) {
				return optional.get();
			}
		}
		return null;
	}

	public boolean delete(Integer id) {
		if (id != null) {
			Optional<Productphoto> optional = productphotoRepository.findById(id);
			if (optional.isPresent()) {
				productphotoRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

	public boolean existById(Integer id) {
		if (id != null) {
			return productphotoRepository.existsById(id);
		}
		return false;
	}
}
