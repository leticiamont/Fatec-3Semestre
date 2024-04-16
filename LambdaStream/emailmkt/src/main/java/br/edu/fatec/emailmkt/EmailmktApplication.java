package br.edu.fatec.emailmkt;

import br.edu.fatec.emailmkt.services.ConsomeAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class EmailmktApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(EmailmktApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		ConsomeAPI lerAPI = new ConsomeAPI();
		String dados = lerAPI.ObterDados("https://jsonplaceholder.typicode.com/comments");
		System.out.println(dados);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(dados);
		//System.out.println(jsonNode);
		List<JsonNode> jsonList =  new ArrayList<>();
		jsonNode.forEach(jsonList::add);
		//jsonList.stream().forEach(System.out::println);
		List<String> emailList = jsonList.stream()
				.map(node -> node.get("email")
						.asText())
				.toList();
		emailList.forEach(System.out::println);
		System.out.println(emailList.size());
	}
}
