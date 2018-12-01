package fechaTest;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Test;

public class parseDate {

	@Test
	public void test() {
		System.out.println("fecha a parsear");
	    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println( LocalDateTime.parse("2020-12-03T10:15:30"));
	}

}
