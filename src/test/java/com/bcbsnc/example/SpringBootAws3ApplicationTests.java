package com.bcbsnc.example;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bcbsnc.example.util.AgeCalculator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootAws3ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void DateTimeTest() throws Exception {
		//testing the logic around birthday and requested day
		//to make sure they age appropriately
		DateTime test1 = new DateTime(2017,  2, 10, 00, 00, 00, 000);
		DateTime bDay1 = new DateTime(2000,  2,  9, 00, 00, 00, 000);
		DateTime bDay2 = new DateTime(2000,  2, 10, 00, 00, 00, 000);
		DateTime bDay3 = new DateTime(2000,  2, 11, 00, 00, 00, 000);

		assertEquals(AgeCalculator.isOver65(bDay1, test1), false);
		assertEquals(AgeCalculator.isOver65(bDay2, test1), false);
		assertEquals(AgeCalculator.isOver65(bDay3, test1), false);
		
		//AgeCalculator.isOver65(bDay1, test1);
		//AgeCalculator.isOver65(bDay2, test1);
		//AgeCalculator.isOver65(bDay3, test1);

		//testing the logic around the age 65
		DateTime test2 = new DateTime(2065,  2,  10, 00, 00, 00, 000);
		DateTime bDay4 = new DateTime(1999,  2,  9, 00, 00, 00, 000);
		DateTime bDay5 = new DateTime(1999,  2,  10, 00, 00, 00, 000);
		DateTime bDay6 = new DateTime(1999,  2,  11, 00, 00, 00, 000);
		
		assertEquals(AgeCalculator.isOver65(bDay4, test2), true);	//Over 65. Turned 66 prior day
		assertEquals(AgeCalculator.isOver65(bDay5, test2), true); 	//Over 65. Turns 66 on test day.
		assertEquals(AgeCalculator.isOver65(bDay6, test2), false);	//Under 65. Turned 66 next day.

	}
	
	@Test
	public void AgeTest() throws Exception {
		//testing the logic around birthday and requested day to make sure they age appropriately
		DateTime test1 = new DateTime(2017,  2, 10, 00, 00, 00, 000);
		DateTime bDay1 = new DateTime(2000,  2,  9, 00, 00, 00, 000);
		DateTime bDay2 = new DateTime(2000,  2, 10, 00, 00, 00, 000);
		DateTime bDay3 = new DateTime(2000,  2, 11, 00, 00, 00, 000);

		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay1, test1), 17);
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay2, test1), 17);
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay3, test1), 16);

		//testing the logic around the age 65
		DateTime test2 = new DateTime(2065,  2,  10, 00, 00, 00, 000);
		DateTime bDay4 = new DateTime(1999,  2,  9, 00, 00, 00, 000);
		DateTime bDay5 = new DateTime(1999,  2,  10, 00, 00, 00, 000);
		DateTime bDay6 = new DateTime(1999,  2,  11, 00, 00, 00, 000);
		
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay4, test2), 66);	//Over 65. Turned 66 prior day
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay5, test2), 66); 	//Over 65. Turns 66 on test day.
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay6, test2), 65);	//Under 65. Turned 66 next day.

		//testing the logic around newborns
		DateTime test3 = new DateTime(2017,  2,  10, 00, 00, 00, 000);
		DateTime bDay7 = new DateTime(2017,  1,  1, 00, 00, 00, 000);
		DateTime bDay8 = new DateTime(2017,  2,  10, 00, 00, 00, 000);
		DateTime bDay9 = new DateTime(2017,  2,  11, 00, 00, 00, 000);
		DateTime bDay10= new DateTime(2018,  1,   1, 00, 00, 00, 000);
		
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay7,  test3), 0);	//Over 65. Turned 66 prior day
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay8,  test3), 0); 	//Over 65. Turns 66 on test day.
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay9,  test3), 0);	//Under 65. Turned 66 next day.
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay10, test3), 0);	//Under 65. Turned 66 next day.
		
		DateTime test4 = new DateTime(2017,  1,  1, 00, 00, 00, 000);
		DateTime bDay11= new DateTime(2017,  1,  1, 00, 00, 00, 000);
		DateTime bDay12= new DateTime(2017,  1,  2, 00, 00, 00, 000);
		DateTime bDay13= new DateTime(2018,  1,  1, 00, 00, 00, 000);
		DateTime bDay14= new DateTime(2016,  1,  1, 00, 00, 00, 000);
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay11, test4), 0);	//Born same day. Make age 0.
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay12, test4), 0);	//Not born yet. Make age 0.
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay13, test4), 0);	//Not born yet. Make age 0.
		assertEquals(AgeCalculator.getAgeAsOfRequestedDate(bDay14, test4), 1);	//First Birthday. Make age 1.
	}
	
}
