package guestbook.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import guestbook.bean.GuestbookDTO;

@Repository
@Transactional
public class GuestbookDAOMybatis implements GuestbookDAO{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void guestbookWrite(GuestbookDTO guestbookDTO) {
		sqlSession.insert("guestbookSQL.guestbookWrite", guestbookDTO);
	}

	@Override
	public int getTotalA() {
		return sqlSession.selectOne("guestbookSQL.getTotalA");
	}

	@Override
	public List<GuestbookDTO> getGuestbookList(Map<String, Integer> map) {
		return sqlSession.selectList("guestbookSQL.getGuestbookList", map);
	}

}
