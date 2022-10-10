<template>
  <main class="flex flex-row p-3">
    <label class="block basis-1/4 m-5">
      Nome
      <input type="text" class="block border-2 h-10 w-full text-md" v-model="nome" />
    </label>
    <label class="block m-5">
      CPF ou CNPJ
      <input type="text" class="block border-2 h-10 text-md" v-model="doc" v-mask="['###.###.###-##', '##.###.###/####-##']" />
    </label>
    <button type="button" class="font-sans text-md text-white bg-sky-400 h-9 m-5 ml-0 p-2 w-16 rounded-md self-end" v-on:click="store.filtrar(nome, doc)">
      Filtrar
    </button>
    <button type="button" class="font-sans text-md text-white bg-zinc-400 h-9 m-5 ml-0 p-2 w-16 rounded-md self-end" v-on:click="handleClick()">
      Limpar
    </button>
  </main>
</template>

<script setup>
import { mask } from "vue-the-mask";
import { usePessoasStore } from "../stores/pessoas";

directives: {
  mask;
}

var nome = null;
var doc = null;

const store = usePessoasStore();

function handleClick() {
  window.location.reload()
}
</script>
