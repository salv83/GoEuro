import javax.json.JsonValue;


public class GeoObj 
{
	private String _type;
	private int _id;
	private String name; 
	private String type;
	private JsonValue latitude;
	private JsonValue longitude;
	
	
	public GeoObj() {
		super();
		this._type = "";
		this._id = 0;
		this.name = "";
		this.type = "";
		this.latitude = null;
		this.longitude = null;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public int get_id() {
		return _id;
	}
	public void set_id(int _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public JsonValue getLatitude() {
		return latitude;
	}
	public void setLatitude(JsonValue jsonValue) {
		this.latitude = jsonValue;
	}
	public JsonValue getLongitude() {
		return longitude;
	}
	public void setLongitude(JsonValue jsonValue) {
		this.longitude = jsonValue;
	}
	
}
