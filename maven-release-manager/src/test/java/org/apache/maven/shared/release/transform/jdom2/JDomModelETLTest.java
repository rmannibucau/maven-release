package org.apache.maven.shared.release.transform.jdom2;

import org.apache.maven.model.Scm;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.release.ReleaseExecutionException;
import org.apache.maven.shared.release.config.ReleaseDescriptor;
import org.apache.maven.shared.release.config.ReleaseDescriptorBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JDomModelETLTest
{
    @Rule
    public final TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void emptyProps() throws IOException, ReleaseExecutionException
    {
        final File from = folder.newFile( "from.pom.xml" );
        Files.write( from.toPath(), ( "" +
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<project" +
                " xmlns=\"http://maven.apache.org/POM/4.0.0\"" +
                " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" +
                " xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "\n" +
                "  <groupId>org.test</groupId>\n" +
                "  <artifactId>test</artifactId>\n" +
                "  <version>1.0.0-SNAPSHOT</version>\n" +
                "  <properties>" +
                // here we want an empty prop (not a null one),
                // keep in mind interpolation does not lead to the same outcome, empty can be used as a prefix, not null
                "    <empty.prop></empty.prop>\n" +
                "  </properties>\n" +
                "</project>\n" ).getBytes( StandardCharsets.UTF_8 ) );
        final File to = folder.newFile( "to.pom.xml" );

        final MavenProject project = new MavenProject();
        project.setVersion( "1.0.0-SNAPSHOT" );

        final JDomModelETL etl = new JDomModelETL();
        etl.setReleaseDescriptor( descriptor() );
        etl.setProject( project );
        etl.extract( from );
        etl.transform();
        etl.load( to );
        assertEquals(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                        "  <modelVersion>4.0.0</modelVersion>\n" +
                        "\n" +
                        "  <groupId>org.test</groupId>\n" +
                        "  <artifactId>test</artifactId>\n" +
                        "  <version>1.0.0-SNAPSHOT</version>\n" +
                        "  <properties>    <empty.prop />\n" + // THIS IS THE WRONG PIECE
                        "  </properties>\n" +
                        "</project>\n",
                new String( Files.readAllBytes( to.toPath() ) ) );
    }

    private ReleaseDescriptor descriptor()
    {
        return new ReleaseDescriptor()
        {
            @Override
            public boolean isUpdateDependencies()
            {
                return false;
            }

            @Override
            public boolean isUseReleaseProfile()
            {
                return false;
            }

            @Override
            public boolean isAutoVersionSubmodules()
            {
                return false;
            }

            @Override
            public boolean isSnapshotReleasePluginAllowed()
            {
                return false;
            }

            @Override
            public boolean isCommitByProject()
            {
                return false;
            }

            @Override
            public boolean isBranchCreation()
            {
                return false;
            }

            @Override
            public boolean isUpdateBranchVersions()
            {
                return false;
            }

            @Override
            public boolean isUpdateWorkingCopyVersions()
            {
                return false;
            }

            @Override
            public boolean isSuppressCommitBeforeTagOrBranch()
            {
                return false;
            }

            @Override
            public boolean isAllowTimestampedSnapshots()
            {
                return false;
            }

            @Override
            public boolean isUpdateVersionsToSnapshot()
            {
                return false;
            }

            @Override
            public boolean isRemoteTagging()
            {
                return false;
            }

            @Override
            public boolean isScmSignTags()
            {
                return false;
            }

            @Override
            public boolean isLocalCheckout()
            {
                return false;
            }

            @Override
            public boolean isPushChanges()
            {
                return false;
            }

            @Override
            public String getWorkItem()
            {
                return null;
            }

            @Override
            public String getDefaultDevelopmentVersion()
            {
                return null;
            }

            @Override
            public String getScmRelativePathProjectDirectory()
            {
                return null;
            }

            @Override
            public String getCheckoutDirectory()
            {
                return null;
            }

            @Override
            public String getPerformGoals()
            {
                return null;
            }

            @Override
            public String getDefaultReleaseVersion()
            {
                return null;
            }

            @Override
            public String getScmReleasedPomRevision()
            {
                return null;
            }

            @Override
            public boolean isAddSchema()
            {
                return false;
            }

            @Override
            public boolean isGenerateReleasePoms()
            {
                return false;
            }

            @Override
            public boolean isInteractive()
            {
                return false;
            }

            @Override
            public boolean isScmUseEditMode()
            {
                return false;
            }

            @Override
            public List<String> getActivateProfiles()
            {
                return null;
            }

            @Override
            public String getCompletedPhase()
            {
                return null;
            }

            @Override
            public List<String> getCheckModificationExcludes()
            {
                return null;
            }

            @Override
            public String getAdditionalArguments()
            {
                return null;
            }

            @Override
            public String getPreparationGoals()
            {
                return null;
            }

            @Override
            public String getCompletionGoals()
            {
                return null;
            }

            @Override
            public String getPomFileName()
            {
                return null;
            }

            @Override
            public String getScmCommentPrefix()
            {
                return null;
            }

            @Override
            public String getScmReleaseCommitComment()
            {
                return null;
            }

            @Override
            public String getScmDevelopmentCommitComment()
            {
                return null;
            }

            @Override
            public String getScmBranchCommitComment()
            {
                return null;
            }

            @Override
            public String getScmRollbackCommitComment()
            {
                return null;
            }

            @Override
            public String getScmPrivateKeyPassPhrase()
            {
                return null;
            }

            @Override
            public String getScmPassword()
            {
                return null;
            }

            @Override
            public String getScmPrivateKey()
            {
                return null;
            }

            @Override
            public String getScmReleaseLabel()
            {
                return null;
            }

            @Override
            public String getScmTagBase()
            {
                return null;
            }

            @Override
            public String getScmBranchBase()
            {
                return null;
            }

            @Override
            public String getScmId()
            {
                return null;
            }

            @Override
            public String getScmSourceUrl()
            {
                return null;
            }

            @Override
            public String getScmUsername()
            {
                return null;
            }

            @Override
            public int getWaitBeforeTagging()
            {
                return 0;
            }

            @Override
            public String getWorkingDirectory()
            {
                return null;
            }

            @Override
            public String getScmTagNameFormat()
            {
                return null;
            }

            @Override
            public String getProjectNamingPolicyId()
            {
                return null;
            }

            @Override
            public String getProjectVersionPolicyId()
            {
                return null;
            }

            @Override
            public String getReleaseStrategyId()
            {
                return null;
            }

            @Override
            public String getDependencyOriginalVersion( String artifactKey )
            {
                return null;
            }

            @Override
            public String getDependencyReleaseVersion( String artifactKey )
            {
                return null;
            }

            @Override
            public String getDependencyDevelopmentVersion( String artifactKey )
            {
                return null;
            }

            @Override
            public String getProjectOriginalVersion( String projectKey )
            {
                return null;
            }

            @Override
            public String getProjectDevelopmentVersion( String projectKey )
            {
                return null;
            }

            @Override
            public String getProjectReleaseVersion( String key )
            {
                return null;
            }

            @Override
            public Scm getOriginalScmInfo( String projectKey )
            {
                return null;
            }

            @Override
            public void addDependencyOriginalVersion( String versionlessKey, String string )
            {

            }

            @Override
            public void addDependencyReleaseVersion( String versionlessKey,  String version )
            {

            }

            @Override
            public void addDependencyDevelopmentVersion( String versionlessKey, String version )
            {

            }

            @Override
            public void addReleaseVersion( String projectId,  String nextVersion )
            {

            }

            @Override
            public void addDevelopmentVersion( String projectId,  String nextVersion )
            {

            }

            @Override
            public void setScmReleaseLabel( String tag )
            {

            }

            @Override
            public void setScmReleasedPomRevision( String scmRevision )
            {

            }

            @Override
            public void setScmRelativePathProjectDirectory( String scmRelativePathProjectDirectory )
            {

            }

            @Override
            public void setScmSourceUrl( String scmUrl )
            {

            }

            @Override
            public String getAutoResolveSnapshots()
            {
                return null;
            }

            @Override
            public boolean isPinExternals()
            {
                return false;
            }
        };
    }
}
