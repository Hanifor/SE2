package data.receiptdata;

import java.rmi.RemoteException;

import data.ManageData;
import dataservice.receiptdataservice.ReceiptDataService;
import po.receiptpo.ReceiptPO;
import util.SerSaveAndLoad;
import util.Util;

public class ReceiptData extends ManageData<ReceiptPO> implements ReceiptDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//poList在Data里,虽然每个Data的继承类都以Data为父类，但他们拥有不同的poList
	public ReceiptData() throws RemoteException {
		poList=new SerSaveAndLoad<ReceiptPO>("data/"+NAME+".ser");
	}
	
	@Override
	public String getID() {
		int newID = poList.size();
		return Util.transIntToString(newID, 5);
	}

}
