package cucumber_java_pet.custom_type;

import cucumber_java_pet.domain_objects.Product;
import io.cucumber.java.ParameterType;

public class CustomParameterType {

    @ParameterType(".*")
    public Product product(String name){
        //return new Product(name);
        return new Product(name.replace("\"", ""));
    }
}
