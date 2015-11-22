package businesslogic.branchbl;
/**
 * @author LIUXUANLIN
 */
import java.rmi.RemoteException;
import java.util.ArrayList;

import businesslogicservice.branchblservice.BranchBLService;
import po.receiptpo.ReceiptPO;
import state.CommodityState;
import state.ConfirmState;
import state.ResultMessage;
import vo.CommodityVO;
import vo.receiptvo.BranchArrivalListVO;
import vo.receiptvo.DeliveryListVO;
import vo.receiptvo.LoadingListVO;

public class BranchController implements BranchBLService {
     Branch BranchBL;
	@Override
	public ConfirmState confirmOperation() {
		// TODO Auto-generated method stub
		return BranchBL.confirmOperation();
	}

	@Override
	public ArrayList<CommodityVO> getAllCommodities() {
		// TODO Auto-generated method stub
		try {
			return BranchBL.getAllCommodities();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<String> getAllOrderNumber()  {
		// TODO Auto-generated method stub
		try {
			return BranchBL.getAllOrderNumber();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BranchArrivalListVO getBranchArrivalList(String transferListID, String departure, CommodityState state,
			ArrayList<String> orders) {
		// TODO Auto-generated method stub
		return BranchBL.getBranchArrivalList(transferListID, departure, state, orders);
	}

	@Override
	public DeliveryListVO getDeliveryList(ArrayList<String> orders, String courierName) {
		// TODO Auto-generated method stub
		return BranchBL.getDeliveryList(orders, courierName);
	}

	@Override
	public ResultMessage submit(ReceiptPO receipt) {
		// TODO Auto-generated method stub
		try {
			return BranchBL.submit(receipt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage save(ReceiptPO receipt)  {
		// TODO Auto-generated method stub
		try {
			return BranchBL.save(receipt);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LoadingListVO truckDeliver(String branchID, String destination, String facilityID, String courierName,
			ArrayList<String> orders) {
		// TODO Auto-generated method stub
		return BranchBL.truckDeliver(branchID, destination, facilityID, courierName, orders);
	}

}
