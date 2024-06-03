package tw.team.project.controller;

import java.net.URI;

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

import tw.team.project.model.Comment;
import tw.team.project.model.Member;
import tw.team.project.service.CommentService;
import tw.team.project.service.MemberService;

@RestController
@RequestMapping("/hotel")
@CrossOrigin
public class CommentController {
	@Value("${local.serverPort}")
	private String serverUri;
	@Autowired
	private CommentService commService;
	@Autowired
	private MemberService memberService;
	
    @PostMapping("/comments")
    public ResponseEntity<?> create(@RequestBody Comment comment){
        if (comment!=null){
            Comment newComment = commService.insert(comment);
            if (newComment!=null){
                String uri = serverUri+"/hotel/comments"+newComment.getCommentId();
                return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON).body(newComment);
            }
            
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/backend/comments")
    public ResponseEntity<?> findAll(@RequestParam(value="p",defaultValue = "1")Integer pageNumber){
    	Page<Comment> page = commService.findAll(pageNumber);
    	return ResponseEntity.ok(page.getContent());
    }
    
    @GetMapping("/comments/{pk}")
    public ResponseEntity<?> findByMemberId(@RequestParam(value="p", defaultValue = "1")Integer pageNumber, @PathVariable("pk")Integer memberId){
    	Member member = memberService.findbyId(memberId);
    	if (member!=null) {
    		Page<Comment> page = commService.findByMemberId(pageNumber, memberId);
    		return ResponseEntity.ok(page.getContent());
    	}
    	return ResponseEntity.notFound().build();
    }
    @PutMapping("/comments/{pk}")
    public ResponseEntity<?> modify(@PathVariable("pk")Integer commentId, @RequestBody Comment comment){
    	if (comment!=null && comment.getCommentId()!=null && comment.getCommentId()==commentId) {
    		Comment newComment = commService.update(comment);
    		if (newComment!=null) {
    			return ResponseEntity.ok(newComment);
    		}
    	}
    	return ResponseEntity.notFound().build();
    	
    }
    @GetMapping("/comments/instances/{item}")
    public ResponseEntity<?> findByInstance(@RequestParam(value="p", defaultValue = "1")Integer pageNumber, @PathVariable("item")String name){
    	Page<Comment> page = commService.findByInstance(pageNumber, name);
    	if (page.hasContent()) {
    		return ResponseEntity.ok(page.getContent());
    	}
    	return ResponseEntity.notFound().build();
    }
//    5/29新增評論刪除測試成功
    @DeleteMapping("/comments/instances/{pk}")
    public ResponseEntity<Void> remove(@PathVariable(name = "pk") Integer id) {
    	if(id!=null && id!=0) {
    		boolean exists = commService.existById(id);
    		if(exists) {
    			if(commService.delete(id)) {
    				return ResponseEntity.noContent().build();
    			}
    		}
    	}
 		return ResponseEntity.notFound().build();
    }
	
}