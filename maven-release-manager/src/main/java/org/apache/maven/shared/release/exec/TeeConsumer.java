package org.apache.maven.shared.release.exec;

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

import java.io.PrintStream;

import org.codehaus.plexus.util.cli.StreamConsumer;

/**
 * Consumer that both funnels to System.out/err, and stores in an internal buffer.
 *
 * @author <a href="mailto:brett@apache.org">Brett Porter</a>
 */
public class TeeConsumer
        implements StreamConsumer
{
    private final PrintStream stream;

    /**
     * @noinspection StringBufferField
     */
    private final StringBuffer content = new StringBuffer();

    private static final String LS = System.getProperty( "line.separator" );

    private final String indent;

    /**
     * <p>Constructor for TeeConsumer.</p>
     *
     * @param stream a {@link java.io.PrintStream} object
     */
    public TeeConsumer( PrintStream stream )
    {
        this( stream, "    " );
    }

    /**
     * <p>Constructor for TeeConsumer.</p>
     *
     * @param stream a {@link java.io.PrintStream} object
     * @param indent a {@link java.lang.String} object
     */
    public TeeConsumer( PrintStream stream, String indent )
    {
        this.stream = stream;
        this.indent = indent;
    }

    @Override
    public void consumeLine( String line )
    {
        stream.println( indent + line );

        content.append( line );
        content.append( LS );
    }

    /**
     * <p>Getter for the field <code>content</code>.</p>
     *
     * @return a {@link java.lang.String} object
     */
    public String getContent()
    {
        return content.toString();
    }

    @Override
    public String toString()
    {
        return getContent();
    }
}
