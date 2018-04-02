package org.jboss.test.arquillian.ce.eap7cd;

import org.jboss.arquillian.ce.api.OpenShiftResource;
import org.jboss.arquillian.ce.api.RoleBinding;
import org.jboss.arquillian.ce.api.RoleBindings;
import org.jboss.arquillian.ce.api.Template;
import org.jboss.arquillian.ce.api.TemplateParameter;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.test.arquillian.ce.eap.common.EapClusteringTestBase;
import org.junit.runner.RunWith;

/**
 * @author Jonh Wendell
 */
@RunWith(Arquillian.class)
@Template(url = "https://raw.githubusercontent.com/${template.repository:jboss-openshift}/application-templates/${template.branch:master}/eap/eap71-basic-s2i.json", parameters = {
        @TemplateParameter(name = "SOURCE_REPOSITORY_URL", value = "https://github.com/jboss-openshift/openshift-examples"),
        @TemplateParameter(name = "SOURCE_REPOSITORY_REF", value = "master"),
        @TemplateParameter(name = "CONTEXT_DIR", value = "eap-tests/cluster1") })
@OpenShiftResource("https://raw.githubusercontent.com/${template.repository:jboss-openshift}/application-templates/${template.branch:master}/secrets/eap7-app-secret.json")
@RoleBindings({
        @RoleBinding(roleRefName = "view", userName = "system:serviceaccount:${kubernetes.namespace}:default"),
        @RoleBinding(roleRefName = "view", userName = "system:serviceaccount:${kubernetes.namespace}:eap7-service-account") })
public class Eap71ClusteringTest extends EapClusteringTestBase {
}
