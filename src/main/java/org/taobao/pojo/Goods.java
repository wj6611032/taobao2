package org.taobao.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Goods { //��Ʒ��
	private Integer goodsId; //��Ʒ���
	private String goodsName; //��Ʒ����
	private String goodsImg; //��ƷͼƬ·��
	private Integer isRecom; //�Ƿ��Ƽ�
	private String goodsDesc; //��Ʒ����
	private Integer saleNum; //��Ʒ������
	private String saleTime; //�ϼ�ʱ��
	private Type lastType; //���һ����Ʒ����  ���һ
	private List<Specs> specs; //��� һ�Զ�
	private List<Appraises> appraises; //���� һ�Զ�
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public Integer getIsRecom() {
		return isRecom;
	}
	public void setIsRecom(Integer isRecom) {
		this.isRecom = isRecom;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public Integer getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	
	@ManyToOne
	@JoinColumn(name="typeId")
	public Type getLastType() {
		return lastType;
	}
	public void setLastType(Type lastType) {
		this.lastType = lastType;
	}
	
	@OneToMany
	@JoinColumn(name="goodsId")
	public List<Specs> getSpecs() {
		return specs;
	}
	public void setSpecs(List<Specs> specs) {
		this.specs = specs;
	}
	
}