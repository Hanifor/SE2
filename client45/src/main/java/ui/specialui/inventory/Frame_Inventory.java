package ui.specialui.inventory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.image.CommonImage;
import ui.myui.MyJFrame;
import ui.myui.MyJPanel;
import ui.specialui.inventory.check.InventoryCheck;
import ui.specialui.inventory.export.CargoExport;
import ui.specialui.inventory.initialization.InventoryInitialization;
import ui.specialui.inventory.stocking.Stocking;
import ui.specialui.inventory.zone_adjust.InventoryZoneAdjust;
import ui.specialui.transfer_counterman.Frame_Transfer;

public class Frame_Inventory extends MyJFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Panel_Inventory_Total totalPanel;
	private MyJPanel subPanel;
	
	public Frame_Inventory() {
		super(false);
		
		this.totalPanel = new Panel_Inventory_Total(this);
		this.add(totalPanel);
		
		this.returnButton.addActionListener(this);
		
		this.setBackground(CommonImage.TEST_BACKGROUND);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("return")){
			//回退到主界面
			if(subPanel != null){
				subPanel.setVisible(false);
				this.remove(subPanel);
				subPanel = null;
				totalPanel.setVisible(true);
			}else{
				new Frame_Transfer(false);
				this.removeAll();
				this.dispose();
			}
		}else if(e.getActionCommand().equals("InventoryCheck")){
			//进入库存查看界面
			totalPanel.setVisible(false);
			subPanel = new InventoryCheck(this);
			this.getLayeredPane().add(subPanel,new Integer(Integer.MAX_VALUE));
		}else if(e.getActionCommand().equals("Stocking")){
			//进入库存盘点界面
			totalPanel.setVisible(false);
			subPanel = new Stocking(this);
			this.getLayeredPane().add(subPanel,new Integer(Integer.MAX_VALUE));
		}else if(e.getActionCommand().equals("InventoryZoneAdjust")){
			//进入库存分区调整界面
			totalPanel.setVisible(false);
			subPanel = new InventoryZoneAdjust(this);
			this.getLayeredPane().add(subPanel,new Integer(Integer.MAX_VALUE));
		}else if(e.getActionCommand().equals("CargoExport")){
			//进入出库界面
			totalPanel.setVisible(false);
			subPanel = new CargoExport(this);
			this.getLayeredPane().add(subPanel,new Integer(Integer.MAX_VALUE));
		}else if(e.getActionCommand().equals("InventoryInitialization")){
			//进入库存信息初始化界面
			totalPanel.setVisible(false);
			subPanel = new InventoryInitialization(this);
			this.getLayeredPane().add(subPanel,new Integer(Integer.MAX_VALUE));
		}
	}
}