package ui.specialui.finance.SettlementManage;

import ui.GetDate;
import ui.myui.MyJLabel;
import ui.myui.MyJTextField;
import ui.myui.MyTranslucentPanel;

public class ModifyPayBill extends MyTranslucentPanel{
	private MyJTextField[] fields ;
	public ModifyPayBill() {
		super(680, 390,550,240);
		this.initComponent();
	}

	private static final long serialVersionUID = 1L;
	private void initComponent() {
		this.add(new MyJLabel(240,10,150,30,"添加收款单",18,true));
		this.add(new MyJLabel(40,50,90,30,"付款人",16,true));
		this.add(new MyJLabel(290,50,90,30,"付款金额",16,true));
		this.add(new MyJLabel(40,90,90,30,"付款账户",16,true));
		this.add(new MyJLabel(290,90,90,30,"付款条目",16,true));
		this.add(new MyJLabel(40,130,90,30,"付款备注",16,true));
		this.add(new MyJLabel(290,130,90,30,"付款日期",16,true));
		fields = new MyJTextField[6];
		fields[0] = new MyJTextField(130,50,120,30);
		this.add(fields[0]);
		
		fields[1] = new MyJTextField(380,50,120,30);
		this.add(fields[1]);
		
		fields[2] = new MyJTextField(130,90,120,30);
		fields[2].setOnlyInteger(19);
		this.add(fields[2]);
		
		fields[3] = new MyJTextField(380,90,120,30);
		this.add(fields[3]);
		
		fields[4] = new MyJTextField(130,130,120,30);
		this.add(fields[4]);
		
		fields[5] = new MyJTextField(380,130,120,30);
		fields[5].setText(GetDate.getTime());
		fields[5].setEditable(false);
		this.add(fields[5]);		
	}



	public void setUneditable() {
		for(MyJTextField field: fields){
			field.setEditable(false);
		}
		
	}
	
	public String[] getData(){
		String [] data = new String[6];
		for(int i=0;i<6;i++){
			data[i] = fields[i].getText();
			if(data[i]==null){
				return null;
			}
		}
		return data;
	}
	
	public void setData(String [] data){
		for(int i=0;i<6;i++){
			fields[i].setText(data[i]);
		}
	}

}
