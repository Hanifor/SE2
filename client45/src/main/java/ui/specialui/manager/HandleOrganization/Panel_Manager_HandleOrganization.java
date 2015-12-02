package ui.specialui.manager.HandleOrganization;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import businesslogic.ControllerFactory;
import businesslogic.inventorybl.InventoryController;
import businesslogic.organizationbl.OrganizationController;
import businesslogic.transferbl.TransferController;
import businesslogicservice.fundblservice.BankAccountBLService;
import businesslogicservice.organizationblservice.OrganizationBLService;
import state.FindTypeAccount;
import state.OrganizationType;
import state.ResultMessage;
import ui.myui.MyFont;
import ui.myui.MyJButton;
import ui.myui.MyJLabel;
import ui.myui.MyJPanel;
import ui.myui.MyJTable;
import ui.myui.MyNotification;
import ui.specialui.manager.FrameManager;
import vo.BankAccountVO;
import vo.BranchVO;
import vo.FacilityVO;
import vo.InventoryVO;
import vo.OrganizationVO;
import vo.TransferVO;
import vo.accountvo.AccountVO;

@SuppressWarnings("unused")
public class Panel_Manager_HandleOrganization extends MyJPanel implements ActionListener{
	private MyJButton commonButton;
	private Panel_Manager_OrganizationInfo organizationInfo;
	private OrganizationDetails organizationDetails;
	private BranchVO branch;
	private TransferVO transfer;
	
	private ArrayList<BranchVO> branchList;
	private ArrayList<TransferVO> transferList;
	private MyJTable table;
	
	static MyJButton check;
	static ArrayList<OrganizationVO> organizationPool;
	static ArrayList<TransferVO> transferPool;
	static ArrayList<BranchVO> branchPool;
	static String organizationID= " ";

	OrganizationBLService controller;
	public Panel_Manager_HandleOrganization(FrameManager frameManager) {
	
		super(0, 0, 1280, 720);
		this.setOpaque(false);
		organizationPool = new ArrayList<OrganizationVO>();
		transferPool = new ArrayList<TransferVO>();
		branchPool = new ArrayList<BranchVO>();
		this.initComponent(frameManager);
		this.showAll();
	}

	

	private void initComponent(FrameManager frame_Manager) {
	
		this.add(new MyJLabel(550, 20, 210, 90, "公司机构信息管理", 24, true));
		
		organizationInfo= new Panel_Manager_OrganizationInfo(this);
		this.add(organizationInfo);
		
		organizationDetails = new OrganizationDetails();
		organizationDetails.setUneditable();
		this.add(organizationDetails);
		this.initButton(frame_Manager);
	}
	
	private void initButton(FrameManager frame) {
		MyJButton insertButton = new MyJButton(55, 660, 130, 40,"添加机构",16);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel_Manager_HandleOrganization.this.insertPanel(frame);
				
			}
		});
		this.add(insertButton);

		MyJButton modifyButton = new MyJButton(200,660,130,40,
				"修改所选机构", 16);
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel_Manager_HandleOrganization.this.modifyPanel(frame);
				
			}
		});
		modifyButton.setActionCommand("ModifyOrganizationInformation");
		modifyButton.addActionListener(this);
		this.add(modifyButton);

		MyJButton searchButton = new MyJButton(345,660,150,40,
				"查看所选机构信息", 16);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel_Manager_HandleOrganization.this.searchPanel(frame);
				
			}
		});
		searchButton.setActionCommand("ViewOrganization");
		searchButton.addActionListener(this);
		this.add(searchButton);

		MyJButton deleteButton = new MyJButton(510,660,130,40,
				"删除所选机构", 16);
		deleteButton.setActionCommand("DeleteOrganization");
		deleteButton.addActionListener(this);
		this.add(deleteButton);
	}
	

	private void insertPanel(FrameManager frame) {
	this.removeAll();
	this.add(organizationInfo);
	this.add(new MyJLabel(550, 20, 210, 90, "公司机构信息管理", 24, true));
	this.initButton(frame);

	
	organizationDetails = new OrganizationDetails();
	organizationDetails.add(new MyJLabel(230,5,120,30,"新增机构",18,true));
	this.add(organizationDetails);
	
	
	commonButton = new MyJButton(890, 670, 120, 30, "添加机构", 20);
	commonButton.setActionCommand("AddOrganization");
	commonButton.addActionListener(this);
	this.add(commonButton);
	
	this.repaint();
}

