package imageboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.dao.ImageboardDAO;

@Service
public class ImageboardServiceImpl implements ImageboardService {
	@Autowired
	private ImageboardDAO imageboardDAO;
	@Autowired
	private ImageboardPaging imageboardPaging;

	@Override
	public void imageboardWrite(ImageboardDTO imageboardDTO) {
		imageboardDAO.imageboardWrite(imageboardDTO);

	}

	@Override
	public List<ImageboardDTO> getImageboardList(String pg) {
		// 1페이지당 3개씩
		int endNum = Integer.parseInt(pg) * 3;
		int startNum = endNum - 2;

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("startNum", startNum);
		map.put("endNum", endNum);

		return imageboardDAO.getImageboardList(map);
	}

	@Override
	public ImageboardPaging imageboardPaging(String pg) {
		int totalA = imageboardDAO.getTotalA(); // 총글수

		imageboardPaging.setCurrentPage(Integer.parseInt(pg));
		imageboardPaging.setPageBlock(3);
		imageboardPaging.setPageSize(3);
		imageboardPaging.setTotalA(totalA);
		imageboardPaging.makePagingHTML();

		return imageboardPaging;
	}

	@Override
	public ImageboardDTO getImageboardView(String seq) {
		return imageboardDAO.getImageboardView(seq);
	}

	@Override
	public void imageboardDelete(String[] check) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("check", check);
		//Mapper에는 map으로 되어있기 때문에 Map을 이용해서 check를 담는다.
		imageboardDAO.imageboardDelete(map);
	}

}
