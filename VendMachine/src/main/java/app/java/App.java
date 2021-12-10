
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.java;


import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author ivaylomaslev
 */
public class App {

    public static void main(String[] args) throws VendingMachinePersistenceException {

      //  UserIO iO = new UserIOImpl();
      //  VendingMachineView myView = new VendingMachineView(iO);
      //  VendingMachineDao myDao = new VendingMachineDaoImpl();
     //   VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
     //   VendingMachineService myService = new VendingMachineServiceImpl(myDao, myAuditDao);
     //   VendingMachineController controller = new VendingMachineController(myService, myView);

     //   controller.run();
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
VendingMachineController controller = ctx.getBean("controller" , VendingMachineController.class);
controller.run();
    }

}
