package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.Mydao;
import dto.Customer;
//this is to map the url to this class(should be same as action - case sensitive)
@WebServlet("/signup")
//this is to receive image we need to use this-encytype
@MultipartConfig 
//this is make class as servlet class to 
public class Customer_Signup extends HttpServlet {
 @Override
 //when there is form and we want data to be secured so doPost
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// logic to receive values from end
	String firstname=req.getParameter("firstname");
	String lastname=req.getParameter("lastname");
	long mobile_num=Long.parseLong(req.getParameter("Mobileno"));
	String email=req.getParameter("email");
	String password=req.getParameter("password");
	String gender=req.getParameter("Gender");
	String country=req.getParameter("select");
	LocalDate dob=LocalDate.parse(req.getParameter("DOB"));
	int age=Period.between(dob, LocalDate.now()).getYears();
	//Logic to receive image and convert  to Byte[]
	 Part pic = req.getPart("Picture");
	 byte[] picture=null;
	 picture=new byte[pic.getInputStream().available()];
	 pic.getInputStream().read(picture);
	 
	 Mydao dao=new Mydao();
	 
	 //Logic to verify email and mobile number is repeated
	 if(dao.fetchByEmail(email)==null && dao.fetchByMobile(mobile_num)==null){
	 Customer c=new Customer();
	 c.setFirstname(firstname);
	 c.setLastname(lastname);
	 c.setMobileno(mobile_num);
	 c.setEmail(email);
	 c.setPassword(password);
	 c.setGender(gender);
	 c.setCountry(country);
	 c.setDob(dob);
	 c.setAge(age);
	 c.setPicture(picture);
//Persisting
	dao.save(c) ;
	resp.getWriter().print("<h1 style='color: green'> Account Created Successfully </h1>");
	req.getRequestDispatcher("login.html").include(req,resp);
	 }
 
	 else {
		 resp.getWriter().print("<h1 style='color: red'> Email and Mobile should be Unique </h1>");
		 req.getRequestDispatcher("Signup.html").include(req, resp);
	 }
}
}
