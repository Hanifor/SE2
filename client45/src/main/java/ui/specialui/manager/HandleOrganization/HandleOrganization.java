package ui.specialui.manager.HandleOrganization;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import businesslogic.ControllerFactory;
import businesslogicservice.organizationblservice.OrganizationBLService;
import state.OrganizationType;
import state.ResultMessage;
import ui.image.CommonImage;
import ui.image.ManagerImage;
import ui.image.FinanceImage.BankAccountImage;
import ui.image.FinanceImage.FinanceImage;
import ui.myui.MyFont;
import ui.myui.MyButton;
import ui.myui.MyJLabel;
import ui.myui.MyJPanel;
import ui.myui.MyJTable;
import ui.myui.MyNotification;
import ui.specialui.manager.FrameManager;
import vo.BranchVO;
import vo.FacilityVO;
import vo.InventoryVO;
import vo.OrganizationVO;
import vo.TransferVO;
import vo.accountvo.AccountVO;
/**
 * 负责实现机构管理中Panel的转换和逻辑层的调用
 * @author zsq
 * @version 2015/12/05 15:11
 */
public class HandleOrganization extends MyJPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	private MyButton commonButton ;
	private MyButton modifyButton ;
	private MyButton backout;
	private MyButton redo;

	private SearchOrganizationInfo organizationInfo;
	private OrganizationDetails organizationDetails;

	private MyJTable table;
	
	static MyButton check;
	static ArrayList<OrganizationVO> organizationPool;
	static ArrayList<TransferVO> transferPool;
	static ArrayList<BranchVO> branchPool;
	static String organizationID= " ";

	OrganizationBLService controller;
	
	public HandleOrganization(FrameManager frameManager) {
		super(0, 0, 1280, 720);
		this.setOpaque(false);
		organizationPool = new ArrayList<OrganizationVO>();
		transferPool = new ArrayList<TransferVO>();
		branchPool = new ArrayList<BranchVO>();
		this.initComponent(frameManager);
		this.showAll();
	}

	private void initComponent(FrameManager frame_Manager) {
	
		this.add(new MyJLabel(530, 20, 300, 40, "公司机构信息管理", 30, true));
		
		organizationInfo= new SearchOrganizationInfo(this);
		this.add(organizationInfo);
		
		organizationDetails = new OrganizationDetails();
		organizationDetails.setUneditable();
		this.add(organizationDetails);
		this.initButton(frame_Manager);
		this.leadline(frame_Manager);
	}
	
	public void leadline(FrameManager frameManager){
		
		MyButton HandleReceipt = new MyButton(300-105, 690,95,20,ManagerImage.getBUTTON_APPROVE_());
		HandleReceipt.setActionCommand("HandleReceipt");
		HandleReceipt.addActionListener(frameManager);
		this.add(HandleReceipt);
		HandleReceipt.setVisible(true);
		
		MyButton HandleOrganization = new MyButton(300, 690,120,20,ManagerImage.getBUTTON_ORGANIZATION_());
		HandleOrganization.setActionCommand("HandleOrganization");
		HandleOrganization.addActionListener(frameManager);
		this.add(HandleOrganization);
		HandleOrganization.setVisible(true);
		
		MyButton AdjustBase = new MyButton(300+120+10, 690,120,20,ManagerImage.getBUTTON_BASE_());
		AdjustBase.setActionCommand("AdjustBase");
		AdjustBase.addActionListener(frameManager);
		this.add(AdjustBase);
		AdjustBase.setVisible(true);
		
		MyButton AdjustSalaryPolicy = new MyButton(300+120+10+130,690,120,20,ManagerImage.getBUTTON_POLICY_());
		AdjustSalaryPolicy.setActionCommand("AdjustSalaryPolicy");
		AdjustSalaryPolicy.addActionListener(frameManager);
		this.add(AdjustSalaryPolicy);
		AdjustSalaryPolicy.setVisible(true);
		
		MyButton ViewUser = new MyButton(300+120+10+130+130,  690,120,20,ManagerImage.getBUTTON_ACCOUNT_());
		ViewUser.setActionCommand("ViewUser");
		ViewUser.addActionListener(frameManager);
		this.add(ViewUser);
		ViewUser.setVisible(true);
		
		MyButton ViewBusinessPerformance = new MyButton(300+120+10+130+130+130,690,120,20,FinanceImage.getButton_JINGYING_());
		ViewBusinessPerformance.setActionCommand("ViewBusinessPerformance");
		ViewBusinessPerformance.addActionListener(frameManager);
		this.add(ViewBusinessPerformance);
		ViewBusinessPerformance.setVisible(true);
		
		MyButton ViewIncomeStatement = new MyButton(300+130*5,690,120,20,FinanceImage.getButton_CHENGBEN_());
		ViewIncomeStatement.setActionCommand("ViewIncomeStatement");
		ViewIncomeStatement.addActionListener(frameManager);
		this.add(ViewIncomeStatement);
		ViewIncomeStatement.setVisible(true);
		
		MyButton viewLog = new MyButton(300+130*6,690,120,20,FinanceImage.getButton_LOG_());
		viewLog.setActionCommand("ViewLogMsg");
		viewLog.addActionListener(frameManager);
		this.add(viewLog);
	}
	
	private void initButton(FrameManager frame) {
		MyButton insertButton = new MyButton(55, 660-30, 150, 40,ManagerImage.getBUTTON_ADDOR());
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HandleOrganization.this.insertPanel(frame);
				
			}
		});
		this.add(insertButton);

		MyButton modifyButton = new MyButton(205,660-30,150,40,
				ManagerImage.getBUTTON_MODIFYOR());
		
		modifyButton.setActionCommand("ModifyOrganizationInformation");
		modifyButton.addActionListener(this);
		this.add(modifyButton);

		MyButton searchButton = new MyButton(355,660-30,150,40,
				ManagerImage.getBUTTON_VIEWOR());
		searchButton.setActionCommand("ViewOrganization");
		searchButton.addActionListener(this);
		this.add(searchButton);

		MyButton deleteButton = new MyButton(505,660-30,150,40,
				ManagerImage.getBUTTON_DELETEOR());
		deleteButton.setActionCommand("DeleteOrganization");
		deleteButton.addActionListener(this);
		this.add(deleteButton);
		
		backout = new MyButton(1227-35-40,632,35,35,CommonImage.getBUTTON_BACKOUT());
		backout.setActionCommand("backout");
		backout.addActionListener(this);
		this.add(backout);
		
		redo = new MyButton(1223-35,610,35,35,CommonImage.getBUTTON_REDO());
		redo.setActionCommand("redo");
		redo.addActionListener(this);
		this.add(redo);
		
	}
	
	private void insertPanel(FrameManager frame) {
		this.removeAll();
		this.add(organizationInfo);
		this.add(new MyJLabel(530, 20, 300, 40, "公司机构信息管理", 30, true));
		this.initButton(frame);

		organizationDetails = new OrganizationDetails();
		organizationDetails.add(new MyJLabel(230,5,120,30,"新增机构",18,true));
		this.add(organizationDetails);
		
		commonButton = new MyButton(890, 670-30, 120, 30, BankAccountImage.getBUTTON_ADD());
		commonButton.setActionCommand("AddOrganization");
		commonButton.addActionListener(this);
		this.add(commonButton);
		this.repaint();
}	

	/**
	 * 显示所有的机构
	 */
	public void showAll(){
		table = (MyJTable) organizationInfo.getTable();
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		
		int rowCount = table.getRowCount();
		
		for(int i = 0; i < rowCount; i++){
			tableModel.removeRow(0);
		}
		
		organizationPool.clear();
		organizationID = "";
		
		try {
			OrganizationBLService controller = ControllerFactory.getOrganizationController();
			ArrayList<TransferVO> transferVO = controller.showTransfer();
			ArrayList<BranchVO> branchVO = controller.showBranch();
			for(int i=0;i<transferVO.size();i++){
				Object [] rowData1 = {transferVO.get(i).organizationID,transferVO.get(i).organizationType,
						transferVO.get(i).date,transferVO.get(i).inventories,transferVO.get(i).accounts,transferVO.get(i).address};
				tableModel.addRow(rowData1);
				organizationPool.add(transferVO.get(i));
				transferPool.add(transferVO.get(i));
				
			}
			for(int i=0;i<branchVO.size();i++){
				Object[] rowData2 = {branchVO.get(i).organizationID,branchVO.get(i).organizationType,branchVO.get(i).date
						,branchVO.get(i).facilities,branchVO.get(i).accounts,branchVO.get(i).address};
				tableModel.addRow(rowData2);
				organizationPool.add(branchVO.get(i));
				branchPool.add(branchVO.get(i));
			}
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
			return;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("SearchOrganization")){
			table = (MyJTable)organizationInfo.getTable();
			DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
			int rowCount = table.getRowCount();
			
			for(int i = 0; i < rowCount; i++){
				tableModel.removeRow(0);
			}
			
			organizationPool.clear();
			organizationID = "";
			
			ArrayList<TransferVO> transferVO = new ArrayList<TransferVO>();
			ArrayList<BranchVO> branchVO = new ArrayList<BranchVO>();
			String data = organizationInfo.getData();
			if(data!=null){
				try {
					controller = ControllerFactory.getOrganizationController();
					switch(Integer.parseInt(data)){
						case 0 : transferVO = controller.showTransfer(); branchVO = controller.showBranch(); this.repaint();break;
						case 1 : branchVO = controller.showBranch(); this.repaint();break;
						case 2 : transferVO = controller.showTransfer();this.repaint();break;
						default:break;
					}
					for(int i=0;i<transferVO.size();i++){
						Object[] rowData1 ={transferVO.get(i).organizationID,transferVO.get(i).organizationType,
								transferVO.get(i).date,transferVO.get(i).inventories,transferVO.get(i).accounts,transferVO.get(i).address};
						tableModel.addRow(rowData1);
						organizationPool.add(transferVO.get(i));
						transferPool.add(transferVO.get(i));
					}
					for(int i=0;i<branchVO.size();i++){
						Object[] rowData2 = {branchVO.get(i).organizationID,branchVO.get(i).organizationType,branchVO.get(i).date
								,branchVO.get(i).facilities,branchVO.get(i).accounts,branchVO.get(i).address};
						tableModel.addRow(rowData2);
						organizationPool.add(branchVO.get(i));
						branchPool.add(branchVO.get(i));
					}
					new MyNotification(this,"共有"+table.getRowCount()+"个机构满足条件！",Color.GREEN);
				}catch (RemoteException | MalformedURLException | NotBoundException e1) {
					new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
					return;
				}
				
			}else{
				new MyNotification(this,"请输入要查询的机构类型！",Color.RED);
			}
		}else if(e.getActionCommand().equals("AddOrganization")){
			String[] data = organizationDetails.getData();
			if(data==null){
				new MyNotification(this,"请检查机构信息填写是否完整！",Color.RED);
			}else{
				if(data[0].equals("营业厅")){
					try {
						controller = ControllerFactory.getOrganizationController();
						BranchVO branch = new BranchVO(controller.getBranchID(data[3]),data[2]+data[3]+data[4], 
								OrganizationType.BRANCH);
						ResultMessage rsg = controller.addBranch(branch);
						if(rsg.equals(ResultMessage.SUCCESS)){
							this.showAll();
							organizationDetails.refresh();
							organizationDetails.setUneditable();
							new MyNotification(this,"新营业厅添加成功！",Color.GREEN);
						}else{
							new MyNotification(this,"新营业厅添加失败！",Color.RED);
						}
					} catch (RemoteException | MalformedURLException | NotBoundException e1) {
						new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
						return;
					}
				}else if(data[0].equals("中转中心")){
					try {
						controller = ControllerFactory.getOrganizationController();
						ResultMessage rsg1 = controller.addTransfer(new TransferVO(controller.getTransferID(data[3]), 
								data[2]+data[3]+data[4],OrganizationType.TRANSFER));
						if(rsg1.equals(ResultMessage.SUCCESS)){
							this.showAll();
							organizationDetails.refresh();
							organizationDetails.setUneditable();
							new MyNotification(this,"新中转中心添加成功！",Color.GREEN);

						}else{
							new MyNotification(this,"新中转中心添加失败！",Color.RED);
						}
					} catch (RemoteException | MalformedURLException | NotBoundException e1) {
						new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
						return;
					}	
				}
			}
		}else if(e.getActionCommand().equals("DeleteOrganization")){
			table =organizationInfo.getTable();
			if(table.getSelectedRowCount() == 0){
				new MyNotification(this,"请先选择要删除的机构！",Color.RED);
			}else{
				new MyNotification(this,"正在删除机构！",Color.GREEN);
				this.deleteOrganization();
			}
		}else if(e.getActionCommand().equals("ModifyOrganizationInformation")){
			table =organizationInfo.getTable();
			this.remove(organizationDetails);
			
			if(table.getSelectedRowCount() == 0){
				new MyNotification(this,"请先选择要修改的机构！",Color.RED);
			}else{
				organizationDetails = new OrganizationDetails();
				organizationDetails.add(new MyJLabel(230,5,120,30,"修改机构信息",18,true));
				this.add(organizationDetails);
				if(commonButton!=null){
					this.remove(commonButton);
				}
				modifyButton = new MyButton(890, 670-30, 120, 30,ManagerImage.getBUTTON_CONFIRMMODIFY());
				modifyButton.setActionCommand("CheckModify");
				modifyButton.addActionListener(this);
				this.add(modifyButton);
				this.repaint();
			
				organizationDetails.add(new MyJLabel(10,280,90,30,"仓库/转运",18,true));
				organizationDetails.add(new MyJLabel(10,303,90,30,"信息",18,true));
				organizationDetails.add(new MyJLabel(10,415,90,30,"人员信息",18,true));
				
				this.initTable();
	
			}
		}else if(e.getActionCommand().equals("CheckModify")){
			table =organizationInfo.getTable();
		//	organizationID=organizationPool.get(table.getSelectedRow()).organizationID;
				if(organizationDetails.getData()==null){
					new MyNotification(this,"请检查机构信息填写是否完整！",Color.RED);
				}else{
					new MyNotification(this,"正在修改机构信息！",Color.GREEN);
					this.modifyOrganization();
				}
		}else if(e.getActionCommand().equals("ViewOrganization")){
			table =organizationInfo.getTable();
			this.remove(organizationDetails);
			if(commonButton!=null){
				this.remove(commonButton);
			}
			if(modifyButton!=null){
				this.remove(modifyButton);
			}
			
			if(table.getSelectedRowCount() == 0){
				new MyNotification(this,"请先选择要查看的机构！",Color.RED);
			}else{
				organizationDetails = new OrganizationDetails();
				organizationDetails.add(new MyJLabel(230,5,120,30,"查看机构信息",18,true));
				this.add(organizationDetails);
				this.repaint();
			
				organizationDetails.add(new MyJLabel(10,280,90,30,"仓库/转运",18,true));
				organizationDetails.add(new MyJLabel(10,303,90,30,"信息",18,true));
				organizationDetails.add(new MyJLabel(10,415,90,30,"人员信息",18,true));
				organizationDetails.setUneditable();
				this.initTable();
	
			}
		}else if(e.getActionCommand().equals("backout")){
			try {
				OrganizationBLService controller = ControllerFactory.getOrganizationController();
				ResultMessage rsg = controller.undo();
				if(rsg.equals(ResultMessage.SUCCESS)){
					new MyNotification(this,"撤销操作成功！",Color.GREEN);
				}else{
					new MyNotification(this,"撤销操作失败！",Color.RED);
				}
			} catch (MalformedURLException | RemoteException
					| NotBoundException e1) {
				new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
				return;
			}
		}else if(e.getActionCommand().equals("redo")){
			try {
				OrganizationBLService controller = ControllerFactory.getOrganizationController();
				ResultMessage rsg = controller.redo();
				if(rsg.equals(ResultMessage.SUCCESS)){
					new MyNotification(this,"撤销操作成功！",Color.GREEN);
				}else{
					new MyNotification(this,"撤销操作失败！",Color.RED);
				}
			} catch (MalformedURLException | RemoteException
					| NotBoundException e1) {
				new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
				return;
			}
		}

	}

	@SuppressWarnings("unused")
	private void modifyOrganization() {
	
		table = organizationInfo.getTable();
		String[] data = organizationDetails.getData();
		organizationID =organizationPool.get(table.getSelectedRow()).organizationID;
		ArrayList<AccountVO> accounts = new ArrayList<AccountVO>();
		ArrayList<FacilityVO> facilities = new ArrayList<FacilityVO>();
		ArrayList<InventoryVO> inventories = new ArrayList<InventoryVO>();
		try {
			controller = ControllerFactory.getOrganizationController();
			switch(organizationPool.get(table.getSelectedRow()).organizationType){
				case TRANSFER:  //accounts = controller.getAccountByOrganizationID(organizationID);
							//if(accounts!=null){
								  
						//	}
							//inventories = controller.getInventoriesByTransferID(organizationID);
							//if(inventories!=null){
								
							//}
							ResultMessage rsg = controller.updateTransfer(new TransferVO(organizationID, data[2]+data[3]+data[4], OrganizationType.TRANSFER));
							
							if(rsg.equals(ResultMessage.SUCCESS)){
								System.out.println("ModifySucceed!");
								this.showAll();
								organizationDetails.refresh();
								new MyNotification(this,"中转中心信息修改成功！",Color.GREEN);		
							}else{
								new MyNotification(this,"中转中心信息修改失败！",Color.RED);
							}break;
				case BRANCH: //accounts = controller.getAccountByOrganizationID(organizationID);
							//if(accounts!=null){
					
							//}
							//facilities = controller.getFacilitiesByBranchID(organizationID);
							//if(facilities!=null){
								
						//	}
							ResultMessage rsg1 = controller.updateBranch(new BranchVO(organizationID,data[2]+data[3]+data[4],OrganizationType.BRANCH));
						 	if(rsg1.equals(ResultMessage.SUCCESS)){
						 		this.showAll();
						 		organizationDetails.refresh();
						 		new MyNotification(this,"营业厅信息修改成功！",Color.GREEN);
						 	}else{
						 		new MyNotification(this,"营业厅信息修改失败！",Color.RED);
						 	}break;
				default:break;
			}
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
			return;
		}
	}



	private void deleteOrganization() {
		
		table = organizationInfo.getTable();
		OrganizationVO vo =organizationPool.get(table.getSelectedRow());
		try {
			controller = ControllerFactory.getOrganizationController();
			switch(organizationPool.get(table.getSelectedRow()).organizationType){
			case TRANSFER:ResultMessage rsg = controller.deleteTransfer(vo.organizationID);
							if(rsg.equals(ResultMessage.SUCCESS)){
								this.showAll();
								this.repaint();
								new MyNotification(this,"中转中心删除成功！",Color.GREEN);
								
							}else{
								this.add(new MyNotification(this,"中转中心删除失败！",Color.RED));
							}
							break;
			case BRANCH:ResultMessage rsg1 = controller.deleteBranch(vo.organizationID);
						if(rsg1.equals(ResultMessage.SUCCESS)){
							System.out.println("DeleteSucceed!");
							this.showAll();
							this.repaint();
							new MyNotification(this,"营业厅删除成功！",Color.GREEN);
						}else{
							new MyNotification(this,"营业厅删除失败！",Color.RED);
						}
						break;
			
			default:break;
			}
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
			return;
		}
		
	}
	
	@SuppressWarnings("unused")
	private void initTable(){
		//库存表格
		String[] headers = {"库存/转运信息"};
		MyJTable table_1 = new MyJTable(headers,false);
		table_1.setBackground(new Color(40, 42, 66));
		table_1.setForeground(Color.WHITE);
		table_1.setFont(new MyFont(14));
	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(JLabel.CENTER);
		table_1.setDefaultRenderer(Object.class, tcr);
		  	
		JScrollPane jsp=new JScrollPane(table_1);
		JTableHeader head = table_1.getTableHeader();
		head.setBackground(new Color(0.1f, 0.19f, 0.54f, 0.2f));
		head.setFont(new MyFont(14));
		head.setForeground(Color.BLACK);
		head.setResizingAllowed(false);
			
		jsp.setBounds(100, 280, 410, 125);
		jsp.getViewport().setBackground(new Color(0,0,0,0.3f));
		jsp.setOpaque(false);
		jsp.setBorder(BorderFactory.createEmptyBorder());
		jsp.setVisible(true);
		organizationDetails.add(jsp);
		//员工表格
		String[] headers_2 = {"员工信息"};
		MyJTable table_2 = new MyJTable(headers_2,false);
		table_2.setBackground(new Color(40, 42, 66));
		table_2.setForeground(Color.WHITE);
		table_2.setFont(new MyFont(14));
	
		DefaultTableCellRenderer tcr_2 = new DefaultTableCellRenderer();// 设置table内容居中
		tcr_2.setHorizontalAlignment(JLabel.CENTER);
		table_2.setDefaultRenderer(Object.class, tcr_2);
		  	
		JScrollPane jsp_2=new JScrollPane(table_2);
		JTableHeader head_2 = table_2.getTableHeader();
		head_2.setBackground(new Color(0.1f, 0.19f, 0.54f, 0.2f));
		head_2.setFont(new MyFont(14));
		head_2.setForeground(Color.BLACK);
		head_2.setResizingAllowed(false);
			
		jsp_2.setBounds(100, 415, 410, 125);
		jsp_2.getViewport().setBackground(new Color(0,0,0,0.3f));
		jsp_2.setOpaque(false);
		jsp_2.setBorder(BorderFactory.createEmptyBorder());
		jsp_2.setVisible(true);
		organizationDetails.add(jsp_2);
	
		DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
		DefaultTableModel tableModel_2 = (DefaultTableModel) table_2.getModel();
		
	
		int rowCount = table.getRowCount();
		/*for(int i = 0; i <=rowCount; i++){
			tableModel.removeRow(0);
		}
	
		int rowCount_2 = table.getRowCount();
		for(int i = 0;i<=rowCount;i++){
			tableModel_2.removeRow(0);
		}*/
		
		table =organizationInfo.getTable();
		String [] data = new String[5];
		data[0] = organizationPool.get(table.getSelectedRow()).organizationType+"";
		data[1] = organizationPool.get(table.getSelectedRow()).date;
		data[2] = organizationPool.get(table.getSelectedRow()).address.substring(0,3);
		data[3] = organizationPool.get(table.getSelectedRow()).address.substring(3,6);
		data[4] = organizationPool.get(table.getSelectedRow()).address.substring(6);
		organizationDetails.setData(data);
		try {
			controller = ControllerFactory.getOrganizationController();
			switch(organizationPool.get(table.getSelectedRow()).organizationType){
				case TRANSFER:
					ArrayList<AccountVO> accounts = new ArrayList<AccountVO>();
					 accounts = controller.getAccountByOrganizationID(organizationPool.get(table.getSelectedRow()).organizationID);
					  if(accounts!=null){
						  for(int i=0;i<accounts.size();i++){
							  Object[]rowData_1 = {accounts.get(i)};
							  tableModel_2.addRow(rowData_1);
						  }
					  }
					  
					  ArrayList<InventoryVO> inventories = controller.getInventoriesByTransferID(organizationPool.get(table.getSelectedRow()).organizationID);
					  if(inventories!=null){
						  for(int i=0;i<inventories.size();i++){
							  Object[]rowData_2 = {inventories.get(i)};
							  tableModel.addRow(rowData_2);
						  }
					  }
					  break;
			   case BRANCH:
				 
				   ArrayList<AccountVO> accounts_2 = controller.getAccountByOrganizationID(organizationPool.get(table.getSelectedRow()).organizationID);
			   		if(accounts_2!=null){
			   			for(int i=0;i<accounts_2.size();i++){
							 Object[]rowData_1 = {accounts_2.get(i)};
							 tableModel_2.addRow(rowData_1);
						 }
			   		}
					ArrayList<FacilityVO> facilities = controller.getFacilitiesByBranchID(organizationPool.get(table.getSelectedRow()).organizationID);
					if(facilities!=null){
						for(int i=0;i<facilities.size();i++){
							Object[] rowData_2 = {facilities.get(i)};
							tableModel.addRow(rowData_2);;
						}
					}
					 break;
			   default:break;
			}
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			new MyNotification(this,"网络连接异常，请检查网络设置！",Color.RED);
			return;
		}

	}

}
