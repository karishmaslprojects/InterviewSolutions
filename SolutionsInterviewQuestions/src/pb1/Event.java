package pb1;
public class Event {
	Integer date;
	Integer userId;
	Integer categoryId;
	Integer subcategoryId;
	Integer value;
	
	public String getKey() {
		return date+","+userId+","+categoryId;
	}
	
	public String toString() {
		return "Event(date="+date+", userId="+userId+", categoryId="+categoryId+", subcategoryId="+subcategoryId+", value="+value+")";
	}
	
	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getSubcategoryId() {
		return subcategoryId;
	}

	public void setSubcategoryId(Integer subcategoryId) {
		this.subcategoryId = subcategoryId;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Event(Integer date, Integer userId, Integer categoryId, Integer subcategoryId, Integer value) {
		super();
		this.date = date;
		this.userId = userId;
		this.categoryId = categoryId;
		this.subcategoryId = subcategoryId;
		this.value = value;
	}
}
