package imageboard.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ImageboardDTO {
	private int seq;
	private String imageId;//상품코드
	private String imageName;//상품명
	private int imagePrice;//단가
	private int imageQty;//개수
	private String imageContent;
	private String image1;
	private String image2;
	private Date logtime;
}
