package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Atividade extends Model {
	
	private static final long serialVersionUID = -4650606087743108505L;

	@Id
	public Long id;
	@Required
    public String titulo;
    public String descricao;
    
    public static Finder<Long,Atividade> find = new Finder(Long.class, Atividade.class);
    
    public static List<Atividade> all() {
      return find.all();
    }
    
    public static void create(Atividade atividade) {
    	atividade.save();
    }
    
    public static void delete(Long id) {
    	find.ref(id).delete();
    }
    
}
