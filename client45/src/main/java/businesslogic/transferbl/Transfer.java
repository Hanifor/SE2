package businesslogic.transferbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import config.RMIConfig;
import dataservice.receiptdataservice.ReceiptDataService;
import po.receiptpo.ReceiptPO;
import state.CommodityState;
import state.ConfirmState;
import state.ResultMessage;
import vo.CommodityVO;
import vo.receiptvo.TransferArrivalListVO;
import vo.receiptvo.TransferOrderVO;

public class Transfer  {
	private ReceiptDataService  receiptData;

	public Transfer() {
		try {
			receiptData = (ReceiptDataService ) Naming
					.lookup(RMIConfig.PREFIX + ReceiptDataService.NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

	
	public ConfirmState confirmOperation() {
		
		return ConfirmState.CONFIRM;
	}

	
	public ArrayList<CommodityVO> getAllCommodities() {
		//ArrayList<ReceiptPO> pos=receiptData.find();
		ArrayList<CommodityVO> vos=new ArrayList<CommodityVO>();
		//for(ReceiptPO po:pos){
			
		//}
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	public TransferOrderVO planeTransfer(String facilityID, String departure, String destination, String courierName,ArrayList<String> orders) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public TransferOrderVO truckTransfer(String facilityID, String departure, String destination, String courierName,
			ArrayList<String> orders) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public TransferOrderVO trainTransfer(String facilityID, String departure, String destination, String courierName,
			ArrayList<String> orders) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage submit(ReceiptPO receipt) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ResultMessage save(ReceiptPO receipt) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public TransferArrivalListVO receiptList(String transferID, String departure, String destination,CommodityState state,
			ArrayList<String> orders) {
		// TODO Auto-generated method stub
		return null;
	}

}
