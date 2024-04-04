package model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CepEndereco(@JsonAlias("cep") String cep,
                          @JsonAlias({"logradouro","street"}) String rua,
                          @JsonAlias({"bairro","neighborhood"}) String bairro,
                          @JsonAlias({"localidade","city"}) String cidade,
                          @JsonAlias("ddd") int ddd)
{

    @Override
    public String toString() {
        return "CepEndereco{" +
                "cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade=" + cidade +
                ", ddd=" + ddd +
                '}';
    }
}

