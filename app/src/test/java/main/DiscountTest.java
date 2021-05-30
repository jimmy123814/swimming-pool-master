package main;

import org.junit.jupiter.api.*;

public class DiscountTest {

	@DisplayName("不同年紀的折扣")
	@Nested
	class DifferentAges {
		String dateTime = "2021-05-26 週三 14:30:00";

		@Test
		public void testAgeHasDiscount() throws Throwable {
			Identity identity = new Identity(10, false, false);
			Discount discount = new Discount(identity, dateTime);
			Assertions.assertEquals(0.8, discount.getDiscount());
		}

		@Test
		public void testAgeLessThan() throws Throwable {
			Identity identity = new Identity(2, false, true);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Your age is too young.", exception.getMessage());
			}
		}
		@Test
		public void testAgemoreThan() throws Throwable {
			Identity identity = new Identity(76, false, true);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Your age doesn't meet the requirements.", exception.getMessage());
			}
		}
	}

	@DisplayName("營業與非營業時間")
	@Nested
	class DifferentBusinessHours {
		@Test
		public void testEarlyBirdBusiness() throws Throwable {
			Identity identity = new Identity(25, false, false);
			Discount discount = new Discount(identity, "2021-05-26 週三 06:30:00");
			Assertions.assertEquals(0.8, discount.getDiscount());
		}

		@Test
		public void testHaveBusiness() throws Throwable {
			Identity identity = new Identity(25, false, false);
			Discount discount = new Discount(identity, "2021-05-26 週三 10:30:00");
			Assertions.assertEquals(1, discount.getDiscount());
		}
		@Test
		public void testBusiness1() throws Throwable {
			String dateTime = "2021-05-26 週三 04:30:00";
			Identity identity = new Identity(25, false, false);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Business hours: 05:00-22:00", exception.getMessage());
			}
		}
		@Test
		public void testBusiness2() throws Throwable {
			String dateTime = "2021-05-26 週三 23:30:00";
			Identity identity = new Identity(25, false, false);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Business hours: 05:00-22:00", exception.getMessage());
			}
		}
		@Test
		public void testBusiness3() throws Throwable {
			String dateTime = "2021-05-26 週三 22:00:00";
			Identity identity = new Identity(25, false, false);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Business hours: 05:00-22:00", exception.getMessage());
			}
		}
		@Test
		public void testBusiness4() throws Throwable {
			String dateTime = "2021-05-26 週三 -1:00:00";
			Identity identity = new Identity(25, false, false);
			try {
				new Discount(identity, dateTime);
			} catch (Throwable exception) {
				Assertions.assertEquals("Business hours: 05:00-22:00", exception.getMessage());
			}
		}
	}
	@DisplayName("會員")
	@Nested
	class Member {
		String dateTime = "2021-05-26 週三 14:30:00";
		@Test
		public void testAgeHasDiscount() throws Throwable {
			Identity identity = new Identity(10, true, false);
			Discount discount = new Discount(identity, dateTime);
			Assertions.assertEquals(0.5, discount.getDiscount());
		}
		
	}
}
