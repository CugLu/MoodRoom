package mr.post.dao;

import java.sql.SQLException;
import java.util.List;

import mr.post.domain.Comment;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class CommentDao {
	private QueryRunner qr = new TxQueryRunner();

	public List<Comment> findPostComment(String pid) {
		try {
			String sql="select * from tb_comment where pid=?";
			return qr.query(sql, new BeanListHandler<Comment>(Comment.class), pid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void add(Comment comment) {
		try {
			String sql = "insert into tb_comment values(?,?,?,?)";
			
			Object[] params = {comment.getCid(),comment.getPid(),
					comment.getUname(),comment.getCcomment()};
			qr.update(sql, params);
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
