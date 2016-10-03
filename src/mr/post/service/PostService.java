package mr.post.service;

import java.util.List;

import mr.post.dao.PostDao;
import mr.post.domain.PageBean;
import mr.post.domain.Post;

public class PostService {
	private PostDao postDao=new PostDao();

	public void add(Post post) {
		postDao.add(post);
	}

	/*public List<Post> findAll() {
		return postDao.findAll();
	}*/
	
	public PageBean<Post> findAll(int pc, int ps) {
		return postDao.findAll(pc, ps);
	}
	
	public List<Post> findUserPost(String uname) {
		return postDao.findUserPost(uname);
	}

	public Post findPost(String pid) {
		return postDao.findPost(pid);
	}

	public void addPcomment(String pid) {
		postDao.addPcomment(pid);
	}

	public void changeZan(String pid, String text) {
		postDao.changeZan(pid, text);
	}

	public String findZan(String pid) {
		return postDao.findZan(pid);
	}

	public List<Post> findHotPost() {
		return postDao.findHotPost();
	}

	public List<Post> findColdPost() {
		return postDao.findColdPost();
	}

	public void delete(String pid) {
		postDao.delete(pid);
	}

}
