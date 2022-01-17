package src.mybbs.vo;

public class ContentsVo {
	private int row_num;
	private int con_num;
	private String menu_name;
	private String con_title;
	private String con_writer;
	private String con_content;
	private String con_writingdate;
	private int con_hitcount;
	
	public ContentsVo() {
	}
	public ContentsVo(int row_num, int con_num, String menu_name, String con_title, String con_writer,
			String con_writingdate, int con_hitcount) {
		
		this.row_num = row_num;
		this.con_num = con_num;
		this.menu_name = menu_name;
		this.con_title = con_title;
		this.con_writer = con_writer;
		this.con_writingdate = con_writingdate;
		this.con_hitcount = con_hitcount;
	}
	
	public ContentsVo(int con_num, String menu_name, String con_title, String con_writer, String con_content) {
		this.con_num = con_num;
		this.menu_name = menu_name;
		this.con_title = con_title;
		this.con_writer = con_writer;
		this.con_content = con_content;
	}
	


	public ContentsVo(int row_num, String con_title, String con_writer, String con_writingdate, int con_hitcount) {
		this.row_num = row_num;
		this.con_title = con_title;
		this.con_writer = con_writer;
		this.con_writingdate = con_writingdate;
		this.con_hitcount = con_hitcount;
	}

	public ContentsVo(int row_num, String menu_name, String con_title, String con_writer,
			String con_writingdate, int con_hitcount) {
		this.row_num = row_num;
		this.menu_name = menu_name;
		this.con_title = con_title;
		this.con_writer = con_writer;
		this.con_writingdate = con_writingdate;
		this.con_hitcount = con_hitcount;
	}

	public int getRow_num() {
		return row_num;
	}

	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}

	public int getCon_num() {
		return con_num;
	}

	public void setCon_num(int con_num) {
		this.con_num = con_num;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getCon_title() {
		return con_title;
	}

	public void setCon_title(String con_title) {
		this.con_title = con_title;
	}

	public String getCon_writer() {
		return con_writer;
	}

	public void setCon_writer(String con_writer) {
		this.con_writer = con_writer;
	}

	public String getCon_content() {
		return con_content;
	}

	public void setCon_content(String con_content) {
		this.con_content = con_content;
	}

	public String getCon_writingdate() {
		return con_writingdate;
	}

	public void setCon_writingdate(String con_writingdate) {
		this.con_writingdate = con_writingdate;
	}

	public int getCon_hitcount() {
		return con_hitcount;
	}

	public void setCon_hitcount(int con_hitcount) {
		this.con_hitcount = con_hitcount;
	}

	@Override
	public String toString() {
		return "ContentsVo [row_num=" + row_num + ", con_num=" + con_num + ", menu_name=" + menu_name + ", con_title="
				+ con_title + ", con_writer=" + con_writer + ", con_content=" + con_content + ", con_writingdate="
				+ con_writingdate + ", con_hitcount=" + con_hitcount + "]";
	}

	
}
