package tw.team.project.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.dto.CreditCardDiscountDTO;
import tw.team.project.model.CreditCardDiscount;
import tw.team.project.service.CreditCardDiscountService;
import tw.team.project.util.JsonContainer;

@RestController
@RequestMapping("/hotel")
public class CreditCardDiscountController {

    @Autowired
    private CreditCardDiscountService creditCardDiscountService;
    
    @GetMapping("/banks")
    public ResponseEntity<?> listBank(@RequestParam(value = "p", defaultValue = "1")Integer pagenumber){
        Page<CreditCardDiscount> page = creditCardDiscountService.findAll(pagenumber);
        List<CreditCardDiscountDTO> bankList = new ArrayList<>();
        for (CreditCardDiscount bank : page.getContent()){
            bankList.add(new JsonContainer().setCrediteCard(bank));
        }
        return ResponseEntity.ok(bankList);

    }

    @GetMapping("/banks/{pk}")
    public ResponseEntity<?> findByid(@PathVariable("pk") Integer id){
        CreditCardDiscount bank = creditCardDiscountService.findById(id);
        if (bank != null){
            CreditCardDiscountDTO bankDTO = new JsonContainer().setCrediteCard(bank);
            return ResponseEntity.ok(bankDTO);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    
    @PostMapping("/banks")
    public ResponseEntity<?> create(@RequestBody CreditCardDiscount bank){
        if (bank!=null){
            boolean exists = creditCardDiscountService.existByName(bank.getBankName());
            if (!exists){
                CreditCardDiscount newBank = creditCardDiscountService.insert(bank);
                if (newBank!=null){
                    String uri = "http://localhost:8080/hotel/banks"+newBank.getBankId();
                    return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON).body(newBank);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/banks/{pk}")
    public ResponseEntity<?> modify(@PathVariable("pk") Integer id ,@RequestBody CreditCardDiscount bank){
        if (bank != null && bank.getBankId()!=null && bank.getBankId()== id){
            CreditCardDiscount newBank = creditCardDiscountService.update(bank);
            if (newBank!=null){
                return ResponseEntity.ok(newBank);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/banks/{pk}")
    public ResponseEntity<Void> remove(@PathVariable("pk") Integer id){
        if(creditCardDiscountService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
