package sp;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import sp.annotations.processors.CreerProcessor;
import sp.lib.VoitureVolvo;



public class Run {
	/**
	 * On récupère toutes les classes annotées par @creer
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println("Lancement de la création");
		List<Object> spring = CreerProcessor.run("sp.lib");
		System.out.println("Liste des objets créés:");
		for (Object object : spring) {
			System.out.println(object.toString());
		}
	}

}
