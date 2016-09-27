package com.box.sdk.retention;

import com.box.sdk.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RetentionPolicyTypeTest {

	@Test
	@Category(UnitTest.class)
	public void rententionPolicyTypeReturnsLowerCaseForIndefinite() throws Exception {
		RetentionPolicyType type = RetentionPolicyType.INDEFINITE;

		assertThat(type.toString(), is("indefinite"));
	}

	@Test
		@Category(UnitTest.class)
		public void rententionPolicyTypeReturnsLowerCaseForFinite() throws Exception {
			RetentionPolicyType type = RetentionPolicyType.FINITE;

			assertThat(type.toString(), is("finite"));
		}
}
