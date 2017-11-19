package com.stackroute.datamunger.query;

import java.util.List;

import com.stackroute.datamunger.query.parser.QueryParameter;
import com.stackroute.datamunger.query.parser.Restriction;

//this class contains methods to evaluate expressions
public class Filter {
	/*
	 * the evaluateExpression() method of this class is responsible for evaluating
	 * the expressions mentioned in the query. It has to be noted that the process
	 * of evaluating expressions will be different for different data types. there
	 * are 6 operators that can exist within a query i.e. >=,<=,<,>,!=,= This method
	 * should be able to evaluate all of them.
	 * Note: while evaluating string expressions, please handle uppercase and lowercase
	 * 
	 */
	public boolean evaluateExpression(String operator, String firstInput, String secondInput, String dataType) {
		switch (operator) {
		case "=":
			if (equalTo(firstInput, secondInput, dataType))
				return true;
			else
				return false;
		case "!=":
			if (!equalTo(firstInput, secondInput, dataType))
				return true;
			else
				return false;
		case ">=":
			if (greaterThanOrEqualTo(firstInput, secondInput, dataType))
				return true;
			else
				return false;
		case "<=":
			if (lessThanOrEqualTo(firstInput, secondInput, dataType))
				return true;
			else
				return false;
		case ">":
			if (greaterThan(firstInput, secondInput, dataType))
				return true;
			else
				return false;
		case "<":
			if (lessThan(firstInput, secondInput, dataType))
				return true;
			else
				return false;
		}
		return false;
	}

	public double sum(double firstInput, String secondInput, String dataType) {
		switch (dataType) {
		case "java.lang.Integer":
			try {
				return (firstInput + Integer.parseInt(secondInput));
			} catch (Exception e) {
				return (firstInput + 0);
			}
		case "java.lang.Double":
			try {
				return firstInput + Double.parseDouble(secondInput);
			} catch (Exception e) {
				return (firstInput + 0);
			}
		default:
			throw new NumberFormatException("the selected column is a String or date");
		}
	}

	public boolean equalTo(String firstInput, String secondInput, String dataType) {
		switch (dataType) {
		case "java.lang.Integer":

		case "java.lang.Double":

		default:
			try {
				if (firstInput.equalsIgnoreCase(secondInput))
					return true;
				else
					return false;
			} catch (Exception e) {
				return false;
			}
		}
	}

	public boolean greaterThan(String firstInput, String secondInput, String dataType) {
		switch (dataType) {
		case "java.lang.Integer":

		case "java.lang.Double":
			try {
				if (Double.parseDouble(firstInput) > Double.parseDouble(secondInput))
					return true;
				else
					return false;
			} catch (Exception nfe) {
				return false;
			}
		default:
			try {
				if ((firstInput.compareToIgnoreCase(secondInput)) > 0)
					return true;
				else
					return false;
			} catch (Exception e) {
				return false;
			}
		}
	}

	public boolean greaterThanOrEqualTo(String firstInput, String secondInput, String dataType) {
		switch (dataType) {
		case "java.lang.Integer":

		case "java.lang.Double":
			try {
				if (Double.parseDouble(firstInput) >= Double.parseDouble(secondInput))
					return true;
				else
					return false;
			} catch (Exception nfe) {
				return false;
			}
		default:
			try {
				if ((firstInput.compareToIgnoreCase(secondInput)) >= 0)
					return true;
				else
					return false;
			} catch (Exception e) {
				return false;
			}
		}
	}

	public boolean lessThan(String firstInput, String secondInput, String dataType) {
		switch (dataType) {
		case "java.lang.Integer":

		case "java.lang.Double":
			try {
				if (Double.parseDouble(firstInput) < Double.parseDouble(secondInput))
					return true;
				else
					return false;
			} catch (Exception nfe) {
				return false;
			}
		default:
			try {
				if ((firstInput.compareToIgnoreCase(secondInput)) < 0)
					return true;
				else
					return false;
			} catch (Exception e) {
				return false;
			}
		}
	}

