package tw.team.project.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
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

import tw.team.project.dto.RefundTypeDTO;
import tw.team.project.model.RefundType;
import tw.team.project.service.RefundTypeService;
import tw.team.project.util.JsonContainer;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class RefundTypeController {
	@Value("${local.serverPort}")
	private String serverUri;
    @Autowired
    private RefundTypeService refundTypeService;

    @GetMapping("/refundTypes")
    public ResponseEntity<?> listRefundType(@RequestParam(value = "p",defaultValue = "1") Integer pageNumber){
        Page<RefundType> page = refundTypeService.findAll(pageNumber);
        List<RefundTypeDTO> refundTypeList = new ArrayList<>();
        for (RefundType refundTypeype : page.getContent()){
            refundTypeList.add(new JsonContainer().setRefundType(refundTypeype));
        }
        return ResponseEntity.ok(refundTypeList);
        
    }
    
    @GetMapping("/refundTypes/{pk}")
    public ResponseEntity<?> findById(@PathVariable("pk") Integer id){
    	if (id!=null) {
    		RefundType refund = refundTypeService.findById(id);
    		if (refund!=null) {
    			return ResponseEntity.ok(refund);
    		}
    	}
    	return ResponseEntity.notFound().build();
    }

    @PostMapping("/refundTypes")
    public ResponseEntity<?> create(@RequestBody RefundType refundType){
        if (refundType!=null){
            if (!refundTypeService.existByName(refundType.getType())){
                RefundType newRefundType =  refundTypeService.insert(refundType);
                if (newRefundType!=null){
                    String uri = serverUri+"/hotel/refundType"+newRefundType.getRefundTypeId();
                    return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON).body(newRefundType);
                }
            }
        }
        return ResponseEntity.notFound().build();
        
    }

    @PutMapping("/refundTypes/{pk}")
    public ResponseEntity<?> modify(@PathVariable("pk") Integer id, @RequestBody RefundType refundType){
        if (refundType!=null && refundType.getRefundTypeId()!= null && refundType.getRefundTypeId()==id){
            RefundType newRefundType = refundTypeService.update(refundType);
            if (newRefundType!=null){
                return ResponseEntity.ok(newRefundType);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/refundTypes/{pk}")
    public ResponseEntity<?> remove(@PathVariable("pk") Integer id){
        if (refundTypeService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
