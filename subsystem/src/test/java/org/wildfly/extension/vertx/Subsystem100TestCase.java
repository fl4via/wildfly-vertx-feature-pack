/*
 * Copyright The WildFly Authors
 * SPDX-License-Identifier: Apache-2.0
 */
package org.wildfly.extension.vertx;

import org.jboss.as.subsystem.test.KernelServices;
import org.junit.Test;

import java.io.IOException;

import static org.jboss.as.controller.descriptions.ModelDescriptionConstants.SUBSYSTEM;
import static org.junit.Assert.assertTrue;

/**
 * Subsystem test for schema version 1.0.0 (legacy compatibility).
 * Tests that 1.0 configurations can be read and migrated to the current version.
 *
 * @author Flavia Rainone
 */
public class Subsystem100TestCase extends AbstractSubsystemTest {

    @Override
    protected String getSubsystemXml() throws IOException {
        return readResource("vertx-options-full-1_0_0.xml");
    }

    @Override
    protected String getSubsystemXsdPath() {
        return "schema/wildfly-vertx_1_0_0.xsd";
    }

    /**
     * Test that 1.0 configuration can be parsed and loaded correctly.
     * The configuration will be migrated to the current schema version (2.0).
     * (this needs to be changed if we decide to add transformers for 1.0.0)
     */
    @Test
    public void testSubsystemLegacyParsing() throws Exception {
        String subsystemXml = getSubsystemXml();
        KernelServices services = createKernelServicesBuilder(createAdditionalInitialization())
                .setSubsystemXml(subsystemXml)
                .build();

        // Verify the subsystem was installed and model is valid
        assertTrue("Failed to boot subsystem with 1.0 configuration", services.isSuccessfulBoot());
        assertTrue("Subsystem model not defined after loading 1.0 configuration", services.readWholeModel().get(SUBSYSTEM).get(VertxSubsystemExtension.SUBSYSTEM_NAME).isDefined());
    }

    /**
     * Override the standard subsystem test since we expect 1.0 to be migrated to 2.0.
     * We don't test round-trip XML equality for legacy versions.
     */
    @Test
    @Override
    public void testSubsystem() throws Exception {
        testSubsystemLegacyParsing();
        testSchema();
    }
}