	public boolean lessThanOrEqualTo(String firstInput, String secondInput, String dataType) {
		switch (dataType) {
		case "java.lang.Integer":

		case "java.lang.Double":
			try {
				if (Double.parseDouble(firstInput) <= Double.parseDouble(secondInput))
					return true;
				else
					return false;
			} catch (Exception nfe) {
				return false;
			}
		default:
			try {
				if ((firstInput.compareToIgnoreCase(secondInput)) <= 0)
					return true;
				else
					return false;
			} catch (Exception e) {
				return false;
			}
		}
	}
	
	/** 
	 * This method return true if the result of all conditions is true
	 * Otherwise return false
	 * @param rowValues
	 * @param queryParameter
	 * @param dataTypeDef
	 * @param header
	 * @return
	 */
	
	public boolean isSelected(String[] rowValues,QueryParameter queryParameter,RowDataTypeDefinitions dataTypeDef, Header header)
	{
		boolean isSelected = true;
		/*
		 * if there are where condition(s) in the query, test the row fields against
		 * those conditions to check whether the selected row satisfies the conditions
		 */
		if (queryParameter.getRestrictions() != null) {
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
			// for (Restriction restriction : queryParameter.getRestrictions()) {

			List<Restriction> restrictions = queryParameter.getRestrictions();

			int restrictionsSize = restrictions.size();

			// get the first restriction and evaluate
			Restriction firstRestriction = restrictions.get(0);
			isSelected = evaluateExpression(firstRestriction.getCondition(),
					rowValues[header.get(firstRestriction.getPropertyName())],
					firstRestriction.getPropertyValue(),
					dataTypeDef.get(header.get(firstRestriction.getPropertyName())));

		
			//Check if there are more than one restrictions.
			if (restrictionsSize > 1) {
				// Evaluate the remaining restrictions
				Restriction restriction;
				for (int index = 1; index < restrictionsSize; index++) {

					/*
					 * check for multiple conditions in where clause for eg: where salary>20000 and
					 * city=Bangalore for eg: where salary>20000 or city=Bangalore and dept!=Sales
					 */
					restriction = restrictions.get(index);

					if (queryParameter.getLogicalOperators().get(index-1) != null) {
						if (queryParameter.getLogicalOperators().get(index - 1).equalsIgnoreCase("and")) {
							isSelected = isSelected && evaluateExpression(restriction.getCondition(),
									rowValues[header.get(restriction.getPropertyName())],
									restriction.getPropertyValue(),
									dataTypeDef.get(header.get(restriction.getPropertyName())));
						} else if (queryParameter.getLogicalOperators().get(index - 1).equalsIgnoreCase("or")) {
							if (isNextLogiclaOperatorIsAnd(queryParameter, index)) {
								boolean flag1 = evaluateExpression(restriction.getCondition(),
										rowValues[header.get(restriction.getPropertyName())],
										restriction.getPropertyValue(),
										dataTypeDef.get(header.get(restriction.getPropertyName())));

								// get next condition
								index++;
								restriction = restrictions.get(index);
								boolean flag2 = evaluateExpression(restriction.getCondition(),
										rowValues[header.get(restriction.getPropertyName())],
										restriction.getPropertyValue(),
										dataTypeDef.get(header.get(restriction.getPropertyName())));

								isSelected = isSelected || (flag1 && flag2);
							}

						}
					}
				}
			}
		}
		return isSelected;
	}
	
	private boolean isNextLogiclaOperatorIsAnd(QueryParameter queryParameter, int iteration) {
		String nextLogicalOperator = queryParameter.getLogicalOperators().get(iteration);
		if (nextLogicalOperator != null && nextLogicalOperator.equalsIgnoreCase("and")) {
			return true;
		}
		return false;
	}
}
