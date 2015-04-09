import java.util.ArrayList;

	/**
	 * 
	 * @author Claudia Rogoz
	 * 
	 * MyArrayList extinde ArrayList in scopul supraincarcarii metodei toString
	 *
	 */
public class MyArrayList extends ArrayList<Integer> {
  
	private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.size(); i++) {
            result += " " + this.get(i);
        }
        return result;
    }
}