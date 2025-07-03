package pb1;
import java.util.Objects;

class EventKey{
	Integer date;
	Integer userId;
	Integer categoryId;
	
	public EventKey(Integer date, Integer userId, Integer categoryId) {
		super();
		this.date = date;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date,userId,categoryId);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		if(obj==null || obj.getClass() !=this.getClass())
		{
			return false;
		}
		EventKey e=(EventKey)obj;
		return e.getDate()==this.getDate() 
				&& e.getCategoryId()==this.categoryId
				&& e.getUserId()==this.userId;
	}
	
	public String toString() {
		return date+" "+userId+" "+categoryId;
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
}