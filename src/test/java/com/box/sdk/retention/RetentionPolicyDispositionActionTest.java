package com.box.sdk.retention;

import com.box.sdk.UnitTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class RetentionPolicyDispositionActionTest {

	@Test
	@Category(UnitTest.class)
	public void rententionPolicyDispositionReturnsLowerCase() throws Exception {
		RetentionPolicyDispositionAction rp = RetentionPolicyDispositionAction.REMOVE_RETENTION;

		assertThat(rp.toString(), is("remove_retention"));
	}
}
