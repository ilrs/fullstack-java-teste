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

import ninja.models.Client;
import ninja.repositories.ClientRepository;
import ninja.repositories.ClientRepositoryImpl;

@SuppressWarnings("serial")
@WebServlet("/clients/*")
public class Clients extends HttpServlet {

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
			Client client = (Client) request.getAttribute("client");
			if (request.getAttribute("_method").equals("delete")) {
				deleteClient(client);
			} else if ((boolean) request.getAttribute("edit")) {
				updateClient(client);
			} else {
				storeClient(client);
			}
		} else if (request.getPathTranslated().endsWith("new")) {
			newClient(request, response);
		} else if (request.getPathTranslated().endsWith("edit")) {
			editClient(request, response);
		} else {
			showClients(request, response);
		}
	}

	/**
	 * Show all the clients on the screen
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void showClients(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getClients(request);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/client/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * Open the page to create a new Client
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void newClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Client client = new Client();
		request.setAttribute("client", client);
		request.setAttribute("edit", false);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/client/newClient.jsp");
		rd.forward(request, response);
	}

	/**
	 * Open the page to edit the Client
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void editClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		getClient(request, ((Client) request.getAttribute("client")).getId());
		request.setAttribute("edit", true);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/client/edit.jsp");
		rd.forward(request, response);
	}

	/**
	 * Retrieve all the clients from the database and add them to the {request}
	 * 
	 * @param request
	 */
	protected void getClients(HttpServletRequest request) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ClientRepository cr = new ClientRepositoryImpl(em);
			List<Client> clients = cr.findAll();

			em.close();
			emf.close();
			request.setAttribute("clientList", clients);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve the specified Client from the database by its Id and add it to
	 * the {request}
	 * 
	 * @param request
	 * @param id
	 */
	protected void getClient(HttpServletRequest request, long id) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ClientRepository cr = new ClientRepositoryImpl(em);
			Client client = cr.find(id);

			em.close();
			emf.close();
			request.setAttribute("client", client);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Store the Client on the database
	 * 
	 * @param client
	 */
	protected void storeClient(Client client) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ClientRepository cr = new ClientRepositoryImpl(em);
			cr.create(client);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update the Client on the database
	 * 
	 * @param client
	 */
	protected void updateClient(Client client) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ClientRepository cr = new ClientRepositoryImpl(em);
			cr.update(client);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Delete the Client from the database
	 * 
	 * @param client
	 */
	protected void deleteClient(Client client) {
		try {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
			EntityManager em = emf.createEntityManager();

			ClientRepository cr = new ClientRepositoryImpl(em);
			cr.destroy(client);

			em.close();
			emf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
