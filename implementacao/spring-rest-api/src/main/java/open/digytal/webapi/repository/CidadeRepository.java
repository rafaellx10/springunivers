package open.digytal.webapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import open.digytal.webapi.model.cadastro.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	Cidade findByIbge(Long ibge);
}
