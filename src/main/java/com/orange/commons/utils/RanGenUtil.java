package com.orange.commons.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RanGenUtil {

	public static String generateOrderNo() {
		String seq = RandomStringUtils.randomNumeric(8);
		DateFormat df = new SimpleDateFormat("yyMMddmmssHH");
		seq = df.format(new Date()) + seq;
		return seq;
	}

	public static String generateBillNo() {
		String seq = RandomStringUtils.randomNumeric(8);
		DateFormat df = new SimpleDateFormat("yyMMddmmssHH");
		seq = df.format(new Date()) + seq;
		return seq;
	}

	public static String generateSettleNo() {
		String seq = RandomStringUtils.randomNumeric(8);
		DateFormat df = new SimpleDateFormat("yyMMddmmssHH");
		seq = df.format(new Date()) + seq;
		return seq;
	}

	public static String generatePaymenNo() {
		String seq = RandomStringUtils.randomNumeric(8);
		DateFormat df = new SimpleDateFormat("yyMMddmmssHH");
		seq = df.format(new Date()) + seq;
		return seq;
	}

	public static String generate32Seq() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String genSmsCode() {
		String ranCode = RandomStringUtils.randomNumeric(6);
		return ranCode;
	}

	public static String genSmsSeq() {
		return RandomStringUtils.randomNumeric(9);
	}
	
	public static void main(String[] args) {
		System.out.println(RanGenUtil.generateOrderNo());
	}
}