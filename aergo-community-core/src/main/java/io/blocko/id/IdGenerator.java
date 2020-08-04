package io.blocko.id;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.slf4j.Logger;

import io.blocko.model.Board;
import io.blocko.model.Comment;
import io.blocko.model.SimpleUser;

public class IdGenerator implements IdentifierGenerator {

	private final Logger logger = getLogger(getClass());
	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		Long id = null;
		
		try {
			final String sql = getSql(object);
			final PreparedStatement pstmt = connection.prepareStatement(sql);
			final ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				id = rs.getLong(1) + 1;
				logger.info("Generated Id : " + id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	private String getSql(Object object) {
		if(object instanceof SimpleUser) {
			return "select max(id) from user";
		}else if(object instanceof Board) {
			return "select max(id) from board";
		}else if(object instanceof Comment) {
			return "select max(id) from comment";
		}else {
			return null;
		}
	}
}
