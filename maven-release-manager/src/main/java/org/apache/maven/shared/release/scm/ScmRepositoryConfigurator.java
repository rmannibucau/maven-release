package org.apache.maven.shared.release.scm;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.scm.manager.NoSuchScmProviderException;
import org.apache.maven.scm.provider.ScmProvider;
import org.apache.maven.scm.repository.ScmRepository;
import org.apache.maven.scm.repository.ScmRepositoryException;
import org.apache.maven.settings.Settings;
import org.apache.maven.shared.release.config.ReleaseDescriptor;

/**
 * Configure an SCM repository using release configuration.
 *
 * @author <a href="mailto:brett@apache.org">Brett Porter</a>
 */
public interface ScmRepositoryConfigurator
{
    /**
     * Construct a configured SCM repository from a release configuration.
     *
     * @param releaseDescriptor the configuration to insert into the repository
     * @param settings          the settings.xml configuraiton
     * @return the repository created
     * @throws org.apache.maven.scm.repository.ScmRepositoryException     if it is not possible to create a suitable
     *         SCM repository
     * @throws org.apache.maven.scm.manager.NoSuchScmProviderException if the requested SCM provider is not available
     */
    ScmRepository getConfiguredRepository( ReleaseDescriptor releaseDescriptor, Settings settings )
        throws ScmRepositoryException, NoSuchScmProviderException;

    /**
     * Get the SCM provider used for the given SCM repository.
     *
     * @param repository the SCM repository
     * @return the SCM provider
     * @throws org.apache.maven.scm.manager.NoSuchScmProviderException if the requested SCM provider is not available
     */
    ScmProvider getRepositoryProvider( ScmRepository repository )
        throws NoSuchScmProviderException;

    /**
     * Construct a configured SCM repository from a release configuration with an overridden base SCM URL.
     *
     * @param url               the SCM URL to use instead of the one from the release descriptor
     * @param releaseDescriptor the configuration to insert into the repository
     * @param settings          the settings.xml configuraiton
     * @return the repository created
     * @throws org.apache.maven.scm.repository.ScmRepositoryException     if it is not possible to create a suitable
     *         SCM repository
     * @throws org.apache.maven.scm.manager.NoSuchScmProviderException if the requested SCM provider is not available
     */
    ScmRepository getConfiguredRepository( String url, ReleaseDescriptor releaseDescriptor, Settings settings )
        throws ScmRepositoryException, NoSuchScmProviderException;
}
