package tw.team.project.controller;

import java.net.URI;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Minibar;
import tw.team.project.service.MinibarService;
import tw.team.project.util.DatetimeConverter;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class MinibarController {
	
	@Value("${local.serverPort}")
	private String serverUri;
	
	@Autowired
	private MinibarService minibarService;

	// 判斷是否有minibar
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
	        e.printStackTrace();
	    }
	    return null;
	}

	// 編輯商品
	@PutMapping("/minibar/{pk}")
	public ResponseEntity<?> modify(@PathVariable("pk") Integer id, @RequestBody Minibar product) {
	    if (product != null && product.getId() != null && product.getId() != 0) {
	        boolean exists = minibarService.existById(product.getId());
	        if (exists) {
	            Minibar newProduct = minibarService.update(product);
	            if (newProduct != null) {
	                return ResponseEntity.ok(newProduct);
	            }
	        }
	    }
	    return ResponseEntity.notFound().build();
	}
	
	
	// 新增商品
	@PostMapping("/minibar")
	public ResponseEntity<?> create(@RequestBody Minibar bean) {
	    // if (bean != null) {
	    //     Minibar product = minibarService.insert(bean);
	    //     if (product != null) {
	    //         String uri = "http://localhost:8080/hotel/minibar" + product.getId();
	    //         return ResponseEntity.created(URI.create(uri))
	    //                 .contentType(MediaType.APPLICATION_JSON)
	    //                 .body(product);
	    //     }
	    // }
	    // return ResponseEntity.noContent().build();

		if (bean != null) {
			Minibar product = minibarService.insert(bean);
			if (product != null) {
				String uri = serverUri+"/hotel/minibar" + product.getId();
				return ResponseEntity.created(URI.create(uri))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(product);			}
		}return ResponseEntity.noContent().build();

	}

	// findAll~
	@PostMapping("/minibar/findall")
	public String findAll() throws JSONException {
		List<Minibar> products = minibarService.findAll();
		JSONArray array = new JSONArray();

		if (products != null && !products.isEmpty())
			for (Minibar product : products) {
				JSONObject item = new JSONObject().put("id", product.getId()).put("item", product.getItem())
						.put("price", product.getPrice()).put("make", product.getMake())
						.put("expire", product.getExpire()).put("photo", product.getPhoto());

				array.put(item);
			}

		return array.toString();
	}
	
//	// 依條件查詢
//	@PostMapping("/minibar/find")
//	public String find(@RequestBody String json) throws JSONException {
//	    JSONObject responseJson = new JSONObject();
//	    JSONArray array = new JSONArray();
//	    List<Minibar> products = minibarService.find(json);
//	    if (products != null && !products.isEmpty()) {
//	        for (Minibar minibar : products) {
//	            String make = DatetimeConverter.toString(minibar.getMake(), "yyyy-MM-dd");
//	            JSONObject item = new JSONObject()
//	                    .put("id", minibar.getId())
//	                    .put("item", minibar.getItem())
//	                    .put("price", minibar.getPrice())
//	                    .put("make", make)
//	                    .put("expire", minibar.getExpire())
//	                    .put("photoId", minibar.getId());
//	            array.put(item);
//	        }
//	    }
//	    responseJson.put("list", array);
//
//	    long count = minibarService.count(json);
//	    responseJson.put("count", count);
//
//	    return responseJson.toString();
//	}

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
	
	// 查詢商品
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
	
	@PostMapping("/minibar/find")
	public String find(@RequestBody String json) throws JSONException {
	    JSONObject responseJson = new JSONObject();
	    JSONArray array = new JSONArray();
	    List<Minibar> products = minibarService.find(json);
	    if (products != null && !products.isEmpty()) {
	        for (Minibar minibar : products) {
	            String make = DatetimeConverter.toString(minibar.getMake(), "yyyy-MM-dd");
	            String photoBase64 = Base64.getEncoder().encodeToString(minibar.getPhoto());
	            JSONObject item = new JSONObject()
	                    .put("id", minibar.getId())
	                    .put("item", minibar.getItem())
	                    .put("price", minibar.getPrice())
	                    .put("make", make)
	                    .put("expire", minibar.getExpire())
	                    .put("photo", photoBase64);
	            array.put(item);
	        }
	    }
	    responseJson.put("list", array);

	    long count = minibarService.count(json);
	    responseJson.put("count", count);

	    return responseJson.toString();
	}

	
	
//	private byte[] photo = null;
//	@GetMapping(path = "/minibar/photos/{photoid}", produces = MediaType.IMAGE_JPEG_VALUE)
//	public @ResponseBody byte[] findPhotoByPhotoId(@PathVariable("photoid") Integer id) {
//	    Minibar minibar = minibarService.findById(id);
//	    byte[] result = this.photo;
//	    if (minibar != null) {
//	    	result = minibar.getPhoto();
//	    }
//	        return result;
//	    }
	

    
//
//    private byte[] photo = null;
//
//    @PostConstruct
//    public void initialize() throws IOException {
//        byte[] buffer = new byte[8192];
//
//        ClassLoader classLoader = getClass().getClassLoader();
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        BufferedInputStream is = new BufferedInputStream(
//                classLoader.getResourceAsStream("static/images/no-image.jpg"));
//        int len = is.read(buffer);
//        while (len != -1) {
//            os.write(buffer, 0, len);
//            len = is.read(buffer);
//        }
//        is.close();
//        this.photo = os.toByteArray();
//    }
//    
//    @GetMapping(path = "/minibar/photo/{photoid}", produces = MediaType.IMAGE_JPEG_VALUE)
//    public @ResponseBody byte[] findPhotoByPhotoId(@PathVariable(name = "photoid") Integer photoid) {
//        Minibar minibar = minibarService.findById(photoid);
//        byte[] result = this.photo;
//        if (minibar != null && minibar.getPhoto() != null) {
//            result = minibar.getPhoto();
//        }
//        return result;
//    }

}