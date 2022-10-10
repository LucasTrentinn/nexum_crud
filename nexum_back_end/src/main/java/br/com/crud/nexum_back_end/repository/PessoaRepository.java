package br.com.crud.nexum_back_end.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crud.nexum_back_end.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	Optional<Pessoa> findById(Long id);

	void deleteById(Long id);

}
