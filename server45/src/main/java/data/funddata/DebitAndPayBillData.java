package data.funddata;

import java.rmi.RemoteException;

import config.XMLReader;
import data.ManageData;
import dataservice.funddataservice.DebitAndPayBillDataService;
import po.receiptpo.DebitAndPayBillPO;
import state.ReceiptType;
import util.SerSaveAndLoad;

public class DebitAndPayBillData extends ManageData<DebitAndPayBillPO> implements DebitAndPayBillDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String EXPrifix;
	String PAYPrifix;
	// poList在Data里,虽然每个Data的继承类都以Data为父类，但他们拥有不同的poList
	public DebitAndPayBillData() throws RemoteException {
		EXPrifix=configReader.getValue("EXPrifix");
		PAYPrifix=configReader.getValue("PAYPrifix");
	}

	@Override
	public String getID(ReceiptType type) throws RemoteException {
		if (type == ReceiptType.EXPENSE) return this.getExpenseID();
		else 			return this.getPayID();
		
	}

	private String getExpenseID() throws RemoteException {
		return EXPrifix+super.getID();
	}

	private String getPayID() throws RemoteException {
		return PAYPrifix+super.getID();
	}

	/*@Override
	public ArrayList<DebitAndPayBillPO> showList(String begin, String end) throws RemoteException {
		// 日期比较可以利用String的字典序
		ArrayList<DebitAndPayBillPO> bills = new ArrayList<>();
		for (DebitAndPayBillPO po : poList.getInList()) {
			if (po.getDate().compareTo(begin) >= 0 && po.getDate().compareTo(end) <= 0)
				bills.add(po);
		}
		return bills;
	}*/

	@Override
	public void initialFile() {
		poList = new SerSaveAndLoad<DebitAndPayBillPO>("data/" + NAME + ".ser");
		configReader = new XMLReader("config/" + NAME + ".xml");
	}

	/*@Override
	public ArrayList<DebitAndPayBillPO> showList(String end) throws RemoteException {
		// 日期比较可以利用String的字典序
		ArrayList<DebitAndPayBillPO> bills = new ArrayList<>();
		for (DebitAndPayBillPO po : poList.getInList()) {
			if (po.getDate().compareTo(end) <= 0)
				bills.add(po);
		}
		return bills;
	}*/

	/*@Override
	public ArrayList<DebitBillPO> showDate(String date) throws RemoteException {
		ArrayList<DebitBillPO> bills = new ArrayList<>();
		for (DebitAndPayBillPO po : poList.getInList()) {
			if ((po.getDate() == date)) {
				bills.add((DebitBillPO) po);
			}
		}
		return bills;
	}*/

}

