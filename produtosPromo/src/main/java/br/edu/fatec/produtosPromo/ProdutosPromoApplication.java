package br.edu.fatec.produtosPromo;

import br.edu.fatec.produtosPromo.services.ConsomeAPI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ProdutosPromoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProdutosPromoApplication.class, args);
	}

	//filtrar os produtos que custam menos ou igual a 30, os nomes dos produtos filtrados devem ser
	// apresentados em lista de imperdiveis e em promoção tudo escrito em maiusculo

	@Override
	public void run(String... args) throws Exception {
		ConsomeAPI lerAPI = new ConsomeAPI();
		String dados = lerAPI.ObterDados("https://api.escuelajs.co/api/v1/products/");
		System.out.println(dados);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(dados);
		List<JsonNode> jsonList =  new ArrayList<>();
		jsonNode.forEach(jsonList::add);
		//jsonList.stream().forEach(System.out::println);
		List<String> imperdiveis = jsonList.stream()
				.filter(node -> node.get("price").asInt() <= 30)
				.map(node -> node.get("title").asText().toUpperCase()
						+ " - Preço: R$ " + node.get("price").asInt())
				.toList();
		System.out.println("\n******************************************************************");
		System.out.println("PRODUTOS IMPERDIVEIS!!");
		System.out.println("******************************************************************\n");
		imperdiveis.forEach(System.out::println);



	}

}
