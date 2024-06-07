package tw.team.project.dao;

import java.util.List;

import org.json.JSONObject;

import tw.team.project.model.RoomManagement;

public interface RoomManagementDAO {
	
	public List<RoomManagement> find(JSONObject obj);

	public long count(JSONObject obj);

}
