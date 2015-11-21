package businesslogic.facility;

import java.util.ArrayList;

import po.FacilityPO;
import po.accountpo.DriverPO;
import po.receiptpo.LoadingListPO;
import vo.FacilityVO;
import vo.accountvo.DriverVO;

public class FacilityTrans {
	public static FacilityPO convertVOtoPO(FacilityVO vo) {
		if (vo == null)
			return null;
		else {
			String ID = vo.getFacilityIdString();
			String date = vo.getDateString();
			String manageID = vo.getManagerId();
			ArrayList<LoadingListPO> deliverHistory = vo.getDeliverHistory();
			return new FacilityPO(ID, date, manageID, deliverHistory);
		}
	}

	public static FacilityVO convertPOtoVO(FacilityPO po) {
		if (po == null)
			return null;
		else{
			String ID = po.getID();
			String date = po.getDate();
			String manageID = po.getManagerId();
			ArrayList<LoadingListPO> deliverHistory = po.getDeliverHistory();
			return new FacilityVO(manageID, deliverHistory, ID, date);
		}
	}
	
	public static DriverPO convertVOtoPO(DriverVO vo) {
		if (vo == null)
			return null;
		else {
			String ID  = vo.getID();
			String duty  =vo.getDuty();
			String name = vo.getName();
			String birthDay  = vo.getBirthDay();
			String IDCard = vo.getIDCard();
			String phone = vo.getPhone();
			double salary = vo.getSalary();
			String workTime = vo.getWorkTime();
			int receiptNum = vo.getReceiptNum();
			return new DriverPO(ID, duty, name, birthDay, IDCard, phone, salary, workTime, receiptNum);
		}
	}

	public static DriverVO convertPOtoVO(DriverPO po) {
		if (po == null)
			return null;
		else{
			String ID  = po.getID();
			String duty  =po.getDuty();
			String name = po.getName();
			String birthDay  = po.getBirthDay();
			String IDCard = po.getIDCard();
			String phone = po.getPhone();
			double salary = po.getSalary();
			String workTime = po.getWorkTime();
			int receiptNum = po.getReceiptNum();
			return new DriverVO(ID, duty, name, birthDay, IDCard, phone, salary, workTime, receiptNum);
		}
	}
}