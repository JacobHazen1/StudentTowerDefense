import java.util.ArrayList;
/**
* @author PickleRick
* This generates the different levels in the game.
*/
public class Level_generator 
{
	private Render render;
	private ArrayList<Enemy> wave = new ArrayList<Enemy>();
	private ArrayList<Tower> arsenal = new ArrayList<Tower>();
	private int TA_populate;
	private int researcher_populate;
	private int level;
	private final int LEVEL_TOTAL = 3;
	private int delay;
	
	/**
	* Returns arrayList of all towers presently on the map 
	*/
	public ArrayList<Tower> get_arsenal()
	{
		return this.arsenal;
	}
	
	/**
	 * About add_to_arsenal(Tower tower):  used to add instances of towers to an ArrayList. The ArrayList
	 * 		is later used to iterate through all the instances of the towers to detect nearby enemies.
	 * @param tower:  an instance of a tower.
	 */
	public void add_to_arsenal(Tower tower) 
	{
		this.arsenal.add(tower);
	}
	
	/**
	 * About get_wave():  creates an ArrayList of enemies using the method create_wave(), and returns this 
	 * 		ArrayList to the location from which it was called.
	 * @return:  Returns an ArrayList of enemies.
	 */
	public ArrayList<Enemy> get_wave()
	{
		create_wave();
		return this.wave;
	}
	
	/**
	 * About get_wave_02():  Used to get the ArrayList of enemies during a given level.
	 * @return:  Returns the ArrayList of enemies during a given level.
	 */
	public ArrayList<Enemy> get_wave_02()
	{
		return this.wave;
	}
	
	/**
	 * About set_populate():  sets the number and types of enemies in a given level.
	 */
	public void set_populate() 
	{
		switch (level) 
		{
		case 1:
			this.TA_populate = 2;
			break;
		case 2:
			this.TA_populate = 4;
			break;
		case 3:
			this.TA_populate = 4;
			this.researcher_populate = 2;
			break;
		}
	}
	
	/**
	 * About increment_level():  at the end of a level, Level_generator is set up to function for the next level.
	 */
	public void increment_level() 
	{
		this.level += 1;
	}
	
	/**
	 * About Level_generator(Render render):  A constructor that takes a parameter render.
	 * @param render:  Render is used for preparing the enemies appropriately for the chosen map.
	 */
	public Level_generator(Render render) 
	{
		this.level = 1;
		this.TA_populate = 0;
		this.researcher_populate = 0;
		this.render = render;
	}
	
	/**
	 * About create_wave():  Used to create an ArrayList of enemies based on the current level.
	 */
	public void create_wave() 
	{
		this.wave.clear();
		set_populate();
		for (int i = 0; i < this.TA_populate; i++) 
		{
			Enemy enemy = new TA(render);
			this.wave.add(enemy);
		}
		for (int i = 0; i < this.researcher_populate; i++) 
		{
			Enemy enemy = new Researcher(render);
			this.wave.add(enemy);
		}
	}
	
	/**
	 * About check_wave():  The boolean generated by check_wave() is the main check used in Engine() to determine 
	 * 		an appropriate end to a given level. If one enemy is still alive, then the while loop continues to 
	 * 		iterate through.
	 * @return
	 */
	public boolean check_wave() 
	{
		for (Enemy enemy : this.wave) 
		{
			if ((enemy.get_health() > 0) || (enemy.get_x() != -1)) 
			{
				return true;
			}
		}
		return false;
	}
}
