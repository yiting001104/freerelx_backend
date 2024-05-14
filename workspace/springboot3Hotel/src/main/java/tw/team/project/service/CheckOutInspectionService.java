package tw.team.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.CheckOutInspection;
import tw.team.project.repository.CheckOutInspectionRepository;

@Service
public class CheckOutInspectionService {

	@Autowired
	private CheckOutInspectionRepository CheckOutInspectionRepo;

	public List<CheckOutInspection> findAll() {
		return CheckOutInspectionRepo.findAll();
	}

	public CheckOutInspection findById(Integer id) {
		Optional<CheckOutInspection> option = CheckOutInspectionRepo.findById(id);
		if (option.isPresent())
			return option.get();
		else
			return null;
	}

//	public long count(String json) {
//		try {
//			JSONObject obj = new JSONObject(json);
//			return CheckOutInspectionRepo.count(obj);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}

	public boolean existById(Integer id) {
		if (id != null) {
			return CheckOutInspectionRepo.existsById(id);
		}
		return false;
	}

	public CheckOutInspection update(CheckOutInspection bean) {
		if (bean != null && bean.getId() != null) {
			Optional<CheckOutInspection> optional = CheckOutInspectionRepo.findById(bean.getId());
			if (optional.isPresent()) {

				CheckOutInspection check = optional.get();

				check.setCompensation(bean.getCompensation());
				check.setFee(bean.getFee());
				check.setPhoto(bean.getPhoto());

				return CheckOutInspectionRepo.save(bean);
			}
		}
		return null;
	}

	public CheckOutInspection insert(CheckOutInspection bean) {
		if (bean != null && bean.getId() != null) {
			Optional<CheckOutInspection> optional = CheckOutInspectionRepo.findById(bean.getId());
			if (optional.isEmpty()) {
				CheckOutInspection check = optional.get();

				check.setCompensation(bean.getCompensation());
				check.setFee(bean.getFee());
				check.setPhoto(bean.getPhoto());

				return CheckOutInspectionRepo.save(bean);
			}
		}
		return null;
	}
}
