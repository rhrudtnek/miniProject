package guestbook.service;

import java.util.Map;

import guestbook.bean.GuestbookDTO;

public interface GuestbookService {

	public void guestbookWrite(GuestbookDTO guestbookDTO);

	public Map<String, Object> getGuestbookList(String pg);

}
