package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Supplier;
import tw.team.project.model.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public Supplier saveSup(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	public List<Supplier> findall() {
		return supplierRepository.findAll();
	}

	public void deleteSupById(Integer id) {
		supplierRepository.deleteById(id);
	}

	public Supplier findSupById(Integer id) {
		Optional<Supplier> optional = supplierRepository.findById(id);

		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Supplier updatesup(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

////////////////////////////////////////////////////
	public boolean existById(Integer id) {
		if (id != null) {
			return supplierRepository.existsById(id);
		}
		return false;
	}

	public Supplier create(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			String name = obj.isNull("name") ? null : obj.getString("name");
			String cname = obj.isNull("cname") ? null : obj.getString("cname");
			String phone = obj.isNull("phone") ? null : obj.getString("phone");
			String email = obj.isNull("email") ? null : obj.getString("email");
			String address = obj.isNull("address") ? null : obj.getString("address");
			Supplier insert = new Supplier();
			insert.setName(name);
			insert.setCname(cname);
			insert.setPhone(phone);
			insert.setEmail(email);
			insert.setAddress(address);
			return supplierRepository.save(insert);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(Integer id) {
		if (id != null) {
			Optional<Supplier> optional = supplierRepository.findById(id);
			if (optional.isPresent()) {
				supplierRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

	public Supplier modify(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String name = obj.isNull("name") ? null : obj.getString("name");
			String cname = obj.isNull("cname") ? null : obj.getString("cname");
			String phone = obj.isNull("phone") ? null : obj.getString("phone");
			String email = obj.isNull("email") ? null : obj.getString("email");
			String address = obj.isNull("address") ? null : obj.getString("address");
			if (id != null) {
				Optional<Supplier> optional = supplierRepository.findById(id);
				if (optional.isPresent()) {
					Supplier update = optional.get();
					update.setName(name);
					update.setCname(cname);
					update.setPhone(phone);
					update.setEmail(email);
					update.setAddress(address);
					return supplierRepository.save(update);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Supplier findById(Integer id) {
		if (id != null) {
			Optional<Supplier> optional = supplierRepository.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}
}
