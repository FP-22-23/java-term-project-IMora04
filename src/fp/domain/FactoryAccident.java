package fp.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactoryAccident {
	
	public static Accident parseLine(String l) {
		return new Accident(l);
	}
		
	
	public static Accidents readFile(String route) {
		Accidents accidents = null;
		try {
			Stream<Accident> st = Files.lines(Paths.get(route))
				.skip(1)
				.map(FactoryAccident::parseLine);
			accidents = new Accidents(st.collect(Collectors.toList()));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return accidents;
	}

}