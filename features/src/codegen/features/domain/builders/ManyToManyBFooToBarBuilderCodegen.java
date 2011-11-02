package features.domain.builders;

import joist.domain.builders.AbstractBuilder;
import features.domain.ManyToManyBBar;
import features.domain.ManyToManyBFoo;
import features.domain.ManyToManyBFooToBar;

@SuppressWarnings("all")
public abstract class ManyToManyBFooToBarBuilderCodegen extends AbstractBuilder<ManyToManyBFooToBar> {

  public ManyToManyBFooToBarBuilderCodegen(ManyToManyBFooToBar instance) {
    super(instance);
  }

  public Long id() {
    return get().getId();
  }

  public ManyToManyBFooToBarBuilder id(Long id) {
    get().setId(id);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooBuilder blue() {
    if (get().getBlue() == null) {
      return null;
    }
    return Builders.existing(get().getBlue());
  }

  public ManyToManyBFooToBarBuilder blue(ManyToManyBFoo blue) {
    get().setBlue(blue);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBFoo blue) {
    get().setBlue(blue);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder blue(ManyToManyBFooBuilder blue) {
    get().setBlue(blue.get());
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBFooBuilder blue) {
    get().setBlue(blue.get());
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBBarBuilder green() {
    if (get().getGreen() == null) {
      return null;
    }
    return Builders.existing(get().getGreen());
  }

  public ManyToManyBFooToBarBuilder green(ManyToManyBBar green) {
    get().setGreen(green);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBBar green) {
    get().setGreen(green);
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder green(ManyToManyBBarBuilder green) {
    get().setGreen(green.get());
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBarBuilder with(ManyToManyBBarBuilder green) {
    get().setGreen(green.get());
    return (ManyToManyBFooToBarBuilder) this;
  }

  public ManyToManyBFooToBar get() {
    return (features.domain.ManyToManyBFooToBar) super.get();
  }

}
