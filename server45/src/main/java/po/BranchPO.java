package po;

import java.util.ArrayList;

import po.accountpo.AccountPO;
import state.OrganizationType;
import state.ReceiptType;

/**
 * 营业厅单一可持续化对象
 * @author czw
 * @version Oct 23, 2015
 */
public class BranchPO extends OrganizationPO{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 营业厅地址
	 */
	private String address;
	
	/**
	 * 员工信息
	 */
	private ArrayList<AccountPO> accounts;
	
	/**
	 * 所属车辆信息
	 */
	private ArrayList<FacilityPO> facilities;
	
	/**
	 * TODO 收款信息定义
	 * private ArrayList<FundPO> fund;
	 */
	
	/**
	 * 构造器
	 */
	public BranchPO(String id, String address, String date, ArrayList<AccountPO> accounts, ArrayList<FacilityPO> facilities) {
		super(id, address,accounts.size(),OrganizationType.BRANCH);
		//Date在Organization里能自动生成
		this.accounts = accounts;
		this.facilities = facilities;
	}

	public String getAddress() {
		return address;
	}

	public ArrayList<AccountPO> getAccounts() {
		return accounts;
	}

	public ArrayList<FacilityPO> getFacilities() {
		return facilities;
	}
}
