package javautilities.games.basic.core.characters;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javautilities.games.basic.core.Resource;
import javautilities.games.basic.core.icons.IconHandler;
import javautilities.games.basic.core.icons.StateAnimation;
import javautilities.meta.MetaMethods;

public class CharacterFactory {

	public static Character create(String name) {
		Character re = new Character(name);
		
		Method method = MetaMethods.getMethod(CharacterFactory.class, name, Character.class);
		if (method != null) {
			try {
				method.invoke(null, re);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		return re;
	}
	
	public static List<String> list() {
		Method[] methods = CharacterFactory.class.getDeclaredMethods();
		List<String> re = new ArrayList<>();
		for (Method method : methods) {
			if (method.getReturnType() == void.class) {
				re.add(method.getName());
			}
		}
		return re;
	}
	
	public static void Pingy(Character character) {
		character.iconHandler = IconHandler.load(new File(Resource.FOLDER + "anims/Pingy.anim").toPath());
		StateAnimation anim = ((StateAnimation) character.iconHandler);
		anim.state = "moving";
		System.out.println("Successfully Pingy");
		System.out.println(character);
	}
	
	public static void Pongy(Character character) {
		character.iconHandler = IconHandler.load(new File(Resource.FOLDER + "anims/Pongy.anim").toPath());
		System.out.println("Successfully Pongy");
		System.out.println(character);
	}
	
}
