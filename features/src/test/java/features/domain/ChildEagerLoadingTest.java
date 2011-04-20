package features.domain;

import joist.jdbc.Jdbc;
import junit.framework.Assert;
import features.domain.builders.Builders;
import features.domain.builders.ParentBuilder;

public class ChildEagerLoadingTest extends AbstractFeaturesTest {

  private ParentBuilder p1;
  private ParentBuilder p2;

  public void setUp() throws Exception {
    super.setUp();
    this.p1 = Builders.aParent().name("p1");
    this.p2 = Builders.aParent().name("p2");
    this.commitAndReOpen();
  }

  public void testEagerLoadingIsEnabledByDefault() {
    // should probably not be enabled by default ... we'll see what happens
    Builders.aChild().name("c1").parent(this.p1);
    Builders.aChild().name("c2").parent(this.p2);
    this.commitAndReOpen();

    this.p1.get(); // reload each parent
    this.p2.get();
    Jdbc.resetStats();
    this.p1.get().getChilds(); // now reload each set of children
    this.p2.get().getChilds();
    Assert.assertEquals(1, Jdbc.numberOfQueries());
  }

  public void testEagerLoadingIsEnabledUsesOneQueryForAllChildren() {
    Builders.aChild().name("c1").parent(this.p1);
    Builders.aChild().name("c2").parent(this.p2);
    this.commitAndReOpen();

    this.p1.get(); // reload each parent
    this.p2.get();
    Jdbc.resetStats();
    this.p1.get().getChilds(); // now reload each set of children
    this.p2.get().getChilds();
    Assert.assertEquals(1, Jdbc.numberOfQueries());
  }

  public void testEagerLoadingIsEnabledAndRemembersIfAParentDoesNotHaveAnyChildren() {
    Builders.aChild().name("c1").parent(this.p1);
    // Builders.aChild().name("c2").parent(this.p2); no child for p2
    this.commitAndReOpen();

    this.p1.get(); // reload each parent
    this.p2.get();
    Jdbc.resetStats();
    this.p1.get().getChilds(); // now reload each set of children
    this.p2.get().getChilds();
    Assert.assertEquals(1, Jdbc.numberOfQueries());
  }

  public void testEagerLoadingIsEnabledAndReQueriesForNewlyLoadedParents() {
    Builders.aChild().name("c1").parent(this.p1);
    Builders.aChild().name("c2").parent(this.p2);
    this.commitAndReOpen();

    this.p1.get(); // reload only p1
    this.p1.get().getChilds();
    // now reload p2
    this.p2.get();
    // and ensure we can get its children
    Assert.assertEquals(1, this.p2.childs().size());
  }

}