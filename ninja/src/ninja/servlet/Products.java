package ninja.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ninja.models.Product;
import ninja.repositories.ProductRepository;
import ninja.repositories.ProductRepositoryImpl;

@SuppressWarnings("serial")
@WebServlet("/products")
public class Products extends HttpServlet {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getMethod().toLowerCase().equals("post")) {
			Product product = (Product) request.getAttribute("product");
			if (request.getAttribute("_method").equals("delete")) {
				deleteProduct(product);
			} else if ((boolean) request.getAttribute("edit")) {
				updateProduct(product);
			} else {
				storeProduct(product);
			}
		} else if (request.getPathTranslated().endsWith("new")) {
			newProduct(request, response);
		} else if (request.getPathTranslated().endsWith("edit")) {
			editProduct(request, response);
		} else {
			showProducts(request, response);
		}
	}

	/**
	 * Show all the products on the screen
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getProducts(request);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/product/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * Open the page to create a new Product
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void newProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Product product = new Product();
		request.setAttribute("product", product);
		request.setAttribute("edit", false);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/product/newProduct.jsp");
		rd.forward(request, response);
	}

	/**
	 * Open the page to edit the Product
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void editProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getProduct(request, ((Product) request.getAttribute("product")).getId());
		request.setAttribute("edit", true);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/product/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * Retrieve all the products from the database and add them to the {request}
	 * 
	 * @param request
	 */
	protected void getProducts(HttpServletRequest request) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ProductRepository cr = new ProductRepositoryImpl(em);
			List<Product> products = cr.findAll();

			em.close();
			emf.close();
			request.setAttribute("productList", products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve the specified Product from the database by its Id and add it to
	 * the {request}
	 * 
	 * @param request
	 * @param id
	 */
	protected void getProduct(HttpServletRequest request, long id) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ProductRepository cr = new ProductRepositoryImpl(em);
			Product product = cr.find(id);

			em.close();
			emf.close();
			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Store the Product on the database
	 * 
	 * @param product
	 */
	protected void storeProduct(Product product) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ProductRepository cr = new ProductRepositoryImpl(em);
			cr.create(product);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the Product on the database
	 * 
	 * @param product
	 */
	protected void updateProduct(Product product) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ProductRepository cr = new ProductRepositoryImpl(em);
			cr.update(product);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the Product from the database
	 * 
	 * @param product
	 */
	protected void deleteProduct(Product product) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ProductRepository cr = new ProductRepositoryImpl(em);
			cr.destroy(product);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
