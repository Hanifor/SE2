package businesslogic.receiptbl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import config.RMIConfig;
import dataservice.receiptdataservice.ReceiptDataService;
import po.receiptpo.ReceiptPO;
import state.ReceiptState;
import state.ReceiptType;
import state.ResultMessage;
import vo.receiptvo.ReceiptVO;

public class Receipt  {
	private ReceiptDataService  receiptData;

	public Receipt() {
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

	
	public ResultMessage updateReceipt(ReceiptVO receiptVO, ReceiptType receiptType) throws RemoteException {
		ReceiptPO po=new ReceiptPO(receiptVO.ID, receiptType);
		receiptData.modify(po);
		return receiptData.modify(po);
	}

	
	public ArrayList<ReceiptPO> passReceipt(ArrayList<ReceiptVO> VOs, ReceiptType receiptTypes) {
		ArrayList<ReceiptPO> POs=new ArrayList<ReceiptPO>();
		for(ReceiptVO vo:VOs){
			ReceiptPO po=new ReceiptPO(vo.ID, receiptTypes);
			po.setReceiptState(ReceiptState.SUCCESS);
			POs.add(po);
		}
		return POs;
	}

	
	public ArrayList<ReceiptPO> dontPassReceipt(ArrayList<ReceiptVO> VOs, ReceiptType receiptTypes) {
		//TODO 写的不对 应该找相应的单据类型
		ArrayList<ReceiptPO> POs=ReceiptTrans.convertVOstoPOs(VOs);
		for (ReceiptPO receiptPO : POs) {
			receiptPO.setReceiptState(ReceiptState.FAILURE);
		}
		return POs;
	}

	
	public ArrayList<ReceiptVO> showReceipt(ReceiptState receiptStates) throws RemoteException {
		ArrayList<ReceiptPO> pos=receiptData.showReceipt(receiptStates);
		ArrayList<ReceiptVO> vos=ReceiptTrans.convertPOstoVOs(pos);
		return vos;
	}
}
