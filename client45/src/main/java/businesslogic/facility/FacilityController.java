package businesslogic.facility;

import java.rmi.RemoteException;

import businesslogicservice.facilityblservice.FacilityBLService;
import state.ConfirmState;
import state.ResultMessage;
import vo.FacilityVO;

/**
 * 
 * @author Ann
 *
 */
public class FacilityController implements FacilityBLService {
	
	Facility facilityBL;
	@Override
	public ConfirmState confirmOperation() {
		return facilityBL.confirmOperation();
	}

	@Override
	public ResultMessage addFacility(FacilityVO facility) {
		try {
			return facilityBL.addFacility(facility);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	@Override
	public ResultMessage deleteFacility(FacilityVO facility) {
		try {
			return facilityBL.deleteFacility(facility);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	@Override
	public ResultMessage modifyFacility(FacilityVO facility) {
		try {
			return facilityBL.modifyFacility(facility);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	@Override
	public FacilityVO findFacility(String facilityId) {
		try {
			return facilityBL.findFacility(facilityId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

}