package sp.annotations.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;

import sp.annotations.Creer;
import sp.lib.VoitureVolvo;

public class CreerProcessor {

	/**
	 * @param pck : package à analyser
	 * @return les instances de chaque classe annotée par créer
	 * @throws Exception : Moche mais facilite la lecture du code
	 */
	public static List<Object> run(String pck) throws Exception {
		List<Object> result = new ArrayList<Object>();
		
		// on va chercher dans un package
		Reflections r = new Reflections(pck);
		// on filtre et on ne garde que les classes ayant l'annotation "Creer"
		Set<Class<?>> classes = r.getTypesAnnotatedWith(Creer.class);
		
		for (Class<?> c : classes) {
			System.out.println(c.getName());
			Object o = c.newInstance();
			result.add(o);
		}
				
		return result;
	}
}
