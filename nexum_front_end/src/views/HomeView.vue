<template>
  <main class="bg-cyan-100 pb-10 min-h-screen ">
    <header class="p-3">
      <h1 class="font-sans text-2xl">Sistema de Gerenciamento de Pessoas</h1>
    </header>
    <section class="bg-white p-5 ml-3 mr-3 rounded-lg">
      <AddBtn />
      <form>
        <Filter />
      </form>
      <section class="m-8 mt-0">
        <TableList />
      </section>
    </section>
  </main>
</template>

<script setup>
import AddBtn from "../components/AddBtn.vue";
import Filter from "../components/Filter.vue";
import TableList from "../components/TableList.vue";

var heigth =  "screen"
</script>
