<template>
  <table
    class="
      table-fixed
      border-collapse border border-slate-100
      rounded-lg
      w-full
    "
  >
    <thead>
      <tr>
        <th class="font-normal text-md border h-10 w-4/12">Nome</th>
        <th class="font-normal text-md border w-2/12">CPF ou CNPJ</th>
        <th class="font-normal text-md border w-5/12">E-mail</th>
        <th class="font-normal text-md border w-1/12">Opções</th>
      </tr>
    </thead>
    <tbody>
      <TableListItem />
    </tbody>
  </table>
</template>

<script setup>
import TableListItem from "./TableListItem.vue";
</script>

