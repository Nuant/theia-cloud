/********************************************************************************
 * Copyright (C) 2022 EclipseSource and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 ********************************************************************************/
package org.eclipse.theia.cloud.operator.handler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.eclipse.theia.cloud.common.k8s.resource.Session;

public interface TimeoutStrategy {
    String getName();

    boolean evaluate(String correlationId, Session session, Instant now, Integer limit);

    static class Inactivity implements TimeoutStrategy {
	@Override
	public String getName() {
	    return "INACTIVITY";
	}

	@Override
	public boolean evaluate(String correlationId, Session session, Instant now, Integer limit) {
	    long lastActivity = session.getSpec().getLastActivity();
	    Instant parse = Instant.ofEpochMilli(lastActivity);
	    long minutesSinceLastActivity = ChronoUnit.MINUTES.between(parse, now);
	    return minutesSinceLastActivity > limit;
	}

    }

    static class FixedTime implements TimeoutStrategy {
	@Override
	public String getName() {
	    return "FIXEDTIME";
	}

	@Override
	public boolean evaluate(String correlationId, Session session, Instant now, Integer limit) {
	    String creationTimestamp = session.getMetadata().getCreationTimestamp();
	    Instant parse = Instant.parse(creationTimestamp);
	    long minutesSinceCreation = ChronoUnit.MINUTES.between(parse, now);
	    return minutesSinceCreation > limit;
	}

    }
}
