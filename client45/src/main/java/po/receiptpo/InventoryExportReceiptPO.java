package po.receiptpo;

import state.ExpressType;
import state.ReceiptType;

public class InventoryExportReceiptPO extends ReceiptPO{
	/** @author lxl
		 *  @version Oct 23,2015
		 *      **/
		
	private static final long serialVersionUID = 1L;
	/**目的地**/
	public String destination;
	public String depture;
	/**装运形式**/
	public ExpressType expressType;
	public String TransferID;
	public String CommoditiesID;
	public int a;
	public int b;
	public int c;
	public int d;
	
	public InventoryExportReceiptPO(String ID, ReceiptType type,String destination, String depture, ExpressType expressType, String transferID,
			String commoditiesID, int a, int b, int c, int d) {
		super(ID, type);
		this.destination = destination;
		this.depture = depture;
		this.expressType=expressType;
		this.TransferID = transferID;
		this.CommoditiesID = commoditiesID;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDepture() {
		return depture;
	}
	public void setDepture(String depture) {
		this.depture = depture;
	}
	public ExpressType getExpressType() {
		return expressType;
	}
	public void setExpressType(ExpressType expressType) {
		this.expressType = expressType;
	}
	public String getTransferID() {
		return TransferID;
	}
	public void setTransferID(String transferID) {
		TransferID = transferID;
	}
	public String getCommoditiesID() {
		return CommoditiesID;
	}
	public void setCommoditiesID(String commoditiesID) {
		CommoditiesID = commoditiesID;
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getD() {
		return d;
	}
	public void setD(int d) {
		this.d = d;
	}
	
	
}
