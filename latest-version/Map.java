import java.util.ArrayList;

// this class store the map that the player, enemies, and items will be located on

public class Map {
	
	private ArrayList<String> grid;
// creates the map
	public Map() {
		this.grid = new ArrayList<String>();
		this.grid.add("%%%%%%%%%%                          ");
		this.grid.add("%        %                          ");
		this.grid.add("%        %     %%%%%%%%%%%%%%%%%%%%%");
		this.grid.add("%        %     %                   %");
		this.grid.add("%        %     %                   %");
		this.grid.add("%        %     %                   %");
		this.grid.add("%        %%%%%%%                   %");
		this.grid.add("%                                  %");
		this.grid.add("%        %%%%%%%                   %");
		this.grid.add("%%%%%%%%%%     %                   %");
		this.grid.add("               %                   %");
		this.grid.add("               %                   %");
		this.grid.add("               %% %%%%%%%%%%%%%%%%%%");
		this.grid.add("                % %                 ");
		this.grid.add("            %%%%% %%%%%             ");
		this.grid.add("            %         %             ");
		this.grid.add("            %         %             ");
		this.grid.add("            %         %             ");
		this.grid.add("            %%%%%%%%%%%             ");
	}
// returns the ArrayList storing the map
	public ArrayList<String> getGrid() {
		return grid;
	}

}
