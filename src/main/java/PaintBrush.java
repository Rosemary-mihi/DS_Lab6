import java.util.Stack;
import javafx.scene.paint.Color;


public class PaintBrush
{

	
	Paint paint;
	
	
	enum BrushMode{
		paintMode,
		fillMode,
		pattern1Mode,
		pattern2Mode
	}

	BrushMode mode;
	
	Paint Gold = new PaintColor(Color.GOLD);
	Paint White = new PaintColor(Color.WHITE);
	
	
/**
set the "paint" for the paintbrush
*/	
	public void setPaint(Paint paint)
	{
		this.paint = paint; 
   
	}


/*
   gets the present paint on the paint brush
*/
	public Paint getPaint()
	{
		return this.paint;
	}
	
   
   /*
   makes the paint on the paint brush a brigter shade.
   */
	public void setBrighter()
	{		
		this.paint = new PaintBrighter(paint);
	}


   /*
      makes the paint on the paintbrush a darker shade
   */
	public void setDarker()
	{
		this.paint = new PaintDarker(paint);
	}


   /*
      paints the mesh, using the current paint and mode at point x,y
   */
	public void paint(int x, int y, Paint[][] mesh)
	{
		if (mode == BrushMode.paintMode)
		{
			mesh[x][y] = this.paint;//ask him about this
		}
		else if(mode == BrushMode.fillMode)
		{
			PaintColor c = (PaintColor) mesh[x][y]; //why is PaintColor like this?
			paintFill(x,y,mesh,c, this.paint);
		}
		else if(mode == BrushMode.pattern1Mode)
		{
			Paint pink = new PaintColor(Color.PINK);
			PaintColor c = (PaintColor) mesh[x][y];
			paintFill(x,y,mesh,c, pink);
			c = (PaintColor) mesh[x][y]; //why is PaintColor like this?
			paintPattern1(x,y,mesh,c);
		}
	}
	public void paintFill(int x, int y, Paint[][] mesh, Paint c, Paint pc)
	{
		mesh[x][y] = pc;
		if (y+1 < mesh.length && c.equals(mesh[x][y+1]))
		{
			paintFill(x, y+1, mesh, c, pc);
		}
		if (y-1 >= 0 && c.equals(mesh[x][y-1]))
		{
			paintFill(x, y-1, mesh, c, pc);
		}
		if (x-1 >= 0 && c.equals(mesh[x-1][y]))
		{
			paintFill(x-1, y, mesh, c, pc);
		}
		if (x+1 < mesh.length && c.equals(mesh[x+1][y]))
		{
			paintFill(x+1, y, mesh, c, pc);
		}
	}
	public void paintPattern1(int x, int y, Paint[][] mesh, Paint c)
	{
		if (x%2 == 0)
		{
			mesh[x][y] = Gold;
		}
		else 
		{
			mesh[x][y] = White;
		}
		if (y+1 < mesh.length && c.equals(mesh[x][y+1]))
		{
			paintPattern1(x, y+1, mesh, c);
		}
		if (y-1 >= 0 && c.equals(mesh[x][y-1]))
		{
			paintPattern1(x, y-1, mesh, c);
		}
		if (x-1 >= 0 && c.equals(mesh[x-1][y]))
		{
			paintPattern1(x-1, y, mesh, c);
		}
		if (x+1 < mesh.length && c.equals(mesh[x+1][y]))
		{
			paintPattern1(x+1, y, mesh, c);
		}
		
	}

	
	
/*
   set the drawing mode of the paint brush.
*/
	public void pointMode()
	{
		mode= BrushMode.paintMode;
	}

	public void fillMode()
	{
		mode = BrushMode.fillMode;
	}

	public void pattern1Mode()
	{
		mode = BrushMode.pattern1Mode;
	}

	public void pattern2Mode()
	{
		mode = BrushMode.pattern2Mode;
	}

}
