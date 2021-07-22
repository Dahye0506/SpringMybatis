package Model;

import java.util.List;

public class GoodsReviewsDTO {
	//1대 다 알고리즘
	
	GoodsDTO goods;  //상품이 1개 일때 : 1대
	List<ReviewDTO> reviews; //리뷰는 여러개 : 다
	
	
	public GoodsDTO getGoods() {
		return goods;
	}
	public void setGoods(GoodsDTO goods) {
		this.goods = goods;
	}
	public List<ReviewDTO> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}
	
}
