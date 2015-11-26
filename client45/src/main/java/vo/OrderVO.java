package vo;

public class OrderVO extends ValueObject {
	// 订单号
	public String orderIdString;
	// 寄件人姓名、寄件人住址、
	// 寄件人电话、寄件人单位
	public String senderName;
	public String senderAddress;
	public String senderTel;
	public String senderCo;
	// 收件人姓名、收件人住址、
	// 收件人电话、收件人单位
	public String recipientName;
	public String recipientAddress;
	public String recipientTel;
	public String recipientCo;
	// 订单运送途径地点
	public String midAddres;
	// 派件时间、收件时间
	public String sendTime;
	public String recipientTime;
	// 费用
	public double money;

	public OrderVO(String sendTime, String recipientTime, double money) {
		this.sendTime = sendTime;
		this.recipientTime = recipientTime;
		this.money = money;
	}

	public OrderVO(String orderIdString, String senderName, String senderAddress, String senderTel, String senderCo,
			String recipientName, String recipientAddress, String recipientTel, String recipientCo, String midAddres,
			String sendTime, String recipientTime, double money) {
		this.orderIdString = orderIdString;
		this.senderName = senderName;
		this.senderAddress = senderAddress;
		this.senderTel = senderTel;
		this.senderCo = senderCo;
		this.recipientName = recipientName;
		this.recipientAddress = recipientAddress;
		this.recipientTel = recipientTel;
		this.recipientCo = recipientCo;
		this.midAddres = midAddres;
		this.sendTime = sendTime;
		this.recipientTime = recipientTime;
		this.money = money;
	}

}
