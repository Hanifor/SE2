package config;
/**
 * 
 * @author Ann
 *
 */
public class RMIConfig {
	public static String HOST ;
	public static int PORT ;
	public static String PREFIX ;

	static {
		XMLReader parse = new XMLReader("config/rmi.xml");
		HOST = parse.getValue("host");
		PORT = Integer.parseInt(parse.getValue("port"));
		PREFIX = "rmi://" + HOST + ":" + PORT + "/";
	}
	
	public static void main(String[] args) {
		System.out.println(RMIConfig.HOST);
		System.out.println(RMIConfig.PORT);
		System.out.println(RMIConfig.PREFIX);
//		XMLReader reader = new XMLReader("config/rmi.xml");
//		reader.setValue("fuck", "brother");
	}
}
