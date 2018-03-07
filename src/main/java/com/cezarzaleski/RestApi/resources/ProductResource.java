package com.cezarzaleski.RestApi.resources;

import com.cezarzaleski.RestApi.models.Product;
import com.cezarzaleski.RestApi.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "API REST - Model Product")
@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Recupera todos os produtos no banco")
    @GetMapping(produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> findAll() {
        List<Product> list = this.productService.findAll();
        return new ResponseEntity<List>(list, HttpStatus.OK);
    }

    @ApiOperation(value = "Recupera um produto no banco")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
        Product product = this.productService.find(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @ApiOperation(value = "Cria um novo produto")
    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
        if (!errors.hasErrors()) {
            Product productCreated = this.productService.create(product);
            return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
        }
        return ResponseEntity
                .badRequest()
                .body(
                        errors.getAllErrors()
                                .stream()
                                .map(msg -> msg.getDefaultMessage())
                                .collect(Collectors.joining(","))
                );
    }

    @ApiOperation(value = "Atualiza os dados do produto")
    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(value = "id") Long id, @RequestBody Product product, Errors errors) {
        if (!errors.hasErrors()) {
            Product productUpdated = this.productService.update(id, product);
            return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
        }
        return ResponseEntity
                .badRequest()
                .body(
                        errors.getAllErrors()
                                .stream()
                                .map(msg -> msg.getDefaultMessage())
                                .collect(Collectors.joining(","))
                );
    }

    @ApiOperation(value = "Deleta um produto pelo id")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        this.productService.delete(id);
    }
}
