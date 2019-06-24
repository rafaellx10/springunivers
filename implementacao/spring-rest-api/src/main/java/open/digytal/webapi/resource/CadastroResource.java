package open.digytal.webapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import open.digytal.webapi.dao.ContatoDao;
import open.digytal.webapi.model.Roles;
import open.digytal.webapi.model.cadastro.Cidade;
import open.digytal.webapi.model.cadastro.Contato;
import open.digytal.webapi.repository.CidadeRepository;

@RestController
@RequestMapping("/cadastro")
public class CadastroResource {
	@Autowired
	private ContatoDao dao;
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@PostMapping(path = "/contatos")
	//@PreAuthorize(Roles.PRE_USER_ADMIN)
	public void incluirContato(@RequestBody Contato contato) {
		Cidade cid =cidadeRepository.findByIbge(contato.getCidade().getIbge());
		contato.setCidade(cid);
		dao.inserirContato(contato);
	}
	
	@GetMapping(path = "/contatos")
	@PreAuthorize(Roles.PRE_USER_ADMIN)
	public List<Contato> listarContatos() {
		return dao.listarContatos();
	}
	//https://cidades.ibge.gov.br/brasil/pi/parnaiba/panorama
	@PostMapping(path = "/cidades")
	@PreAuthorize(Roles.PRE_ADMIN)
	public void incluirCidade(@RequestBody Cidade cidade) {
		dao.inserirCidade(cidade);
	}
	
	@GetMapping(path = "/cidades")
	public List<Cidade> listarCidade() {
		return dao.listarCidades();
	}
	@GetMapping(path = "/cidades/{ibge}")
	public Cidade buscarCidade(@PathVariable("ibge") Long ibge) {
		return dao.buscarCidade(ibge);
	}
}
