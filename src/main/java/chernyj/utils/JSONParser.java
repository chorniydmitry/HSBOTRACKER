package chernyj.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import model.Card;
import model.Expansion;

/**
 * @author Chernyj Dmitry
 *
 */
public class JSONParser {
	
	public Collection<Card> getCards() throws JsonSyntaxException, MalformedURLException, IOException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(Resources.CARDS_JSON));
		
		Type collectionType = new TypeToken<Collection<Card>>(){}.getType();
		return gson.fromJson(reader, collectionType);
	}
	
	public Collection<Expansion> getExpansions() throws JsonSyntaxException, MalformedURLException, IOException {
		Gson gson = new Gson();
		JsonReader reader = new JsonReader(new InputStreamReader(Resources.EXPANSIONS_JSON));
		
		Type collectionType = new TypeToken<Collection<Expansion>>(){}.getType();
		return gson.fromJson(reader, collectionType);
	}
	


}
