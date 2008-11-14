package features.domain;

import features.domain.ManyToManyBFooAlias;
import features.domain.ManyToManyBFooToBarAlias;
import features.domain.queries.ManyToManyBFooQueries;
import java.util.ArrayList;
import java.util.List;
import org.exigencecorp.domainobjects.AbstractDomainObject;
import org.exigencecorp.domainobjects.Id;
import org.exigencecorp.domainobjects.Shim;
import org.exigencecorp.domainobjects.orm.AliasRegistry;
import org.exigencecorp.domainobjects.orm.ForeignKeyListHolder;
import org.exigencecorp.domainobjects.uow.UoW;
import org.exigencecorp.domainobjects.validation.rules.MaxLength;
import org.exigencecorp.domainobjects.validation.rules.NotNull;
import org.exigencecorp.util.Copy;

abstract class ManyToManyBFooCodegen extends AbstractDomainObject {

    static {
        AliasRegistry.register(ManyToManyBFoo.class, new ManyToManyBFooAlias("a"));
    }

    public static final ManyToManyBFooQueries queries = new ManyToManyBFooQueries();
    private Id<ManyToManyBFoo> id = null;
    private String name = null;
    private Integer version = null;
    private static final ManyToManyBFooToBarAlias blueManyToManyBFooToBarsAlias = new ManyToManyBFooToBarAlias("a");
    private ForeignKeyListHolder<ManyToManyBFoo, ManyToManyBFooToBar> blueManyToManyBFooToBars = new ForeignKeyListHolder<ManyToManyBFoo, ManyToManyBFooToBar>((ManyToManyBFoo) this, blueManyToManyBFooToBarsAlias, blueManyToManyBFooToBarsAlias.blue);

    protected ManyToManyBFooCodegen() {
        this.addExtraRules();
    }

    private void addExtraRules() {
        this.addRule(new NotNull<ManyToManyBFoo>("name", Shims.name));
        this.addRule(new MaxLength<ManyToManyBFoo>("name", 100, Shims.name));
    }

    public Id<ManyToManyBFoo> getId() {
        return this.id;
    }

    public void setId(Id<ManyToManyBFoo> id) {
        this.recordIfChanged("id", this.id, id);
        this.id = id;
        if (UoW.isOpen()) {
            UoW.getIdentityMap().store(this);
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(java.lang.String name) {
        this.recordIfChanged("name", this.name, name);
        this.name = name;
    }

    public Integer getVersion() {
        return this.version;
    }

    public List<ManyToManyBFooToBar> getBlueManyToManyBFooToBars() {
        return this.blueManyToManyBFooToBars.get();
    }

    public void addBlueManyToManyBFooToBar(ManyToManyBFooToBar o) {
        o.setBlueWithoutPercolation((ManyToManyBFoo) this);
        this.addBlueManyToManyBFooToBarWithoutPercolation(o);
    }

    public void addBlueManyToManyBFooToBarWithoutPercolation(ManyToManyBFooToBar o) {
        this.recordIfChanged("blueManyToManyBFooToBars");
        this.blueManyToManyBFooToBars.add(o);
    }

    public void removeBlueManyToManyBFooToBar(ManyToManyBFooToBar o) {
        o.setBlueWithoutPercolation(null);
        this.removeBlueManyToManyBFooToBarWithoutPercolation(o);
    }

    public void removeBlueManyToManyBFooToBarWithoutPercolation(ManyToManyBFooToBar o) {
        this.recordIfChanged("blueManyToManyBFooToBars");
        this.blueManyToManyBFooToBars.remove(o);
    }

    public List<ManyToManyBBar> getGreens() {
        List<ManyToManyBBar> l = new ArrayList<ManyToManyBBar>();
        for (ManyToManyBFooToBar o : this.getBlueManyToManyBFooToBars()) {
            l.add(o.getGreen());
        }
        return l;
    }

    public void addGreen(ManyToManyBBar o) {
        ManyToManyBFooToBar a = new ManyToManyBFooToBar();
        a.setBlue((ManyToManyBFoo) this);
        a.setGreen(o);
    }

    public void removeGreen(ManyToManyBBar o) {
        for (ManyToManyBFooToBar a : Copy.shallow(this.getBlueManyToManyBFooToBars())) {
            if (a.getGreen().equals(o)) {
                a.setGreen(null);
                a.setBlue(null);
                UoW.delete(a);
            }
        }
    }

    public static class Shims {
        public static final Shim<ManyToManyBFoo, Id<ManyToManyBFoo>> id = new Shim<ManyToManyBFoo, Id<ManyToManyBFoo>>() {
            public void set(ManyToManyBFoo instance, Id<ManyToManyBFoo> id) {
                ((ManyToManyBFooCodegen) instance).id = id;
            }
            public Id<ManyToManyBFoo> get(ManyToManyBFoo instance) {
                return ((ManyToManyBFooCodegen) instance).id;
            }
        };
        public static final Shim<ManyToManyBFoo, java.lang.String> name = new Shim<ManyToManyBFoo, java.lang.String>() {
            public void set(ManyToManyBFoo instance, java.lang.String name) {
                ((ManyToManyBFooCodegen) instance).name = name;
            }
            public String get(ManyToManyBFoo instance) {
                return ((ManyToManyBFooCodegen) instance).name;
            }
        };
        public static final Shim<ManyToManyBFoo, java.lang.Integer> version = new Shim<ManyToManyBFoo, java.lang.Integer>() {
            public void set(ManyToManyBFoo instance, java.lang.Integer version) {
                ((ManyToManyBFooCodegen) instance).version = version;
            }
            public Integer get(ManyToManyBFoo instance) {
                return ((ManyToManyBFooCodegen) instance).version;
            }
        };
    }

}