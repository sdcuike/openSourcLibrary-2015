package com.doctor.java.design_pattern;

/**
 * @see http://javapapers.com/design-patterns/template-method-design-pattern/
 * 
 *      Template method design pattern is to define an algorithm as skeleton of operations and leave the details to be implemented by the child
 *      classes. The overall structure and sequence of the algorithm is preserved by the
 *      parent class.
 * 
 *      -. Key Points on Template Method Design Pattern
 * 
 *      1.Base abstract class contain complete implementation of an operation(method) and used in the algorithm. In such cases, those methods should
 *      be clearly marked as not for implementation by subclasses.
 * 
 *      2.This design pattern can also be implemented with a base concrete class and all the operations given a default behavior in base class itself.
 * 
 *      3.The template method in base class should be restricted so that the subclasses does not override it.
 * 
 *      4. It is better to have lesser number of methods to be implemented by the subclasses and that will help provide an easy implementation.
 * 
 *      5. Use better naming convention to highlight the methods that should be implemented by the subclasses.
 * 
 *      6.Template method design pattern uses the inverted control structure as in “Don’t call, we will call you”. The operations defined in
 *      subclasses are called by base class from within the template method.
 * 
 *      7.More care should be taken when defining the Java access modifiers.
 * 
 * 
 *      Example in Java API:Class HttpServlet and it doGet, doPost, doPut,… methods are example of template method pattern usage in Java API.
 * 
 * @author doctor
 *
 * @time 2015年4月1日 下午1:56:01
 */
public class TemplateMethodDesignPattern {

	public static void main(String[] args) {
		System.out.println("$$$$$$$ NetOrder instance of the Template: $$$$$$$");
		OrderProcessTemplate netOrder = new NetOrder();
		netOrder.processOrder();

		System.out.println("$$$$$$$ StoreOrder instance of the Template: $$$$$$$");
		OrderProcessTemplate storeOrder = new StoreOrder();
		storeOrder.processOrder();

	}

	private static abstract class OrderProcessTemplate {
		protected boolean isGift;

		protected abstract void doSelect();

		protected abstract void doPay();

		protected abstract void doDelivery();

		public final void giftWrap() {
			System.out.println("Gift wrap done.");
		}

		public final void processOrder() {
			doSelect();
			doPay();
			if (isGift) {
				giftWrap();
			}
			doDelivery();
		}
	}

	private static class NetOrder extends OrderProcessTemplate {

		@Override
		protected void doSelect() {
			System.out.println("Item added to online shopping cart,");
			System.out.println("Get gift wrap preference,");
			System.out.println("Get delivery address.");
		}

		@Override
		protected void doPay() {
			System.out.println("Online Payment through Netbanking/Cards.");
		}

		@Override
		protected void doDelivery() {
			System.out.println("Ship the item through post to delivery address");
		}
	}

	private static class StoreOrder extends OrderProcessTemplate {

		@Override
		protected void doSelect() {
			System.out.println("Customer chooses the item from shelf.");
		}

		@Override
		protected void doPay() {
			System.out.println("Pays at counter through cash/POS");
		}

		@Override
		protected void doDelivery() {
			System.out.println("Item deliverd to in delivery counter.");
		}

	}
}
