/*
 * This file is part of Infinitest.
 *
 * Copyright (C) 2010
 * "Ben Rady" <benrady@gmail.com>,
 * "Rod Coffin" <rfciii@gmail.com>,
 * "Ryan Breidenbach" <ryan.breidenbach@gmail.com>, et al.
 *
 * Infinitest is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Infinitest is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Infinitest.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.infinitest.intellij.idea.language;

import static org.hamcrest.Matchers.*;
import static org.infinitest.testrunner.TestEvent.*;
import static org.junit.Assert.*;

import org.junit.*;

public class WhenFormattingTooltipText {
	@Test
	public void shouldIncludeExceptionType() {
		InfinitestGutterIconRenderer renderer = new InfinitestGutterIconRenderer(new InnerClassFriendlyTestEvent(methodFailed(null, "", new RuntimeException("BUG"))));

		assertThat(renderer.getTooltipText(), containsString("RuntimeException"));
	}

	@Test
	public void shouldIncludeMessage() {
		InfinitestGutterIconRenderer renderer = new InfinitestGutterIconRenderer(new InnerClassFriendlyTestEvent(methodFailed(null, "", new RuntimeException("BUG"))));

		assertThat(renderer.getTooltipText(), containsString("BUG"));
	}

    @Test
    public void shouldBeEquals() {
		InfinitestGutterIconRenderer firstRenderer = new InfinitestGutterIconRenderer(new InnerClassFriendlyTestEvent(methodFailed(null, "", new RuntimeException("BUG"))));
		InfinitestGutterIconRenderer secondRenderer = new InfinitestGutterIconRenderer(new InnerClassFriendlyTestEvent(methodFailed(null, "", new RuntimeException("BUG"))));

        assertThat(firstRenderer, equalTo(secondRenderer));
    }
}
