package features.domain;

import features.domain.mappers.ChildAlias;
import features.domain.mappers.ParentAlias;
import org.exigencecorp.domainobjects.AbstractDomainObject;
import org.exigencecorp.domainobjects.Id;
import org.exigencecorp.domainobjects.Shim;
import org.exigencecorp.domainobjects.queries.Alias;
import org.exigencecorp.domainobjects.queries.Select;
import org.exigencecorp.domainobjects.uow.UoW;

public abstract class ChildCodegen extends AbstractDomainObject {

    private Id<Child> id = null;
    private String name = null;
    private Integer version = null;
    private Parent parent = null;
    private Integer parentId = null;

    public Alias<? extends Child> newAlias(String alias) {
        return new ChildAlias(alias);
    }

    public Id<Child> getId() {
        return this.id;
    }

    public void setId(Id<Child> id) {
        this.recordIfChanged("id", this.id, id);
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.recordIfChanged("name", this.name, name);
        this.name = name;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Parent getParent() {
        if (this.parent == null && this.parentId != null && UoW.isOpen()) {
            ParentAlias a = new ParentAlias("a");
            this.parent = Select.from(a).where(a.id.equals(this.parentId)).unique();
        }
        return this.parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public static class Shims {
        public static final Shim<Child, Id<Child>> id = new Shim<Child, Id<Child>>() {
            public void set(Child instance, Id<Child> id) {
                ((ChildCodegen) instance).id = id;
            }
            public Id<Child> get(Child instance) {
                return ((ChildCodegen) instance).id;
            }
        };
        public static final Shim<Child, String> name = new Shim<Child, String>() {
            public void set(Child instance, String name) {
                ((ChildCodegen) instance).name = name;
            }
            public String get(Child instance) {
                return ((ChildCodegen) instance).name;
            }
        };
        public static final Shim<Child, Integer> version = new Shim<Child, Integer>() {
            public void set(Child instance, Integer version) {
                ((ChildCodegen) instance).version = version;
            }
            public Integer get(Child instance) {
                return ((ChildCodegen) instance).version;
            }
        };
        public static final Shim<Child, Integer> parentId = new Shim<Child, Integer>() {
            public void set(Child instance, Integer parentId) {
                ((ChildCodegen) instance).parentId = parentId;
            }
            public Integer get(Child instance) {
                ChildCodegen instanceCodegen = instance;
                if (instanceCodegen.parent != null) {
                    return instanceCodegen.parent.getId().intValue();
                }
                return ((ChildCodegen) instance).parentId;
            }
        };
    }

}
