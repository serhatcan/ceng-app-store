/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import tr.edu.metu.ceng352.model.dbModel.SidebarCategory;
import tr.edu.metu.ceng352.repository.CategoryRepository;

import java.util.List;

/**
 * Created by Serhat CAN on 01.06.2015.
 */

@Controller
public class MainController {

    @Autowired
    CategoryRepository categoryRepository;

    @ModelAttribute("categories")
    public List<SidebarCategory> getAllCategories() {
        return categoryRepository.findSidebarCategories();
    }
}
