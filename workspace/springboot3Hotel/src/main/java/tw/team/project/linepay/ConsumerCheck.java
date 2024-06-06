package tw.team.project.linepay;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class ConsumerCheck {
	@Value("${local.frontendPort}")
	private String frontendUri;
	
	public static String encrypt(final String keys, final String data) {
		return toBase64String(HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_256, keys.getBytes()).doFinal(data.getBytes()));
		}

    public static String toBase64String(byte[] bytes) {
        byte[] byteArray = Base64.encodeBase64(bytes);
        return new String(byteArray);
    }
	
    public String linePay(JSONObject inputJson) {
        CheckoutPaymentRequestForm form = new CheckoutPaymentRequestForm();
        
        JSONObject obj;
		try {
			Integer orderTotalAmount = inputJson.isNull("orderTotalAmount") ? null : inputJson.getInt("orderTotalAmount");
			Integer orderId = inputJson.isNull("orderId") ? null : inputJson.getInt("orderId");
			Integer totalPrice = inputJson.isNull("totalPrice") ? null : inputJson.getInt("totalPrice");
			String productId = inputJson.isNull("productId") ? null : inputJson.getString("productId");
			String productName = inputJson.isNull("productName") ? null : inputJson.getString("productName");
			String productPicture = inputJson.isNull("productPicture") ? null : inputJson.getString("productPicture");
			Integer productQuality = inputJson.isNull("productQuality") ? null : inputJson.getInt("productQuality");
			Integer singlePrice = inputJson.isNull("singlePrice") ? null : inputJson.getInt("singlePrice");
			
			System.out.println("orderId"+ orderId);
			
			
			form.setAmount(new BigDecimal(orderTotalAmount)); 	// 訂單總價格 ， 
			form.setCurrency("TWD");				// 訂單幣種
			form.setOrderId("RnJlZVJlbHggSE9URUw_"+orderId);   // 訂單編號(可以抓transaction_id)

			ProductPackageForm productPackageForm = new ProductPackageForm();
			productPackageForm.setId("test_"+orderId); 			  // 訂單Id
			productPackageForm.setName("連加網路商業股份有限公司");			  // 商店名稱 FreeRelx_HOTEL
			productPackageForm.setAmount(new BigDecimal(totalPrice));  // 商品總價格：商品數量乘上單價

			ProductForm productForm = new ProductForm();		
			productForm.setId("product_"+productId);					 // 商品id (roomInformation_id)
			productForm.setName(productName);				 // 商品名稱
			productForm.setImageUrl("");						 // 商品圖片 productPicture
			productForm.setQuantity(new BigDecimal(productQuality));		 // 商品數量
			productForm.setPrice(new BigDecimal(singlePrice));          // 商品價格 (單筆的價格) 
			productPackageForm.setProducts(Arrays.asList(productForm));

			form.setPackages(Arrays.asList(productPackageForm));
			RedirectUrls redirectUrls = new RedirectUrls();
			redirectUrls.setConfirmUrl(frontendUri+"/member/paySuccess");			// 成功後的轉導頁面  
			redirectUrls.setCancelUrl(frontendUri+"/member/payFalse");  		// 失敗後的轉導頁面  
			form.setRedirectUrls(redirectUrls);

			ObjectMapper mapper = new ObjectMapper();
			obj = new JSONObject();
			try {														  
				String ChannelSecret = "1958c77216e3e214b2604182db455bc5";
				String requestUri = "/v3/payments/request";
				String nonce = UUID.randomUUID().toString();
				String signature = encrypt(ChannelSecret, ChannelSecret + requestUri + mapper.writeValueAsString(form) + nonce);
				obj.put("nonce", nonce)
				   .put("signature", signature)
				   .put("body", mapper.writeValueAsString(form));
				System.out.println("body" + mapper.writeValueAsString(form));
				return obj.toString();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;				
        
    }
    
    public String confirmLine(JSONObject inputJson) {
         JSONObject obj;
 		try {
 			Integer orderTotalAmount = inputJson.isNull("orderTotalAmount") ? null : inputJson.getInt("orderTotalAmount");
 			String transactionId = inputJson.isNull("transactionId") ? null : inputJson.getString("transactionId");
 			

 			ObjectMapper mapper = new ObjectMapper();
 			obj = new JSONObject();
 			
 			ConfirmData confirmData = new ConfirmData();
 			confirmData.setAmount(new BigDecimal(orderTotalAmount));
 			confirmData.setCurrency("TWD");
 			
 			String confirmUri = "/v3/payments/"+transactionId+"/confirm";   // 含{transactionId}
 			
 			
 			try {														  
 				String ChannelSecret = "1958c77216e3e214b2604182db455bc5"; 				
 				String confrimBody = mapper.writeValueAsString(confirmData);
 				String confirmNonce = UUID.randomUUID().toString();
 				String signatureConfirm = encrypt(ChannelSecret, ChannelSecret + confirmUri + mapper.writeValueAsString(confirmData) + confirmNonce);
 				
 				obj.put("confirmNonce", confirmNonce)
 				   .put("signatureConfirm", signatureConfirm)
 				   .put("confrimBody", confrimBody)
 				   .put("confirmUri", confirmUri);
 					
 				
 				System.out.println("confrimBody" + mapper.writeValueAsString(confirmData));
 				return obj.toString();
 			} catch (JsonProcessingException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
 		} catch (JSONException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         
         return null;
    	
    }
}

