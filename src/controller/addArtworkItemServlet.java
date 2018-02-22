package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Artwork;

/**
 * Servlet implementation class addArtworkItemServlet
 */
@WebServlet("/addArtworkItemServlet")
public class addArtworkItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addArtworkItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response) throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// response.getWriter().append("Served at: ").append(request.getContextPath());
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String title = request.getParameter("title");
		String artistName = request.getParameter("artistName");
		String media = request.getParameter("media");
		String year = request.getParameter("year");
		String stringValue = request.getParameter("value");
		double value = Double.valueOf(stringValue);
		
//		System.out.println("in doPost - addArtworkItemServlet " + value);
//		System.out.println("artistname: " + artistName);
//		System.out.println("Title: " + title);
//		System.out.println("media: " + media);
//		System.out.println("year: " + year);
		
		Artwork li = new Artwork(title, artistName, media, year, value);
		ArtGalleryHelper dao = new ArtGalleryHelper();
		dao.insertItem(li);

		getServletContext().getRequestDispatcher("/addArtwork.html").forward(request, response);
		// doGet(request, response);
	}

}
