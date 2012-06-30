package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import play.Logger;
import play.data.format.Formats;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.jpa.JPA;

@Entity
public class Atividade extends Model {
	
	private static final long serialVersionUID = -4650606087743108505L;

	@Id
	public Long id;

	@Required(message="Informe o título")
	public String titulo;
	
	public String descricao;
	
	@Required(message="Informe a data")
	@Temporal(TemporalType.DATE)
    @Formats.DateTime(pattern="dd/MM/yyyy")
	public Date data;
	
    @Required(message="Informe o local")
    public String local;
    
    public String duracao;
    
    public String participantes;
    
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
    
    public static Map<String,String> duracaoOptions() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        options.put("10-30", "10 a 30 min");
        options.put("30-60", "30 min a 1 hr");
        options.put("60-90", "1:00 a 1:30 hs");
        options.put("90-120", "1:30 a 2:00 hs");
        options.put("120-180", "2:00 a 3:00 hs");
        options.put(">180", "mais de 3:00 hs");
        options.put("dia", "o dia inteiro");
        options.put(">dia", "vários dias (especifique)");
        
        return options;
    }

	@SuppressWarnings("unchecked")
	public static List<Atividade> explorar(String filtro) {
		
		if(filtro != null && !filtro.equals("")) {
			
			String filtroTratado = filtro.trim().toLowerCase();
			Logger.debug("filtro recebido: ["+filtro+"], filtro tratado: ["+filtroTratado+"]");
			
					List<Atividade> atividades = JPA.em()
					.createQuery("from Atividade a where lower(a.titulo) like ? or lower(a.descricao) like ? or lower(a.local) like ?")
					.setParameter(1, "%" + filtroTratado + "%")
					.setParameter(2, "%" + filtroTratado + "%")
					.setParameter(3, "%" + filtroTratado + "%")
					.getResultList();
			
			return atividades;
		}
		else {
			return new ArrayList<Atividade>();
		}
	}
}
