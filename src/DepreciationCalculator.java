
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author harini kumar
 *
 */
public class DepreciationCalculator {
	
	private int assetDepreciationRange = 0;
	private GregorianCalendar acquisitionDate;
	private double acquisitionCost = 0.0;
	
	/**
	 * @param assetDepreciationRange
	 * @param acquisitionDate
	 * @param acquisitionCost
	 */
	public DepreciationCalculator(int assetDepreciationRange, GregorianCalendar acquisitionDate, double acquisitionCost) {
		this.assetDepreciationRange = assetDepreciationRange;
		this.acquisitionDate = acquisitionDate;
		this.acquisitionCost = acquisitionCost;
	}
	
	
	/**
	 * Calculates the linear depreciation over a period of 5 years and also the Depreciation Rate
	 */
	
	public void calculateLinearDepreciation() {
		int year = acquisitionDate.get(Calendar.YEAR);
		//Calculates depreciation amount
		double deprecitaionCost = acquisitionCost/assetDepreciationRange;
		double residualBookValue = acquisitionCost;
		for(int i = 1; i <= assetDepreciationRange; i++) {
			//Calculates residual book value 
			residualBookValue -= deprecitaionCost;
			System.out.println(year++ + ":" + " | " + "Depreciation Amount : " + Math.round(deprecitaionCost * 100.0)/100.0 + " | " + "Residual Book Value: " + Math.round(residualBookValue * 100.0)/ 100.0);
		}
		//Calculates the Depreciation Rate
		double depreciationRatePerYear = (double) 1/assetDepreciationRange;
		double annualDepreciationExpense = depreciationRatePerYear * deprecitaionCost;
		System.out.println("Annual depreciation Expense : " + Math.round(annualDepreciationExpense * 100.0) / 100.0);
	}
	
	/**
	 * Calculates the Double-Declining Depreciation Rate over a period of 5 years
	 * Depreciation amount decreases at a rate of 20% if the acquisition date is before January 1, 2006
	 * Otherwise the amount decreases at rate of 30% if the acquisition date is on or after January 1, 2006
	 */
	public void calculateDegressiveDepreciation() {
		
		double doubleDecliningRate = 0.0;
		double depreciationExpense = 0.0;
		int year = acquisitionDate.get(Calendar.YEAR);
		GregorianCalendar date = new GregorianCalendar(2006, 0, 01);
		//Checks if acquisition date is lesser than January 1, 2006 
		if(acquisitionDate.before(date)) {
			doubleDecliningRate = (double) 1/assetDepreciationRange * 2;
			depreciationExpense = acquisitionCost;
			for(int i = 1; i <= assetDepreciationRange; i++) {
				depreciationExpense -= (doubleDecliningRate * depreciationExpense);
				System.out.println(year++ + ":" + " | " + "Depreciation Rate : " +  Math.round(doubleDecliningRate * 100) +"%" + " | " + "Depreciation Expense : " + Math.round(depreciationExpense * 100.0) / 100.0);
			}
		}
		
		//Checks if acquisition date is greater than or equal to January 1, 2006  
		else if(acquisitionDate.after(date) || acquisitionDate.equals(date)) {
			doubleDecliningRate = (double) 1/assetDepreciationRange * 3;
			depreciationExpense = acquisitionCost;
			for(int i = 1; i <= assetDepreciationRange; i++) {
				depreciationExpense -= (doubleDecliningRate * depreciationExpense);
				System.out.println(year++ + ":" + " | " + "Depreciation Rate : " +  Math.round(doubleDecliningRate * 100) +"%" + " | " + "Depreciation Expense : " + Math.round(depreciationExpense * 100.0) / 100.0);
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		@SuppressWarnings("deprecation")
		DepreciationCalculator depreciationCalculator = new DepreciationCalculator(5, new GregorianCalendar(2015, 0, 01), 1000.00);
		depreciationCalculator.calculateLinearDepreciation();
		depreciationCalculator.calculateDegressiveDepreciation();
	}

}
