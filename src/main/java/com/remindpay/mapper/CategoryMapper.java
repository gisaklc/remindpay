package com.remindpay.mapper;

import com.remindpay.dto.CategoryResponseDto;
import com.remindpay.model.Category;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "cdi")
public interface CategoryMapper {

    List<CategoryResponseDto> toDtoList(List<Category> categories);
}
