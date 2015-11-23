package ui.specialui.manager.AdjustBase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ui.myui.MyJButton;
import ui.myui.MyJLabel;
import ui.myui.MyJPanel;
import ui.specialui.manager.FrameManager;


public class Panel_Manager_AdjustBase extends MyJPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseDetails baseDetails;
	private BaseInfoList baseInfo;
	private MyJButton commonButton;
	public Panel_Manager_AdjustBase(FrameManager frameManager) {
		
		super(0, 0, 1280, 720);
		this.setOpaque(false);
		this.initComponent(frameManager);
		
	}
	

	private void initComponent(FrameManager frameManager) {
		this.add(new MyJLabel(530, 20, 250, 90, "公司成本常量制定", 24, true));
		baseDetails = new BaseDetails();
		baseDetails.setEnabled(false);
		this.add(baseDetails);
		
		baseInfo = new BaseInfoList(frameManager);
		this.add(baseInfo);
		this.initButton(frameManager);
		
		
	}
	private void initButton(FrameManager frame) {
		MyJButton insertButton = new MyJButton(0, 150, 40, 130,
				"<html>添<br/>加<br/>常<br/>量<br/></html>", 18);
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel_Manager_AdjustBase.this.insertPanel(frame);
				
			}
		});
		this.add(insertButton);

		MyJButton modifyButton = new MyJButton(0, 280, 40, 130,
				"<html>修<br/>改<br/>常<br/>量<br/>信<br/>息<br/></html>", 18);
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel_Manager_AdjustBase.this.modifyPanel(frame);
				
			}
		});
		this.add(modifyButton);

		MyJButton searchButton = new MyJButton(0, 410, 40, 130,
				"<html>查<br/>看<br/>常<br/>量<br/></html>", 18);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel_Manager_AdjustBase.this.searchPanel(frame);
				
			}
		});
		this.add(searchButton);

		MyJButton deleteButton = new MyJButton(0, 540, 40, 110,
				"<html>删<br/>除<br/>常<br/>量<br/></html>", 18);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Panel_Manager_AdjustBase.this.deletePanel(frame);
				
			}
		});
		this.add(deleteButton);
	}
	

	private void insertPanel(FrameManager frame) {
	this.removeAll();
	this.add(baseInfo);
	this.add(new MyJLabel(530, 20, 250, 90, "公司成本常量制定", 24, true));
	this.initButton(frame);

	
	baseDetails = new BaseDetails();
	baseDetails.add(new MyJLabel(230,5,120,30,"新增常量",18,true));
	this.add(baseDetails);
	
	
	commonButton = new MyJButton(890, 670, 120, 30, "添加常量", 20);
	commonButton.setActionCommand("AddNewBase");
	commonButton.addActionListener(frame);
	this.add(commonButton);
	
	this.repaint();
}

private void modifyPanel(FrameManager frame) {
	this.removeAll();
	this.add(new MyJLabel(530, 20, 250, 90, "公司成本常量制定", 24, true));
	this.add(baseInfo);
	this.initButton(frame);
	
	baseDetails = new BaseDetails();
	baseDetails.add(new MyJLabel(230,5,120,30,"修改常量信息",18,true));
	this.add(baseDetails);
	
	commonButton = new MyJButton(890, 670, 150, 30, "修改常量信息", 20);
	commonButton.setActionCommand("ModifyBase");
	commonButton.addActionListener(frame);
	this.add(commonButton);
	
	this.repaint();
}

private void searchPanel(FrameManager frame) {
	
	this.removeAll();
	this.add(new MyJLabel(530, 20, 250, 90, "公司成本常量制定", 24, true));
	this.add(baseInfo);
	this.initButton(frame);
	
	baseDetails = new BaseDetails();
	baseDetails.setUneditable();
	baseDetails.add(new MyJLabel(230,5,120,30,"查看常量信息",18,true));
	this.add(baseDetails);
	
	this.repaint();
}

private void deletePanel(FrameManager frame) {
	
	this.removeAll();
	this.add(new MyJLabel(530,20, 250, 90, "公司成本常量制定", 24, true));
	this.add(baseInfo);
	this.initButton(frame);
	
	baseDetails = new BaseDetails();
	baseDetails.setUneditable();
	baseDetails.add(new MyJLabel(230,5,120,30,"删除常量",18,true));
	this.add(baseDetails);
	
	commonButton = new MyJButton(890, 670, 120, 30, "删除常量", 20);
	commonButton.setActionCommand("DeleteBase");
	commonButton.addActionListener(frame);
	this.add(commonButton);
	
	this.repaint();
}
/**
 * TODO 从bl层获取数据
 * 添加用户
 */
/*
public int addOrganization() {
	String [] data = organizationDetails.getData();

	if(data == null){
		return 1;
	}
	if(data[4].equals("营业厅")){
		data[0] = organizationController.getID();
		//branch = new BranchVO(name, name, name, null, null);
		organizationController.addBranch(branch);
	}else if(data[4].equals("中转中心")){
		data[0] = organizationController.getID();
		//transfer = new TransferVO(name, name, flags, null, null);
		organizationController.addTransfer(transfer);
	}
	
	return 0;
	
}
/**
* 修改用户信息
* 从bl层获得数据
*/
/*public int modifyOrganization() {
	String [] data = organizationDetails.getData();
	if(data == null){
		return 1;
	}
	if(data[4].equals("营业厅")){
		branch.setId(data[0]);
		branch.setAddress(data[7]);
		organizationController.updateBranch(branch);
	}else if(data[4].equals("中转中心")){
		organizationController.updateTransfer(transfer);
	}
	

	
	return 0;
}
/**
 * 删除用户
 * @return
 */
/*public int deleteOrganization() {
	String[] data = organizationDetails.getData();
	//现在列表中选择一个用户后再进行删除
	//TODO -
	if(data[4].equals("营业厅")){
		organizationController.deleteBranch(data[0]);
		return 0;
	}else if(data[4].equals("中转中心")){
		organizationController.deleteTransfer(data[0]);
		return 0;
	}
	
	
		return 0;
}

public void refresh() {
	organizationDetails.refresh();
}
/**
 * 查看用户信息列表
 * TODO 从bl层获取数据
 */
/*public boolean searchOrganization() {		
	String type = organizationInfo.getData();
	if(type==null){
		return false;
	}else if(type.equals("营业厅")){
		branchList = organizationController.showBranch();
		return true;
	}else if(type.equals("中转中心")){
		transferList = organizationController.showTransfer();
		return true;
	}
	return false;
}

/**
 * 查看用户详细信息
 */
/*public boolean vieworganizationDetails(){
	//TODO
	//从organizationList中选择一个要查看的用户
	return false;
}*/
}
