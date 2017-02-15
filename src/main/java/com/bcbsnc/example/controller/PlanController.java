package com.bcbsnc.example.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bcbsnc.example.entity.Plan;
import com.bcbsnc.example.repository.PlanRepository;

//@Controller
public class PlanController {

//	  @Autowired
//	  private PlanRepository planDao;
//	  
//	  @RequestMapping("/getplan")
//	  @ResponseBody 
//	  public String getPlan(String PLAN_ID, int PLAN_VARNT_ID, String SALE_BY_DT) throws ParseException
//	  {
//		  //Date and time format - ISO 8601
//		  //ISO 8601 tackles the uncertainty of date formatting by setting out an internationally agreed way to represent dates:
//		  //YYYY-MM-DD
//		  //
//		  //Date:	2016-12-14
//		  //Combined date and time in UTC:	2016-12-14T14:17:22+00:00
//		  //								2016-12-14T14:17:22Z
//		  //								20161214T141722Z
//		  System.out.println("Find rate for plan:"+PLAN_ID+"-"+PLAN_VARNT_ID+" on "+SALE_BY_DT);
//		  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		  Date saleByDate = df.parse(SALE_BY_DT);
//		  System.out.println("Find rate for plan:"+PLAN_ID+"-"+PLAN_VARNT_ID+" on "+saleByDate);
//		  
//		  try {
////			  List<Plan> plans1 = planDao.findTest1(PLAN_ID, PLAN_VARNT_ID);
////			  System.out.println("Found "+plans1.size()+" plans.");
////			  Iterator<Plan> planIterator1 = plans1.iterator();
////			  while (planIterator1.hasNext())
////			  {
////				  System.out.println("HELLO "+planIterator1.next());
////			  }
//
////			  List<Plan> plans2 = planDao.findTest2(saleByDate);
////			  System.out.println("Found "+plans2.size()+" plans.");
////			  Iterator<Plan>planIterator2 = plans2.iterator();
////			  while (planIterator2.hasNext())
////			  {
////				  System.out.println(planIterator2.next());
////			  }
//		  }
//		  catch (Exception ex) {
//			  ex.printStackTrace();
//			  return "User not found";
//		  }
//		  return "Success";
//	  }

}
