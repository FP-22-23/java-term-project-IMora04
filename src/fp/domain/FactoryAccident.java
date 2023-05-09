package fp.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FactoryAccident {
	
	public static Accident parseLine(String l) {
		return new Accident(l);
	}
		
	
	public static List<Accident> readFile(String route) {
		List<Accident> accidents = null;
		try {
			Stream<Accident> st = Files.lines(Paths.get(route))
				.skip(1)
				.map(FactoryAccident::parseLine);
			accidents = new ArrayList<Accident>(st.collect(Collectors.toList()));
		} catch(IOException e) {
			e.printStackTrace();
		}
		return accidents;
	}

	public static Accidents readFileContainer(String route) {
		return new Accidents(readFile(route));
	}
	
}