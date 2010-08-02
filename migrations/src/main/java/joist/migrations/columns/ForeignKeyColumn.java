package joist.migrations.columns;

import java.util.List;

import joist.migrations.MigrationKeywords;
import joist.util.Interpolate;
import joist.util.Wrap;

public class ForeignKeyColumn extends AbstractColumn<ForeignKeyColumn> {

    // We need something short (64 char max constraint names), different, and
    // that will change each time we apply a new column. nanos might also work.
    private static long hackyNextId = System.currentTimeMillis();
    private String otherTable;
    private String otherTableColumn;
    private Owner owner;

    private enum Owner {
        IsMe, IsThem, IsNeither
    };

    public ForeignKeyColumn(String otherTable) {
        super(otherTable + "_id", "int");
        this.otherTable = otherTable;
        this.otherTableColumn = "id";
        this.owner = Owner.IsNeither;
    }

    public ForeignKeyColumn(String columnName, String otherTable, String otherTableColumn) {
        super(columnName, "int");
        if (!columnName.endsWith("id")) {
            throw new RuntimeException("names of fk columns should end with id");
        }
        this.otherTable = otherTable;
        this.otherTableColumn = otherTableColumn;
        this.owner = Owner.IsNeither;
    }

    public ForeignKeyColumn ownerIsMe() {
        this.owner = Owner.IsMe;
        return this;
    }

    public ForeignKeyColumn ownerIsThem() {
        this.owner = Owner.IsThem;
        return this;
    }

    public ForeignKeyColumn ownerIsNeither() {
        this.owner = Owner.IsNeither;
        return this;
    }

    @Override
    public List<String> postInjectCommands() {
        List<String> sqls = super.postInjectCommands();

        String constraintName = Interpolate.string("c_{}_{}_fk",//
            (ForeignKeyColumn.hackyNextId++),
            this.owner.toString().toLowerCase());
        // ...why was this commented out for MySQL?
        String optionalCascade = (MigrationKeywords.db.isPg() && this.owner == ForeignKeyColumn.Owner.IsThem) ? " ON DELETE CASCADE" : "";
        String optionalDeferrable = (MigrationKeywords.db.isPg() ? " DEFERRABLE INITIALLY DEFERRED" : "");
        sqls.add(Interpolate.string(
            "ALTER TABLE {} ADD CONSTRAINT {} FOREIGN KEY ({}) REFERENCES {} ({}) {} {};",
            Wrap.quotes(this.getTableName()),
            constraintName,
            Wrap.quotes(this.getName()),
            Wrap.quotes(this.otherTable),
            Wrap.quotes(this.otherTableColumn),
            optionalCascade,
            optionalDeferrable));
        // ...why does MySQL not get indexes?
        if (MigrationKeywords.db.isPg()) {
            String indexName = this.getTableName() + "_" + this.getName() + "_idx";
            sqls.add(Interpolate.string(//
                "CREATE INDEX {} ON {} USING btree ({});",
                indexName,
                Wrap.quotes(this.getTableName()),
                Wrap.quotes(this.getName())));
        }

        return sqls;
    }
}
