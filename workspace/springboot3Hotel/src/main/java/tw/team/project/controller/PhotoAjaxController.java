package tw.team.project.controller;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tw.team.project.model.Product;
import tw.team.project.model.Productphoto;
import tw.team.project.service.ProductphotoService;

@RestController
@RequestMapping("hotel")
@CrossOrigin
public class PhotoAjaxController {
	@Autowired
	private ProductphotoService productphotoService;

	private byte[] photo = null;

	@PostMapping("/photos/upload")
	public String uploadPost(@RequestParam Product productid, @RequestParam MultipartFile photoFile, Model model)
			throws IOException {
		JSONObject responseJson = new JSONObject();
		Productphoto gp = new Productphoto();
		gp.setPhotoFile(photoFile.getBytes());
		gp.setProductid(productid);
		productphotoService.savephoto(gp);
		responseJson.put("success", true);
		responseJson.put("message", "新增成功");
		return responseJson.toString();
	}

	@GetMapping(path = "/photos/{photoid}", produces = { MediaType.IMAGE_JPEG_VALUE })
	public @ResponseBody byte[] findPhotoByPhotoId(@PathVariable(name = "photoid") Product productid) {
		Productphoto detail = productphotoService.findById(productid);
		byte[] result = this.photo;
		if (detail != null) {
			result = detail.getPhotoFile();
		}
		return result;
	}

	@GetMapping(path = "/photosALL/{photoid}")
	public String findPhotoByPhotoIdALL(@PathVariable(name = "photoid") Product productid) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Productphoto> detail = productphotoService.findByIdall(productid);
		if (detail != null && !detail.isEmpty()) {
			for (Productphoto one : detail) {
				JSONObject item = new JSONObject().put("id", one.getId());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@GetMapping(path = "/photo/{photoentityid}", produces = { MediaType.IMAGE_JPEG_VALUE })
	public @ResponseBody byte[] findPhotoByentityId(@PathVariable(name = "photoentityid") Integer id) {
		Productphoto detail = productphotoService.findByentityId(id);
		byte[] result = this.photo;
		if (detail != null) {
			result = detail.getPhotoFile();
		}
		return result;
	}

	@DeleteMapping("/photos/{pk}")
	public String remove(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (!productphotoService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id不存在");
		} else {
			if (productphotoService.delete(id)) {
				responseJson.put("success", true);
				responseJson.put("message", "刪除成功");
			} else {
				responseJson.put("success", false);
				responseJson.put("message", "刪除失敗");
			}
		}
		return responseJson.toString();
	}
}
