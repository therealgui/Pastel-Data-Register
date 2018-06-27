package model;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecordTest {

	private Record record1;
	private Record record2;
	
	@BeforeEach
	void setUp() throws Exception {
		double receitaDiaria = 10;
		double despesaFatura = 10;
		double despesa = 10;
		double iva = 23;
		LocalDate date = LocalDate.of(2018, Month.MARCH, 1);
		record1 = new Record(receitaDiaria, despesaFatura, despesa, iva, date);
		record2 = new Record(receitaDiaria, despesaFatura, despesa, iva, date);
	}

	@Test
	void equals_ObjectsAreTheSameByDate_True() {
		assertTrue(record1.equals(record2));
	}

	@Test
	void equals_ObjectsAreNotTheSameDiffDates_False() {
		LocalDate date = LocalDate.of(2018, Month.FEBRUARY, 1);
		record2.setDate(date);
		assertFalse(record1.equals(record2));
	}

	@Test
	void equals_ObjectsEqualSameReference_True(){
		Record record3 = record1;
		assertTrue(record1.equals(record3));
	}

	@Test
	void compareTo_SameDate_True() {
		assertTrue(record1.compareTo(record2) == 0);
	}

	@Test
	void compareTo_GreaterDate_True(){
		LocalDate date = record2.getDate();
		date = date.minusMonths(1);
		record2.setDate(date);
		assertTrue(record1.compareTo(record2) == 1);
	}

	@Test
	void compareTo_LesserDate_True(){
		LocalDate date = record2.getDate();
		date = date.plusMonths(1);
		record2.setDate(date);
		assertTrue(record1.compareTo(record2) == -1);
	}

}
