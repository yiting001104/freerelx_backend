package tw.team.project.controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Income;
import tw.team.project.service.IncomeService;


@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class IncomeController {
	@Value("${local.serverPort}")
	private String serverUri;
	@Autowired
	private IncomeService incomeService;
	
    @GetMapping("/incomes")
    public ResponseEntity<?> listBank(@RequestParam(value = "p", defaultValue = "1")Integer pagenumber){
        Page<Income> page = incomeService.findAll(pagenumber);
        return ResponseEntity.ok(page.getContent());

    }

    @PostMapping("/incomes")
    public ResponseEntity<?> create(@RequestBody Income income){
        if (income!=null){
            Income newIncome = incomeService.insert(income);
            if (newIncome!=null){
                String uri = serverUri+"/hotel/banks"+newIncome.getIncomeId();
                Income dbIncome = incomeService.findById(income.getIncomeId());
                return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON).body(dbIncome);
            }
        
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/incomes/{pk}")
    public ResponseEntity<Void> remove(@PathVariable("pk") Integer id){
        if(incomeService.deleteById(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/incomes/{beginDate}-{endDate}")
    public ResponseEntity<?> findByDate(@PathVariable("beginDate") @DateTimeFormat(pattern = "yyyy.MM.dd") Date beginDate, @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy.MM.dd") Date endDate){
    	
    	if (beginDate!=null && endDate!=null) {
    		List<Income> listIncome = incomeService.findByCreateDate(beginDate,endDate);
    		return ResponseEntity.ok(listIncome);
    	}
        return ResponseEntity.notFound().build();
    }
    
}
