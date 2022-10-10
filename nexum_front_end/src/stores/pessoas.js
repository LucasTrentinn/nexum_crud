import { defineStore } from "pinia";
import { api } from "../api";

export const usePessoasStore = defineStore({
  id: "pessoas",
  state: () => ({
    pessoas: [],
    pessoa: [],
  }),
  getters: {},
  actions: {
    async consultarTodos() {
      const res = await api.get("/");
      this.pessoas = res.data;
    },
    async consultarUm(id) {
      const res = await api.get(`/${id}`);
      this.pessoa = res.data;
    },
    async criarPessoa(nome, email, tipoPF, cpf, cnpj, cep, logradouro, num, bairro, cidade, estado) {
      return await api.post("/", {
        nome: nome,
        email: email,
        tipoPF: tipoPF,
        cpf: cpf,
        cnpj: cnpj,
        cep: cep,
        logradouro: logradouro,
        numero: num,
        bairro: bairro,
        cidade: cidade,
        estado: estado,
      });
    },
    async atualizarPessoa(id, nome, email, tipoPF, cpf, cnpj, cep, logradouro, num, bairro, cidade, estado) {
      return await api.put(`/${id}`, {
        nome: nome,
        email: email,
        tipoPF: tipoPF,
        cpf: cpf,
        cnpj: cnpj,
        cep: cep,
        logradouro: logradouro,
        numero: num,
        bairro: bairro,
        cidade: cidade,
        estado: estado,
      });
    },
    async deletarPessoa(id) {
      return await api.delete(`/${id}`);
    },
    async filtrar(nome, doc) {
      const res = await api.get(`/`);

      let pessoasFiltradas = [];

      for (let i = 0; i < Object.values(Object.values(res.data)).length; i++) {
        if (Object.values(res.data)[i].nome.includes(nome) || Object.values(res.data)[i].cpf.includes(doc) || Object.values(res.data)[i].cnpj.includes(doc)) {
          pessoasFiltradas.push(Object.values(res.data)[i]);
        }
      }

      console.log(pessoasFiltradas)

      this.pessoas = pessoasFiltradas;
    },
  },
});
