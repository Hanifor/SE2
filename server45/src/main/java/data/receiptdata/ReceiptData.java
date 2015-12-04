package data.receiptdata;

import java.rmi.RemoteException;

import config.XMLReader;
import data.ManageData;
import dataservice.receiptdataservice.ReceiptDataService;
import po.receiptpo.AdjustReceiptPO;
import po.receiptpo.InventoryExportReceiptPO;
import po.receiptpo.InventoryImportReceiptPO;
import po.receiptpo.ReceiptPO;
import po.receiptpo.orderreceiptpo.TransferArrivalListPO;
import util.SerSaveAndLoad;

public class ReceiptData extends ManageData<ReceiptPO> implements ReceiptDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	String importPost ;
//	String exportPost ;
//	String adjustPost ;
//	String transferArrialPost;
	// poList在Data里,虽然每个Data的继承类都以Data为父类，但他们拥有不同的poList
	public ReceiptData() throws RemoteException {
//		importPost=configReader.getValue("importPost");
//		exportPost=configReader.getValue("exportPost");
//		adjustPost=configReader.getValue("adjustPost");
//		transferArrialPost=configReader.getValue("transferArrialPost");
	}

	@Override
	public TransferArrivalListPO findTransferArrivalList(String id) throws RemoteException {
		return (TransferArrivalListPO) super.find(id);
	}

	@Override
	public String getImportID() throws RemoteException {
		return  super.getID();
	}

	@Override
	public String getExportID() throws RemoteException {
		 return  super.getID();
	}

	@Override
	public String getAdjustID() throws RemoteException {
		 return  super.getID();
	}
	
	@Override
	public String getTransferID() throws RemoteException {
		  return  super.getID();
	}


	@Override
	public InventoryImportReceiptPO findImport(String importID) throws RemoteException {
		return (InventoryImportReceiptPO) super.find(importID);
	}
	@Override
	public InventoryExportReceiptPO findExport(String exportID) throws RemoteException {
		return (InventoryExportReceiptPO) super.find(exportID);
	}
	@Override
	public AdjustReceiptPO findAdjust(String adjustID) throws RemoteException {
		return (AdjustReceiptPO) super.find(adjustID);
	}

	@Override
	public void initialFile() {
		poList=new SerSaveAndLoad<ReceiptPO>("data/"+NAME+".ser");
		configReader=new XMLReader("config/"+NAME+".xml");
	}

}
