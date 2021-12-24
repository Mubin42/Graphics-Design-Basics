package Lab02;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class kaziehsanulmubin_task02 implements GLEventListener{
	 private GLU glu;
	   @Override
	   public void display(GLAutoDrawable drawable) {
	      final GL2 gl = drawable.getGL().getGL2();
	      gl.glColor3d(1, 1, 1);
	      gl.glBegin (GL2.GL_POINTS);
	      
	      convertToZone0(gl, 0,0,0,80);
	      convertToZone0(gl, -80,0,80,0);
	      convertToZone0(gl, -60,-20,60,-20);
	      convertToZone0(gl, -60,-20,-80,0);
	      convertToZone0(gl, 60,-20,80,0);
	      convertToZone0(gl, -60,-20,-80,0);
	      convertToZone0(gl, 30,0,0,80);
	      convertToZone0(gl, -30,0,0,40);
	          	    	     
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
	       glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
	   }

	   

	   @Override
	   public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
	      // method body
	   }
	   
	   
	   public int zoneDetection(int x1, int y1, int x2, int y2) {    
	    int zone=0;
	    
	    int dx=x2-x1;
	    int dy=y2-y1;
	   
	    if(Math.abs(dx)>Math.abs(dy)) {
	     if(dx>=0 && dy>=0) zone=0;
	     else if(dx>=0 && dy<=0) zone=7;
	     else if(dx<=0 && dy>=0) zone=3;
	     else if(dx<=0 && dy<=0) zone=4;
	    }else {
	     if(dx>=0 && dy>=0) zone=1;
	     else if(dx>=0 && dy<=0) zone=6;
	     else if(dx<=0 && dy>=0) zone=2;
	     else if(dx<=0 && dy<=0) zone=5;
	    }
	    return zone;
	   }
	   
	   public int[] anyZoneToOriginal(int x, int y, int zone) {
	    int a=x;
	    int b=y;
	        
	     if(zone==1) {
	     a=y;
	     b=x;     
	    }else if(zone==2) {
	     a=(-1)*y;
	     b=x;     
	    }else if(zone==3) {
	     a=(-1)*x;
	     b=y;    
	    }else if(zone==4) {
	     a=(-1)*x;
	     b=(-1)*y;    
	    }else if(zone==5) {
	     a=(-1)*y;
	     b=(-1)*x;    
	    }else if(zone==6) {
	     a=y;
	     b=(-1)*x;    
	    }else if(zone==7) {
	     a=x;
	     b=(-1)*y;
	    }
	    int rev[]= {a,b};
	    return rev;
	   }
	   
	   public void convertToZone0(GL2 gl, int x1, int y1, int x2, int y2) {
	    int a=0;
	    int b=0;
	    int c=0;
	    int d=0;
	    int zone=zoneDetection(x1, y1, x2, y2);
	    if (zone==0) {
	     a=x1;
	     b=y1;
	     c=x2;
	     d=y2;
	    }else if(zone==1) {
	     a=y1;
	     b=x1;
	     c=y2;
	     d=x2;
	    }else if(zone==2) {
	     a=y1;
	     b=(-1)*x1;
	     c=y2;
	     d=(-1)*x2;
	    }else if(zone==3) {
	     a=(-1)*x1;
	     b=y1;
	     c=(-1)*x2;
	     d=y2;
	    }else if(zone==4) {
	     a=(-1)*x1;
	     b=(-1)*y1;
	     c=(-1)*x2;
	     d=(-1)*y2;
	    }else if(zone==5) {
	     a=(-1)*y1;
	     b=(-1)*x1;
	     c=(-1)*y2;
	     d=(-1)*x2;
	    }else if(zone==6) {
	     a=(-1)*y1;
	     b=x1;
	     c=(-1)*y2;
	     d=x2;
	    }else if(zone==7) {
	     a=x1;
	     b=(-1)*y1;
	     c=x2;
	     d=(-1)*y2;
	    }
	    midPointLineAlgo(gl, a, b, c, d, zone);
	   }
	   
	   public void midPointLineAlgo(GL2 gl, int x1, int y1, int x2, int y2, int zone) {   
	       int dy=y2-y1;
	    int dx=x2-x1;
	    int d=2*dy-dx;
	    while(x1<=x2) {
	     int []array=anyZoneToOriginal(x1, y1, zone);
	     gl.glVertex3f(array[0],array[1],0);
	     x1++;
	     if(d>0) {
	      d=d+2*dy-2*dx;
	      y1++;
	     }else {
	      d=d+2*dy;
	     }
	    }   
	   }
	   
	   
	   
	   public static void main(String[] args) {
	      //getting the capabilities object of GL2 profile
	      final GLProfile profile = GLProfile.get(GLProfile.GL2);
	      GLCapabilities capabilities = new GLCapabilities(profile);
	      // The canvas 
	      final GLCanvas glcanvas = new GLCanvas(capabilities);
	      kaziehsanulmubin_task02 l = new kaziehsanulmubin_task02();
	      glcanvas.addGLEventListener(l);
	      glcanvas.setSize(400, 400);
	      //creating frame
	      final JFrame frame = new JFrame ("straight Line");
	      //adding canvas to frame
	      frame.getContentPane().add(glcanvas);
	      frame.setSize(frame.getContentPane().getPreferredSize());
	      frame.setVisible(true);
	   }//end of main
	}//end of classimport javax.media.opengl.GL2;
