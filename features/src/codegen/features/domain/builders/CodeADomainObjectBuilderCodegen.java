package features.domain.builders;

import features.domain.CodeAColor;
import features.domain.CodeADomainObject;
import features.domain.CodeASize;
import joist.domain.builders.AbstractBuilder;

@SuppressWarnings("all")
public abstract class CodeADomainObjectBuilderCodegen extends AbstractBuilder<CodeADomainObject> {

    public CodeADomainObjectBuilderCodegen(CodeADomainObject instance) {
        super(instance);
    }

    public CodeADomainObjectBuilder id(Integer id) {
        get().setId(id);
        return (CodeADomainObjectBuilder) this;
    }

    public CodeADomainObjectBuilder name(String name) {
        get().setName(name);
        return (CodeADomainObjectBuilder) this;
    }

    public CodeADomainObjectBuilder codeAColor(CodeAColor codeAColor) {
        get().setCodeAColor(codeAColor);
        return (CodeADomainObjectBuilder) this;
    }

    public CodeADomainObjectBuilder codeASize(CodeASize codeASize) {
        get().setCodeASize(codeASize);
        return (CodeADomainObjectBuilder) this;
    }

    public CodeADomainObject get() {
        return (features.domain.CodeADomainObject) super.get();
    }

}