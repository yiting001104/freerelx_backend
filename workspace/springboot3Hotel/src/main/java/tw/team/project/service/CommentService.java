package tw.team.project.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
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
	
	public Page<Comment> findAll(Integer pageNumber){
		Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.DESC,"createDate");
		return commRepository.findAll(pgb);
	}
	
	public Page<Comment> findByMemberId(Integer pageNumber, Integer memberId){
		if (memberId!=null) {
			Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.DESC,"createDate");
			return commRepository.findByMemberId(memberId, pgb);
		}
		return null;
	}
	
	@Transactional
	public Comment update(Comment comment) {
		if (comment.getCommentId()!=null) {
			Optional<Comment> optional = commRepository.findById(comment.getCommentId());
			if (optional.isPresent()) {
				Comment origin = optional.get();
				origin.setCreateDate(new Date());
				origin.setCommentText(comment.getCommentText());
				origin.setPicture(comment.getPicture());
				origin.setScore(comment.getScore());
				origin.setSituationType(comment.getSituationType());
				origin.setTypeInstance(comment.getTypeInstance());
				return origin;
			}
			
		}
		return null;
		
	}
	
	public Page<Comment> findByInstance(Integer pageNumber, String instanceName){
		if (instanceName!=null && instanceName.length()!=0) {
			Pageable pgb = PageRequest.of(pageNumber-1, 10, Sort.Direction.DESC,"createDate");
			return commRepository.findByInstance(instanceName, pgb);
		}
		return null;
	}
//   5/29新增評論刪除測試成功
	public boolean existById(Integer id) {
		if(id!=null) {
			return commRepository.existsById(id);
		}
		return false;
	}
	public boolean delete(Integer id) {
		if(id!=null) {
			Optional<Comment> optional = commRepository.findById(id);
			if(optional.isPresent()) {
				commRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}
}