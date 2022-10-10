import { defineStore } from "pinia";
import { apiCEP } from "../api";

export const useEnderecoStore = defineStore({
  id: "endereco",
  state: () => ({
    endereco: []
  }),
  getters: {},
  actions: {
    async consultarUm(cep) {
        const res = await apiCEP.get(`/${cep}/json`)

        if(!Object.entries(res.data)[0].includes('erro')) {
          this.endereco = res.data
        } else {
          throw Error()
        }
             
    }
  }
}) 