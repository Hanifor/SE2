package ui.myui;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import ui.image.CommonImage;
/**
 * 半透明Panel,用于放置输入所需要的组件
 * @author czw
 * @time 2015年11月16日下午10:01:51
 */
public class MyTranslucentPanel extends MyJPanel {
	private static final long serialVersionUID = 1L;

	public MyTranslucentPanel(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public void paint(Graphics g){
		BufferedImage image = new BufferedImage(550, 640, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setComposite(AlphaComposite.SrcOver.derive(1.0f));
		g2d.drawImage(CommonImage.TEST_HALFOPAQUE, 0, 0, null);
	    g2d.dispose();
	    g.drawImage(image, 0, 0, null);
	    super.paintComponents(g);
	}
}