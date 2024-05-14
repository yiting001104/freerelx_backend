package tw.team.project.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.team.project.model.Minibar;
import tw.team.project.service.MinibarService;
import tw.team.project.util.DatetimeConverter;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class MinibarController {

	@Autowired
	private MinibarService minibarService;
	

	//判斷是否有minibar
	@GetMapping("/minibar/item/{item}")
	public String existsByItem(@PathVariable("item") String item) {
		JSONObject responseJson = new JSONObject();
		boolean exist = minibarService.existsByItem(item);
		try {
			if (exist) {
				responseJson.put("success", false);
				responseJson.put("message", "品項已存在");
			} else {
				responseJson.put("success", true);
				responseJson.put("message", "品項不存在");
			}
			return responseJson.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	// 編輯商品
	@PutMapping("/minibar/{pk}")
	public String modify(@PathVariable(name = "pk") Integer id, @RequestParam("file") MultipartFile file,
			@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (!minibarService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id不存在");
		} else {
			Minibar product = minibarService.modify(json, file);
			if (product == null) {
				responseJson.put("success", false);
				responseJson.put("message", "修改失敗");
			} else {
				responseJson.put("success", true);
				responseJson.put("message", "修改成功");
			}
		}
		return responseJson.toString();
	}

	// 新增商品
	@PostMapping("/minibar")
	public String create(@RequestParam("file") MultipartFile file, @RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONObject obj = new JSONObject(json);
		Integer id = obj.isNull("id") ? null : obj.getInt("id");
		
				
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (minibarService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id已存在");
		} else {
			Minibar product = minibarService.create(json, file);
			if (product == null) {
				responseJson.put("success", false);
				responseJson.put("message", "新增失敗");
			} else {
				responseJson.put("success", true);
				responseJson.put("message", "新增成功");
			}
		}
		return responseJson.toString();
	}
	
	// 新增商品
    @PostMapping("/minibar/create")
    public ResponseEntity<?> create(@RequestBody Minibar bean) {
    	if(bean!=null && bean.getId()!=null && bean.getId()!=0) {
    		boolean exists = minibarService.existById(bean.getId());
    		if(!exists) {
    			Minibar product = minibarService.insert(bean);
    			if(product!=null) {
    				String uri = "http://localhost:8080/hotel/minibar/create/"+product.getId();
    				return ResponseEntity.created(URI.create(uri))
    						.contentType(MediaType.APPLICATION_JSON)
    						.body(product);
    			}
    		}
    	}    	
    	return ResponseEntity.noContent().build();
    }
	
	// findAll~
	@PostMapping("/minibar/findall")
	public String findAll() throws JSONException {
		List<Minibar> products = minibarService.findAll();
		JSONArray array = new JSONArray();
		
		if (products != null && !products.isEmpty())
		for (Minibar product : products) {
			JSONObject item = new JSONObject()
					.put("id", product.getId())
					.put("item", product.getItem())
					.put("price", product.getPrice())
					.put("make", product.getMake())
					.put("expire", product.getExpire())
					.put("photo", product.getPhoto());
					
			array.put(item);
		}

		return array.toString();
	}
	
	//依條件查詢~
	@PostMapping("/minibar/find")
	public String find(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();

		JSONArray array = new JSONArray();
		List<Minibar> products = minibarService.find(json);
		if (products != null && !products.isEmpty()) {
			for (Minibar minibar : products) {
				String make = DatetimeConverter.toString(minibar.getMake(), "yyyy-MM-dd");
				JSONObject item = new JSONObject()
						.put("id", minibar.getId())
						.put("item", minibar.getItem())
						.put("price", minibar.getPrice())
						.put("make", make)
						.put("expire", minibar.getExpire())
						.put("photo", minibar.getPhoto());
						
				array.put(item);
			}
		}
		responseJson.put("list", array);

		long count = minibarService.count(json);
		responseJson.put("count", count);

		return responseJson.toString();
	}

	// 移除商品~
	@DeleteMapping("/minibar/{pk}")
	public String remove(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (!minibarService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id不存在");
		} else {
			if (minibarService.delete(id)) {
				responseJson.put("success", true);
				responseJson.put("message", "刪除成功");
			} else {
				responseJson.put("success", false);
				responseJson.put("message", "刪除失敗");
			}
		}
		return responseJson.toString();
	}
	
    //findById~
	@GetMapping("/minibar/{pk}")
	public String findById(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Minibar product = minibarService.findById(id);
		if (product != null) {
			String make = DatetimeConverter.toString(product.getMake(), "yyyy-MM-dd");
			JSONObject item = new JSONObject()
					.put("id", product.getId())
					.put("item", product.getItem())
					.put("price", product.getPrice())
					.put("make", make)
					.put("expire", product.getExpire())
					.put("photo", product.getPhoto());
			array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

//	@GetMapping("/minibar/upload")
//	public String upload() {
//		return "minibar/upload";
//	}

//	@PostMapping("/minibar/upload")
//	public String uploadPost(
//			@RequestParam String item,
//			@RequestParam Integer price,
//			@RequestParam MultipartFile photoFile,
//			Model model)throws IOException{
//		
//		Minibar newMb = new Minibar();
//		newMb.setItem(item);
//		newMb.setPrice(price);
//		newMb.setPhotoFile(photoFile.getBytes());
//		
//		minibarService.saveMinibar(newMb);
//		
//		model.addAttribute("okMsg", "上傳成功!!");
//		
//		return "minibar/upload";
//	}
}
