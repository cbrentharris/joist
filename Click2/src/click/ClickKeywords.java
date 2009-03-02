package click;

import click.controls.table.Table;

public class ClickKeywords {

    public static void redirect(String url) {
        throw new RedirectException(url);
    }

    public static <T> Table<T> table(String name) {
        return new Table<T>(name);
    }

}