package po;

import java.util.ArrayList;

import po.receiptpo.ReceiptPO;
import state.ReceiptState;
import vo.CommodityVO;
import vo.OrderVO;

/**
 * 订单的PO类， 包括订单号、收件人（寄件人）姓名、收件人（寄件人）住址、 收件人（寄件人）电话、收件人（寄件人）单位、 货物集合信息、订单运送途径地点、
 * 派件时间、收件时间、费用， 费用格式必须是大于等于0、精确到小数点后1为的浮点数，单位是元。
 * 
 * @author Ann
 *
 */
public class OrderPO extends ReceiptPO {

	protected OrderPO(String id) {
		super(id);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 订单号
	private String orderIdString;
	// 寄件人姓名、寄件人住址、
	// 寄件人电话、寄件人单位
	private String senderName;
	private String senderAddress;
	private String senderTel;
	private String senderCo;
	// 收件人姓名、收件人住址、
	// 收件人电话、收件人单位
	private String recipientName;
	private String recipientAddress;
	private String recipientTel;
	private String recipientCo;
	// 货物集合信息
	private ArrayList<CommodityVO> commodityVO;
	// 订单运送途径地点
	private String midAddres;
	// 派件时间、收件时间
	private String sendTime;
	private String recipientTime;
	// 费用
	private double money;
	private ReceiptState state;

	public OrderPO(String orderIdString, String senderName, String senderAddress, String senderTel,
			String senderCo, String recipientName, String recipientAddress, String recipientTel, String recipientCo,
			ArrayList<CommodityVO> commodityVO, String midAddres, String sendTime, String recipientTime, double money) {
		super(orderIdString);
		this.orderIdString = orderIdString;
		this.senderName = senderName;
		this.senderAddress = senderAddress;
		this.senderTel = senderTel;
		this.senderCo = senderCo;
		this.recipientName = recipientName;
		this.recipientAddress = recipientAddress;
		this.recipientTel = recipientTel;
		this.recipientCo = recipientCo;
		this.commodityVO = commodityVO;
		this.midAddres = midAddres;
		this.sendTime = sendTime;
		this.recipientTime = recipientTime;
		this.money = money;
		this.state=state;
	}

	public String getOrderIdString() {
		return orderIdString;
	}

	public void setOrderIdString(String orderIdString) {
		this.orderIdString = orderIdString;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public String getSenderTel() {
		return senderTel;
	}

	public void setSenderTel(String senderTel) {
		this.senderTel = senderTel;
	}

	public String getSenderCo() {
		return senderCo;
	}

	public void setSenderCo(String senderCo) {
		this.senderCo = senderCo;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientAddress() {
		return recipientAddress;
	}

	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}

	public String getRecipientTel() {
		return recipientTel;
	}

	public void setRecipientTel(String recipientTel) {
		this.recipientTel = recipientTel;
	}

	public String getRecipientCo() {
		return recipientCo;
	}

	public void setRecipientCo(String recipientCo) {
		this.recipientCo = recipientCo;
	}

	public ArrayList<CommodityVO> getCommodityVO() {
		return commodityVO;
	}

	public void setCommodityVO(ArrayList<CommodityVO> commodityVO) {
		this.commodityVO = commodityVO;
	}

	public String getMidAddres() {
		return midAddres;
	}

	public void setMidAddres(String midAddres) {
		this.midAddres = midAddres;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getRecipientTime() {
		return recipientTime;
	}

	public void setRecipientTime(String recipientTime) {
		this.recipientTime = recipientTime;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	public  ReceiptState getState() {
		return state;
	}

	public  void setState(ReceiptState state) {
		this.state = state;
	}

	

}
