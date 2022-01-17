package src.mybbs.vo;

public class MenuVo {
	private int menu_num;
	private String menu_name;
	
	public MenuVo() {
	}

	public MenuVo(int menu_num, String menu_name) {
		this.menu_num = menu_num;
		this.menu_name = menu_name;
	}

	public int getMenu_num() {
		return menu_num;
	}

	public void setMenu_num(int menu_num) {
		this.menu_num = menu_num;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	@Override
	public String toString() {
		return "MenuVo [menu_num=" + menu_num + ", menu_name=" + menu_name + "]";
	}
	
	
}
