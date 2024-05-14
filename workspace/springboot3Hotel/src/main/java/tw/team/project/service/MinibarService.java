package tw.team.project.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import tw.team.project.model.Minibar;
import tw.team.project.repository.MinibarRepository;
import tw.team.project.util.DatetimeConverter;

@Service
@Transactional
public class MinibarService {

	@Autowired
	private MinibarRepository minibarRepo;
	
	
	public List<Minibar> findAll() {
		return minibarRepo.findAll();
}

	public boolean existsByItem(String item) {
		if (item != null && item.length() != 0) {
			long result = minibarRepo.countByItem(item);
			if (result != 0) {
				return true;
			}
		}
		return false;
	}
	
	public Minibar insert(Minibar bean) {
		if(bean!=null && bean.getId()!=null) {
			Optional<Minibar> optional = minibarRepo.findById(bean.getId());
			if(optional.isEmpty()) {
				return minibarRepo.save(bean);
			}
		}
		return null;
	}

	public long count(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return minibarRepo.count(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public boolean existById(Integer id) {
		if (id != null) {
			return minibarRepo.existsById(id);
		}
		return false;
	}

	public List<Minibar> find(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			return minibarRepo.find(obj);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Minibar findById(Integer id) {
		if (id != null) {
			Optional<Minibar> optional = minibarRepo.findById(id);
			if (optional.isPresent()) {
				return optional.get();
			}
		}
		return null;
	}

	public List<Minibar> select(Minibar bean) {
		List<Minibar> result = null;
		if (bean != null && bean.getId() != null && !bean.getId().equals(0)) {
			Optional<Minibar> optional = minibarRepo.findById(bean.getId());
			if (optional.isPresent()) {
				result = new ArrayList<Minibar>();
				result.add(optional.get());
			}
		} else {
			result = minibarRepo.findAll();
		}
		return result;
	}

	public Minibar create(String json, MultipartFile multipartFile) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String item = obj.isNull("item") ? null : obj.getString("item");
			String price = obj.isNull("price") ? null : obj.getString("price");
			BigDecimal price1 = new BigDecimal (price);
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");
			byte[] photo = multipartFile.isEmpty() ? null : multipartFile.getBytes();

			if (id != null) {
				Optional<Minibar> optional = minibarRepo.findById(id);
				if (optional.isEmpty()) {
					Minibar insert = new Minibar();
					insert.setId(id);
					insert.setItem(item);
					insert.setPrice(price1);
					if (make != null && make.length() != 0) {
						java.util.Date temp = DatetimeConverter.parse(make, "yyyy-MM-dd");
						insert.setMake(temp);
					} else {
						insert.setMake(null);
					}
					insert.setExpire(expire);
					insert.setPhoto(photo);

					return minibarRepo.save(insert);
				}
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Minibar modify(String json, MultipartFile multipartFile) {
		try {
			JSONObject obj = new JSONObject(json);

			Integer id = obj.isNull("id") ? null : obj.getInt("id");
			String item = obj.isNull("item") ? null : obj.getString("item");
			String price = obj.isNull("price") ? null : obj.getString("price");
			BigDecimal price1 = new BigDecimal (price);
			String make = obj.isNull("make") ? null : obj.getString("make");
			Integer expire = obj.isNull("expire") ? null : obj.getInt("expire");

			if (id != null) {
				Optional<Minibar> optional = minibarRepo.findById(id);
				if (optional.isPresent()) {
					Minibar update = optional.get();
					update.setId(id);
					update.setItem(item);
					update.setPrice(price1);
					if (make != null && make.length() != 0) {
						java.util.Date temp = DatetimeConverter.parse(make, "yyyy-MM-dd");
						update.setMake(temp);
					} else {
						update.setMake(null);
					}
					update.setExpire(expire);

					// 如果传入了新的文件，则更新文件内容
					if (multipartFile != null && !multipartFile.isEmpty()) {
						byte[] photo = multipartFile.getBytes();
						update.setPhoto(photo);
					}
					return minibarRepo.save(update);
				}
			}

		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean delete(Integer id) {
		if (id != null) {
			Optional<Minibar> optional = minibarRepo.findById(id);
			if (optional.isPresent()) {
				minibarRepo.deleteById(id);
				return true;
			}
		}
		return false;
	}

}
