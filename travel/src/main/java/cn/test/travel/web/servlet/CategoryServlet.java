package cn.test.travel.web.servlet;

import cn.test.travel.domain.Category;
import cn.test.travel.service.CategoryService;
import cn.test.travel.service.Impl.CategoryServiceImpl;

import javax.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    private CategoryService service=new CategoryServiceImpl();
    public void findAll(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, java.io.IOException {
        List<Category> list = service.findAll();
        writeValues(list,response);
    }

}