private void modifyPanel(FrameManager frame) {
	table = (MyJTable) organizationInfo.getTable();
	this.removeAll();
	this.add(new MyJLabel(550, 20, 210, 90, "公司机构信息管理", 24, true));
	this.add(organizationInfo);
	this.initButton(frame);
	
	this.add(new MyJLabel(687,400,90,30,"仓库/转运",18,true));
	this.add(new MyJLabel(687,430,90,30,"信息",18,true));
	this.add(new MyJLabel(687,530,90,30,"人员信息",18,true));
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
			
	jsp.setBounds(777, 400, 400, 125);
	jsp.getViewport().setBackground(new Color(0,0,0,0.3f));
	jsp.setOpaque(false);
	jsp.setBorder(BorderFactory.createEmptyBorder());
	jsp.setVisible(true);
	this.add(jsp);
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
			
	jsp_2.setBounds(777, 530, 400, 125);
	jsp_2.getViewport().setBackground(new Color(0,0,0,0.3f));
	jsp_2.setOpaque(false);
	jsp_2.setBorder(BorderFactory.createEmptyBorder());
	jsp_2.setVisible(true);
	this.add(jsp_2);
	
	organizationDetails = new OrganizationDetails();
	organizationDetails.add(new MyJLabel(230,5,120,30,"修改机构信息",18,true));
	this.add(organizationDetails);
	
	DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
	DefaultTableModel tableModel_2 = (DefaultTableModel) table_2.getModel();
	OrganizationBLService controller = ControllerFactory.getOrganizationController();
	
	int rowCount = table.getRowCount();
	for(int i = 0; i < rowCount; i++){
		tableModel.removeRow(0);
	}
	
	int rowCount_2 = table.getRowCount();
	for(int i = 0;i<rowCount;i++){
		tableModel_2.removeRow(0);
	}
	switch(organizationPool.get(table.getSelectedRow()).organizationType){
	case TRANSFER:ArrayList<AccountVO> accounts = controller.getAccountByOrganizationID(organizationPool.get(table.getSelectedRow()).organizationID);
				  for(int i=0;i<accounts.size();i++){
					  Object[]rowData_1 = {accounts.get(i)};
					  tableModel_2.addRow(rowData_1);
				  }
				  ArrayList<InventoryVO> inventories = controller.getInventoriesByTransferID(organizationPool.get(table.getSelectedRow()).organizationID);
				  for(int i=0;i<inventories.size();i++){
					  Object[]rowData_2 = {inventories.get(i)};
					  tableModel.addRow(rowData_2);
				  }
	case BRANCH:ArrayList<AccountVO> accounts_2 = controller.getAccountByOrganizationID(organizationPool.get(table.getSelectedRow()).organizationID);
				 for(int i=0;i<accounts_2.size();i++){
					 Object[]rowData_1 = {accounts_2.get(i)};
					 tableModel_2.addRow(rowData_1);
				 }
				ArrayList<FacilityVO> facilities = controller.getFacilitiesByBranchID(organizationPool.get(table.getSelectedRow()).organizationID);
				for(int i=0;i<facilities.size();i++){
					Object[] rowData_2 = {facilities.get(i)};
					tableModel.addRow(rowData_2);;
				}
	}


	commonButton = new MyJButton(890, 670, 150, 30, "修改机构信息", 20);
	commonButton.setActionCommand("CheckModify");
	commonButton.addActionListener(this);
	this.add(commonButton);
	
	this.repaint();
}

