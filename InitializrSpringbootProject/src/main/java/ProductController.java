
import com.crud.demo.Product;
import com.crud.demo.ProductDAO;
import com.crud.demo.ProductDAOImpl;
import java.sql.SQLException;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author pipel
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductDAO productDAO;

    public ProductController() throws SQLException {
        this.productDAO = new ProductDAOImpl();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productDAO.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productDAO.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return productDAO.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productDAO.deleteProduct(id);
    }
}

