package tw.team.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tw.team.project.model.Comment;
import tw.team.project.model.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commRepository;
	
	public Comment insert(Comment newComment) {
		if (newComment != null) {
			return commRepository.save(newComment);
		}
		return null;
	}
//	
//	public Page<Comment> findAll(Integer pageNumber){
//		Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.ASC,"")
//		commRepository
//	}
}
