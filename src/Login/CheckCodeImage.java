package Login;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckCodeImage
 */
public class CheckCodeImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 120;
	private static final int HEIGHT = 30;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		setBackGround(g);
		setBorder(g);
		drawRandomLine(g);
		
		String random = drawRandomNum((Graphics2D) g, "nl");
		
		request.getSession().setAttribute("checkcode", random);
		response.setContentType("image/jpeg");
		
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		ImageIO.write(bi, "jpg", response.getOutputStream());
	}
	
	public void setBackGround(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	public void setBorder(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawLine(1, 1, WIDTH-2, HEIGHT-2);
	}
	public void drawRandomLine(Graphics g) {
		g.setColor(Color.GREEN);
		for (int i = 0; i < 5; i++) {
			int x1 = new Random().nextInt(WIDTH);
			int y1 = new Random().nextInt(HEIGHT);
			int x2 = new Random().nextInt(WIDTH);
			int y2 = new Random().nextInt(HEIGHT);
			g.drawLine(x1, y1, x2, y2);
		}
	}
	public String drawRandomNum(Graphics2D g, String choose) {
		g.setColor(Color.BLUE);
		g.setFont(new Font(null, Font.BOLD, 20));
		String baseNumLetter = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return creatRandomChar(g, baseNumLetter);
	}
	
	public String creatRandomChar(Graphics2D g, String baseChar) {
		StringBuffer sb = new StringBuffer();
		int x = 5;
		String ch = "";
		for (int i = 0; i < 4; i++) {
			int degree = new Random().nextInt() % 30;
			ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
			sb.append(ch);
			
			g.rotate(degree * Math.PI / 180, x, 20);
			g.drawString(ch, x, 20);
			
			g.rotate(-degree * Math.PI / 180, x, 20);
			x += 30;
		}
		return sb.toString();
	}

}
