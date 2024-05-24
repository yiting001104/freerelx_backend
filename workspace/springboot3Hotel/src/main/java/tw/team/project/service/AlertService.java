package tw.team.project.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.team.project.model.Alert;
import tw.team.project.model.AlertRepository;
import tw.team.project.model.Cart;
import tw.team.project.model.CartRepository;

@Service
public class AlertService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private AlertRepository alertRepository;
	
	//新增一筆下架訊息
	public boolean createalert(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer productId = obj.isNull("productId") ? null : obj.getInt("productId");
			List<Cart> all=cartRepository.findByidCart(productId);
			for (Cart one : all) {
				Alert alert = new Alert();
				alert.setMemberid(one.getMember());//得到成員id
				alert.setAlertmessage("{"+one.getId().getProductName()+"}產品已被下架了!");//得到產品名稱
				alertRepository.save(alert); //儲存
			}
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	//使用者得到訊息
	public List<Alert> getalert(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			Integer memberid = obj.isNull("memberid") ? null : obj.getInt("memberid");
			List<Alert> alert=alertRepository.findByAlertmemberId(memberid);
			return alert;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void deletealert(Integer id) {
		alertRepository.deleteById(id);
	}
}
