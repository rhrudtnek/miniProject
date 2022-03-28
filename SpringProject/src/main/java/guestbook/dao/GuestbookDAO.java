package guestbook.dao;

import java.util.List;
import java.util.Map;

import guestbook.bean.GuestbookDTO;

public interface GuestbookDAO {

	public void guestbookWrite(GuestbookDTO guestbookDTO);

	public int getTotalA();

	public List<GuestbookDTO> getGuestbookList(Map<String, Integer> map);

}
