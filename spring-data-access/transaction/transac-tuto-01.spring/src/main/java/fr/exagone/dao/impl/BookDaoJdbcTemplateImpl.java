package fr.exagone.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import fr.exagone.dao.IBookDao;
import fr.exagone.dao.ex.ExceptionDao;
import fr.exagone.entity.IBookEntity;

@Repository
public class BookDaoJdbcTemplateImpl extends JdbcDaoSupport implements IBookDao {

	private static final Logger LOG = LogManager.getLogger();
	
	public BookDaoJdbcTemplateImpl(@Autowired DataSource dataSource) {
		this.setDataSource(dataSource);
	}
	
	@Override
	public void updatePrice(String isbn, Integer price) {
		LOG.info("updatePrice - isbn = {} - price = {}", isbn, price);
		// maj du prix
		getJdbcTemplate().update("UPDATE BOOK SET PRICE = ? WHERE ISBN = ?", 
				new Object[] { price, isbn });
	}
	
	@Override
	public IBookEntity insert(IBookEntity pUneEntite) throws ExceptionDao {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBookEntity update(IBookEntity pUneEntite) throws ExceptionDao {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(IBookEntity pUneEntite) throws ExceptionDao {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IBookEntity select(int pUneClef) throws ExceptionDao {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBookEntity> selectAll(String pAWhere, String pAnOrderBy) throws ExceptionDao {
		// TODO Auto-generated method stub
		return null;
	}



}
