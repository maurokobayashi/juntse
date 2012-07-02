package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Atividade;

import org.codehaus.jackson.JsonNode;

import play.Logger;
import play.data.Form;
import play.libs.Json;
import play.mvc.Content;
import play.mvc.Controller;
import play.mvc.Result;

public class Atividades extends Controller {
	
	static Form<Atividade> atividadeForm = form(Atividade.class);

    public static Result index() {
    	return redirect(routes.Atividades.list());
    }
    
    public static Result show(Long id) {
    	Logger.debug("show()");
    	
    	Atividade atividade = Atividade.findById(id);
    	
    	if(atividade == null) {
    		return notFound("A atividade não existe.");
    	}
    	return ok(views.html.Atividades.show.render(atividade));
    }
    
    public static Result list() {
    	Logger.debug("list()");
    	
    	return ok(views.html.Atividades.index.render(Atividade.all(), atividadeForm));
    }
    
    public static Result create() {
    	Logger.debug("create()");
    	
    	Form<Atividade> filledForm = atividadeForm.bindFromRequest();
    	Logger.info(filledForm.toString());
    	  
    	if(filledForm.hasErrors()) {
    	    return badRequest(
    	      views.html.Atividades.index.render(Atividade.all(), filledForm)
    	    );
    	  }
    	  else {
    		  Atividade.create(filledForm.get());
    	    return redirect(routes.Atividades.list());
    	  }
    }
    
    public static Result delete(Long id) {
    	Logger.debug("delete()");
    	
    	Atividade.delete(id);
    	return redirect(routes.Atividades.list());
    }

    public static Result explorar(String filtro) {
    	Logger.debug("explorar()");
    	Logger.debug("Filtro recebido: ["+filtro+"]");
    	
    	return ok(views.html.Atividades.explorar.render(Atividade.explorar(filtro)));
    }
    
    public static Result explorarAutoComplete() {
    	Logger.debug("explorarAutoComplete()");
    	
    	List<Atividade> atividades = Atividade.all();
    	List<String> resultados = new ArrayList<String>();
    	
    	for(Atividade atividade : atividades) {
    		resultados.add(atividade.titulo + " (" + atividade.local + ")");
    	}
    	JsonNode json = Json.toJson(resultados);

    	Logger.debug("JSON retornado: " + json.toString());
    	return ok(json.toString());
    }
}