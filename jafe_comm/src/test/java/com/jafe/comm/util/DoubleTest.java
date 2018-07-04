package com.jafe.comm.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoubleTest {


	@Test
	public void testFunction() throws Exception {
		System.out.println(0.05+0.01);
		System.out.println(1.0-0.42);
		System.out.println(4.015*100);
		System.out.println(123.3/100);

		double v1 = 0.05;
		double v2 = 0.01;
		System.out.println(v1+v2);

		double v3 = 10;
		double v4 = 3;
		System.out.println( v3/v4);

		long v5 = 10;
		long v6 = 3;
		System.out.println( v5/v6);

		System.out.println( v5 % v6);

		int v7 = 10;
		int v8 = 3;
		System.out.println( v7 / v8);
		System.out.println( v7 % v8);

		double v9 = 4.015;
		double v10 = Math.round(v9*100)/100.0;
		System.out.println(v10);

		Double v11 = 4.015;
		Double v12 = Math.round(v11*100)/100.0;
		System.out.println(v12);

		double v13 = 4.015;
		double v14 = 100;
		double v15 = v13*v14;
		System.out.println(v15);

		System.out.println("decimalFormat: " + new java.text.DecimalFormat("0.00").format(4.025)); //jdk 1.8 输出4.03 jdk1.7 输出4.02

		double v16 = 4.017;
		double v17 = 100;
		double v18 = v16*v17;
		System.out.println(v18);

		double v19 = Math.round(v16 * v17)/v17;
		System.out.println(v19);

		System.out.println(Math.round(11.5));
		System.out.println(Math.round(-11.5));


		System.out.println(Math.ceil(11.3));
		System.out.println(Math.ceil(-11.3));

		System.out.println(Math.floor(11.6));
		System.out.println(Math.floor(-11.6));

//		System.out.println(Math.round(value*100)/100.0);
	}

}