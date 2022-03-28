package imageboard.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import imageboard.bean.ImageboardDTO;
import imageboard.bean.ImageboardPaging;
import imageboard.service.ImageboardService;

@Controller
@RequestMapping(value = "imageboard")
public class ImageboardController {
	@Autowired
	private ImageboardService imageboardService;

	@GetMapping(value = "imageboardWriteForm")
	public String imageboardWriteForm(Model model) {
		model.addAttribute("display", "/imageboard/imageboardWriteForm.jsp");
		return "/index";
	}

	// name="img" 1개인 경우
	/*
	 * @PostMapping(value="imageboardWrite")
	 * 
	 * @ResponseBody public void imageboardWrite(@ModelAttribute ImageboardDTO
	 * imageboardDTO,
	 * 
	 * @RequestParam MultipartFile img, HttpSession session) {
	 * 
	 * //가상폴더 //String filePath =
	 * "D:\\Spring\\workspace\\SpringProject\\src\\main\\webapp\\storage"; //String
	 * fileName = img.getOriginalFilename();
	 * 
	 * //File file = new File(filePath, fileName); //파일 생성
	 * 
	 * //파일 복사 //try { // FileCopyUtils.copy(img.getInputStream(), new
	 * FileOutputStream(file)); //} catch (IOException e) { // e.printStackTrace();
	 * //}
	 * 
	 * 
	 * //실제 폴더 String filePath =
	 * session.getServletContext().getRealPath("/storage"); String fileName =
	 * img.getOriginalFilename();
	 * 
	 * File file = new File(filePath, fileName); //파일 생성
	 * 
	 * try { img.transferTo(file); } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * 
	 * imageboardDTO.setImage1(fileName);
	 * 
	 * //DB imageboardService.imageboardWrite(imageboardDTO); }
	 */

	// name="img" 2개 이상인 경우
	/*
	 * @PostMapping(value="imageboardWrite")
	 * 
	 * @ResponseBody public void imageboardWrite(@ModelAttribute ImageboardDTO
	 * imageboardDTO,
	 * 
	 * @RequestParam MultipartFile[] img, HttpSession session) { //실제 폴더 String
	 * filePath = session.getServletContext().getRealPath("/storage"); String
	 * fileName; File file;
	 * 
	 * if(img[0] != null) { fileName = img[0].getOriginalFilename(); file = new
	 * File(filePath, fileName);
	 * 
	 * try { img[0].transferTo(file); } catch (IOException e) { e.printStackTrace();
	 * }
	 * 
	 * imageboardDTO.setImage1(fileName); }else { imageboardDTO.setImage1(""); }
	 * 
	 * if(img[1] != null) { fileName = img[1].getOriginalFilename(); file = new
	 * File(filePath, fileName);
	 * 
	 * try { img[1].transferTo(file); } catch (IOException e) { e.printStackTrace();
	 * }
	 * 
	 * imageboardDTO.setImage2(fileName); }else { imageboardDTO.setImage2(""); }
	 * 
	 * //DB imageboardService.imageboardWrite(imageboardDTO); }
	 */

	// 한번에 여러개의 파일을 선택했을 때
	@PostMapping(value = "imageboardWrite")
	@ResponseBody
	public void imageboardWrite(@ModelAttribute ImageboardDTO imageboardDTO,
			@RequestParam("img[]") List<MultipartFile> list, HttpSession session) {
		// 실제 폴더
		String filePath = session.getServletContext().getRealPath("/storage");
		String fileName;
		File file;

		for (MultipartFile img : list) {
			fileName = img.getOriginalFilename();
			file = new File(filePath, fileName);

			try {
				img.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
			}

			imageboardDTO.setImage1(fileName);
			imageboardDTO.setImage2("");

			// DB
			imageboardService.imageboardWrite(imageboardDTO);
		} // for
	}

	@GetMapping(value = "imageboardList")
	public String imageboardList(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardList.jsp");
		return "/index";
	}

	@PostMapping(value = "getImageboardList")
	@ResponseBody
	public ModelAndView getImageboardList(@RequestParam String pg) {
		// DB - 1페이지당 3개씩
		List<ImageboardDTO> list = imageboardService.getImageboardList(pg);

		// 페이징 처리
		ImageboardPaging imageboardPaging = imageboardService.imageboardPaging(pg);

		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.addObject("imageboardPaging", imageboardPaging);
		mav.setViewName("jsonView");
		return mav;
	}

	@GetMapping(value = "imageboardView")
	public String imageboardView(@RequestParam String seq, @RequestParam String pg, Model model) {
		model.addAttribute("seq", seq);
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/imageboard/imageboardView.jsp");
		return "/index";
	}

	@PostMapping(value = "getImageboardView")
	@ResponseBody
	public ImageboardDTO getImageboardView(@RequestParam String seq) {
		ImageboardDTO imageboardDTO = imageboardService.getImageboardView(seq);
		return imageboardDTO;
	}

	@GetMapping(value="imageboardDelete")
	public ModelAndView imageboardDelete(String[] check) {
		imageboardService.imageboardDelete(check);
        return new ModelAndView("redirect:/imageboard/imageboardList");	
	}
}
