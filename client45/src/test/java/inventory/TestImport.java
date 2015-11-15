package inventory;

import static org.junit.Assert.*;

import java.rmi.RemoteException;

import org.junit.Test;

import state.ResultMessage;
import vo.InventoryImportReceiptVO;
import vo.InventoryVO;

public class TestImport {
	private ResultMessage SUCCESS;

	@Test	
	public void testImport()throws RemoteException{
		InventoryImportReceiptVO importReceipt=new InventoryImportReceiptVO(null, null, 0, 0, 0, 0);
		MockInventoryImport test=new MockInventoryImport();
		assertEquals("1",test.getImportID());
		assertEquals("nanjing520",test.getCommodities());
		assertEquals("nanjing",test.getDestination());
		assertEquals((new InventoryVO(1, 1, 1, 1)).getA(),(test.addPlace()).getA());
		assertEquals((new InventoryVO(1, 1, 1, 1)).getB(),(test.addPlace()).getB());
		assertEquals((new InventoryVO(1, 1, 1, 1)).getC(),(test.addPlace()).getC());
		assertEquals((new InventoryVO(1, 1, 1, 1)).getD(),(test.addPlace()).getD());
		assertEquals(SUCCESS,test.submitImport(importReceipt));
	}
	
}
