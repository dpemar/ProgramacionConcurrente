/**
 * 
 */
package HojaEjercicio3;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Personajes {

	private String name;
	private String sex;
	private String birth;
	private String city;
	private String country;
	private String continent;
	private String occupation;
	private String industry;
	private String domain;
	private Double languajes;
	private Double views;
	private Double average;
	private Double popularity;
	
	
	@Override
	public String toString() {
		return "Personajes [name=" + name + ", sex=" + sex + ", birth=" + birth + ", city=" + city + ", country="
				+ country + ", continent=" + continent + ", occupation=" + occupation + ", industry=" + industry
				+ ", domain=" + domain + ", languajes=" + languajes + ", views=" + views + ", average=" + average
				+ ", popularity=" + popularity + "]";
	}
	
	/**
	 * @param name
	 * @param sex
	 * @param birth
	 * @param city
	 * @param country
	 * @param continent
	 * @param occupation
	 * @param industry
	 * @param domain
	 * @param languajes
	 * @param views
	 * @param average
	 * @param popularity
	 */
	public Personajes(String name, String sex, String birth, String city, String country, String continent,
			String occupation, String industry, String domain, Double languajes, Double views, Double average,
			Double popularity) {
		super();
		this.name = name;
		this.sex = sex;
		this.birth = birth;
		this.city = city;
		this.country = country;
		this.continent = continent;
		this.occupation = occupation;
		this.industry = industry;
		this.domain = domain;
		this.languajes = languajes;
		this.views = views;
		this.average = average;
		this.popularity = popularity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Double getLanguajes() {
		return languajes;
	}
	public void setLanguajes(Double languajes) {
		this.languajes = languajes;
	}
	public Double getViews() {
		return views;
	}
	public void setViews(Double views) {
		this.views = views;
	}
	public Double getAverage() {
		return average;
	}
	public void setAverage(Double average) {
		this.average = average;
	}
	public Double getPopularity() {
		return popularity;
	}
	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}

	
}

