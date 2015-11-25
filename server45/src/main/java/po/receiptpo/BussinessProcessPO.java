package po.receiptpo;

import java.util.ArrayList;

import dataservice.funddataservice.DebitAndPayBillDataService;
import po.PersistentObject;
import po.receiptpo.DebitAndPayBillPO;


	/**
	 * 经营历程表
	 * 查看一段时间里的所有单据，单据分为：
	 * 入款单和出款单
	 * 筛选条件为：时间区间
	 * 显示出符合条件的单据后，可以对单据进行查看操作，但是不可修改和删除。
	*/
	/**serialVersionUID*/
	
public class BussinessProcessPO extends PersistentObject{
	/**serialVersionUID*/
	private static final long serialVersionUID = 1L;
		
		public ArrayList<DebitAndPayBillPO> Receipt;
	
		public BussinessProcessPO(String ID,ArrayList<DebitAndPayBillPO> Receipt) {
		super(ID);
		this.Receipt = Receipt;
		

	}
		
		
}
