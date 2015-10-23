package po;
/**
 * 需要通过总经理审批的单据类型
 * @author zsq
 * @version Oct 22,2015
 *
 */
public enum ReceiptType {
	CONFIRM("签收确认单", "QSQRD"),
	ORDER("寄件订单", "JJDD"),
	BRANCH_TRUCK("装车单", "ZCD"),
	BRANCH_ARRIVAL("营业厅到达单", "YYTDDD"),
	BRANCH_DELIVER("营业厅派件单", "YYTPJD"),
	EXPENSE("收款单", "SKD"),
	PAY("付款单","FKD"),
	TRANS_ARRIVAL("中转中心到达单", "ZZZXDDD"),
	TRANS_PLANE("飞机转运单", "FJZYD"),
	TRANS_TRAIN("火车转运单", "HCZYD"),
	TRANS_TRUCK("卡车转运单", "KCZYD"),
	INSTOCK("入库单","RKD"),
	OUTSTOCK("出库单","CCD"),
	TAKINGSTOCK("库存调整单","KCTZD");
	
	public final String value;
	public final String prefix;
	
	ReceiptType(String value,String prefix){
		this.value = value;
		this.prefix = prefix;
	}
}
