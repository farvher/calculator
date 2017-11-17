package com.calculator.calculator.enumeration;

public class BCD {

	public static byte[] DecimalToBCD(long num) {
		int digits = 0;

		long temp = num;
		while (temp != 0) {
			digits++;
			temp /= 10;
		}

		int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;

		byte bcd[] = new byte[byteLen];

		for (int i = 0; i < digits; i++) {
			byte tmp = (byte) (num % 10);

			if (i % 2 == 0) {
				bcd[i / 2] = tmp;
			} else {
				bcd[i / 2] |= (byte) (tmp << 4);
			}

			num /= 10;
		}

		for (int i = 0; i < byteLen / 2; i++) {
			byte tmp = bcd[i];
			bcd[i] = bcd[byteLen - i - 1];
			bcd[byteLen - i - 1] = tmp;
		}

		return bcd;
	}

	public static long BCDToDecimal(byte[] bcd) {
		return Long.valueOf(BCD.BCDtoString(bcd));
	}

	public static String BCDtoString(byte bcd) {
		StringBuffer sb = new StringBuffer();

		byte high = (byte) (bcd & 0xf0);
		high >>>= (byte) 4;
		high = (byte) (high & 0x0f);
		byte low = (byte) (bcd & 0x0f);

		sb.append(high);
		sb.append(low);

		return sb.toString();
	}

	public static String BCDtoString(byte[] bcd) {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < bcd.length; i++) {
			sb.append(BCDtoString(bcd[i]));
		}

		return sb.toString();
	}

	public static String testForValue(long val) {
		String binaryString = BCD.byteArrayToBinaryString(BCD.DecimalToBCD(val));
		return binaryString;
	}

	public static String byteArrayToBinaryString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		for (byte i : bytes) {
			String byteInBinary = String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0');
			sb.append(byteInBinary);
		}
		return sb.toString();
	}
}