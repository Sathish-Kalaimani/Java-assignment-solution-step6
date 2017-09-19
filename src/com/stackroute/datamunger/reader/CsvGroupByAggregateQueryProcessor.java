package com.stackroute.datamunger.reader;

import java.util.HashMap;

import com.stackroute.datamunger.query.parser.QueryParameter;

/* this is the CsvGroupByAggregateQueryProcessor class used for evaluating queries with 
 * aggregate functions and group by clause*/
public class CsvGroupByAggregateQueryProcessor implements QueryProcessingEngine {
	/*
	 * This method will take QueryParameter object as a parameter which contains the
	 * parsed query and will process and populate the ResultSet
	 */
	public HashMap getResultSet(QueryParameter queryParameter) {
		
		
		/*
		 * initialize BufferedReader to read from the file which is mentioned in
		 * QueryParameter. Consider Handling Exception related to file reading.
		 */
		
		/*
		 * read the first line which contains the header. Please note that the headers
		 * can contain spaces in between them. For eg: city, winner
		 */
		
		/*
		 * read the next line which contains the first row of data. We are reading this
		 * line so that we can determine the data types of all the fields. Please note
		 * that ipl.csv file contains null value in the last column. If you do not
		 * consider this while splitting, this might cause exceptions later
		 */
		
		/*
		 * populate the header Map object from the header array. header map is having
		 * data type <String,Integer> to contain the header and it's index.
		 */
		
		/*
		 * We have read the first line of text already and kept it in an array. Now, we
		 * can populate the dataTypeDefinition Map object. dataTypeDefinition map is
		 * having data type <Integer,String> to contain the index of the field and it's
		 * data type. To find the dataType by the field value, we will use getDataType()
		 * method of DataTypeDefinitions class
		 */
		
		
		/*
		 * once we have the header and dataTypeDefinitions maps populated, we can start
		 * reading from the first line. We will read one line at a time, then check
		 * whether the field values satisfy the conditions mentioned in the query,if
		 * yes, then we will add it to the resultSet. Otherwise, we will continue to
		 * read the next line. We will continue this till we have read till the last
		 * line of the CSV file.
		 */
		
		/* reset the buffered reader so that it can start reading from the first line */
		
		/*
		 * skip the first line as it is already read earlier which contained the header
		 */
		
		/* read one line at a time from the CSV file till we have any lines left */
		
		/*
		 * once we have read one line, we will split it into a String Array. This array
		 * will continue all the fields of the row. Please note that fields might
		 * contain spaces in between. Also, few fields might be empty.
		 */
		
		/*
		 * if there are where condition(s) in the query, test the row fields against
		 * those conditions to check whether the selected row satifies the conditions
		 */
		
		
		/*
		 * from QueryParameter object, read one condition at a time and evaluate the
		 * same. For evaluating the conditions, we will use evaluateExpressions() method
		 * of Filter class. Please note that evaluation of expression will be done
		 * differently based on the data type of the field. In case the query is having
		 * multiple conditions, you need to evaluate the overall expression i.e. if we
		 * have OR operator between two conditions, then the row will be selected if any
		 * of the condition is satisfied. However, in case of AND operator, the row will
		 * be selected only if both of them are satisfied.
		 */
		
		/*
		 * check for multiple conditions in where clause for eg: where salary>20000 and
		 * city=Bangalore for eg: where salary>20000 or city=Bangalore and dept!=Sales
		 */
		
		
		/*
		 * if the overall condition expression evaluates to true, then we need to check
		 * for the existence for group by clause in the Query Parameter. 
		 * The dataSet generated after processing a group by clause is completely different from 
		 * a dataSet structure(which contains multiple rows of data). In case of queries containing 
		 * group by clause, the resultSet will contain multiple dataSets, each of which will be assigned 
		 * to the group by column value i.e. for all unique values of the group by column,
		 * there can be multiple rows associated with it.
		 * Hence, we will use GroupedDataSet<String,Object> to store the same and not DataSet<Long,Row>.
		 * Please note we will process queries containing one group by column only
		 * for this example.
		 */
		
		//return groupedDataSet object
		
		return null;
	}
	
	
	
	
	
}
