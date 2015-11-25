package data.inventorydata;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.ManageData;
import dataservice.inventorydataservice.InventoryDataService;
import po.InventoryPO;
import po.OpeningStockPO;
import util.SerSaveAndLoad;

public class InventoryData extends ManageData<InventoryPO> implements InventoryDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// poList在Data里,虽然每个Data的继承类都以Data为父类，但他们拥有不同的poList
	public InventoryData() throws RemoteException {
		poList = new SerSaveAndLoad<InventoryPO>("data/" + NAME + ".ser");
	}

	@Override
	public void init() throws RemoteException {
		// TODO Auto-generated method stub
	}

	@Override
	public int getexportNumber(String beginDate, String endDate) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getimportNumber(String beginDate, String endDate) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getNum(String beginDate, String endDate) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InventoryPO getInventoryPO(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryPO getInventoryPO(int a, int b, int c, int d) throws RemoteException {
//		for (int i = 0; i < poList.size(); i++) {
//			InventoryPO po = poList.get(i);
//			if (po.getDate().compareTo(date)<0) {
//				inventories.add(po);
//			}
//		}
		return null;
	}
	
	private boolean isValid(InventoryPO po,int a,int b,int c,int d){
		return false;
//		if(po.g)
	}

	@Override
	public ArrayList<InventoryPO> getInventoryPOList(String date) throws RemoteException {
		ArrayList< InventoryPO> inventories = new ArrayList<>();
		for (int i = 0; i < poList.size(); i++) {
			InventoryPO po = poList.get(i);
			if (po.getDate().compareTo(date)<0) {
				inventories.add(po);
			}
		}
		return inventories;
	}
}
