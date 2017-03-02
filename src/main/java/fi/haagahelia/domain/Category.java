package fi.haagahelia.domain;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	  @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
		private Long catid;
		private String catname;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
		private List<Book> book;
		
		public Category() {}
		
		public Category(String catname) {
			super();		
			this.catname = catname;
		}

		public Long getCatid() {
			return catid;
		}

		public void setCatid(Long catid) {
			this.catid = catid;
		}

		public String getCatname() {
			return catname;
		}

		public void setCatname(String catname) {
			this.catname = catname;
		}

		public List<Book> getBook() {
			return book;
		}

		public void setBook(List<Book> book) {
			this.book = book;
		}
		
	
		@Override
		public String toString() {
			return "Category [catid=" + catid + ", catname=" + catname + "]";
		}
	}
