package tw.team.project.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.team.project.model.Comment;
import tw.team.project.model.CreditCardDiscount;
import tw.team.project.service.CommentService;

@RestController
@RequestMapping("/hotel")
public class CommentController {
	
	@Autowired
	private CommentService commService;
	
    @PostMapping("/comments")
    public ResponseEntity<?> create(@RequestBody Comment comment){
        if (comment!=null){
            Comment newComment = commService.insert(comment);
            if (newComment!=null){
                String uri = "http://localhost:8080/hotel/comments"+newComment.getCommentId();
                return ResponseEntity.created(URI.create(uri)).contentType(MediaType.APPLICATION_JSON).body(newComment);
            }
            
        }
        return ResponseEntity.notFound().build();
    }
	
}