private void searchPanel(FrameManager frame) {
	table = (MyJTable) organizationInfo.getTable();
	this.removeAll();
	this.add(new MyJLabel(550, 20, 210, 90, "公司机构信息管理", 24, true));
	this.add(organizationInfo);
	this.initButton(frame);
	
	this.add(new MyJLabel(687,400,90,30,"仓库/转运",18,true));
	this.add(new MyJLabel(687,430,90,30,"信息",18,true));
	this.add(new MyJLabel(687,530,90,30,"人员信息",18,true));
	
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
			
	jsp.setBounds(777, 400, 400, 125);
	jsp.getViewport().setBackground(new Color(0,0,0,0.3f));
	jsp.setOpaque(false);
	jsp.setBorder(BorderFactory.createEmptyBorder());
	jsp.setVisible(true);
	this.add(jsp);
	
	String[] headers_2 = {"员工信息"};
	MyJTable table_2 = new MyJTable(headers_2,false);
	table_2.setBackground(new Color(40, 42, 66));
	table_2.setForeground(Color.WHITE);
	table_2.setFont(new MyFont(14));
	
	DefaultTableCellRenderer tcr_2 = new DefaultTableCellRenderer();// 设置table内容居中
	tcr_2.setHorizontalAlignment(JLabel.CENTER);
	table_2.setDefaultRenderer(Object.class, tcr_2);
		  	
	JScrollPane jsp_2=new JScrollPane(table_2);
	JTableHeader head_2 = table.getTableHeader();
	head_2.setBackground(new Color(0.1f, 0.19f, 0.54f, 0.2f));
	head_2.setFont(new MyFont(14));
	head_2.setForeground(Color.BLACK);
	head_2.setResizingAllowed(false);
			
	jsp_2.setBounds(777, 530, 400, 125);
	jsp_2.getViewport().setBackground(new Color(0,0,0,0.3f));
	jsp_2.setOpaque(false);
	jsp_2.setBorder(BorderFactory.createEmptyBorder());
	jsp_2.setVisible(true);
	this.add(jsp_2);
	
	organizationDetails = new OrganizationDetails();
	organizationDetails.setUneditable();
	organizationDetails.add(new MyJLabel(230,5,120,30,"查看机构信息",18,true));
	this.add(organizationDetails);
	
	DefaultTableModel tableModel = (DefaultTableModel) table_1.getModel();
	DefaultTableModel tableModel_2 = (DefaultTableModel) table_2.getModel();
	OrganizationBLService controller = ControllerFactory.getOrganizationController();
	
	int rowCount = table_1.getRowCount();
	for(int i = 0; i < rowCount; i++){
		tableModel.removeRow(0);
	}
	
	int rowCount_2 = table_2.getRowCount();
	for(int i = 0;i<rowCount;i++){
		tableModel_2.removeRow(0);
	}
	switch(organizationPool.get(table.getSelectedRow()).organizationType){
	case TRANSFER:ArrayList<AccountVO> accounts = controller.getAccountByOrganizationID(organizationPool.get(table.getSelectedRow()).organizationID);
				  for(int i=0;i<accounts.size();i++){
					  Object[]rowData_1 = {accounts.get(i)};
					  tableModel_2.addRow(rowData_1);
				  }
				  ArrayList<InventoryVO> inventories = controller.getInventoriesByTransferID(organizationPool.get(table.getSelectedRow()).organizationID);
				  for(int i=0;i<inventories.size();i++){
					  Object[]rowData_2 = {inventories.get(i)};
					  tableModel.addRow(rowData_2);
				  }
	case BRANCH:ArrayList<AccountVO> accounts_2 = controller.getAccountByOrganizationID(organizationPool.get(table.getSelectedRow()).organizationID);
				 for(int i=0;i<accounts_2.size();i++){
					 Object[]rowData_1 = {accounts_2.get(i)};
					 tableModel_2.addRow(rowData_1);
				 }
				ArrayList<FacilityVO> facilities = controller.getFacilitiesByBranchID(organizationPool.get(table.getSelectedRow()).organizationID);
				for(int i=0;i<facilities.size();i++){
					Object[] rowData_2 = {facilities.get(i)};
					tableModel.addRow(rowData_2);;
					}
	
	this.repaint();
	}
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
		
		OrganizationBLService controller = ControllerFactory.getOrganizationController();
		ArrayList<TransferVO> transferVO = controller.showTransfer();
		ArrayList<BranchVO> branchVO = controller.showBranch();
		for(int i=0;i<transferVO.size();i++){
			Object [] rowData1 = {transferVO.get(i).organizationID,transferVO.get(i).organizationType,transferVO.get(i).number,
					transferVO.get(i).date,transferVO.get(i).inventories,transferVO.get(i).accounts,transferVO.get(i).address};
			tableModel.addRow(rowData1);
			organizationPool.add(transferVO.get(i));
			
		}
		for(int i=0;i<branchVO.size();i++){
			Object[] rowData2 = {branchVO.get(i).organizationID,branchVO.get(i).organizationType,branchVO.get(i).number,branchVO.get(i).date
					,branchVO.get(i).facilities,branchVO.get(i).accounts,branchVO.get(i).address};
			tableModel.addRow(rowData2);
			organizationPool.add(branchVO.get(i));
 		}
	}

	private static final long serialVersionUID = 1L;
	@Override
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
			//营业厅、中转中心
			controller = ControllerFactory.getOrganizationController();
			
			ArrayList<TransferVO> transferVO = new ArrayList<TransferVO>();
			ArrayList<BranchVO> branchVO = new ArrayList<BranchVO>();
			String data = organizationInfo.getData();
			if(data!=null){
				switch(Integer.parseInt(data)){
					case 0 : transferVO = controller.showTransfer(); branchVO = controller.showBranch(); break;
					case 1 : branchVO = controller.showBranch();
					case 2 : transferVO = controller.showTransfer();
				}
				for(int i=0;i<transferVO.size();i++){
					Object[] rowData1 ={transferVO.get(i).organizationID,transferVO.get(i).organizationType,transferVO.get(i).number,
							transferVO.get(i).date,transferVO.get(i).inventories,transferVO.get(i).accounts,transferVO.get(i).address};
					tableModel.addRow(rowData1);
					organizationPool.add(transferVO.get(i));
				}
				for(int i=0;i<branchVO.size();i++){
					Object[] rowData2 = {branchVO.get(i).organizationID,branchVO.get(i).organizationType,branchVO.get(i).number,branchVO.get(i).date
							,branchVO.get(i).facilities,branchVO.get(i).accounts,branchVO.get(i).address};
					tableModel.addRow(rowData2);
					organizationPool.add(branchVO.get(i));
		 		}
				new MyNotification(this,"共有"+table.getRowCount()+"个机构满足条件！",Color.GREEN);
				
			}else{
				new MyNotification(this,"请输入要查询的机构类型！",Color.RED);
			}
		}else if(e.getActionCommand().equals("AddOrganization")){
			String[] data = organizationDetails.getData();
			controller = ControllerFactory.getOrganizationController();
			if(data==null){
				new MyNotification(this,"请检查机构信息填写是否完整！",Color.RED);
			}else{
				if(data[0].equals("营业厅")){
					//TODO 
					ResultMessage rsg = controller.addBranch(new BranchVO(controller.getBranchID(data[0]),data[0], data[1], null, null));
					if(rsg.equals(ResultMessage.SUCCESS)){
						System.out.println("AddSucceed!");
						this.showAll();
						new MyNotification(this,"新机构添加成功！",Color.GREEN);
					}else{
						new MyNotification(this,"新机构添加失败！",Color.RED);
					}
				}else{
					//TODO 
					ResultMessage rsg1 = controller.addTransfer(new TransferVO(controller.getTransferID(data[0]), data[2],data[3], new ArrayList<>(), new ArrayList<>()));
					if(rsg1.equals(ResultMessage.SUCCESS)){
						this.showAll();
						new MyNotification(this,"新机构添加成功！",Color.GREEN);
			
					}else{
						new MyNotification(this,"新机构添加失败！",Color.RED);
					}	
				}
			}
		}else if(e.getActionCommand().equals("DeleteOrganization")){
			//System.out.println("111");
			table =organizationInfo.getTable();
			if(table.getSelectedRowCount() == 0){
				new MyNotification(this,"请先选择要删除的机构！",Color.RED);
			}else{
				new MyNotification(this,"正在删除机构！",Color.GREEN);
				this.deleteOrganization();
			}
		}else if(e.getActionCommand().equals("ModifyOrganizationInformation")){
			table =organizationInfo.getTable();
			if(table.getSelectedRowCount() == 0){
				new MyNotification(this,"请先选择要修改的机构！",Color.RED);
			}else{
				switch(organizationPool.get(table.getSelectedRow()).organizationType){
					case TRANSFER:Object[] data2 = new String[3];
							organizationID = organizationPool.get(table.getSelectedRow()).organizationID;
						//	data2[0] = organizationID;
							data2[0] =  organizationPool.get(table.getSelectedRow()).organizationType;
							data2[1] =  organizationPool.get(table.getSelectedRow()).date;
							//data2[2] = organizationPool.get(table.getSelectedRow()).number;
						//	data2[4] = transferPool.get(table.getSelectedRow()).inventories;
							//data2[5] = transferPool.get(table.getSelectedRow()).accounts;
							data2[2] =transferPool.get(table.getSelectedRow()).address;
							String[] data3 = (String[]) data2;
							organizationDetails.setData(data3);
					case BRANCH:
							Object[] data_2 = new String[3];
							organizationID = organizationPool.get(table.getSelectedRow()).organizationID;
						//	data_2[0] = organizationID;
							data_2[0] =  organizationPool.get(table.getSelectedRow()).organizationType;
							data_2[1] =  organizationPool.get(table.getSelectedRow()).date;
							//data_2[2] = organizationPool.get(table.getSelectedRow()).number;
					//		data_2[4] = branchPool.get(table.getSelectedRow()).facilities;
						//	data_2[5] = branchPool.get(table.getSelectedRow()).accounts;
							data_2[2] =branchPool.get(table.getSelectedRow()).address;
							String[] data2_2 = (String[]) data_2;
							organizationDetails.setData(data2_2);
				}
			}
		
		}else if(e.getActionCommand().equals("CheckModify")){
			table =organizationInfo.getTable();
			//System.out.println("111");
			organizationID=organizationPool.get(table.getSelectedRow()).organizationID;
		//	if(table.getSelectedRow()==0){
			//	new MyNotification(this,"请先选择需要修改的机构！",Color.RED);
			//}else{
				if(organizationDetails.getData()==null){
					new MyNotification(this,"请检查机构信息填写是否完整！",Color.RED);
				}else{
					new MyNotification(this,"正在修改机构信息！",Color.GREEN);
					this.modifyOrganization();
				}
			//}
		}

	}


	



	private void modifyOrganization() {
		controller = ControllerFactory.getOrganizationController();
		table = organizationInfo.getTable();
		String[] data = organizationDetails.getData();
		switch(organizationPool.get(table.getSelectedRow()).organizationType){
		case TRANSFER:ResultMessage rsg = controller.updateTransfer(new TransferVO(organizationID, data[0], data[1], null, null));
						if(rsg.equals(ResultMessage.SUCCESS)){
							System.out.println("ModifySucceed!");
							this.showAll();
							new MyNotification(this,"中转中心信息修改成功！",Color.GREEN);		
						}else{
							new MyNotification(this,"中转中心信息修改失败！",Color.RED);
						}break;
		case BRANCH:ResultMessage rsg1 = controller.updateBranch(new BranchVO(organizationID, data[0], data[1], null, null));
					if(rsg1.equals(ResultMessage.SUCCESS)){
						this.showAll();
						new MyNotification(this,"营业厅信息修改成功！",Color.GREEN);
					}else{
						new MyNotification(this,"营业厅信息修改失败！",Color.RED);
					}
	}
	}



	private void deleteOrganization() {
		controller = ControllerFactory.getOrganizationController();
		switch(organizationPool.get(table.getSelectedRow()).organizationType){
		case TRANSFER:ResultMessage rsg = controller.deleteTransfer(organizationPool.get(table.getSelectedRow()).organizationID);
						if(rsg.equals(ResultMessage.SUCCESS)){
							this.showAll();
							new MyNotification(this,"中转中心删除成功！",Color.GREEN);
						}else{
							this.add(new MyNotification(this,"中转中心删除失败！",Color.RED));
						}
						break;
		case BRANCH:ResultMessage rsg1 = controller.deleteBranch(organizationPool.get(table.getSelectedRow()).organizationID);
					if(rsg1.equals(ResultMessage.SUCCESS)){
						System.out.println("DeleteSucceed!");
						this.showAll();
						new MyNotification(this,"营业厅删除成功！",Color.GREEN);
					}else{
						new MyNotification(this,"营业厅删除失败！",Color.RED);
					}
					break;
		
		default:
			break;
		}
		
	}

}
