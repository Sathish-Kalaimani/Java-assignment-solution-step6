package com.stackroute.datamunger.query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * implementation of DataTypeDefinitions class. This class contains a method getDataTypes() 
 * which will contain the logic for getting the datatype for a given field value. This
 * method will be called from QueryProcessors.   
 * In this assignment, we are going to use Regular Expression to find the 
 * appropriate data type of a field. 
 * Integers: should contain only digits without decimal point 
 * Double: should contain digits as well as decimal point 
 * Date: Dates can be written in many formats in the CSV file. 
 * However, in this assignment,we will test for the following date formats('dd/mm/yyyy',
 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
 */
public class DataTypeDefinitions {

	public static Object getDataType(String input) {
		
		//check for empty object
		
		
		// checking for Integer
		
		
		// checking for floating point numbers
		
		
		// checking for date format dd/mm/yyyy
		
		
		// checking for date format mm/dd/yyyy
		
		
		// checking for date format dd-mon-yy
		
		
		// checking for date format dd-mon-yyyy
		
		
		// checking for date format dd-month-yy
		
		
		// checking for date format dd-month-yyyy
		
		return null;
	}
	
	
}
