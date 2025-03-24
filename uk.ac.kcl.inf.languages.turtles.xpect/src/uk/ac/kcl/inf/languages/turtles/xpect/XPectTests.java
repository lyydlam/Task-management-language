package uk.ac.kcl.inf.languages.turtles.xpect;

import org.eclipse.xpect.runner.XpectRunner;
import org.eclipse.xpect.runner.XpectSuiteClasses;

import org.eclipse.xpect.xtext.lib.tests.GeneratorTest;
import org.eclipse.xpect.xtext.lib.tests.LinkingTest;
import org.eclipse.xpect.xtext.lib.tests.ResourceDescriptionTest;
import org.eclipse.xpect.xtext.lib.tests.ScopingTest;
import org.eclipse.xpect.xtext.lib.tests.ValidationTest;
import org.eclipse.xpect.xtext.lib.tests.XtextTests;
import org.junit.runner.RunWith;

@XpectSuiteClasses({
    LinkingTest.class, //check cross-reference--depend
    ResourceDescriptionTest.class, //describe resources
    ScopingTest.class, //check the scoping
    ValidationTest.class // check grammar
})

@RunWith(XpectRunner.class)
public class XPectTests extends XtextTests {
	

}
