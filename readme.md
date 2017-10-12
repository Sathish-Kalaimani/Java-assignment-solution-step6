## Seed code - Boilerplate for step 6 - Database Engine Assignment

### Problem Statement

In our previous assignment step 5, we had filtered the CSV file based on to multiple where condition and selected fields.

In this assignment step, we will try to filter the data from CSV file with much more complex queries. 
  Our query is expected to contain **aggregate functions, group by, order by, aggregate group by.**
  Finally store the filtered data in JSON file.

For Example: 

1. Query: **select city,winner,team1,team2 from data/ipl.csv where city='Bangalore' order by winner**
   
    Expected Output in JSON file: 
        
        {
          "1": {
            "winner": "",
            "city": "Bangalore",
            "team1": "Royal Challengers Bangalore",
            "team2": "Rajasthan Royals"
          },
          "2": {
            "winner": "",
            "city": "Bangalore",
            "team1": "Delhi Daredevils",
            "team2": "Royal Challengers Bangalore"
          },..........
            ..........
        } 

2. Query: **select city,sum(win_by_runs) from data/ipl.csv group by city**

    Expected Output in JSON file: 
   
        {
          "Bangalore": 1202.0,
          "Chandigarh": 536.0,
          "Delhi": 662.0,
          "Mumbai": 1058.0,
          "Kolkata": 479.0,
          ..........
          ..........
        }

3. Query: **select city,count(*) from data/ipl.csv group by city**

    Expected Output in JSON file:
   
        {
          "Bangalore": 58,
          "Chandigarh": 42,
          "Delhi": 53,
          "Mumbai": 77,
           ...........
           ........... 
        }

4. Query: **select count(city), sum(win_by_runs), min(season),max(win_by_wickets) from data/ipl.csv**

    Expected Output in JSON file:
   
        {
          "count(city)": "570",
          "sum(win_by_runs)": "7914.0",
          "min(season)": "2008",
          "max(win_by_wickets)": "10"
        }


### Expected solution

A JSON file containing the filtered result set.

### Following are the broad tasks:

- Read the query from the user
- parse the query
- forward the object of query parameter to CsvQueryProcessor
- filter out rows basis on the conditions mentioned in the where clause
- write the result set into a JSON file

### Project structure

The folders and files you see in this repositories, is how it is expected to be in projects, which are submitted for automated evaluation by Hobbes

    Project
	|
	├── data 			                    // If project needs any data file, it can be found here/placed here, if data is huge they can be mounted, no need put it in your repository
	|
	├── com.stackroute.datamunger	            // all your java file will be stored in this package
	|	    └── DataMunger.java	                        // This is the main file, all your logic is written in this file only   
	├── com.stackroute.datamunger.query
	|		└── DataSet.java 		                    // This class will be acting as the DataSet containing multiple rows
	|		└── DataTypeDefinitions.java                // This class contains methods to find the column data types
	|		└── Filter.java 		                    // This class contains methods to evaluate expressions
	|       └── GenericComparator.java                  // This class contains the implementation of the custom comparator which can work for all data types.
	|		└── Header.java                             // This class implements the getHeader method to return a Header object which contains a String array for containing headers.
	|		└── Query.java                              // This class selects the appropriate processor based on the type of query
	|		└── Row.java                                // This class contains the row object as ColumnName/Value 
	|		└── RowDataTypeDefinitions.java             // This class will be used to store the column data types as columnIndex/DataType
	├── com.stackroute.datamunger.query.parser
	|		└── AggregateFunction.java                  // This class is used to store Aggregate Function
	|		└── QueryParameter.java                     // This class contains the parameters and accessor/mutator methods of QueryParameter
	|		└── QueryParser.java                        // This class will parse the queryString and return an object of QueryParameter class
	|		└── Restriction.java	                    // This class is for storing Restriction object
	├── com.stackroute.datamunger.reader
	|		└── CsvAggregateQueryProcessor.java         // This is the CsvAggregateQueryProcessor class used for evaluating queries with aggregate functions without group by clause
	|		└── CsvGroupByAggregateQueryProcessor.java  // This is the CsvGroupByAggregateQueryProcessor class used for evaluating queries with aggregate functions and group by clause
	|		└── CsvGroupByQueryProcessor.java           // This is the CsvGroupByQueryProcessor class used for evaluating queries without aggregate functions but with group by clause
	|		└── CsvQueryProcessor.java                  // This class is used to read data from CSV file
	|		└── QueryProcessingEngine.java              // This is an interface with getResultset() in it.
	├── com.stackroute.datamunger.test
	|	    └── DataMungerTest.java                     // all your test cases are written using JUnit, these test cases can be run by selecting Run As -> JUnit Test 
	├── com.stackroute.datamunger.writer
	|		└── JsonWriter.java                         // This class writes the result in a JSON file
	|
	├── .classpath			                            // This file is generated automatically while creating the project in eclipse
	├── .hobbes   			                    // Hobbes specific config options, such as type of evaluation schema, type of tech stack etc., Have saved a default values for convenience
	├── .project			                    // This is automatically generated by eclipse, if this file is removed your eclipse will not recognize this as your eclipse project. 
	├── pom.xml 			                    // This is a default file generated by maven, if this file is removed your project will not get recognised in hobbes.
	└── PROBLEM.md  		                    // This files describes the problem of the assignment/project, you can provide as much as information and clarification you want about the project in this file

> PS: All lint rule files are by default copied during the evaluation process, however if need to be customizing, you should copy from this repo and modify in your project repo


#### To use this as a boilerplate for your new project, you can follow these steps

1. Clone the base boilerplate in the folder **assignment-solution-step6** of your local machine
     
    `git clone https://gitlab-wd.stackroute.in/stack_java_datamunging/step6-boilerplate.git assignment-solution-step6`

2. Navigate to assignment-solution-step6 folder

    `cd assignment-solution-step6`

3. Remove its remote or original reference

     `git remote rm origin`

4. Create a new repo in gitlab named `assignment-solution-step6` as private repo

5. Add your new repository reference as remote

     `git remote add origin https://gitlab-wd.stackroute.in/{{yourusername}}/assignment-solution-step6.git`

     **Note: {{yourusername}} should be replaced by your username from gitlab**

5. Check the status of your repo 
     
     `git status`

6. Use the following command to update the index using the current content found in the working tree, to prepare the content staged for the next commit.

     `git add .`
 
7. Commit and Push the project to git

     `git commit -a -m "Initial commit | or place your comments according to your need"`

     `git push -u origin master`

8. Check on the git repo online, if the files have been pushed

### Important instructions for Participants
> - We expect you to write the assignment on your own by following through the guidelines, learning plan, and the practice exercises
> - The code must not be plagirized, the mentors will randomly pick the submissions and may ask you to explain the solution
> - The code must be properly indented, code structure maintained as per the boilerplate and properly commented
> - Follow through the problem statement shared with you

### Further Instructions on Release

*** Release 0.1.0 ***

- Right click on the Assignment select Run As -> Java Application to run your Assignment.
- Right click on the Assignment select Run As -> JUnit Test to run your Assignment.