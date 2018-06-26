package model;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.DateTime;

class RecordTest {

	private Record record1;
	private Record record2;
	
	@BeforeEach
	void setUp() throws Exception {
		double receitaDiaria = 10;
		double despesaFatura = 10;
		double despesa = 10;
		double iva = 23;
		Calendar date = DateTime.now();
		
		record1 = new Record(receitaDiaria, despesaFatura, despesa, iva, date);
		record2 = new Record(receitaDiaria, despesaFatura, despesa, iva, date);
	}

	@Test
	void equals_ObjectsAreTheSameByDate_True() {
		assertTrue(record1.equals(record2));
	}

	@Test
	void equals_ObjectsAreNotTheSameDiffDates_False() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR,3);
		cal.set(Calendar.DAY_OF_WEEK,4);
		record2.setDate(cal);
		assertFalse(record1.equals(record2));
	}

	@Test
	void testCompareTo() {
		fail("Not yet implemented");
	}

}
