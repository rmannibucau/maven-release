package org.apache.maven.shared.release.stubs;

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

import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.maven.shared.release.policy.PolicyException;
import org.apache.maven.shared.release.policy.naming.NamingPolicy;
import org.apache.maven.shared.release.policy.naming.NamingPolicyRequest;
import org.apache.maven.shared.release.policy.naming.NamingPolicyResult;

/**
 * A NamingPolicy stub, always return STUB as name
 * @author Robert Scholte
 *
 */
@Singleton
@Named( "stub" )
public class NamingPolicyStub implements NamingPolicy
{
    @Override
    public NamingPolicyResult getName( NamingPolicyRequest request )
        throws PolicyException
    {
        return new NamingPolicyResult().setName( "STUB" );
    }
}
