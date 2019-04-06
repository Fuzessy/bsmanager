package hu.fuz.bs.common.dao.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationBuilder {

//  public static <T> Specification<T> and(List<Specification<T>> filters) {
//    Specification<T> spec = null;
//    for (Specification<T> s : filters) {
//      if (spec == null) {
//        spec = s;
//      } else {
//        spec = spec.and(s);
//      }
//    }
//    return spec;
//  }

  public static <T> SpecificationBuilderExpression<T> where(boolean addIfNotNull, Specification<T> specification) {
    return new SpecificationBuilderExpression<T>(addIfNotNull, specification);
  }

  public static <T> SpecificationBuilderExpression<T> where(Specification<T> specification) {
    return new SpecificationBuilderExpression<T>(specification);
  }
}
