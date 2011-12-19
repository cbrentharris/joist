package features.domain.queries;

import features.domain.CodeAColor;
import features.domain.CodeADomainObject;
import features.domain.CodeADomainObjectAlias;
import features.domain.CodeASize;
import java.util.List;
import joist.domain.AbstractQueries;
import joist.domain.orm.queries.Select;

public abstract class CodeADomainObjectQueriesCodegen extends AbstractQueries<CodeADomainObject> {

  public CodeADomainObjectQueriesCodegen() {
    super(CodeADomainObject.class);
  }

  public void delete(CodeADomainObject instance) {
    super.delete(instance);
  }

  public List<CodeADomainObject> findByCodeAColor(CodeAColor codeAColor) {
    CodeADomainObjectAlias cado0 = new CodeADomainObjectAlias("cado0");
    return Select.from(cado0).where(cado0.codeAColor.eq(codeAColor)).list();
  }

  public List<CodeADomainObject> findByCodeASize(CodeASize codeASize) {
    CodeADomainObjectAlias cado0 = new CodeADomainObjectAlias("cado0");
    return Select.from(cado0).where(cado0.codeASize.eq(codeASize)).list();
  }

}
