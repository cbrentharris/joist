package features.domain;

import java.util.ArrayList;
import java.util.List;

import joist.domain.AbstractChanged;
import joist.domain.AbstractDomainObject;
import joist.domain.Changed;
import joist.domain.Shim;
import joist.domain.orm.ForeignKeyListHolder;
import joist.domain.uow.UoW;
import joist.domain.validation.rules.MaxLength;
import joist.domain.validation.rules.NotNull;
import joist.util.Copy;
import features.domain.queries.ManyToManyBBarQueries;

@SuppressWarnings("all")
public abstract class ManyToManyBBarCodegen extends AbstractDomainObject {

  public static final ManyToManyBBarQueries queries;
  private Long id = null;
  private String name = null;
  private Long version = null;
  private ForeignKeyListHolder<ManyToManyBBar, ManyToManyBFooToBar> greenManyToManyBFooToBars = new ForeignKeyListHolder<ManyToManyBBar, ManyToManyBFooToBar>((ManyToManyBBar) this, Aliases.manyToManyBFooToBar(), Aliases.manyToManyBFooToBar().green);
  protected Changed changed;

  static {
    Aliases.manyToManyBBar();
    queries = new ManyToManyBBarQueries();
  }

  protected ManyToManyBBarCodegen() {
    this.addExtraRules();
  }

  private void addExtraRules() {
    this.addRule(new NotNull<ManyToManyBBar>(Shims.name));
    this.addRule(new MaxLength<ManyToManyBBar>(Shims.name, 100));
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.getChanged().record("id", this.id, id);
    this.id = id;
    if (UoW.isOpen()) {
      UoW.getIdentityMap().store(this);
    }
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.getChanged().record("name", this.name, name);
    this.name = name;
  }

  protected void defaultName(String name) {
    this.name = name;
  }

  public Long getVersion() {
    return this.version;
  }

  public List<ManyToManyBFooToBar> getGreenManyToManyBFooToBars() {
    return this.greenManyToManyBFooToBars.get();
  }

  public void setGreenManyToManyBFooToBars(List<ManyToManyBFooToBar> greenManyToManyBFooToBars) {
    for (ManyToManyBFooToBar o : Copy.list(this.getGreenManyToManyBFooToBars())) {
      this.removeGreenManyToManyBFooToBar(o);
    }
    for (ManyToManyBFooToBar o : greenManyToManyBFooToBars) {
      this.addGreenManyToManyBFooToBar(o);
    }
  }

  public void addGreenManyToManyBFooToBar(ManyToManyBFooToBar o) {
    o.setGreenWithoutPercolation((ManyToManyBBar) this);
    this.addGreenManyToManyBFooToBarWithoutPercolation(o);
  }

  public void removeGreenManyToManyBFooToBar(ManyToManyBFooToBar o) {
    o.setGreenWithoutPercolation(null);
    this.removeGreenManyToManyBFooToBarWithoutPercolation(o);
  }

  protected void addGreenManyToManyBFooToBarWithoutPercolation(ManyToManyBFooToBar o) {
    this.getChanged().record("greenManyToManyBFooToBars");
    this.greenManyToManyBFooToBars.add(o);
  }

  protected void removeGreenManyToManyBFooToBarWithoutPercolation(ManyToManyBFooToBar o) {
    this.getChanged().record("greenManyToManyBFooToBars");
    this.greenManyToManyBFooToBars.remove(o);
  }

  public List<ManyToManyBFoo> getBlues() {
    List<ManyToManyBFoo> l = new ArrayList<ManyToManyBFoo>();
    for (ManyToManyBFooToBar o : this.getGreenManyToManyBFooToBars()) {
      l.add(o.getBlue());
    }
    return l;
  }

  public void setBlues(List<ManyToManyBFoo> blues) {
    for (ManyToManyBFoo o : Copy.list(this.getBlues())) {
      this.removeBlue(o);
    }
    for (ManyToManyBFoo o : blues) {
      this.addBlue(o);
    }
  }

  public void addBlue(ManyToManyBFoo o) {
    ManyToManyBFooToBar a = new ManyToManyBFooToBar();
    a.setGreen((ManyToManyBBar) this);
    a.setBlue(o);
  }

  public void removeBlue(ManyToManyBFoo o) {
    for (ManyToManyBFooToBar a : Copy.list(this.getGreenManyToManyBFooToBars())) {
      if (a.getBlue().equals(o)) {
        a.setBlue(null);
        a.setGreen(null);
        if (UoW.isOpen()) {
          UoW.delete(a);
        }
      }
    }
  }

  public ManyToManyBBarChanged getChanged() {
    if (this.changed == null) {
      this.changed = new ManyToManyBBarChanged((ManyToManyBBar) this);
    }
    return (ManyToManyBBarChanged) this.changed;
  }

  @Override
  public void clearAssociations() {
    super.clearAssociations();
    for (ManyToManyBFooToBar o : Copy.list(this.getGreenManyToManyBFooToBars())) {
      o.setGreenWithoutPercolation(null);
    }
  }

  static class Shims {
    protected static final Shim<ManyToManyBBar, Long> id = new Shim<ManyToManyBBar, Long>() {
      public void set(ManyToManyBBar instance, Long id) {
        ((ManyToManyBBarCodegen) instance).id = id;
      }
      public Long get(ManyToManyBBar instance) {
        return ((ManyToManyBBarCodegen) instance).id;
      }
      public String getName() {
        return "id";
      }
    };
    protected static final Shim<ManyToManyBBar, String> name = new Shim<ManyToManyBBar, String>() {
      public void set(ManyToManyBBar instance, String name) {
        ((ManyToManyBBarCodegen) instance).name = name;
      }
      public String get(ManyToManyBBar instance) {
        return ((ManyToManyBBarCodegen) instance).name;
      }
      public String getName() {
        return "name";
      }
    };
    protected static final Shim<ManyToManyBBar, Long> version = new Shim<ManyToManyBBar, Long>() {
      public void set(ManyToManyBBar instance, Long version) {
        ((ManyToManyBBarCodegen) instance).version = version;
      }
      public Long get(ManyToManyBBar instance) {
        return ((ManyToManyBBarCodegen) instance).version;
      }
      public String getName() {
        return "version";
      }
    };
  }

  public static class ManyToManyBBarChanged extends AbstractChanged {
    public ManyToManyBBarChanged(ManyToManyBBar instance) {
      super(instance);
    }
    public boolean hasId() {
      return this.contains("id");
    }
    public Long getOriginalId() {
      return (Long) this.getOriginal("id");
    }
    public boolean hasName() {
      return this.contains("name");
    }
    public String getOriginalName() {
      return (java.lang.String) this.getOriginal("name");
    }
    public boolean hasVersion() {
      return this.contains("version");
    }
    public Long getOriginalVersion() {
      return (Long) this.getOriginal("version");
    }
    public boolean hasGreenManyToManyBFooToBars() {
      return this.contains("greenManyToManyBFooToBars");
    }
  }

}
