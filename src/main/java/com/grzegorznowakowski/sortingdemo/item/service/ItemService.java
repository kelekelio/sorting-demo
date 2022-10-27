package com.grzegorznowakowski.sortingdemo.item.service;

import com.grzegorznowakowski.sortingdemo.item.dto.ItemFilters;
import com.grzegorznowakowski.sortingdemo.item.entity.Item;
import com.grzegorznowakowski.sortingdemo.item.entity.Item_;
import com.grzegorznowakowski.sortingdemo.item.repository.ItemRepository;
import com.grzegorznowakowski.sortingdemo.translation.entity.Translation;
import com.grzegorznowakowski.sortingdemo.translation.entity.Translation_;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.grzegorznowakowski.sortingdemo.utils.CriteriaBuilderHelper.addLike;
import static com.grzegorznowakowski.sortingdemo.utils.CriteriaBuilderHelper.toArray;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Page<Item> findAll(ItemFilters itemFilters, Pageable pageable) {
        Specification<Item> specification = getItemSpecification(itemFilters,pageable);
        var x = itemRepository.findAll(specification, pageable);
        return x;
    }

    private Specification<Item> getItemSpecification(ItemFilters itemFilters, Pageable pageable) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();

//            Join<Item, Translation> translationJoin = root.join(Item_.translation, JoinType.LEFT);

            Fetch<Item, Translation> translationFetch = root.fetch(Item_.translation, JoinType.LEFT);
            addLike(root, cb, predicates, Item_.NAME, itemFilters.getName());
//            var orders = pageable.getSort().get().collect(Collectors.toList());

//            query.groupBy(translationJoin);

//            query.orderBy(cb.desc(translationJoin.get("name")));

            return cb.and(toArray(predicates));
        };
    }

    public Item findById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Item with ID %s not found.", id)));
    }
}
