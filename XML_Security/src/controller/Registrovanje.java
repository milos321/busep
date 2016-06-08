package controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jaxb.Marshalling;
import security.SignEnveloped;
import util.FilePaths;
import util.FileWriterReader;
import util.Util;
import xquery.XMLWriter;
import jaxb.Korisnik;

/**
 * Servlet implementation class Registrovanje
 */
public class Registrovanje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrovanje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String repeat_password = request.getParameter("repeat_password");
		String ime = request.getParameter("ime");
		String prezime = request.getParameter("prezime");
		String email = request.getParameter("email");
		String certificate = request.getParameter("certificate");
		
	//	ServletContext context=getServletContext();
		

		if(password.equalsIgnoreCase(repeat_password)){
			    Korisnik kor = new Korisnik();
			    kor.setKorisnickoIme(username);
			    kor.setLozinka(password);
			    kor.setIme(ime);
			    kor.setPrezime(prezime);
			    kor.setUloga("gradjanin");
			    kor.setEmail(email);
			    
			    Integer rbr = FileWriterReader.read("rbr.txt");
			    
			    kor.setRbrPoruke(rbr);
			    rbr++;
			    FileWriterReader.write("rbr.txt", rbr);
			    
			    Date date = new Date();
			    kor.setTimeStamp(date.toString());
			    	Marshalling marsh = new Marshalling();
			    	try {
						marsh.test(kor);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	
			    if(new File(FilePaths.keystores+certificate+".jks").exists()){
			    SignEnveloped sign = new SignEnveloped();
			    sign.setIN_FILE(FilePaths.korisnici);
			    sign.setOUT_FILE(FilePaths.korisnici);
			    sign.setKEY_STORE_FILE(FilePaths.keystores+certificate+".jks");
			    sign.setName(certificate);
			    sign.setPass(certificate);
			    sign.testIt();
			    }
			    
			    XMLWriter.run(Util.loadProperties());
			   	RequestDispatcher disp = request.getRequestDispatcher("index.jsp");
				disp.forward(request, response);
			    
			    
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("korisnik_register.jsp");
			disp.forward(request, response);
		}
	}

}
