package tw.team.project.dao;

import java.util.List;

import org.json.JSONObject;

import tw.team.project.model.Product;

public interface ProductDAO {

	List<Product> find(JSONObject obj);

	long count(JSONObject obj);
	

}