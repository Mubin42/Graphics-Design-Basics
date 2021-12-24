package task03;

import com.jogamp.opengl.GL2;
        import com.jogamp.opengl.GLAutoDrawable;
        import com.jogamp.opengl.GLCapabilities;
        import com.jogamp.opengl.GLEventListener;
        import com.jogamp.opengl.GLProfile;
        import com.jogamp.opengl.awt.GLCanvas;
        import com.jogamp.opengl.glu.GLU;
        import java.lang.Math;
        import javax.swing.JFrame;

class ThirdGLEventListener implements GLEventListener {
    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
         * put your code here
         */
        gl.glBegin (GL2.GL_POINTS);
        int ID = 18101391;
        if(ID%2==1) {
        	DDA(gl,-100,100,-100,-100);
        	DDA(gl,100,100,100,-100);
        	DDA(gl,-100,0,100,0);
        }
        else {
        	DDA(gl,-100,100,100,100);
            DDA(gl,0,100,0,-100);
        }
        gl.glEnd();
        
        
        

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    public void displayChanged(GLAutoDrawable drawable,
                               boolean modeChanged, boolean deviceChanged) {
    }
    

    
    public void DDA(GL2 gl, float x1, float y1, float x2, float y2) {
        gl.glPointSize(3.0f);
        gl.glColor3d(1, 1, 1);
        
        float m=(y1-y2)/(x1-x2);
        if(m>-1 && m<1) {
     	   if(x2>x1) {
     		   while(x1<=x2) {
     			   x1 = x1 + 1;
     			   y1 = y1 + m;
     			   gl.glVertex2f(x1,y1);
     			   
     		   }
     	   }else {
     		   while(x2<=x1) {
     			   x1 = x1 - 1;
     			   y1 = y1 - m;
     			   gl.glVertex2f(x1,y1);
     			   
     		   }
     	   }
        }else {
     	   if(y2>y1) {
     		   while(y1<=y2) {
     			   x1 = x1 + (1/m);
     			   y1 = y1 + 1;
     			   gl.glVertex2f(x1,y1);
     			   
     		   }
     	   }else {
     		   while(y2<=y1) {
     			   x1 = x1 - (1/m);
     			   y1 = y1 -1;
     			   gl.glVertex2f(x1,y1);
     			   
     		   }
     	   }
        }
        
     }

    public void dispose(GLAutoDrawable arg0)
    {

    }
}
public class kaziehsanulmubin_task03
{
    public static void main(String args[])
    {
        //getting the capabilities object of GL2 profile
        final GLProfile profile=GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities=new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas=new GLCanvas(capabilities);
        ThirdGLEventListener b=new ThirdGLEventListener();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame=new JFrame("Basic frame");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640,480);
        frame.setVisible(true);
    }
}