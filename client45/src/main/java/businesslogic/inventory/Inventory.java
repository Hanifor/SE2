package businesslogic.inventory;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.inventoryblservice.InventoryBLService;
import dataservice.basedataservice.BaseDataService;
import dataservice.inventorydataservice.InventoryDataService;
import po.receiptpo.AdjustReceiptPO;
import po.receiptpo.InventoryExportReceiptPO;
import po.receiptpo.InventoryImportReceiptPO;
import state.ResultMessage;
import vo.InventoryCheckVO;
import vo.InventoryVO;
import vo.InventoryViewVO;
import vo.receiptvo.InventoryExportReceiptVO;
import vo.receiptvo.InventoryImportReceiptVO;
import vo.receiptvo.TransferArrivalListVO;

public class Inventory implements InventoryBLService {
	private InventoryDataService inventoryData;
	public Inventory() {
		try {
			inventoryData= (InventoryDataService)Naming.lookup("rmi://" + "127.0.0.1" + ":" + "8888" + "/"+InventoryDataService.NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	@Override
	public InventoryViewVO viewInventory(String beginDate, String endDate) throws RemoteException  {
		
			InventoryViewVO viewVO=new InventoryViewVO (inventoryData.getimportNumber(beginDate,  endDate), inventoryData.getexportNumber( beginDate, endDate), inventoryData.getNum( beginDate,  endDate));
		    return viewVO;
		
	}

	@Override
	public InventoryCheckVO checkRecord(String enddate) throws RemoteException {
		ArrayList<InventoryImportReceiptPO> POs=inventoryData.showImport(enddate);
		ArrayList<InventoryImportReceiptVO> VOs = new ArrayList<InventoryImportReceiptVO>();
		//记得补充一个生成方法
		String lotNum = null;
		for(InventoryImportReceiptPO po : POs) {
			InventoryImportReceiptVO vo = POtoVO(po);
			VOs.add(vo);
		}
		InventoryCheckVO checkVO=new InventoryCheckVO(VOs,lotNum);
		return checkVO;
	}

	private InventoryImportReceiptVO POtoVO(InventoryImportReceiptPO po) throws RemoteException {
		InventoryImportReceiptVO vo = new InventoryImportReceiptVO(po.getCommoditiesID(),po.getDestination(),po.getA(),po.getB(),po.getC(),po.getD());
		return vo;
	}
	
	@Override
	public String getImportID() throws RemoteException {
		// TODO Auto-generated method stub
		String ID=inventoryData.getImportID();
		return ID;
	}

	@Override
	public InventoryImportReceiptPO addCommodities(TransferArrivalListVO receipt,InventoryVO vo) throws RemoteException {
		  String ID=inventoryData.getImportID();
		  String commodities=receipt.getTransferCenterID();
		  String destination=receipt.getDestination();
		  String depture=receipt.getDeparture();
		  int a=vo.getA();
		  int b=vo.getB();
		  int c=vo.getC();
	 	  int d=vo.getD();
		  InventoryImportReceiptPO po=new InventoryImportReceiptPO(ID,destination,depture,commodities,a,b,c,d);
		  inventoryData.insertImport(po);
		  return po;
		 
	}


	@Override
	public ResultMessage submitImport(InventoryImportReceiptVO importReceipt) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public String getExportID() throws RemoteException {
		String ID=inventoryData.getExportID();
		return ID;
	}
	@Override
   public String getTransfer() throws RemoteException {
		String Transfer=inventoryData.getTransfer();
		return Transfer;
	}
	@Override
	public InventoryExportReceiptPO minusCommodities(TransferArrivalListVO receipt) throws RemoteException {
		InventoryVO vo=inventoryData.getInventoryVO();
		  int a=vo.getA();
		  int b=vo.getB();
		  int c=vo.getC();
	 	  int d=vo.getD();
		String ID=inventoryData.getExportID();
		String Transfer=inventoryData.getTransfer();
		String depture=receipt.getDeparture();
		String TransferID=inventoryData.getTransferID();
		String destination=receipt.getDestination();
		String Commodities=receipt.getTransferCenterID();
		InventoryExportReceiptPO po=new InventoryExportReceiptPO(ID, destination, depture,Transfer, TransferID, Commodities, a, b, c, d);
		inventoryData.insertExport(po);
		return po;
			
		
	}

	@Override
	public ResultMessage submitExport(InventoryExportReceiptVO exportReceipt) {
		// TODO Auto-generated method stub
		return ResultMessage.SUCCESS;
	}

	@Override
	public String getAdjustID() throws RemoteException {
		String ID=inventoryData.getImportID();
		return ID;
	}

	@Override
	public AdjustReceiptPO adjust(InventoryVO before,InventoryVO now) throws RemoteException {
		int exA = before.getA();
		int exB = before.getB();
		int exC = before.getC();
		int exD = before.getD();
		int afA = now.getA();
		int afB = now.getB();
		int afC = now.getC();
		int afD = now.getD();
		String ID=inventoryData.getImportID();
		AdjustReceiptPO po=new AdjustReceiptPO(ID,exA,exB, exC,exD, afA,afB, afC,afD);
		inventoryData.insertAdjust(po);
		return po;
	
	}
	

}