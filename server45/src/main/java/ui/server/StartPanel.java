package ui.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import server.RMIManage;
import ui.CommonImage;
import ui.ServerButton;
import ui.ServerLabel;
import ui.ServerPanel;

public class StartPanel extends ServerPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	/** 显示系统信息 */
	private ServerInfoPanel serverInfoPanel;
	/** 显示当前登录客户信息 */
	private ClientInfoPanel clientInfoPanel;
	/** 启动服务器按钮 */
	private ServerButton startBtn;
	/** 关闭服务器按钮 */
	private ServerButton stopBtn;

	/**刷新按钮*/
	private ServerButton refreshBtn;
	
	private RMIManage server;
	
	public StartPanel() {
		super(0,0,900,506);
		this.setOpaque(false);
		this.initComponent();
		this.repaint();
		
	}
	private void initComponent(){
		server = new RMIManage();

		serverInfoPanel = new ServerInfoPanel(server.getHostAddr(), server.getHostName());
		this.add(serverInfoPanel);
		this.addStartStopButton();
		//clientInfoPanel = new ClientInfoPanel();
		//this.add(clientInfoPanel);
		
		this.add(new ServerLabel(750/2,40,150,30,"服务器信息",30,true));
		//this.add(new ServerLabel(580+450/2,40,150,30,"客户端信息",24,true));
	}
	
	/**
	 * 添加开始和关闭按钮
	 */
	private void addStartStopButton() {

		startBtn = new ServerButton(325,257,200,60,CommonImage.getBUTTON_OPEN());
		this.add(startBtn);
		
		stopBtn = new ServerButton(325,257+60+20,200,60,CommonImage.getCLOSE());
		this.add(stopBtn);
		stopBtn.setEnabled(false);
		
	//	refreshBtn = new ServerButton(580+225,565+77,150,40,CommonImage.getBUTTON_REFRESH());
		//this.add(refreshBtn);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBtn) {
		/*	if (!server.isStarted()) {
				startBtn.setEnabled(false);
				stopBtn.setEnabled(true);
				server.startServer();
				serverInfoPanel.setStarted(true);
			}*/
		} else if (e.getSource() == stopBtn) {
			/*if (server.isStarted()) {
				startBtn.setEnabled(true);
				stopBtn.setEnabled(false);
				server.stopServer();
				serverInfoPanel.setStarted(false);
			}*/
		}else if(e.getSource() == refreshBtn){
			clientInfoPanel.removeAll();
			clientInfoPanel.initComponent();
		}
	}
}
