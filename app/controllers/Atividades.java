package controllers;

import models.Atividade;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Atividades extends Controller {
	
	static Form<Atividade> atividadeForm = form(Atividade.class);

    public static Result index() {
    	return redirect(routes.Atividades.list());
    }
    
    public static Result show(Long id) {
    	return TODO;
    }
    
    public static Result list() {
	  return ok(views.html.Atividades.index.render(Atividade.all(), atividadeForm));
    }
    
    public static Result create() {

    	Form<Atividade> filledForm = atividadeForm.bindFromRequest();
    	  if(filledForm.hasErrors()) {
    	    return badRequest(
    	      views.html.Atividades.index.render(Atividade.all(), filledForm)
    	    );
    	  } else {
    		  Atividade.create(filledForm.get());
    	    return redirect(routes.Atividades.list());
    	  }
    }
    
    public static Result delete(Long id) {
    	Atividade.delete(id);
    	return redirect(routes.Atividades.list());
    }
}