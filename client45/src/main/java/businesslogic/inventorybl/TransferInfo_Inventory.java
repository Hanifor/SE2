package businesslogic.inventorybl;

import java.rmi.RemoteException;

import po.TransferPO;

/** 
* 程序的简单说明 
* @author Ann
* @version 创建时间：2015年12月5日 下午3:50:38 
*/
public interface TransferInfo_Inventory {
	public TransferPO getTransfer(String transferID) throws RemoteException;
}
