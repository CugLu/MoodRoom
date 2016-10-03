package mr.post.service;

import java.util.List;

import mr.post.dao.CommentDao;
import mr.post.domain.Comment;

public class CommentService {
	private CommentDao commentDao=new CommentDao();

	public List<Comment> findPostComment(String pid) {
		return commentDao.findPostComment(pid);
	}

	public void add(Comment comment) {
		commentDao.add(comment);
	}
	
}
