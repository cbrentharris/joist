package features.domain;

import features.domain.OneToOneBBar;
import features.domain.OneToOneBFoo;
import java.util.ArrayList;
import java.util.List;
import org.exigencecorp.domainobjects.queries.Alias;
import org.exigencecorp.domainobjects.queries.columns.AliasColumn;
import org.exigencecorp.domainobjects.queries.columns.ForeignKeyAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.IdAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.IntAliasColumn;
import org.exigencecorp.domainobjects.queries.columns.StringAliasColumn;

public class OneToOneBBarAlias extends Alias<OneToOneBBar> {

    private final List<AliasColumn<OneToOneBBar, ?, ?>> columns = new ArrayList<AliasColumn<OneToOneBBar, ?, ?>>();
    public final IdAliasColumn<OneToOneBBar> id = new IdAliasColumn<OneToOneBBar>(this, "id", OneToOneBBarCodegen.Shims.id);
    public final StringAliasColumn<OneToOneBBar> name = new StringAliasColumn<OneToOneBBar>(this, "name", OneToOneBBarCodegen.Shims.name);
    public final IntAliasColumn<OneToOneBBar> version = new IntAliasColumn<OneToOneBBar>(this, "version", OneToOneBBarCodegen.Shims.version);
    public final ForeignKeyAliasColumn<OneToOneBBar, OneToOneBFoo> oneToOneBFoo = new ForeignKeyAliasColumn<OneToOneBBar, OneToOneBFoo>(this, "one_to_one_b_foo_id", OneToOneBBarCodegen.Shims.oneToOneBFooId);

    public OneToOneBBarAlias(String alias) {
        super(OneToOneBBar.class, "one_to_one_b_bar", alias);
        this.columns.add(this.id);
        this.columns.add(this.name);
        this.columns.add(this.version);
        this.columns.add(this.oneToOneBFoo);
    }

    public List<AliasColumn<OneToOneBBar, ?, ?>> getColumns() {
        return this.columns;
    }

    public IdAliasColumn<OneToOneBBar> getIdColumn() {
        return this.id;
    }

    public IntAliasColumn<OneToOneBBar> getVersionColumn() {
        return this.version;
    }

    public IdAliasColumn<OneToOneBBar> getSubClassIdColumn() {
        return null;
    }

}