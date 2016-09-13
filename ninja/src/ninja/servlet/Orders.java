package ninja.servlet;

import java.io.IOException;
import java.util.Date;
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

import ninja.models.Order;
import ninja.models.Product;
import ninja.repositories.OrderRepository;
import ninja.repositories.OrderRepositoryImpl;
import ninja.repositories.ProductRepository;
import ninja.repositories.ProductRepositoryImpl;

@SuppressWarnings("serial")
@WebServlet("/orders")
public class Orders extends HttpServlet {

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
			Order order = (Order) request.getAttribute("order");
			if (request.getAttribute("_method").equals("delete")) {
				deleteOrder(order);
			} else if ((boolean) request.getAttribute("edit")) {
				updateOrder(order);
			} else {
				storeOrder(order);
			}
		} else if (request.getPathTranslated().endsWith("new")) {
			newOrder(request, response);
		} else if (request.getPathTranslated().endsWith("edit")) {
			editOrder(request, response);
		} else {
			showOrders(request, response);
		}
	}

	/**
	 * Show all the orders on the screen
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showOrders(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getOrders(request);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/order/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * Open the page to create a new Order
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void newOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Order order = new Order();
		request.setAttribute("order", order);
		request.setAttribute("edit", false);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/order/newOrder.jsp");
		rd.forward(request, response);
	}

	/**
	 * Open the page to edit the Order
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void editOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getOrder(request, ((Order) request.getAttribute("order")).getId());
		request.setAttribute("edit", true);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/order/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * Retrieve all the orders from the database and add them to the {request}
	 * 
	 * @param request
	 */
	protected void getOrders(HttpServletRequest request) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			OrderRepository cr = new OrderRepositoryImpl(em);
			List<Order> orders = cr.findAll();

			em.close();
			emf.close();
			request.setAttribute("orderList", orders);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve the specified Order from the database by its Id and add it to
	 * the {request}
	 * 
	 * @param request
	 * @param id
	 */
	protected void getOrder(HttpServletRequest request, long id) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			OrderRepository cr = new OrderRepositoryImpl(em);
			Order order = cr.find(id);

			em.close();
			emf.close();
			request.setAttribute("order", order);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Store the Order on the database
	 * 
	 * @param order
	 */
	protected void storeOrder(Order order) {
		try {
			order.setEmissionDate(new Date());
			setOrderTotalValue(order);
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			OrderRepository cr = new OrderRepositoryImpl(em);
			cr.create(order);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the Order on the database
	 * 
	 * @param order
	 */
	protected void updateOrder(Order order) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			OrderRepository cr = new OrderRepositoryImpl(em);
			setOrderTotalValue(order);
			cr.update(order);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set the total value of the order and update the product quantity in stock
	 * 
	 * @param order
	 */
	protected void setOrderTotalValue(Order order) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();
			ProductRepository cr = new ProductRepositoryImpl(em);

			String[] products = order.getProducts().split(",");
			Double totalValue = 0.0;

			for (int i = 0; i < products.length; i++) {
				Product product = cr.find(Long.parseLong(products[i]));
				if (product != null && product.getQuantity() > 0) {
					product.setQuantity(product.getQuantity() - 1);
					totalValue += product.getUnitValue();
					cr.update(product);
				}
			}

			order.setTotalValue(totalValue);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the Order from the database
	 * 
	 * @param order
	 */
	protected void deleteOrder(Order order) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			OrderRepository cr = new OrderRepositoryImpl(em);
			cr.destroy(order);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
