package tw.team.project.dao;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Repository;

import tw.team.project.model.Minibar;



@Repository
public interface MinibarDAO {

	public List<Minibar> find(JSONObject obj);

	public long count(JSONObject obj);
}
