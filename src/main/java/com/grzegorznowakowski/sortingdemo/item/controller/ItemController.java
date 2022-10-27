package com.grzegorznowakowski.sortingdemo.item.controller;

import com.grzegorznowakowski.sortingdemo.item.dto.ItemFilters;
import com.grzegorznowakowski.sortingdemo.item.entity.Item;
import com.grzegorznowakowski.sortingdemo.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping()
    public Page<Item> getAllItems(@Valid ItemFilters itemFilters, @PageableDefault(size = 20) Pageable pageable) {
        log.info("All items requested");
        return itemService.findAll(itemFilters, pageable);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable("id") Long id) {
        log.info("Item with ID {} requested", id);
        return itemService.findById(id);
    }
}
