package tw.team.project.controller;


import java.util.ArrayList;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.dto.TransactionDTO;
import tw.team.project.model.Transaction;
import tw.team.project.service.TransactionService;
import tw.team.project.util.JsonContainer;


@RestController
@RequestMapping("/hotel")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

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
    
    // 刪除必須和訂單同時進行
}
