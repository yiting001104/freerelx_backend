package tw.team.project.dao;

import java.util.List;

import org.json.JSONObject;

import tw.team.project.model.RoomAssignment;

public interface RoomAssignmentDAO {

	public List<RoomAssignment> find(JSONObject obj);

	public long count(JSONObject obj);
}
