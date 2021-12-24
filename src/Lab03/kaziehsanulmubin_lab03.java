package Lab03;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class kaziehsanulmubin_lab03 implements GLEventListener{
	   
		private GLU glu;
	   @Override
	   public void display(GLAutoDrawable drawable) {
	      final GL2 gl = drawable.getGL().getGL2();
	      gl.glBegin (GL2.GL_POINTS);
	      
	      midPointCircle(gl,100,0,0);
	      midPointCircle(gl,50,50,0);
	      midPointCircle(gl,50,-50,0);
	      midPointCircle(gl,50,0,50);
	      midPointCircle(gl,50,0,-50);
	     
	      gl.glEnd();
	   }
	   @Override
	   public void dispose(GLAutoDrawable arg0) {
	      //method body
	   }
	   
	   @Override
	   public void init(GLAutoDrawable gld) {
	       GL2 gl = gld.getGL().getGL2();
	       glu = new GLU();

	       gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	       gl.glViewport(-100, -50, 50, 100);
	       gl.glMatrixMode(GL2.GL_PROJECTION);
	       gl.glLoadIdentity();
	       glu.gluOrtho2D(-120.0, 120.0, -120.0, 120.0);
	       gl.glColor3d(1, 1, 1);
	   }

	   

	   @Override
	   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
	      // method body
	   }
	  
	   
	   public void midPointCircle(GL2 gl, int r, int x1, int y1) {
		   int x=0; 
		   int y=r;
		   int d=1-r;
		   
		   
		   drawPoints(gl, x, y, x1, y1);
		   
		   while(x<y) {
			   int dn = (2*x+3);
			   int dnw = (2*x-2*y+5);
			   
			   if(d<0) {
				   
				   d=d+dn;
				   x++;
				   
			   }else {
				   
				   d=d+dnw;
				   x++;
				   y--;
			   }
			   
			   drawPoints(gl, x, y, x1, y1);
		   }
	   }
	   
	  
	   public void drawPoints(GL2 gl, int x, int y, int A, int B) {
		   gl.glVertex3f(y+A,x+B,0);
		   gl.glVertex3f(x+A,y+B ,0);
		   gl.glVertex3f(-x+A,y+B,0);
		   gl.glVertex3f(-y+A,x+B,0);
		   gl.glVertex3f(-y+A,-x+B,0);
		   gl.glVertex3f(-x+A,-y+B,0);
		   gl.glVertex3f(x+A,-y+B,0);
		   gl.glVertex3f(y+A,-x+B,0);
	   }
	   
	   
	   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	      final GLProfile profile = GLProfile.get(GLProfile.GL2);
	      GLCapabilities capabilities = new GLCapabilities(profile);
	      // The canvas 
	      final GLCanvas glcanvas = new GLCanvas(capabilities);
	      kaziehsanulmubin_lab03 l = new kaziehsanulmubin_lab03();
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(800, 800);
	      //creating frame
	      final JFrame frame = new JFrame ("MidPoint Circle");
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
	   }//end of main
	}//end of classimport javax.media.opengl.GL2;

