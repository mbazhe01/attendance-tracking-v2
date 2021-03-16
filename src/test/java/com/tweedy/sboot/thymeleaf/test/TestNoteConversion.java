package com.tweedy.sboot.thymeleaf.test;

import java.sql.Timestamp;
import java.text.ParseException;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tweedy.sboot.thymeleaf.entity.Note;
import com.tweedy.sboot.thymeleaf.entity.NoteTL;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestNoteConversion {

	public static void main(String[] args) throws ParseException {
		try {
			// String dateInString = "27/02/2016 12:15";
			// SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy hh:mm");
			Timestamp date = new Timestamp(System.currentTimeMillis());
			Note note = new Note(1, 4, date, "test note");
			System.out.println(note);
			System.out.println("Convert to NoteTL:");
			NoteTL noteTL = note.convertToNoteTL();
			System.out.println(noteTL);
			System.out.println("Convert to Note");
			// System.out.println(noteTL.convertToNote());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
