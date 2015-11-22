package dataservice.orderdataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import dataservice.ManageDataService;
import po.OrderPO;
import state.ReceiptState;
import state.ResultMessage;
import vo.CommodityVO;

/**
 *提供订单集体载入、增改查的服务
 *@author czw
 *@version Oct 22,2015 
 */
public interface OrderDataService  extends ManageDataService<OrderPO>{
	/** 接口的名称，RMI绑定时候的名称 */
	public static final String NAME = "OrderData";
	/**
	 * 根据订单号查找订单
	 * @param ordernumber 订单号
	 * @return 订单持久化对象，若没有找到返回null
	 * @author czw
	 * @version Oct 22,2015 
	 */
	public OrderPO find(String ordernumber) throws RemoteException;
	
	/**
	 * 新增订单
	 * @param order 订单持久化对象
	 * @return 处理信息
	 * @author czw
	 * @version Oct 22,2015 
	 */
	public ResultMessage insert(OrderPO order) throws RemoteException;
	
	/**
	 * 修改订单
	 * @param order 订单持久化对象
	 * @return 处理信息
	 * @author czw
	 * @version Oct 22,2015 
	 */
	public ResultMessage update(OrderPO order) throws RemoteException;
	
	
	
	public ResultMessage modify(OrderPO order,String orderIdString, String senderName, String senderAddress, String senderTel,String senderCo, String recipientName, String recipientAddress, String recipientTel, String recipientCo,ArrayList<CommodityVO> commodityVO, String midAddres, String sendTime, String recipientTime, double money,ReceiptState state)throws RemoteException;
}