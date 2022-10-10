<template>
  <main class="bg-cyan-100 h-screen">
    <header class="p-3">
      <h1 class="font-sans text-2xl">Sistema de Gerenciamento de Pessoas</h1>
    </header>
    <section class="bg-white h-[90%] ml-3 mr-3 rounded-lg">
      <AddForms />
    </section>
  </main>
</template>

<script setup>
import AddForms from "../components/AddForms.vue";
</script>