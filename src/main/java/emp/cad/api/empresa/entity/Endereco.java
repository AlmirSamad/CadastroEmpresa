package emp.cad.api.empresa.entity;

import emp.cad.api.empresa.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private String complemento;

    public Endereco(EnderecoDTO dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.bairro = dados.bairro();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
        this.cep = dados.cep();
        this.complemento = dados.complemento();
    }

    public void atualizarendereco(EnderecoDTO dados) {
      if(dados != null){
          if (StringUtils.hasText(dados.logradouro())){
              this.logradouro = dados.logradouro();
          }
          if (StringUtils.hasText(dados.numero())){
              this.numero = dados.numero();
          }
          if (StringUtils.hasText(dados.bairro())){
              this.bairro = dados.bairro();
          }
          if (StringUtils.hasText(dados.cidade())){
              this.cidade = dados.cidade();
          }
          if (StringUtils.hasText(dados.uf())){
              this.uf = dados.uf();
          }
          if (StringUtils.hasText(dados.cep())){
              this.cep = dados.cep();
          }
          if (StringUtils.hasText(dados.complemento())){
              this.complemento = dados.complemento();
          }
      }
    }
}
