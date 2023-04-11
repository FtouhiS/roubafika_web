package Entities;
//il vous mieux changer classe Reponse a ReponseReclamation
import java.util.Objects;

public class Reponse {
	private int Idrep;
	private String DescriptionRep;
        private int id_rec;

	public Reponse() {


	}

    public Reponse(String DescriptionRep, int id_rec) {
        this.DescriptionRep = DescriptionRep;
        this.id_rec = id_rec;
    }

	public Reponse(String descriptionRep) {


		DescriptionRep = descriptionRep;
	}


	public Reponse(int idrep, String descriptionRep) {

		Idrep = idrep;
		DescriptionRep = descriptionRep;
	}


	public int getIdrep() {
		return Idrep;
	}


	public void setIdrep(int idrep) {
		Idrep = idrep;
	}

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }


	public String getDescriptionRep() {
		return DescriptionRep;
	}


	public void setDescriptionRep(String descriptionRep) {
		DescriptionRep = descriptionRep;
	}


	@Override
	public int hashCode() {
		return Objects.hash(DescriptionRep, Idrep);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reponse other = (Reponse) obj;
		return Objects.equals(DescriptionRep, other.DescriptionRep) && Idrep == other.Idrep;
	}


	@Override
	public String toString() {
		return "Reponse [Idrep=" + Idrep + ", DescriptionRep=" + DescriptionRep + "]";
	}




}
