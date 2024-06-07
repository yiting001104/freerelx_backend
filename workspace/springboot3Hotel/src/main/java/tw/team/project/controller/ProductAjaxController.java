package tw.team.project.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Product;
import tw.team.project.model.Supplier;
import tw.team.project.service.ProductService;

@RestController
@RequestMapping("hotel")
@CrossOrigin
public class ProductAjaxController {

	@Autowired
	private ProductService productService;
//////////要過濾
	@PostMapping("/products")
	public String create(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		Product product = productService.create(json);
		if (product == null) {
			responseJson.put("success", false);
			responseJson.put("message", "名稱已重複");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "新增成功");
		}
		return responseJson.toString();
	}
//////////要過濾
	@DeleteMapping("/products/{pk}")
	public String remove(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		if (id == null) {
			responseJson.put("success", false);
			responseJson.put("message", "id是必要欄位");
		} else if (!productService.existById(id)) {
			responseJson.put("success", false);
			responseJson.put("message", "id不存在");
		} else {
			if (productService.delete(id)) {
				responseJson.put("success", true);
				responseJson.put("message", "刪除成功");
			} else {
				responseJson.put("success", false);
				responseJson.put("message", "刪除失敗");
			}
		}
		return responseJson.toString();
	}
//////////要過濾
	@PutMapping("/products/modify")
	public String modify(@RequestBody String json) throws JSONException {
		JSONObject responseJson = new JSONObject();
		Product product = productService.modify(json);
		if (product == null) {
			responseJson.put("success", false);
			responseJson.put("message", "修改失敗");
		} else {
			responseJson.put("success", true);
			responseJson.put("message", "修改成功");
		}
		return responseJson.toString();
	}

	@GetMapping("/products/{pk}")
	public String findById(@PathVariable(name = "pk") Integer id) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		Product product = productService.findById(id);
		if (product != null) {
			JSONObject item = new JSONObject().put("id", product.getId()).put("productName", product.getProductName())
					.put("productPrice", product.getProductPrice()).put("productStock", product.getProductStock())
					.put("productSupplierId", product.getProductSupplierId().getId())
					.put("productDescription", product.getProductDescription())
					.put("productArrivalDay", product.getProductArrivalDay());
			array.put(item);
			System.out.println(product.getProductName());
		}
		responseJson.put("list", array);
		System.out.print(responseJson.toString());
		return responseJson.toString();
	}
//////////要過濾
	@GetMapping("/products/owner/{pk}")
	public String findByproductowner(@PathVariable(name = "pk") Supplier productSupplierId) throws JSONException {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Product> product = productService.findByowner(productSupplierId);
		if (product != null && !product.isEmpty()) {
			for (Product productone : product) {
				JSONObject item = new JSONObject().put("id", productone.getId())
						.put("productName", productone.getProductName())
						.put("productPrice", productone.getProductPrice())
						.put("productStock", productone.getProductStock())
						.put("productSupplierId", productone.getProductSupplierId().getId())
						.put("productDescription", productone.getProductDescription())
						.put("productArrivalDay", productone.getProductArrivalDay());
				array.put(item);
			}
		}
		long count = productService.countByOwner(productSupplierId);
		responseJson.put("count", count);
		responseJson.put("list", array);
		System.out.print(responseJson.toString());
		return responseJson.toString();
	}

	@PostMapping("/products/find")
	public String find(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		List<Product> products = productService.find(json);
		if (products != null && !products.isEmpty()) {
			for (Product productone : products) {
				JSONObject item = new JSONObject().put("id", productone.getId())
						.put("productName", productone.getProductName())
						.put("productPrice", productone.getProductPrice())
						.put("productStock", productone.getProductStock())
						.put("productSupplierId", productone.getProductSupplierId().getId())
						.put("productDescription", productone.getProductDescription())
						.put("productArrivalDay", productone.getProductArrivalDay());
				array.put(item);
			}
		}
		responseJson.put("list", array);
		long count = productService.count(json);
		responseJson.put("count", count);
		return responseJson.toString();
	}
}
