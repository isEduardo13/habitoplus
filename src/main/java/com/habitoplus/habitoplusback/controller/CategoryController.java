package com.habitoplus.habitoplusback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habitoplus.habitoplusback.model.Category;
import com.habitoplus.habitoplusback.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Category")
@RequestMapping("categories")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET })
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found categories", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Category.class)))
            }),
            @ApiResponse(responseCode = "204", description = "No categories found"),
            @ApiResponse(responseCode = "400", description = "Bad request", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content
            })
    })
    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get category by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found category", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Category.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid category ID", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "404", description = "Category not found", content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = {
                    @Content
            })
    })
    @GetMapping("{idCategory}")
    public ResponseEntity<?> getByCategoryId(@PathVariable Integer idCategory) {
        Category category = service.getByCategoryId(idCategory);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }
    
}