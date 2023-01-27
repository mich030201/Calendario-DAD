package dad.javafx.calendario.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static int firstDay(int anio, int mes) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anio, mes - 1, 1);
		int weekday = calendario.get(Calendar.DAY_OF_WEEK);
		return (weekday == 1) ? 7 : weekday - 1;
	}
	
	public static int lastDay(int anio, int mes) {
		LocalDate first = LocalDate.of(anio, mes, 1);
		return first.plusMonths(1).minusDays(1).getDayOfMonth();
	}
	
	public static Date newDate(int anio, int mes, int dia) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(anio, mes - 1, dia);
		return calendario.getTime();
	}
	
}
