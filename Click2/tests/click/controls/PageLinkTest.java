package click.controls;

import junit.framework.Assert;
import bindgen.click.fakedomain.EmployeeBinding;
import click.AbstractPage;
import click.fakedomain.Employee;
import click.pages.AddModelPublicFieldPage;
import click.pages.HelloWorldPage;

public class PageLinkTest extends AbstractClickControlTest {

    public void testNoParameters() {
        PageLink p = new PageLink(HelloWorldPage.class);
        Assert.assertEquals("/helloWorld.htm", p.getHref());
    }

    public void testTextDefaultsToPageName() {
        PageLink p = new PageLink(HelloWorldPage.class);
        Assert.assertEquals("<a id=\"HelloWorld\" href=\"/helloWorld.htm\">Hello World</a>", this.render(p));
    }

    public void testTextOverride() {
        PageLink p = new PageLink(HelloWorldPage.class).text("click here");
        Assert.assertEquals("<a id=\"HelloWorld\" href=\"/helloWorld.htm\">click here</a>", this.render(p));
    }

    public void testOneStringParameter() {
        PageLink p = new PageLink(AddModelPublicFieldPage.class).param("bar");
        Assert.assertEquals("/addModelPublicField.htm?value=bar", p.getHref());
    }

    public void testTwoStringParametersMeansWeJustPickTheFirst() {
        PageLink p = new PageLink(TwoStringFieldsPage.class).param("bar");
        Assert.assertEquals("click/controls/pageLinkTest$TwoStringFields.htm?value1=bar", p.getHref());
    }

    public void testTwoStringParametersGetTheSecondWithTheExplicitName() {
        PageLink p = new PageLink(TwoStringFieldsPage.class);
        p.param("bar");
        p.param("value2", "baz");
        Assert.assertEquals("click/controls/pageLinkTest$TwoStringFields.htm?value1=bar&value2=baz", p.getHref());
    }

    public void testParameterGetsConvertedToAString() {
        PageLink p = new PageLink(EmployeePage.class).param(new Employee(2));
        Assert.assertEquals("click/controls/pageLinkTest$Employee.htm?employee=2", p.getHref());
    }

    public void testBoundParameterGetsConvertedToAString() {
        EmployeeBinding b = new EmployeeBinding(new Employee(2));
        PageLink p = new PageLink(EmployeePage.class).param(b);
        Assert.assertEquals("click/controls/pageLinkTest$Employee.htm?employee=2", p.getHref());
    }

    public void testBoundParameterGetsConvertedToAStringWithExplicitName() {
        EmployeeBinding b = new EmployeeBinding(new Employee(2));
        PageLink p = new PageLink(EmployeePage.class).param("employee", b);
        Assert.assertEquals("click/controls/pageLinkTest$Employee.htm?employee=2", p.getHref());
    }

    public static class TwoStringFieldsPage extends AbstractPage {
        public String value1;
        public String value2;
    }

    public static class EmployeePage extends AbstractPage {
        public Employee employee;
    }

}
