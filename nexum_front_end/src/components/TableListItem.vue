<template>
  <tr v-for="pessoa in pessoas" :key="pessoa.id">
    <td class="border pl-3 h-10">{{ pessoa.nome }}</td>
    <td class="border pl-3">{{ pessoa.tipoPF == true ? pessoa.cpf : pessoa.cnpj }}</td>
    <td class="border pl-3">{{ pessoa.email }}</td>
    <td class="border pl-3 flex justify-center">
      <router-link :to="{name: 'update', params: {id: pessoa.id}}">
        <button
          class="
            font-sans
            text-md text-white
            bg-amber-300
            h-8
            m-2
            p-2
            w-8
            rounded-md
            flex
            items-center
          "
        >
          &#9998;
        </button>
      </router-link>

      <button
        class="
          font-sans
          text-md text-white
          bg-red-600
          h-8
          m-2
          p-2
          w-8
          rounded-md
          flex
          items-center
          justify-center
        "
        v-on:click="() => store.deletarPessoa(pessoa.id).then(this.$router.go())"
      >
        &#215;
      </button>
    </td>
  </tr>
</template>

<script setup>
import { computed, onMounted } from "@vue/runtime-core";
import { usePessoasStore } from "../stores/pessoas";

const store = usePessoasStore();
const pessoas = computed(() => store.pessoas);

onMounted(() => {
  store.consultarTodos();
  
});
</script>
