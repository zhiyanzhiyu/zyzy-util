package com.zyzy.util;

import java.math.BigDecimal;

import org.junit.Test;

import junit.framework.TestCase;

public class AmountUtilTest extends TestCase {

	public AmountUtilTest(String testName) {
		super(testName);
	}

	@Test
	public void testFormatAmount() throws Exception {
		double formatAmountResult = AmountUtil.formatAmount(1234.539);
		assertEquals(1234.54, formatAmountResult);
		double formatAmountResult2 = AmountUtil.formatAmount(1234.539,1);
		assertEquals(1234.5, formatAmountResult2);
	}
		
	@Test
	public void testCheckAmount() throws Exception{
		boolean isValid = AmountUtil.checkAmount("12344.43");
		assertEquals(true,isValid);
		boolean isValid2 = AmountUtil.checkAmount("1234-443");
		assertEquals(false,isValid2);
	}
	
	@Test
	public void testFormatAmountStr() throws Exception{
		String fas1 = AmountUtil.formatAmountStr("1234");
		assertEquals("1234.00", fas1);
		String fas2 = AmountUtil.formatAmountStr("1234.1");
		assertEquals("1234.10", fas2);
		String fas3 = AmountUtil.formatAmountStr("1234.12");
		assertEquals("1234.12", fas3);
		String fas4 = AmountUtil.formatAmountStr("1234.123");
		assertEquals("1234.12", fas4);
	}
	
	@Test
	public void testDivide100() throws Exception {
		BigDecimal bd1 = AmountUtil.divide100(999999l);
		assertEquals("9999.99",bd1.toString());
		BigDecimal bd2 = AmountUtil.divide100(999992l,1);
		assertEquals("9999.9",bd2.toString());
		BigDecimal bd3 = AmountUtil.divide100(new BigDecimal(999992));
		assertEquals("9999.92",bd3.toString());
	}
	
	@Test
	public void testMultiply100() throws Exception{
		Long bd1 = AmountUtil.multiply100(999999l);
		assertEquals(new Long(99999900),bd1);
		Long bd2 = AmountUtil.multiply100(999999.99);
		assertEquals(new Long(99999999),bd2);
		Long bd3 = AmountUtil.multiply100(new BigDecimal(999999.99));
		assertEquals(new Long(99999999),bd3);
	}
}



