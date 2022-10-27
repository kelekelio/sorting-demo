package com.grzegorznowakowski.sortingdemo.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class CriteriaBuilderHelper {

    public static Predicate[] toArray(Collection<Predicate> predicates) {
        return predicates.toArray(new Predicate[0]);
    }

    public static <Z, X> void addLike(From<Z, X> root,
                                      CriteriaBuilder cb,
                                      List<Predicate> predicates,
                                      String attribute,
                                      String value) {

        if (ObjectUtils.isNotEmpty(value)) {
            predicates.add(caseInsensitiveLike(cb, root.get(attribute), value));
        }
    }

    public static Predicate caseInsensitiveLike(CriteriaBuilder cb, Path<String> path, String value) {
        return cb.like(cb.lower(path), '%' + value.toLowerCase() + '%');
    }
}
