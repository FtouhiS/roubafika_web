package Entities;



import java.sql.Date;
import java.util.Objects;


public class Reclamation {
	private int IdReclamation;
	private Date Date_ajout;
	private String Sujet;
	private String Description;
        private int id_user;

	public Reclamation() {

	}	

    public Reclamation(int IdReclamation, Date Date_ajout, String Sujet, String Description, int id_user) {
        this.IdReclamation = IdReclamation;
        this.Date_ajout = Date_ajout;
        this.Sujet = Sujet;
        this.Description = Description;
        this.id_user = id_user;
    }

    

    public Reclamation(Date Date_ajout, String Sujet, String Description, int id_user) {
        this.Date_ajout = Date_ajout;
        this.Sujet = Sujet;
        this.Description = Description;
        this.id_user = id_user;
    }

    public Reclamation(Date Date_ajout, String Sujet, String Description) {
        this.Date_ajout = Date_ajout;
        this.Sujet = Sujet;
        this.Description = Description;
    }

	public Reclamation(int idReclamation, Date date_ajout, String sujet, String description) {

		this.IdReclamation = idReclamation;
		this.Date_ajout = date_ajout;
		this.Sujet = sujet;
		this.Description = description;
	}

	public int getIdReclamation() {
		return IdReclamation;
	}

	public void setIdReclamation(int idReclamation) {

		this.IdReclamation = idReclamation;
	}

	public Date getDate_ajout() {
		return Date_ajout;
	}

	public void setDate_ajout(Date date_ajout) {
		Date_ajout = date_ajout;
	}

	public String getSujet() {
		return Sujet;
	}

	public void setSujet(String sujet) {
		Sujet = sujet;
	}

	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {

		this.Description = description;
	}
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
	@Override
	public String toString() {
		return "Reclamation [IdReclamation=" + IdReclamation + ", Date_ajout=" + Date_ajout + ", Sujet=" + Sujet
				+ ", Description=" + Description + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(Date_ajout, Description, IdReclamation, Sujet);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reclamation other = (Reclamation) obj;
		return Objects.equals(Date_ajout, other.Date_ajout) && Objects.equals(Description, other.Description)
				&& IdReclamation == other.IdReclamation && Objects.equals(Sujet, other.Sujet);
	}

}



