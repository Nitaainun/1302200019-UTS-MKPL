package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee extends DataPegawai{

	private enum NamaGender{
		Lakilaki,
		Perempuan
	}


	private DataPegawai pegawai;
	
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(DataPegawai pegawai, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
		
		pegawai = new DataPegawai();
		pegawai.employeeId = employeeId;
		pegawai.firstName = firstName;
		pegawai.lastName = lastName;
		pegawai.idNumber = idNumber;
		pegawai.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	
	public final int grade1 = 3000000;
	public final int grade2 = 5000000;
	public final int grade3 = 7000000;

	public void setMonthlySalary(int grade) {	
		if (grade == 1) {
			monthlySalary = grade1;
			if (isForeigner) {
				monthlySalary = (int) (grade1 * 1.5);
			}
		}else if (grade == 2) {
			monthlySalary = grade2;
			if (isForeigner) {
				monthlySalary = (int) (grade2 * 1.5);
			}
		}else if (grade == 3) {
			monthlySalary = grade3;
			if (isForeigner) {
				monthlySalary = (int) (grade3 * 1.5);
			}
		}
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}
	
	public int getAnnualIncomeTax() {
		
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == yearJoined) {
			monthWorkingInYear = date.getMonthValue() - monthJoined;
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	}
}
