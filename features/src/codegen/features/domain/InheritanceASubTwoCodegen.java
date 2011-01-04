package features.domain;

import features.domain.queries.InheritanceASubTwoQueries;
import joist.domain.Shim;
import joist.domain.validation.rules.MaxLength;
import joist.domain.validation.rules.NotNull;

@SuppressWarnings("all")
public abstract class InheritanceASubTwoCodegen extends InheritanceABase {

    public static final InheritanceASubTwoQueries queries;
    private String two = null;

    static {
        Aliases.inheritanceASubTwo();
        queries = new InheritanceASubTwoQueries();
    }

    protected InheritanceASubTwoCodegen() {
        this.addExtraRules();
    }

    private void addExtraRules() {
        this.addRule(new NotNull<InheritanceASubTwo>(Shims.two));
        this.addRule(new MaxLength<InheritanceASubTwo>(Shims.two, 100));
    }

    public String getTwo() {
        return this.two;
    }

    public void setTwo(String two) {
        this.getChanged().record("two", this.two, two);
        this.two = two;
    }

    protected void defaultTwo(String two) {
        this.two = two;
    }

    public InheritanceASubTwoChanged getChanged() {
        if (this.changed == null) {
            this.changed = new InheritanceASubTwoChanged((InheritanceASubTwo) this);
        }
        return (InheritanceASubTwoChanged) this.changed;
    }

    @Override
    public void clearAssociations() {
        super.clearAssociations();
    }

    static class Shims {
        protected static final Shim<InheritanceASubTwo, String> two = new Shim<InheritanceASubTwo, String>() {
            public void set(InheritanceASubTwo instance, String two) {
                ((InheritanceASubTwoCodegen) instance).two = two;
            }
            public String get(InheritanceASubTwo instance) {
                return ((InheritanceASubTwoCodegen) instance).two;
            }
            public String getName() {
                return "two";
            }
        };
    }

    public static class InheritanceASubTwoChanged extends InheritanceABaseChanged {
        public InheritanceASubTwoChanged(InheritanceASubTwo instance) {
            super(instance);
        }
        public boolean hasTwo() {
            return this.contains("two");
        }
        public String getOriginalTwo() {
            return (java.lang.String) this.getOriginal("two");
        }
    }

}
