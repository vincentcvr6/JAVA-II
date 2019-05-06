public class Article {
	private String id;
	private String author;
	private String journal;
	private String title;
	private String year;
	private String volume;
	private String number;
	private String pages;
	private String keywords;
	private String doi;
	private String issn;
	private String month;
	
	public Article() {
		this.id=null;
		this.author=null;
		this.journal=null;
		this.title=null;
		this.year=null;
		this.volume=null;
		this.number=null;
		this.pages=null;
		this.keywords=null;
		this.doi=null;
		this.issn=null;
		this.month=null;
	}
	
	public Article (String id, String author, String journal, String title, String year, String volume, String number, 
					String pages, String keywords, String doi, String issn, String month) {
		this.id=id;
		this.author=author;
		this.journal=journal;
		this.title=title;
		this.year=year;
		this.volume=volume;
		this.number=number;
		this.pages=pages;
		this.keywords=keywords;
		this.doi=doi;
		this.issn=issn;
		this.month=month;
	}
	
	public Article(Article a) {
		this.id=a.getId();
		this.author=a.getAuthor();
		this.journal=a.getJournal();
		this.title=a.getTitle();
		this.year=a.getYear();
		this.volume=a.getVolume();
		this.number=a.getNumber();
		this.pages=a.getPages();
		this.keywords=a.getKeywords();
		this.doi=a.getDoi();
		this.issn=a.getIssn();
		this.month=a.getMonth();
	}
	
	public Article clone() {
		return new Article(this);
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String aut) {
		author=aut;
	}
	
	public String getJournal() {
		return journal;
	}
	
	public void setJournal(String jou) {
		journal=jou;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String tit) {
		title=tit;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String yr) {
		year=yr;
	}
	
	public String getVolume() {
		return volume;
	}
	
	public void setVolume(String vol) {
		volume=vol;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String num) {
		number=num;
	}
	
	public String getPages() {
		return pages;
	}
	
	public void setPages(String pa) {
		pages=pa;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String kw) {
		keywords=kw;
	}
	
	public String getDoi() {
		return doi;
	}
	
	public void setDoi(String doi) {
		this.doi=doi;
	}
	
	public String getIssn() {
		return issn;
	}
	
	public void setIssn(String issn) {
		this.issn=issn;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String mo) {
		month=mo;
	}
	

}