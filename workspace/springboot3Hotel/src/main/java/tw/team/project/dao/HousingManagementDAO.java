package tw.team.project.dao;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONObject;

import tw.team.project.model.HousingManagement;

public interface HousingManagementDAO {

	
	public List<HousingManagement> find(JSONObject obj);

	public long count(JSONObject obj);
}
