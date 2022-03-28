package guestbook.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guestbook.bean.GuestbookDTO;
import guestbook.bean.GuestbookPaging;
import guestbook.dao.GuestbookDAO;

@Service
public class GuestbookServiceImpl implements GuestbookService {
	@Autowired
	private GuestbookDAO guestbookDAO;
	@Autowired
	private GuestbookPaging guestbookPaging;
	
	@Override
	public void guestbookWrite(GuestbookDTO guestbookDTO) {
		guestbookDAO.guestbookWrite(guestbookDTO);
	}

	@Override
	public Map<String, Object> getGuestbookList(String pg) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("endNum", Integer.parseInt(pg)*2);
		map.put("startNum", Integer.parseInt(pg)*2-1);
		
		guestbookPaging.setCurrentPage(Integer.parseInt(pg));
		guestbookPaging.setPageBlock(3);
		guestbookPaging.setPageSize(2);
		guestbookPaging.setTotalA(guestbookDAO.getTotalA());
		
		guestbookPaging.makePagingHTML();
		
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("list", guestbookDAO.getGuestbookList(map));
		temp.put("guestbookPaging", guestbookPaging.getPagingHTML());
		return temp;
	}

}
