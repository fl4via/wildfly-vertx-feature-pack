/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package org.wildfly.extension.vertx;

import java.io.IOException;

/**
 * Subsystem test for schema version 2.0.0 (current).
 *
 * @author Flavia Rainone
 */
public class Subsystem200TestCase extends AbstractSubsystemTest {

    @Override
    protected String getSubsystemXml() throws IOException {
        return readResource("vertx-options-full.xml");
    }

    @Override
    protected String getSubsystemXsdPath() {
        return "schema/wildfly-vertx_2_0_0.xsd";
    }
}
