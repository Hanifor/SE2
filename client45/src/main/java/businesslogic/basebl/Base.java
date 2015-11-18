package businesslogic.basebl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import dataservice.basedataservice.BaseDataService;

public class Base {
	private BaseDataService baseData;
	public Base() {
		try {
			baseData = (BaseDataService)Naming.lookup("rmi://" + "127.0.0.1" + ":" + "8888" + "/"+BaseDataService.NAME);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
