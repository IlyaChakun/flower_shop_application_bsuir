package by.bsuir.repository.specification;

import by.bsuir.entity.product.flower.Flower;
import by.bsuir.entity.product.flower.FlowerType;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class FlowerSpecification {

    private FlowerSpecification() {

    }


    public static Specification<Flower> findAllByFlowerTypes(Set<FlowerType> flowerTypes) {
        return (Specification<Flower>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = buildPredicatesFlowerTypes(root, criteriaBuilder, flowerTypes);
            return
                    criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
        };
    }


    private static List<Predicate> buildPredicatesFlowerTypes(Root<Flower> root,
                                                              CriteriaBuilder criteriaBuilder,
                                                              Set<FlowerType> flowerTypes) {
        List<Predicate> predicates = new ArrayList<>();
        flowerTypes.forEach(flowerType -> predicates.add(
                buildFlowerTypeFlowerPredicate(root, criteriaBuilder, flowerType.getFlowerType())));
        return predicates;
    }

    private static Predicate buildFlowerTypeFlowerPredicate(Root<Flower> root,
                                                            CriteriaBuilder criteriaBuilder,
                                                            String flowerType) {
        Join<FlowerType, Flower> flowerTypeFlowerJoin = root.join("flowerType");
        return criteriaBuilder.equal(flowerTypeFlowerJoin.get("flowerType"), flowerType);
    }

}
