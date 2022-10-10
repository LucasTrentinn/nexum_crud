package br.com.crud.nexum_back_end.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.nexum_back_end.model.Pessoa;
import br.com.crud.nexum_back_end.repository.PessoaRepository;
  
@Transactional
@RestController
@RequestMapping(path="/pessoas")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@GetMapping(path="")
	public Iterable<Pessoa> getAllPessoa() {
		return pessoaRepository.findAll();
	}
	
	@PostMapping(path="")
	public Pessoa postPessoa(@RequestBody Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return pessoa;
	}

	@GetMapping(path="/{id}")
	public Optional<Pessoa> getOnePessoa(@PathVariable Long id) {
	  return pessoaRepository.findById(id);
	}
	
	@PutMapping(path="/{id}")
	public Pessoa putPessoa(@RequestBody Pessoa novaPessoa, @PathVariable Long id) {
		return pessoaRepository.findById(id)
				.map(pessoa -> {
					pessoa.setNome(novaPessoa.getNome());
					pessoa.setEmail(novaPessoa.getEmail());
					pessoa.setTipoPF(novaPessoa.isTipoPF());
					pessoa.setCpf(novaPessoa.getCpf());
					pessoa.setCnpj(novaPessoa.getCnpj());
					pessoa.setCep(novaPessoa.getCep());
					pessoa.setLogradouro(novaPessoa.getLogradouro());
					pessoa.setNumero(novaPessoa.getNumero());
					pessoa.setBairro(novaPessoa.getBairro());
					pessoa.setCidade(novaPessoa.getCidade());
					pessoa.setEstado(novaPessoa.getEstado());
					return pessoaRepository.save(pessoa);
				})
				.orElseGet(() -> {
					novaPessoa.setId(id);
					return pessoaRepository.save(novaPessoa);
				});
	}
	
	@DeleteMapping(path="/{id}")
	public void deletePessoa(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}
}