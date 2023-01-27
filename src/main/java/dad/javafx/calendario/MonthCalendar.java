package dad.javafx.calendario;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dad.javafx.calendario.utils.DateUtils;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MonthCalendar extends GridPane implements Initializable {
	
	private static final DateFormat FORMATTER = new SimpleDateFormat("MMMM"); 

	// modelo
	private IntegerProperty anio = new SimpleIntegerProperty();
	private IntegerProperty mes = new SimpleIntegerProperty();
	
	// view
	@FXML private Label monthNameLabel; 
	@FXML private List<Label> daysLabelList;

	
	public MonthCalendar() {
		super();
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MonthCalendar.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		this.mes.addListener((o, ov, nv) -> onModelChanged(o, ov, nv));
		this.anio.addListener((o, ov, nv) -> onModelChanged(o, ov, nv));
	}

	private void onModelChanged(ObservableValue<? extends Number> o, Number ov, Number nv) {

		int first = DateUtils.firstDay(anio.get(), mes.get()) - 1;
		int last = DateUtils.lastDay(anio.get(), mes.get());
		for (int i = 0; i < first; i++) {
			daysLabelList.get(i).setText("");
			daysLabelList.get(i).getStyleClass().remove("today");
		}
		for (int i = first, j = 1; i < first + last; i++, j++) {
			daysLabelList.get(i).setText("" + j);
			if (LocalDate.of(anio.get(), mes.get(), j).equals(LocalDate.now())) {
				daysLabelList.get(i).getStyleClass().add("today");				
			} else {
				daysLabelList.get(i).getStyleClass().remove("today");				
			}
		}
		for (int i = first + last; i < daysLabelList.size(); i++) {
			daysLabelList.get(i).setText("");
			daysLabelList.get(i).getStyleClass().remove("today");
		}
		Date day = DateUtils.newDate(anio.get(), mes.get(), 1);
		monthNameLabel.setText(FORMATTER.format(day));
	}

	public IntegerProperty yearProperty() {
		return this.anio;
	}

	public int getYear() {
		return this.yearProperty().get();
	}

	public void setYear(final int year) {
		this.yearProperty().set(year);
	}

	public IntegerProperty monthProperty() {
		return this.mes;
	}

	public int getMonth() {
		return this.monthProperty().get();
	}

	public void setMonth(final int month) {
		this.monthProperty().set(month);
	}
}
