package click.controls.form;

import org.exigencecorp.bindgen.Binding;

import click.util.HtmlWriter;

public class TextField extends AbstractField<TextField> {

    public TextField() {
    }

    public TextField(Binding<?> binding) {
        super(binding);
    }

    public void render(HtmlWriter w) {
        w.append("<input id={} name={} type={} value={}/>", this.getId(), this.getId(), "text", this.getBoundValue());
    }

    public TextField getThis() {
        return this;
    }

}
