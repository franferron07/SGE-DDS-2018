package HibernateVersion;

import static org.junit.Assert.*;

import org.junit.Test;

public class HibernateVersionTest {

	@Test
	public void test() {
		System.out.println(org.hibernate.Version.getVersionString());
	}

}
