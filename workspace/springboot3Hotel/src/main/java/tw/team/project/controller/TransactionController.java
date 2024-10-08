package tw.team.project.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import tw.team.project.dto.TransactionDTO;
import tw.team.project.model.Transaction;
import tw.team.project.service.TransactionService;
import tw.team.project.util.JsonContainer;


@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;
    
    private RestTemplate template = new RestTemplate();

    @GetMapping("/orderRoom/transactions")
    public ResponseEntity<?> listOrderPage(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber){
        Page<Transaction> page = transactionService.findAll(pageNumber);
        List<TransactionDTO> tranList = new ArrayList<>();
        for (Transaction trans: page.getContent()){
           tranList.add(new JsonContainer().setTransaction(trans));
        }
        return ResponseEntity.ok(tranList);
    }

    @GetMapping("/orderRoom/transactions/{pk}")
    public ResponseEntity<?> findById(@PathVariable("pk") Integer id){
        Transaction trans = transactionService.findById(id);
        if (trans != null){
            TransactionDTO transDTO = new JsonContainer().setTransaction(trans);
            ResponseEntity<TransactionDTO> ok = ResponseEntity.ok(transDTO);
            return ok;
        } else {
            ResponseEntity<Void> notFound = ResponseEntity.notFound().build();
            return notFound;
        }
    }

    @PutMapping("/orderRoom/transactions/{pk}")
    public ResponseEntity<?> modify(@PathVariable("pk") Integer id, @RequestBody TransactionDTO transDTO){
        
        if (transactionService.existById(id)){
            TransactionDTO newTrans = transactionService.update(transDTO);
            if (newTrans!=null){
                return ResponseEntity.ok(newTrans);
            }
        }
        return ResponseEntity.notFound().build();
    }
    
    // 刪除由訂單那邊進行同時進行因為CascadeType.ALL關係
    
    @GetMapping("/orderRoom/transactions/orderId-{pk}")
    public ResponseEntity<?> findByOrderId(@PathVariable("pk") Integer id){
        Transaction trans = transactionService.finByOrderId(id);
        if (trans != null){
            TransactionDTO transDTO = new JsonContainer().setTransaction(trans);
            ResponseEntity<TransactionDTO> ok = ResponseEntity.ok(transDTO);
            return ok;
        } else {
            ResponseEntity<Void> notFound = ResponseEntity.notFound().build();
            return notFound;
        }
    }
    
    
    //linePay
    @PostMapping("/orderRoom/transactions/line-pay")
    public ResponseEntity<?> requestToLinePay(@RequestBody String json){
    	
    	try {
			String response = transactionService.callLinePay(json);
			JSONObject obj = new JSONObject(response);
			URI uri = URI.create("https://sandbox-api-pay.line.me/v3/payments/request");
			HttpMethod method = HttpMethod.POST;
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("X-LINE-ChannelId", "2005471992");
			headers.add("X-LINE-Authorization-Nonce", obj.getString("nonce"));
			headers.add("X-LINE-Authorization",obj.getString("signature"));
			RequestEntity<String> request = new RequestEntity<>(obj.getString("body"), headers, method, uri);
			System.out.println("拿到傳給linePay的 header "+request.getHeaders());
			System.out.println("拿到傳給linePay的 methods "+request.getMethod());
			
			if (request.hasBody()) {
//				ResponseEntity<String> result = template.exchange(request, String.class);
//				System.out.println("line回復付款 "+result);
//				System.out.println("result.getBody() "+result.getBody());
//				return ResponseEntity.ok(result.getBody());
				return ResponseEntity.noContent().build();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/orderRoom/transactions/confirm/line-pay")
    public ResponseEntity<?> confirmToLinePay(@RequestBody String json){
    	JSONObject responseJson = new JSONObject();
    	String endPoint = "https://sandbox-api-pay.line.me";
    	try {
			String response = transactionService.confirmLinePay(json);
			HttpMethod method = HttpMethod.POST;
			JSONObject obj = new JSONObject(response);
			
			URI uri = URI.create(endPoint+obj.getString("confirmUri"));
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("X-LINE-ChannelId", "2005471992");
			headers.add("X-LINE-Authorization-Nonce", obj.getString("confirmNonce"));
			headers.add("X-LINE-Authorization",obj.getString("signatureConfirm"));
			RequestEntity<String> request = new RequestEntity<>(obj.getString("confrimBody"), headers, method, uri);
			
			
			
			if (request.hasBody()) {
				ResponseEntity<String> result = template.exchange(request, String.class);
				System.out.println(result);
				return ResponseEntity.ok(result.getBody());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ResponseEntity.noContent().build();
    }
    
    
    //linePayv2
    @PostMapping("/orderRoom/transactions/line-payV2")
    public ResponseEntity<?> requestToLinePayV2(@RequestBody String json){
    	
    	try {
    		String requestObkect = transactionService.callLinePayV2(json);
			if (requestObkect!=null) {
				System.out.println(requestObkect);
				
			
			
				URI uri = URI.create("https://sandbox-api-pay.line.me/v2/payments/request");
				HttpMethod method = HttpMethod.POST;
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "application/json");
				headers.add("X-LINE-ChannelId", "2005471992");
				headers.add("X-LINE-ChannelSecret", "1958c77216e3e214b2604182db455bc5");
				RequestEntity<String> request = new RequestEntity<>(requestObkect, headers, method, uri);
				System.out.println("拿到傳給linePay的 header in v2"+request.getHeaders());
				System.out.println("拿到傳給linePay的 methods "+request.getMethod());
				if (request.hasBody()) {
					ResponseEntity<String> result = template.exchange(request, String.class);
					System.out.println("line回復付款 "+result);
					System.out.println("result.getBody() "+result.getBody());
					return ResponseEntity.ok(result.getBody());
					
				}
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/orderRoom/transactions/confirm/line-payV2")
    public ResponseEntity<?> confirmToLinePayV2(@RequestBody String json){
    	JSONObject responseJson = new JSONObject();
    	String endPoint = "https://sandbox-api-pay.line.me";
    	try {
			String response = transactionService.confirmLinePay(json);
			HttpMethod method = HttpMethod.POST;
			JSONObject obj = new JSONObject(response);
			
			URI uri = URI.create(endPoint+obj.getString("confirmUri"));
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("X-LINE-ChannelId", "2005471992");
			headers.add("X-LINE-Authorization-Nonce", obj.getString("confirmNonce"));
			headers.add("X-LINE-Authorization",obj.getString("signatureConfirm"));
			RequestEntity<String> request = new RequestEntity<>(obj.getString("confrimBody"), headers, method, uri);
			
			
			
			if (request.hasBody()) {
				ResponseEntity<String> result = template.exchange(request, String.class);
				System.out.println(result);
				return ResponseEntity.ok(result.getBody());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return ResponseEntity.noContent().build();
    }
}