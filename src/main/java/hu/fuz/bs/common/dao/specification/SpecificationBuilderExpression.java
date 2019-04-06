package hu.fuz.bs.common.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.function.Supplier;

public class SpecificationBuilderExpression<T> {

  private Specification<T> specification;

  protected SpecificationBuilderExpression(Specification<T> specification) {
    this.specification = specification;
  }

  SpecificationBuilderExpression(boolean addIfTrue, Specification<T> specification) {
    if(addIfTrue){
      this.specification = specification;
    }
  }

  /**
   * nem kell a kiértékelést végrehajtani a hívás pillanatában, elég akkor, ja a feltétel igaz
   * @param addIfTrue
   * @param supplier
   * @return
   */
  public SpecificationBuilderExpression<T> and(boolean addIfTrue, Supplier<Specification<T>> supplier) {
    if(addIfTrue){
      return this.and(true,supplier.get());
    }else {
      return new SpecificationBuilderExpression<>(this.specification);
    }
  }

  public SpecificationBuilderExpression<T> and(boolean addIfTrue, Specification<T> specification) {
    if(addIfTrue){
      if(this.specification != null){
        this.specification = this.specification.and(specification);
      }else{
        this.specification = specification;
      }
    }
    return new SpecificationBuilderExpression<>(this.specification);
  }

  public Specification<T> build() {
    return this.specification;
  }
}
